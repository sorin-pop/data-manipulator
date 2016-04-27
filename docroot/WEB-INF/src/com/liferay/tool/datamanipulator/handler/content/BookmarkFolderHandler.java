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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.model.BookmarksFolderConstants;
import com.liferay.bookmarks.service.BookmarksFolderLocalServiceUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liferay.tool.datamanipulator.annotation.Handler;
import com.liferay.tool.datamanipulator.annotation.HandlerType;
import com.liferay.tool.datamanipulator.context.RequestContext;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.handler.BaseHandler;

/**
 * @author Yg0R2
 */
@Handler(type = HandlerType.CONTENT, displayName = "Bookmarks Handler")
public class BookmarkFolderHandler extends BaseHandler {

	public BookmarkFolderHandler() throws Exception {
		super("Bookmark Folder", "bookmark-folder");
	}

	@Override
	public DisplayFields getDisplayFields(long groupId, long companyId) throws Exception {
		DisplayFields displayFields = new DisplayFields();

		displayFields.addUserMultiSelect(FieldKeys.MULTI_SELECT_USER_LIST);
		displayFields.addInfo(
			getDisplayFieldName(FieldKeys.MULTI_SELECT_USER_LIST));

		displayFields.addSeparator("");

		displayFields.addSelectList(
			getDisplayFieldName(FieldKeys.SELECT_PARENT_LIST),
			_getFolderNameIdPairs(groupId));

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
			(new BookmarkEntryHandler()).getDisplayFields(groupId, companyId));

		return displayFields;
	}

	@Override
	protected Class<?>[] getAddArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, Long.TYPE, String.class, String.class,
			ServiceContext.class
		};
	}

	@Override
	protected String[] getAddArgNames() {
		return new String[] {
			"userId", "parentFolderId", "name", "description", "serviceContext"
		};
	}

	@Override
	protected Class<?> getAddClass() throws ClassNotFoundException {
		return BookmarksFolderLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getAddEntrySpecifiedArgs(
		RequestContext requestContext) throws Exception {

		return  new HashMap<String, Object>(0);
	}

	@Override
	protected String getAddMethodName() {
		return "addFolder";
	}

	@Override
	protected List<String> getChildHandlerNames() {
		List<String> childHandlerNames = new ArrayList<String>();

		childHandlerNames.add(BookmarkEntryHandler.class.getSimpleName());

		return childHandlerNames;
	}

	@Override
	protected String getClassPKName() {
		return "folderId";
	}

	@Override
	protected String getParentClassPKName() {
		return "parentFolderId";
	}

	@Override
	protected Class<?>[] getUpdateArgClazzs() {
		if (buildNumber < 6200) {
			return new Class<?>[] {
				Long.TYPE, Long.TYPE, String.class, String.class, Boolean.TYPE,
				ServiceContext.class
			};
		}

		return new Class<?>[] {
			Long.TYPE, Long.TYPE, Long.TYPE, String.class, String.class,
			Boolean.TYPE, ServiceContext.class
		};
	}

	@Override
	protected String[] getUpdateArgNames() {
		if (buildNumber < 6200) {
			return new String[] {
				"folderId", "parentFolderId", "name", "description",
				"mergeWithParentFolder", "serviceContext"
			};
		}

		return new String[] {
			"userId", "folderId", "parentFolderId", "name", "description",
			"mergeWithParentFolder", "serviceContext"
		};
	}

	@Override
	protected Class<?> getUpdateClass() throws ClassNotFoundException {
		return BookmarksFolderLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getUpdateEntrySpecifiedArgs(
		Object entry, RequestContext requestContext) throws Exception {

		Map<String, Object> args = new HashMap<String, Object>(1);

		args.put("mergeWithParentFolder", false);

		return args;
	}

	@Override
	protected String getUpdateMethodName() {
		return "updateFolder";
	}

	private List<KeyValuePair> _getFolderNameIdPairs(long groupId)
		throws SystemException {

		List<BookmarksFolder> folders =
			BookmarksFolderLocalServiceUtil.getFolders(
				groupId, BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID);

		List<KeyValuePair> folderNameIdPairs = new ArrayList<KeyValuePair>();

		folderNameIdPairs.add(
			new KeyValuePair(
				"",
				String.valueOf(
					BookmarksFolderConstants.DEFAULT_PARENT_FOLDER_ID)));

		for (BookmarksFolder folder : folders) {
			String folderId = String.valueOf(folder.getFolderId());
			String folderName = folder.getName();

			try {
				Method method = BookmarksFolder.class.getMethod(
					"getStatus", new Class<?>[0]);

				Object status = method.invoke(
					BookmarksFolder.class, new Object[0]);

				if ((Integer)status != WorkflowConstants.STATUS_APPROVED) {
					continue;
				}
			}
			catch (Exception e) {
			}

			folderNameIdPairs.add(new KeyValuePair(folderName, folderId));
		}

		return folderNameIdPairs;
	}

}