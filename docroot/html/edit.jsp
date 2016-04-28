<%--
/**
 * Copyright (c) 2014-present Yg0R2. All rights reserved.
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

<%@ page import="com.liferay.tool.datamanipulator.displayfield.FieldKeys"%>
<%@ page import="com.liferay.tool.datamanipulator.displayfield.DisplayFields"%>
<%@ page import="com.liferay.tool.datamanipulator.handler.BaseHandler"%>
<%@ page import="com.liferay.tool.datamanipulator.handler.util.HandlerUtil"%>
<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.KeyValuePair" %>
<%@ page import="com.liferay.portal.kernel.util.ObjectValuePair"%>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.tool.datamanipulator.context.RequestContext" %>


<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map"%>

<%@ include file="/html/init.jsp" %>

<portlet:actionURL name="create" var="createURL">
	<portlet:param name="struts_action" value="/html/edit" />
</portlet:actionURL>

<aui:form action="<%= createURL %>" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" />
	<aui:input name="createURL" type="hidden" value="<%= createURL.toString() %>" />
	<aui:input name="handlerName" type="hidden" value="<%= handlerName %>" />
	<aui:input name="redirect" type="hidden" />

	<%--<h2><liferay-ui:message key="<%= HandlerUtil.getHandlerDisplayName(handlerName) %>" /></h2>--%>

	<%
	boolean neverExpired = true;

	BaseHandler handler = HandlerUtil.getHandler(handlerName);

	DisplayFields displayFields = handler.getDisplayFields(themeDisplay.getScopeGroupId(), themeDisplay.getCompanyId());

	List<String> names = displayFields.getDisplayFieldNames();

	for (String name : names) {
		String type = displayFields.getDisplayFieldType(name);
		Object value = displayFields.getDisplayFieldValue(name);

		if (type.equals(FieldKeys.CHECK_BOX)) {
			%>

			<aui:input label="<%= name %>" name="<%= name %>" type="checkbox" useNamespace="false" value="<%= ((value != null) && ((Boolean)value)) %>" />

			<%
		}
		else if (type.equals(FieldKeys.DATE)) {
			Calendar calendar = CalendarFactoryUtil.getCalendar(timeZone, locale);

			Calendar dateFrom = (Calendar)calendar.clone();
			dateFrom.add(Calendar.MONTH, -5);

			Calendar dateTo = (Calendar)calendar.clone();
			dateTo.add(Calendar.MONTH, 5);
			%>

			<aui:model-context bean="<%= null %>" model="<%= com.liferay.tool.datamanipulator.handler.BaseHandler.class %>" />
			<%--<aui:layout>--%>
				<aui:input label='<%= name + "-from" %>' name='<%= name + "-from" %>' useNamespace="false" value="<%= dateFrom %>" />
				<aui:input label='<%= name + "-to" %>' name='<%= name + "-to" %>' useNamespace="false" value="<%= dateTo %>" />
			<%--</aui:layout>--%>

			<%
		}
		else if (type.equals(FieldKeys.FILE)) {
			%>

			<aui:input label="<%= name %>" name="<%= name %>" type="file" useNamespace="false" />

			<%
		}
		else if (type.equals(FieldKeys.FILE_REQUIRED)) {
			%>

			<aui:input label="<%= name %>" name="<%= name %>" type="file" useNamespace="false">
				<aui:validator name="required" />
			</aui:input>

			<%
		}
		else if (type.equals(FieldKeys.HIDDEN)) {
			%>

			<aui:input label="<%= name %>" name="<%= name %>" type="hidden" useNamespace="false" value="<%= (String) value %>" />

			<%
		}
		else if (type.equals(FieldKeys.INFO)) {
			%>

			<div class="portlet-msg-info">
				<liferay-ui:message key="<%= name %>" />
			</div>

			<%
		}
		else if (type.equals(FieldKeys.INPUT)){
			%>

			<aui:input label="<%= name %>" name="<%= name %>" type="input" useNamespace="false" value="<%= (String) value %>" />

			<%
		}
		else if (type.equals(FieldKeys.INPUT_COUNT) || type.equals(FieldKeys.INPUT_DEPTH) ||
				 type.equals(FieldKeys.INPUT_SUBCOUNT) || type.equals(FieldKeys.INPUT_UPDATE_COUNT)) {

			%>

			<aui:input label="<%= name %>" name="<%= name %>" type="input" useNamespace="false" value="<%= String.valueOf(value) %>" />

			<%
		}
		else if (type.equals(FieldKeys.INPUT_REQUIRED)) {
			%>

			<aui:input label="<%= name %>" name="<%= name %>" type="input" useNamespace="false" value="<%= (String) value %>" >
				<aui:validator name="required" />
			</aui:input>

			<%
		}
		else if (type.equals(FieldKeys.INPUT_REQUIRED_COUNT) || type.equals(FieldKeys.INPUT_REQUIRED_DEPTH) ||
				 type.equals(FieldKeys.INPUT_REQUIRED_SUBCOUNT) || type.equals(FieldKeys.INPUT_REQUIRED_UPDATE_COUNT)) {

			%>

			<aui:input label="<%= name %>" name="<%= name %>" type="input" useNamespace="false" value="<%= String.valueOf(value) %>" >
				<aui:validator name="required" />
			</aui:input>

			<%
		}
		else if (type.equals(FieldKeys.LABEL)) {
			%>

			<h3><liferay-ui:message key="<%= name %>" /></h3>

			<%
		}
		else if (type.equals(FieldKeys.MULTI_SELECT_LIST) || type.equals(FieldKeys.MULTI_SELECT_ORGANIZATION_LIST) ||
				 type.equals(FieldKeys.MULTI_SELECT_ROLE_LIST) || type.equals(FieldKeys.MULTI_SELECT_SITE_LIST) ||
				 type.equals(FieldKeys.MULTI_SELECT_USER_LIST) || type.equals(FieldKeys.MULTI_SELECT_USERGROUP_LIST)) {

			List<KeyValuePair> values = (List<KeyValuePair>) value;
			%>

			<aui:select label="<%= name %>" multiple="<%= true %>" name="<%= name %>" size="10" useNamespace="false">

				<%
				for (KeyValuePair kvp : values) {
					%>

					<aui:option label="<%= kvp.getKey() %>" value="<%= kvp.getValue() %>" />

					<%
				}
				%>

			</aui:select>

			<%
		}
		else if (type.equals(FieldKeys.SELECT_ADD_TO_PARENT)) {
			List<KeyValuePair> values = (List<KeyValuePair>) value;
			%>

			<aui:select label="<%= name %>" name="<%= name %>" useNamespace="false">

				<%
				for (KeyValuePair kvp : values) {
					%>

					<aui:option label="<%= kvp.getKey() %>" value="<%= kvp.getValue() %>" />

					<%
				}
				%>

			</aui:select>

			<%
		}
		else if (type.equals(FieldKeys.SELECT_LIST)) {
			List<KeyValuePair> values = (List<KeyValuePair>) value;
			%>

			<aui:select label="<%= name %>" name="<%= name %>" useNamespace="false">

				<%
				for (KeyValuePair kvp : values) {
					%>

					<aui:option label="<%= kvp.getKey() %>" value="<%= kvp.getValue() %>" />

					<%
				}
				%>

			</aui:select>

			<%
		}
		else if (type.equals(FieldKeys.SELECT_GROUPED_LIST)) {
			Map<String, List<KeyValuePair>> values = (Map<String, List<KeyValuePair>>) value;
			%>

			<aui:select label="<%= name %>" name="<%= name %>" useNamespace="false">
				<aui:option label="" selected="true" value="" />

				<%
				for (Map.Entry<String, List<KeyValuePair>> groupValue : values.entrySet()) {
					String groupName = groupValue.getKey();

					List<KeyValuePair> groupValues = groupValue.getValue();
					%>

					<optgroup label="<liferay-ui:message key="<%= groupName %>" />">

						<%
						for (KeyValuePair kvp : groupValues) {
							%>

							<aui:option label="<%= kvp.getKey() %>" value="<%= kvp.getValue() %>" />

							<%
						}
						%>

					</optgroup>

					<%
				}
				%>

			</aui:select>

			<%
		}
		else if (type.equals(FieldKeys.SEPARATOR)) {
			%>

			<div class="separator article-separator"></div>

			<%
		}
		else if (type.equals(FieldKeys.TIME)) {
			%>

			<aui:fieldset>
			<aui:field-wrapper label="<%= name %>">
				<aui:select cssClass="time-field-hour" label="hour" name='<%= name + "-hour" %>' useNamespace="false">

					<%
					for (int i = 0; i <= 24 ; i++) {
						%>

						<aui:option label="<%= i %>" selected="<%= i == 0 %>" value="<%= i %>" />

						<%
					}
					%>

				</aui:select>
				<aui:select label="minutes" name='<%= name + "-minutes" %>' useNamespace="false">

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
		<aui:button type="submit" value="go" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>