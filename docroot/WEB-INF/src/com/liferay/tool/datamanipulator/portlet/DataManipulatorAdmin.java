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

package com.liferay.tool.datamanipulator.portlet;

import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.tool.datamanipulator.context.RequestContext;
import com.liferay.tool.datamanipulator.handler.BaseHandler;
import com.liferay.tool.datamanipulator.handler.util.HandlerUtil;
import com.liferay.tool.datamanipulator.thread.HandlerThread;

/**
 * @author Yg0R2
 */
public class DataManipulatorAdmin extends MVCPortlet {

	public void create(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception { 

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
			actionRequest);

		RequestContext requestContext = new RequestContext(uploadRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)uploadRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String handlerName = ParamUtil.getString(uploadRequest, "handlerName");

		BaseHandler handler = HandlerUtil.getHandler(handlerName);

		boolean handlerIsPortalType = HandlerUtil.isPortalType(handlerName);

		long[] groupIds = requestContext.getGroupIds();

		if ((groupIds.length <= 0) || handlerIsPortalType) {
			groupIds = new long[] {themeDisplay.getScopeGroupId()};
		}

		long[] userIds = requestContext.getUserIds();

		if ((userIds.length <= 0) || handlerIsPortalType) {
			userIds = new long[] {themeDisplay.getUserId()};
		}

		for (long groupId : groupIds) {
			for (long userId : userIds) {
				requestContext = new RequestContext(uploadRequest);

				requestContext.setGroupId(groupId);
				requestContext.setUserId(userId);

				HandlerThread handlerThread = new HandlerThread(
					handler, requestContext);

				handlerThread.start();
			}
		}
	}

}