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
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.model.MBMessageConstants;
import com.liferay.portlet.messageboards.model.MBThread;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBThreadLocalServiceUtil;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryArgs;
import com.liferay.tool.datamanipulator.entry.util.EntryUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.AbstractEntryHandler;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;
import com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil;

import java.util.ArrayList;

/**
 * @author Tibor Kovács
 *
 */
public class MBThreadHandler extends AbstractEntryHandler
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
	public MBThreadHandler(
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

		args.setParameter("categoryId", parentId);
		args.setParameter("threadId", (long)0);
		args.setParameter(
			"parentMessageId", MBMessageConstants.DEFAULT_PARENT_MESSAGE_ID);

		args.setParameter(
			"subject", "Test MB Thread" + postString + " Subject");

		args.setParameter("body", "Test MB Thread" + postString + " Body");
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

		MBThread thread = ((MBMessage)createdEntry).getThread();

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			thread.getGroupId(), MBThread.class.getName(),
			thread.getThreadId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		MBThread thread = MBThreadLocalServiceUtil.getThread(entryId);

		long messageId = thread.getRootMessageId();

		MBMessage message = MBMessageLocalServiceUtil.getMBMessage(messageId);

		String subject = EntryUtil.getEditString(
			message.getSubject(), postString);

		String body = message.getBody();
		body += "\nTest MB Thread " + postString + ". Edit Body";

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("messageId", messageId);
		args.setParameter("subject", subject);
		args.setParameter("body", body);
		args.setParameter("existingFiles", new ArrayList<String>(0));
		args.setParameter("priority", message.getPriority());

		return args;
	}

}