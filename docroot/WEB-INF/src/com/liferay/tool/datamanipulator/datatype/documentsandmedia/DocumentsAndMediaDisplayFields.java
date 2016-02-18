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

package com.liferay.tool.datamanipulator.datatype.documentsandmedia;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.model.DLFolderConstants;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
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
public class DocumentsAndMediaDisplayFields {

	public static final String DOCUMENTS_AND_MEDIA_FOLDER_ID =
		"documents-and-media-folder-id";

	public static final String DOCUMENTS_AND_MEDIA_FOLDER_LIST =
		"documents-and-media-folder-list";

	public static List<Field> getDisplayFields(long groupId)
		throws SystemException {

		List<DLFolder> dlFolderList =
			DLFolderLocalServiceUtil.getFolders(
				groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		List<KeyValuePair> dlFolderNameId = new ArrayList<KeyValuePair>(
			dlFolderList.size() + 1);

		dlFolderNameId.add(
			new KeyValuePair(
				EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA + StringPool.DASH +
					FieldKeys.DEFAULT_PARENT,
				String.valueOf(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)));

		for (DLFolder folder : dlFolderList) {
			dlFolderNameId.add(
				new KeyValuePair(
					folder.getName(), String.valueOf(folder.getFolderId())));
		}

		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addFile("upload-file", true);
		fields.addSeparator();

		fields.addInfo("specify-root-folder");
		fields.addSelectList(DOCUMENTS_AND_MEDIA_FOLDER_LIST, dlFolderNameId);
		fields.addInput(
			DOCUMENTS_AND_MEDIA_FOLDER_ID,
			String.valueOf(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID));
		fields.addSeparator();

		fields.addInfo("folder-generation-settings");
		fields.addCount(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);
		fields.addDepth(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);
		fields.addSubCount(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);
		fields.addSeparator();

		fields.addInfo("document-generation-settings");
		fields.addCount(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FILE);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FILE);
		fields.addToParent(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FILE);
		fields.addSeparator();

		fields.addInput("repository-id");
		fields.addSeparator();

		return fields.getDisplayFields();
	}

}