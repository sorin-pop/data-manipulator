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

package com.liferay.tool.datamanipulator.datatype.team;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;
import com.liferay.tool.datamanipulator.entryreader.EntryTypeReader;
import com.liferay.tool.datamanipulator.entryreader.util.EntryReaderUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.handler.portlethandler.AbstractPortletHandler;
import com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;

/**
 * @author Tibor Kovács
 *
 */
public class TeamHandler extends AbstractPortletHandler
	implements PortletHandlerModel {

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel#startErase(com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public void startErase(RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel#startGenerate(com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public void startGenerate(RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		int teamCount = requestProcessor.getCount(EntryTypeKeys.PORTAL_TEAM);
		int teamUpdateLevel = requestProcessor.getUpdateLevel(
			EntryTypeKeys.PORTAL_TEAM);

		EntryTypeReader teamEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.PORTAL_TEAM);

		BaseEntry teamEntry = new BaseEntry(teamEntryType);

		EntryHandlerModel teamHandler = null;

		long[] siteIds = requestProcessor.getGroupIds();

		for (long siteId : siteIds) {
			requestProcessor.setGroupId(siteId);

			teamHandler = new TeamEntryHandler(
				teamCount, teamUpdateLevel, 0, 0, teamEntry, null,
				requestProcessor);

			teamHandler.generateEntries((long)0);
		}

		long[] orgIds = requestProcessor.getOrganizationIds();

		for (long orgId : orgIds) {
			Organization org = OrganizationLocalServiceUtil.getOrganization(
				orgId);

			requestProcessor.setGroupId(org.getGroupId());

			teamHandler = new TeamEntryHandler(
				teamCount, teamUpdateLevel, 0, 0, teamEntry, null,
				requestProcessor);

			teamHandler.generateEntries((long)0);
		}
	}

}