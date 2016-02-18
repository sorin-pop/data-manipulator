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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageConstants;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryArgs;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;
import com.liferay.tool.datamanipulator.entry.util.EntryUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.AbstractEntryHandler;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;
import com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class MBMessageHandler extends AbstractEntryHandler
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
	public MBMessageHandler(
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

		long threadId = parentId;

		MBThread parentThread = MBThreadLocalServiceUtil.getThread(threadId);

		String addToTag = requestProcessor.getAddToParent(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);

		long parentMessageId = 0;

		List<MBMessage> messages =
			MBMessageLocalServiceUtil.getThreadMessages(
				threadId, WorkflowConstants.STATUS_ANY);

		if (addToTag.equals(FieldKeys.ADD_TO_ALL_PARENT)) {
			parentMessageId = messages.get(0).getMessageId();
		}
		else if (addToTag.equals(FieldKeys.ADD_TO_INNERMOST_PARENT)) {
			int messageCount = parentThread.getMessageCount();

			parentMessageId = messages.get(messageCount).getMessageId();
		}
		else if (addToTag.equals(FieldKeys.ADD_TO_RANDOM_PARENT)) {
			int messageCount = parentThread.getMessageCount();

			int rndIndex = EntryUtil.nextInt(messageCount);

			parentMessageId = messages.get(rndIndex).getMessageId();
		}

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("categoryId", parentThread.getCategoryId());
		args.setParameter("threadId", threadId);
		args.setParameter("parentMessageId", parentMessageId);
		args.setParameter(
			"subject", "Test MB Message" + postString + " Subject");

		args.setParameter("body", "Test MB Message" + postString + " Body");
		args.setParameter("format", MBMessageConstants.DEFAULT_FORMAT);
		args.setParameter("priority", 0.0);

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */

	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			((MBMessage)createdEntry).getGroupId(), MBMessage.class.getName(),
			((MBMessage)createdEntry).getMessageId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		MBMessage message = MBMessageLocalServiceUtil.getMBMessage(entryId);

		String subject = EntryUtil.getEditString(
			message.getSubject(), postString);

		String body = message.getBody();
		body += "\nTest MB Message " + postString + ". Edit Body";

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("messageId", entryId);
		args.setParameter("subject", subject);
		args.setParameter("body", body);
		args.setParameter("existingFiles", new ArrayList<String>(0));
		args.setParameter("priority", message.getPriority());

		return args;
	}

}