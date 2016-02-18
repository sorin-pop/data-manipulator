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

package com.liferay.tool.datamanipulator.portlet;

import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.tool.datamanipulator.datatype.PortletHandlerFactory;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;
import com.liferay.tool.datamanipulator.runner.DataManipulatorRunner;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Tibor Kovács
 *
 */
public class DataManipulatorAdminPortlet extends MVCPortlet {

	public void generateData(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
			actionRequest);

		RequestProcessor requestProcessor = new RequestProcessor(uploadRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)uploadRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String entryType = ParamUtil.getString(uploadRequest, "entryType");

		List<String> portalEntryTypes = EntryTypeKeys.ENTRY_TYPE_PORTAL_LIST;

		long[] groupIds = requestProcessor.getGroupIds();

		if ((groupIds.length <= 0) || portalEntryTypes.contains(entryType)) {
			groupIds = new long[] {themeDisplay.getScopeGroupId()};
		}

		long[] userIds = requestProcessor.getUserIds();

		if ((userIds.length <= 0) || portalEntryTypes.contains(entryType)) {
			userIds = new long[] {themeDisplay.getUserId()};
		}

		for (long groupId : groupIds) {
			for (long userId : userIds) {
				requestProcessor = new RequestProcessor(uploadRequest);

				requestProcessor.setGroupId(groupId);
				requestProcessor.setUserId(userId);

				DataManipulatorRunner runner = new DataManipulatorRunner(
					PortletHandlerFactory.getHandlerInstance(entryType),
					requestProcessor);

				runner.start();
			}
		}
	}

}