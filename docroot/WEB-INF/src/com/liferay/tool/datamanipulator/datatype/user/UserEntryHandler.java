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

package com.liferay.tool.datamanipulator.datatype.user;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryArgs;
import com.liferay.tool.datamanipulator.handler.entryhandler.AbstractEntryHandler;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;
import com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil;

/**
 * @author Tibor Kovács
 *
 */
public class UserEntryHandler extends AbstractEntryHandler
	implements EntryHandlerModel {

	/**
	 * @param count
	 * @param update
	 * @param depth
	 * @param subCount
	 * @param baseEntry
	 * @param subEntryHandler
	 * @param requestProcessor
	 */
	public UserEntryHandler(
		int count, int update, int depth, int subCount, BaseEntry baseEntry,
		EntryHandlerModel subEntryHandler, RequestProcessor requestProcessor) {

		super(
			count, update, depth, subCount, baseEntry, subEntryHandler,
			requestProcessor);
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getCreateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getCreateEntryArgs(
			long parentId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		String screenName = "test" + postString;

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("creatorUserId", requestProcessor.getUserId());
		args.setParameter("password1", "test");
		args.setParameter("password2", "test");
		args.setParameter("screenName", screenName);
		args.setParameter("emailAddress", screenName + "@liferay.com");
		args.setParameter("facebookId", (long)0);
		args.setParameter("openId", StringPool.BLANK);
		args.setParameter("firstName", "Test" + postString);
		args.setParameter("middleName", StringPool.BLANK);
		args.setParameter("lastName", "Test" + postString);
		args.setParameter("prefixId", 0);
		args.setParameter("suffixId", 0);
		args.setParameter("birthdayMonth", 10);
		args.setParameter("birthdayDay", 16);
		args.setParameter("birthdayYear", 1984);
		args.setParameter("jobTitle", StringPool.BLANK);
		args.setParameter("groupIds", requestProcessor.getGroupIds());

		args.setParameter(
			"organizationIds", requestProcessor.getOrganizationIds());

		args.setParameter("roleIds", requestProcessor.getRoleIds());
		args.setParameter("userGroupIds", requestProcessor.getUserGroupIds());
		args.setParameter("sendEmail", false);

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */

	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			((User)createdEntry).getCompanyId(), User.class.getName(),
			((User)createdEntry).getUserId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		return null;
	}

}