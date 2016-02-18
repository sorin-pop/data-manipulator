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

package com.liferay.tool.datamanipulator.datatype.bookmarks;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.model.BookmarksFolderConstants;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;
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
public class BookmarksHandler extends AbstractPortletHandler
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

		// Bookmarks Entry

		int entryCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_BOOKMARKS_ENTRY);

		int entryUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_BOOKMARKS_ENTRY);

		EntryTypeReader entryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_BOOKMARKS_ENTRY);

		BaseEntry entry = new BaseEntry(entryType);

		EntryHandlerModel entryhandler = new BookmarksEntryHandler(
			entryCount, entryUpdate, 0, 0, entry, null, requestProcessor);

		// Bookmarks Folder

		int folderCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);

		int folderUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);

		int folderDepth = requestProcessor.getDepth(
			EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);

		int folderSubCount = requestProcessor.getSubCount(
			EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);

		EntryTypeReader folderEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);

		BaseEntry folderEntry = new BaseEntry(folderEntryType);

		EntryHandlerModel folderEntryhandler = new BookmarksFolderHandler(
			folderCount, folderUpdate, folderDepth, folderSubCount, folderEntry,
			entryhandler, requestProcessor);

		long parentFolderId = requestProcessor.getLong(
			BookmarksDisplayFields.BOOKMARKS_FOLDER_ID);

		if (parentFolderId == 0) {
			parentFolderId = requestProcessor.getLong(
				BookmarksDisplayFields.BOOKMARKS_FOLDER_LIST);
		}

		BookmarksFolder folder =
			BookmarksFolderLocalServiceUtil.fetchBookmarksFolder(
				parentFolderId);

		if (Validator.isNotNull(folder)) {
			folderEntryhandler.generateEntries(parentFolderId);
		}
		else {
			folderEntryhandler.generateEntries(
				BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		}
	}

}