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

package com.liferay.tool.datamanipulator.handler.content;

import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.mail.kernel.model.Account;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.message.boards.kernel.model.MBCategoryConstants;
import com.liferay.message.boards.kernel.service.MBCategoryLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;

import com.liferay.tool.datamanipulator.annotation.Handler;
import com.liferay.tool.datamanipulator.annotation.HandlerType;
import com.liferay.tool.datamanipulator.context.RequestContext;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.handler.BaseHandler;
import com.liferay.tool.datamanipulator.util.GetterUtil;

/**
 * @author Yg0R2
 */
@Handler(type = HandlerType.CONTENT, displayName = "Message Boards Handler")
public class MBCategoryHandler extends BaseHandler {

	public MBCategoryHandler() throws Exception {
		super("Message Board Category", "message-board-category");

		_mailSettingsArgs = _getMailSettings();
	}

	@Override
	public DisplayFields getDisplayFields(long groupId, long companyId) throws Exception {
		DisplayFields displayFields = new DisplayFields();

		displayFields.addUserMultiSelect(FieldKeys.MULTI_SELECT_USER_LIST);
		displayFields.addInfo(
			getDisplayFieldName(FieldKeys.MULTI_SELECT_USER_LIST));

		displayFields.addSeparator("");

		displayFields.addLabel(getDisplayFieldName());

		displayFields.addCount(getDisplayFieldName(FieldKeys.INPUT_COUNT));

		displayFields.addUpdateCount(
			getDisplayFieldName(FieldKeys.INPUT_UPDATE_COUNT));

		displayFields.addDepth(getDisplayFieldName(FieldKeys.INPUT_DEPTH));

		displayFields.addSubCount(
			getDisplayFieldName(FieldKeys.INPUT_SUBCOUNT));

		displayFields.addSeparator("");

		displayFields.addAll(
			(new MBThreadHandler()).getDisplayFields(groupId, companyId));

		return displayFields;
	}

	@Override
	protected Class<?>[] getAddArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, Long.TYPE, String.class, String.class, String.class,
			String.class, String.class, String.class, Integer.TYPE,
			Boolean.TYPE, String.class, String.class, Integer.TYPE,
			String.class, Boolean.TYPE, String.class, Integer.TYPE,
			Boolean.TYPE, String.class, String.class, Boolean.TYPE,
			Boolean.TYPE, ServiceContext.class
		};
	}

	@Override
	protected String[] getAddArgNames() {
		return new String[] {
			"userId", "parentCategoryId", "name", "description", "displayStyle",
			"emailAddress", "inProtocol", "inServerName", "inServerPort",
			"inUseSSL", "inUserName", "inPassword", "inReadInterval",
			"outEmailAddress", "outCustom", "outServerName", "outServerPort",
			"outUseSSL", "outUserName", "outPassword", "allowAnonymous",
			"mailingListActive", "serviceContext"
		};
	}

	@Override
	protected Class<?> getAddClass() throws ClassNotFoundException {
		return MBCategoryLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getAddEntrySpecifiedArgs(
		RequestContext requestContext) throws Exception {

		Map<String, Object> args = new HashMap<String, Object>();

		args.putAll(_mailSettingsArgs);
		args.put("displayStyle", MBCategoryConstants.DEFAULT_DISPLAY_STYLE);
		args.put("mailingListActive", false);

		return args;
	}

	@Override
	protected String getAddMethodName() {
		return "addCategory";
	}

	@Override
	protected List<String> getChildHandlerNames() {
		List<String> childHandlerNames = new ArrayList<String>();

		childHandlerNames.add(MBThreadHandler.class.getSimpleName());

		return childHandlerNames;
	}

	@Override
	protected String getClassPKName() {
		return "categoryId";
	}

	@Override
	protected String getParentClassPKName() {
		return "parentCategoryId";
	}

	@Override
	protected Class<?>[] getUpdateArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, Long.TYPE, String.class, String.class, String.class,
			String.class, String.class, String.class, Integer.TYPE,
			Boolean.TYPE, String.class, String.class, Integer.TYPE,
			String.class, Boolean.TYPE, String.class, Integer.TYPE,
			Boolean.TYPE, String.class, String.class, Boolean.TYPE,
			Boolean.TYPE, Boolean.TYPE, ServiceContext.class
		};
	}

	@Override
	protected String[] getUpdateArgNames() {
		return new String[] {
			"categoryId", "parentCategoryId", "name", "description",
			"displayStyle", "emailAddress", "inProtocol", "inServerName",
			"inServerPort", "inUseSSL", "inUserName", "inPassword",
			"inReadInterval", "outEmailAddress", "outCustom", "outServerName",
			"outServerPort", "outUseSSL", "outUserName", "outPassword",
			"allowAnonymous", "mailingListActive", "mergeWithParentCategory",
			"serviceContext"
		};
	}

	@Override
	protected Class<?> getUpdateClass() throws ClassNotFoundException {
		return MBCategoryLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getUpdateEntrySpecifiedArgs(
		Object entry, RequestContext requestContext) throws Exception {

		Map<String, Object> args = new HashMap<String, Object>();

		args.put("mergeWithParentCategory", false);

		return args;
	}

	@Override
	protected String getUpdateMethodName() {
		return "updateCategory";
	}

	private Map<String, Object> _getMailSettings() throws Exception {
		Session session = null;
		try {
			session = MailServiceUtil.getSession();
		}
		catch (SystemException e) {
			_log.error(e, e);
			return new HashMap<String, Object>(0);
		}

		String storeProtocol = (String)GetterUtil.getFieldValue(
			"com.liferay.portal.kernel.util.PropsValues",
			"MAIL_SESSION_MAIL_STORE_PROTOCOL");

		if (!storeProtocol.equals(Account.PROTOCOL_POPS)) {
			storeProtocol = Account.PROTOCOL_POP;
		}

		String storePrefix = "mail." + storeProtocol + ".";

		String transportProtocol = (String)GetterUtil.getFieldValue(
			"com.liferay.portal.kernel.util.PropsValues",
			"MAIL_SESSION_MAIL_TRANSPORT_PROTOCOL");

		if (!transportProtocol.equals(Account.PROTOCOL_SMTPS)) {
			transportProtocol = Account.PROTOCOL_SMTP;
		}

		String transportPrefix = "mail." + transportProtocol + ".";

		HashMap<String, Object> args = new HashMap<String, Object>(15);

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

	private Map<String, Object> _mailSettingsArgs;

	private static Log _log = LogFactoryUtil.getLog(MBCategoryHandler.class);

}