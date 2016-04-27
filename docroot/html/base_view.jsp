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

<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>

<%@ page import="javax.portlet.PortletURL" %>

<%@ include file="/html/init.jsp" %>

<%
String cmd = ParamUtil.getString(request, Constants.CMD, Constants.VIEW);

String currentURL = PortalUtil.getCurrentURL(request);

String handlerName = ParamUtil.getString(request, "handlerName");

String redirect = ParamUtil.getString(request, "redirect", currentURL);
String backURL = ParamUtil.getString(request, "backURL", redirect);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/html/base_view");
portletURL.setParameter(Constants.CMD, Constants.EDIT);
portletURL.setParameter("redirect", redirect);
%>

<%@ include file="/html/navigation.jspf" %>

<c:choose>
	<c:when test="<%= cmd.equals(Constants.VIEW) %>">
		<%@ include file="/html/summary.jsp" %>
	</c:when>
	<c:otherwise>
		<%@ include file="/html/edit.jsp" %>
	</c:otherwise>
</c:choose>

<aui:script>
	function <portlet:namespace />editHandler(field) {
		var editHandlertURL = field.value;

		if (editHandlertURL == '') {

			<%
			PortletURL newHandlerURL = renderResponse.createRenderURL();

			newHandlerURL.setParameter("struts_action", "/html/base_view");
			newHandlerURL.setParameter(Constants.CMD, Constants.VIEW);
			%>

			editHandlertURL = '<%= newHandlerURL %>';
		}

		location.href = editHandlertURL;
	}
</aui:script>