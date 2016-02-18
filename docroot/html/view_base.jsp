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

<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="javax.portlet.PortletURL" %>

<%@ include file="/html/init.jsp" %>

<liferay-ui:success key="dataGenerated" message="data-generated-successfully" />
<liferay-ui:error key="errorOccurred" message="error-occurred" />

<%
String cmd = ParamUtil.getString(request, Constants.CMD, Constants.VIEW);

String currentURL = PortalUtil.getCurrentURL(request);

String entryType = ParamUtil.getString(request, "entryType");

String redirect = ParamUtil.getString(request, "redirect", currentURL);
String backURL = ParamUtil.getString(request, "backURL", redirect);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/html/view_base");
portletURL.setParameter(Constants.CMD, Constants.EDIT);
portletURL.setParameter("redirect", backURL);
%>

<%@ include file="/html/navigation.jspf" %>

<c:choose>
	<c:when test="<%= cmd.equals(Constants.VIEW) %>">
		<%@ include file="/html/view_summary.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/html/edit_entry.jsp" %>
	</c:otherwise>
</c:choose>

<aui:script>
	function <portlet:namespace />generateEntryType(field) {
		var entryTypeURL = field.value;

		if (entryTypeURL == '') {

			<%
			PortletURL addEntryTypesURL = renderResponse.createRenderURL();

			addEntryTypesURL.setParameter("struts_action", "/html/view_base");
			addEntryTypesURL.setParameter(Constants.CMD, Constants.EDIT);
			addEntryTypesURL.setParameter("entryType", entryType);
			%>

			dataTypeURL = '<%= addEntryTypesURL %>';
		}

		location.href = entryTypeURL;
	}
</aui:script>