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

package com.liferay.tool.datamanipulator.datatype.messageboards;

import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.Account;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryArgs;
import com.liferay.tool.datamanipulator.entry.util.EntryUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.AbstractEntryHandler;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;
import com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil;

import java.util.HashMap;

import javax.mail.Session;

/**
 * @author Tibor Kovács
 *
 */
public class MBCategoryHandler extends AbstractEntryHandler
	implements EntryHandlerModel {

	/**
	 * @param count
	 * @param update
	 * @param depth
	 * @param subCount
	 * @param baseEntry
	 * @param threadHandler
	 * @param requestProcessor
	 */
	public MBCategoryHandler(
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

		postString += EntryUtil.nextString();

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("parentCategoryId", parentId);
		args.setParameter("name", "Test MB Category" + postString + " Name");
		args.setParameter(
			"description", "Test MB Category" + postString + " Description");

		args.setParameter(
			"displayStyle", MBCategoryConstants.DEFAULT_DISPLAY_STYLE);

		args.setParameters(_initMailSettings());
		args.setParameter("mailingListActive", false);

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */

	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			((MBCategory)createdEntry).getGroupId(), MBCategory.class.getName(),
			((MBCategory)createdEntry).getCategoryId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		MBCategory mbCategory = MBCategoryLocalServiceUtil.getCategory(entryId);

		String name = EntryUtil.getEditString(mbCategory.getName(), postString);

		String description = EntryUtil.getEditString(
			mbCategory.getDescription(), postString);

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("categoryId", entryId);
		args.setParameter("parentCategoryId", mbCategory.getParentCategoryId());
		args.setParameter("name", name);
		args.setParameter("description", description);
		args.setParameter(
			"displayStyle", MBCategoryConstants.DEFAULT_DISPLAY_STYLE);

		args.setParameters(_initMailSettings());
		args.setParameter("mailingListActive", false);

		return args;
	}

	private HashMap<String, Object> _initMailSettings() {
		HashMap<String, Object> args = new HashMap<String, Object>(15);

		Session session = null;
		try {
			session = MailServiceUtil.getSession();
		}
		catch (SystemException e) {
			_log.error(e, e);
			return args;
		}

		String storeProtocol = PropsKeys.MAIL_SESSION_MAIL_STORE_PROTOCOL;

		if (!storeProtocol.equals(Account.PROTOCOL_POPS)) {
			storeProtocol = Account.PROTOCOL_POP;
		}

		String storePrefix = "mail." + storeProtocol + ".";

		String transportProtocol =
			PropsKeys.MAIL_SESSION_MAIL_TRANSPORT_PROTOCOL;

		if (!transportProtocol.equals(Account.PROTOCOL_SMTPS)) {
			transportProtocol = Account.PROTOCOL_SMTP;
		}

		String transportPrefix = "mail." + transportProtocol + ".";

		args.put("emailAddress", StringPool.BLANK);
		args.put("inProtocol", storeProtocol);
		args.put("inServerName", session.getProperty(storePrefix + "host"));
		args.put(
			"inServerPort",
			Integer.valueOf(session.getProperty(storePrefix + "port")));

		args.put(
			"inUseSSL",
			Boolean.valueOf(session.getProperty(storePrefix + "auth")));

		args.put("inUserName", session.getProperty(storePrefix + "user"));
		args.put("inPassword", session.getProperty(storePrefix + "password"));

		args.put("inReadInterval", 5);
		args.put("outEmailAddress", StringPool.BLANK);
		args.put("outCustom", false);
		args.put(
			"outServerName", session.getProperty(transportPrefix + "host"));

		args.put(
			"outServerPort",
			Integer.valueOf(session.getProperty(transportPrefix + "port")));

		args.put(
			"outUseSSL",
			Boolean.valueOf(session.getProperty(transportPrefix + "auth")));

		args.put("outUserName", session.getProperty(transportPrefix + "user"));

		args.put(
			"outPassword", session.getProperty(transportPrefix + "password"));

		return args;
	}

	private static Log _log = LogFactoryUtil.getLog(MBCategoryHandler.class);

}