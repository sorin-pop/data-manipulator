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

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.message.boards.kernel.model.MBMessageConstants;
import com.liferay.message.boards.kernel.model.MBThread;
import com.liferay.message.boards.kernel.service.MBMessageLocalServiceUtil;
import com.liferay.message.boards.kernel.service.MBThreadLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liferay.tool.datamanipulator.context.RequestContext;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.handler.BaseHandler;

/**
 * @author Yg0R2
 */
public class MBMessageHandled extends BaseHandler {

	public MBMessageHandled() throws Exception {
		super("Message Board Message", "message-board-message");
	}

	@Override
	public DisplayFields getDisplayFields(long groupId, long companyId) throws Exception {
		DisplayFields displayFields = new DisplayFields();

		displayFields.addLabel(getDisplayFieldName());

		displayFields.addCount(getDisplayFieldName(FieldKeys.INPUT_COUNT));

		displayFields.addUpdateCount(
			getDisplayFieldName(FieldKeys.INPUT_UPDATE_COUNT));

		return displayFields;
	}

	@Override
	protected Class<?>[] getAddArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, String.class, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE,
			String.class, String.class, String.class, List.class, Boolean.TYPE,
			Double.TYPE, Boolean.TYPE, ServiceContext.class
		};
	}

	@Override
	protected String[] getAddArgNames() {
		return new String[] {
			"userId", "userName", "groupId", "categoryId", "threadId",
			"parentMessageId", "subject", "body", "format", "inputStreamOVPs",
			"anonymous", "priority", "allowPingbacks", "serviceContext"
		};
	}

	@Override
	protected Class<?> getAddClass() throws ClassNotFoundException {
		return MBMessageLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getAddEntrySpecifiedArgs(
			RequestContext requestContext) throws Exception {

		long categoryId = requestContext.getLong(
			getDisplayFieldName("categoryId"));

		long parentMessageId = MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID;

		long threadId = requestContext.getLong(getDisplayFieldName("threadId"));

		if (threadId != 0) {
			MBThread mbThread = MBThreadLocalServiceUtil.getMBThread(threadId);

			parentMessageId = mbThread.getRootMessageId();
		}

		Map<String, Object> args = new HashMap<String, Object>(5);

		args.put("categoryId", categoryId);
		args.put("format", MBMessageConstants.DEFAULT_FORMAT);
		args.put("parentMessageId", parentMessageId);
		args.put("priority", 0.0);
		args.put("threadId", threadId);

		return args;
	}

	@Override
	protected String getAddMethodName() {
		return "addMessage";
	}

	@Override
	protected List<String> getChildHandlerNames() {
		return new ArrayList<String>(0);
	}

	@Override
	protected String getClassPKName() {
		return "messageId";
	}

	@Override
	protected String getParentClassPKName() {
		return "threadId";
	}

	@Override
	protected Class<?>[] getUpdateArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, Long.TYPE, String.class, String.class, List.class,
			List.class, Double.TYPE, Boolean.TYPE, ServiceContext.class
		};
	}

	@Override
	protected String[] getUpdateArgNames() {
		return new String[] {
			"userId", "messageId", "subject", "body", "inputStreamOVPs",
			"existingFiles", "priority", "allowPingbacks", "serviceContext"
		};
	}

	@Override
	protected Class<?> getUpdateClass() throws ClassNotFoundException {
		return MBMessageLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getUpdateEntrySpecifiedArgs(
		Object entry, RequestContext requestContext) throws Exception {

		return new HashMap<String, Object>(0);
	}

	@Override
	protected String getUpdateMethodName() {
		return "updateMessage";
	}

}