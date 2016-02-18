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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.bookmarks.model.BookmarksFolder;
import com.liferay.portlet.bookmarks.model.BookmarksFolderConstants;
import com.liferay.portlet.bookmarks.service.BookmarksFolderLocalServiceUtil;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class BookmarksDisplayFields {

	public static final String BOOKMARKS_FOLDER_ID = "bookmarks-folder-id";

	public static final String BOOKMARKS_FOLDER_LIST = "bookmarks-folder-list";

	public static List<Field> getDisplayFields(long groupId)
		throws SystemException {

		List<BookmarksFolder> bookmarksFolderList =
			BookmarksFolderLocalServiceUtil.getFolders(
				groupId, BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		List<KeyValuePair> bookmarksFolderNameId = new ArrayList<KeyValuePair>(
			bookmarksFolderList.size() + 1);

		bookmarksFolderNameId.add(
			new KeyValuePair(
				EntryTypeKeys.GENERAL_BOOKMARKS + StringPool.DASH +
					FieldKeys.DEFAULT_PARENT,
				String.valueOf(
					BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID)));

		for (BookmarksFolder folder : bookmarksFolderList) {
			bookmarksFolderNameId.add(
				new KeyValuePair(
					folder.getName(), String.valueOf(folder.getFolderId())));
		}

		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addSelectList(BOOKMARKS_FOLDER_LIST, bookmarksFolderNameId);
		fields.addInput(
			BOOKMARKS_FOLDER_ID,
			String.valueOf(BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID));

		fields.addInfo(EntryTypeKeys.GENERAL_BOOKMARKS + "-add-to-exist");
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);
		fields.addDepth(EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);
		fields.addSubCount(EntryTypeKeys.GENERAL_BOOKMARKS_FOLDER);
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_BOOKMARKS_ENTRY);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_BOOKMARKS_ENTRY);
		fields.addToParent(EntryTypeKeys.GENERAL_BOOKMARKS_ENTRY);
		fields.addSeparator();

		fields.addInput("url", "http://www.liferay.com", true);
		fields.addSeparator();

		return fields.getDisplayFields();
	}

}