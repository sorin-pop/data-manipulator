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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
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
public class MessageBoardsHandler extends AbstractPortletHandler
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

		// MessageBoards Message

		int messageCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);

		int messageUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);

		EntryTypeReader messageEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);

		BaseEntry messageEntry = new BaseEntry(messageEntryType);

		EntryHandlerModel messageHandler = new MBMessageHandler(
			messageCount, messageUpdate, 0, 0, messageEntry, null,
			requestProcessor);

		// MessageBoards Thread

		int threadCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_THREAD);

		int threadUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_THREAD);

		EntryTypeReader threadEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);

		BaseEntry threadEntry = new BaseEntry(threadEntryType);

		EntryHandlerModel threadHandler = new MBThreadHandler(
			threadCount, threadUpdate, 0, 0, threadEntry, messageHandler,
			requestProcessor);

		// MessageBoards Category

		int categoryCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);

		int categoryUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);

		int categoryDepth = requestProcessor.getDepth(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);

		int categorySubCount = requestProcessor.getSubCount(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);

		EntryTypeReader categoryEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);

		BaseEntry categoryEntry = new BaseEntry(categoryEntryType);

		EntryHandlerModel categoryHandler = new MBCategoryHandler(
			categoryCount, categoryUpdate, categoryDepth, categorySubCount,
			categoryEntry, threadHandler, requestProcessor);

		// Start create

		long parentCategoryId = requestProcessor.getLong(
			MessageBoardsDisplayFields.MESSAGE_BOARDS_CATEGORY_ID);

		if (parentCategoryId == 0) {
			parentCategoryId = requestProcessor.getLong(
				MessageBoardsDisplayFields.MESSAGE_BOARDS_CATEGORY_LIST);
		}

		MBCategory category = MBCategoryLocalServiceUtil.fetchMBCategory(
			parentCategoryId);

		if (Validator.isNotNull(category)) {
			categoryHandler.generateEntries(parentCategoryId);
		}
		else {
			categoryHandler.generateEntries((long)0);
		}
	}

}