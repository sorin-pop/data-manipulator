<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.KeyValuePair" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.tool.datamanipulator.datatype.EntryDisplayFieldsFactory" %>
<%@ page import="com.liferay.tool.datamanipulator.displayfield.Field" %>
<%@ page import="com.liferay.tool.datamanipulator.displayfield.FieldTypeKeys" %>
<%@ page import="com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel" %>

<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %>

<%@ include file="/html/init.jsp" %>

<portlet:actionURL name="generateData" var="editEntryTypeURL">
	<portlet:param name="struts_action" value="/html/edit_entry" />
</portlet:actionURL>

<aui:form action="<%= editEntryTypeURL %>" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="<%= com.liferay.portal.kernel.search.Field.COMPANY_ID %>" type="hidden" value="<%= themeDisplay.getCompanyId() %>" />
	<aui:input name="editEntryTypeURL" type="hidden" value="<%= editEntryTypeURL.toString() %>" />
	<aui:input name="entryType" type="hidden" value="<%= entryType %>" />
	<aui:input name="<%= com.liferay.portal.kernel.search.Field.GROUP_ID %>" type="hidden" value="<%= themeDisplay.getScopeGroupId() %>" />
	<aui:input name="redirect" type="hidden" />

	<h2><liferay-ui:message key="<%= entryType %>" /></h2>

	<%
	boolean neverExpired = true;

	List<Field> fields = EntryDisplayFieldsFactory.getDisplayFields(entryType, themeDisplay.getScopeGroupId());

	for (Field field : fields) {
		String label = entryType + StringPool.DASH + field.getKey();

		if (field.isTypeOf(FieldTypeKeys.CHECK_BOX)) {
			%>

			<aui:input label="<%= label %>" name="<%= field.getKey() %>" type="checkbox" value="<%= ((field.getValue() != null) && (Boolean.valueOf(field.getValue()) == true)) %>" />

			<%
		}
		else if (field.isTypeOf(FieldTypeKeys.DATE)) {
			Calendar calendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

			Calendar dateFrom = (Calendar)calendar.clone();
			dateFrom.add(Calendar.MONTH, -5);

			Calendar dateTo = (Calendar)calendar.clone();
			dateTo.add(Calendar.MONTH, 5);
			%>

			<aui:model-context bean="<%= null %>" model="<%= EntryHandlerModel.class %>" />
			<aui:layout>
				<aui:input label='<%= label + "-from" %>' name='<%= field.getKey() + "-from" %>' value="<%= dateFrom %>" />
				<aui:input label='<%= label + "-to" %>' name='<%= field.getKey() + "-to" %>' value="<%= dateTo %>" />
			</aui:layout>

			<%
		}
		else if (field.isTypeOf(FieldTypeKeys.FILE)) {
			%>

			<aui:input label="<%= label %>" name="<%= field.getKey() %>" type="file" />

			<%
		}
		else if (field.isTypeOf(FieldTypeKeys.FILE_REQUIRED)) {
			%>

			<aui:input label="<%= label %>" name="<%= field.getKey() %>" type="file">
				<aui:validator name="required" />
			</aui:input>

			<%
		}
		else if (field.isTypeOf(FieldTypeKeys.HIDDEN)) {
			%>

			<aui:input label="<%= label %>" name="<%= field.getKey() %>" type="hidden" value="<%= field.getValue() %>" />

			<%
		}
		else if (field.isTypeOf(FieldTypeKeys.INFO)) {
			%>

			<div class="portlet-msg-info">
				<liferay-ui:message key="<%= label %>" />
			</div>

			<%
		}
		else if (field.isTypeOf(FieldTypeKeys.INPUT)) {
			%>

			<aui:input label="<%= label %>" name="<%= field.getKey() %>" type="input" value="<%= field.getValue() %>" />

			<%
		}
		else if (field.isTypeOf(FieldTypeKeys.MULTI_SELECT_LIST)) {
			List<KeyValuePair> values = field.getValues();
			%>

			<aui:select label="<%= label %>" multiple="<%= true %>" name="<%= field.getKey() %>" size="10">

				<%
				for (KeyValuePair value : values) {
					if ((value.getKey()).equals(StringPool.BLANK) ||
						(value.getValue()).equals(StringPool.BLANK) ) {

						continue;
					}
					%>

					<aui:option label="<%= value.getKey() %>" value="<%= value.getValue() %>" />

					<%
				}
				%>

			</aui:select>

			<%
		}
		else if (field.isTypeOf(FieldTypeKeys.INPUT_REQUIRED)) {
			%>

			<aui:input label="<%= label %>" name="<%= field.getKey() %>" type="input" value="<%= field.getValue() %>">
				<aui:validator name="required" />
			</aui:input>

			<%
		}
		else if (field.isTypeOf(FieldTypeKeys.LABEL)) {
			%>

			<h3><liferay-ui:message key="<%= label %>" /></h3>

			<%
		}
		else if (field.isTypeOf(FieldTypeKeys.SELECT_LIST)) {
			List<KeyValuePair> values = field.getValues();
			%>

			<aui:select label="<%= label %>" name="<%= field.getKey() %>">

				<%
				for (KeyValuePair value : values) {
					if ((value.getKey()).equals(StringPool.BLANK) ||
						(value.getValue()).equals(StringPool.BLANK) ) {

						continue;
					}
					%>

					<aui:option label="<%= value.getKey() %>" value="<%= value.getValue() %>" />

					<%
				}
				%>

			</aui:select>

			<%
		}
		else if (field.isTypeOf(FieldTypeKeys.SEPARATOR)) {
			%>

			<div class="separator article-separator"></div>

			<%
		}
		else if (field.isTypeOf(FieldTypeKeys.TIME)) {
			%>

			<aui:fieldset>
			<aui:field-wrapper label="<%= label %>">
				<aui:select cssClass="time-field-hour" label="hour" name='<%= FieldTypeKeys.TIME + "-hour" %>'>

					<%
					for (int i = 0; i <= 24 ; i++) {
						%>

						<aui:option label="<%= i %>" selected="<%= i == 0 %>" value="<%= i %>" />

						<%
					}
					%>

				</aui:select>
				<aui:select label="minutes" name='<%= FieldTypeKeys.TIME + "-minutes" %>'>

					<%
					for (int i=0; i < 60 ; i = i + 5) {
						%>

						<aui:option label='<%= ":" + (i < 10 ? "0" : StringPool.BLANK) + i %>' selected="<%= i == 5 %>" value="<%= i %>" />

						<%
					}
					%>

				</aui:select>
			</aui:field-wrapper>
			</aui:fieldset>

			<%
		}
	}
	%>

	<aui:button-row>
		<aui:button type="submit" value="save" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>