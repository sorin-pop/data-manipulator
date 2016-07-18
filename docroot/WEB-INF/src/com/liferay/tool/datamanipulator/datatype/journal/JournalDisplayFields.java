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

package com.liferay.tool.datamanipulator.datatype.journal;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.journal.model.JournalFolder;
import com.liferay.portlet.journal.service.JournalFolderLocalServiceUtil;
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
public class JournalDisplayFields {

	public static final String JOURNAL_FOLDER_ID = "journal-folder-id";

	public static final String JOURNAL_FOLDER_LIST = "journal-folder-list";

	public static List<Field> getDisplayFields(long groupId)
		throws SystemException {

		List<JournalFolder> journalFolderList =
			JournalFolderLocalServiceUtil.getFolders(groupId, 0);

		List<KeyValuePair> journalFolderNameId = new ArrayList<KeyValuePair>(
			journalFolderList.size() + 1);

		journalFolderNameId.add(
			new KeyValuePair(
				EntryTypeKeys.GENERAL_JOURNAL + StringPool.DASH +
					FieldKeys.DEFAULT_PARENT, "0"));

		for (JournalFolder folder : journalFolderList) {
			journalFolderNameId.add(
				new KeyValuePair(
					folder.getName(), String.valueOf(folder.getFolderId())));
		}

		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addInfo("specify-root-folder");
		fields.addSelectList(JOURNAL_FOLDER_LIST, journalFolderNameId);
		fields.addInput(JOURNAL_FOLDER_ID);
		//fields.addInfo(EntryTypeKeys.GENERAL_JOURNAL + "-add-to-exist");
		fields.addSeparator();

		fields.addInfo("folder-generation-settings");
		fields.addDepth(EntryTypeKeys.GENERAL_JOURNAL_FOLDER);
		fields.addCount(EntryTypeKeys.GENERAL_JOURNAL_FOLDER);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_JOURNAL_FOLDER);
		//fields.addSubCount(EntryTypeKeys.GENERAL_JOURNAL_FOLDER);  --I took it out because seems to have the same meaning as the first field, and didn't seem to have any effect whatsoever
		fields.addSeparator();

		fields.addInfo("article-generation-settings");
		fields.addCount(EntryTypeKeys.GENERAL_JOURNAL_ARTICLE);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_JOURNAL_ARTICLE);
		fields.addToParent(EntryTypeKeys.GENERAL_JOURNAL_ARTICLE);
		fields.addSeparator();

		fields.addInfo("article-date-settings");
		fields.addLabel("display-date");
		fields.addDate("display-date");
		fields.addSeparator();
		fields.addLabel("expiration-date");
		fields.addDate("expiration-date");
		fields.addSeparator();
		fields.addLabel("review-date");
		fields.addDate("review-date");
		fields.addSeparator();

		return fields.getDisplayFields();
	}

}