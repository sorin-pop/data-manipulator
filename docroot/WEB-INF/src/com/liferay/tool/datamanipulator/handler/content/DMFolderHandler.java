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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.service.RepositoryLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.document.library.kernel.service.DLFolderLocalServiceUtil;

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
import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Yg0R2
 */
@Handler(type = HandlerType.CONTENT, displayName = "Documents and Media")
public class DMFolderHandler extends BaseHandler {

	public static final String PARENT_FOLDER_LIST = "parent-folder-list";
	public static final String REPOSITORY_LIST = "repository-list";

	public DMFolderHandler() throws Exception {
		super("Documents and Media Folder", "documents-and-media-folder");
	}

	@Override
	public DisplayFields getDisplayFields(long groupId, long companyId) throws Exception {
		DisplayFields displayFields = new DisplayFields();

		displayFields.addInfo(getDisplayFieldName(FieldKeys.MULTI_SELECT_USER_LIST));
		displayFields.addUserMultiSelect(FieldKeys.MULTI_SELECT_USER_LIST);

		displayFields.addSeparator("");
		
		/*displayFields.addInfo(
			getDisplayFieldName(PARENT_FOLDER_LIST + "-" + REPOSITORY_LIST));

		displayFields.addSelectList(
			getDisplayFieldName(REPOSITORY_LIST),
			_getRepositoryNameIdPairs(groupId));*/

		displayFields.addSelectList(
			getDisplayFieldName(FieldKeys.SELECT_PARENT_LIST),
			_getFolderNameIdPairs(groupId));


		displayFields.addSeparator("");

		displayFields.addLabel(getDisplayFieldName() + "Settings");

		displayFields.addCount(getDisplayFieldName(FieldKeys.INPUT_COUNT));
		
		displayFields.addDepth(getDisplayFieldName(FieldKeys.INPUT_DEPTH));

		displayFields.addSubCount(
			getDisplayFieldName(FieldKeys.INPUT_SUBCOUNT));

		displayFields.addUpdateCount(
			getDisplayFieldName(FieldKeys.INPUT_UPDATE_COUNT));


		displayFields.addSeparator("");

		displayFields.addAll(
			(new DMDocumentHandler()).getDisplayFields(groupId, companyId));

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
			"repositoryId", "parentFolderId", "name", "description",
			"serviceContext"
		};
	}

	@Override
	protected Class<?> getAddClass() throws ClassNotFoundException {
		return DLAppServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getAddEntrySpecifiedArgs(
		RequestContext requestContext) throws Exception {

		long parentFolderId = requestContext.getLong(
			getDisplayFieldName(FieldKeys.SELECT_PARENT_LIST));

		long repositoryId = requestContext.getLong(
			getDisplayFieldName(REPOSITORY_LIST), requestContext.getGroupId());

		Map<String, Object> args = new HashMap<String, Object>(2);

		if ((repositoryId != requestContext.getGroupId()) &&
			(parentFolderId != DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)) {

			args.put(
				"parentFolderId", DLFolderConstants.DEFAULT_PARENT_FOLDER_ID);
		}

		args.put("repositoryId", repositoryId);

		return args;
	}

	@Override
	protected String getAddMethodName() {
		return "addFolder";
	}

	@Override
	protected List<String> getChildHandlerNames() {
		List<String> childHandlers = new ArrayList<String>(1);

		childHandlers.add(DMDocumentHandler.class.getSimpleName());

		return childHandlers;
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
		return new Class<?> [] {
			Long.TYPE, String.class, String.class, ServiceContext.class
		};
	}

	@Override
	protected String[] getUpdateArgNames() {
		return new String[] {
			"folderId", "name", "description", "serviceContext"
		};
	}

	@Override
	protected Class<?> getUpdateClass() throws ClassNotFoundException {
		return DLAppServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getUpdateEntrySpecifiedArgs(Object entry,
		RequestContext requestContext) throws Exception {

		return new HashMap<String, Object>(0);
	}

	@Override
	protected String getUpdateMethodName() {
		return "updateFolder";
	}

	private List<KeyValuePair> _getFolderNameIdPairs(long groupId)
		throws SystemException {

		List<DLFolder> dlFolderList =
			DLFolderLocalServiceUtil.getFolders(
				groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, WorkflowConstants.STATUS_APPROVED, false, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		List<KeyValuePair> dlFolderNameIdList = new ArrayList<KeyValuePair>(
			dlFolderList.size() + 1);

		dlFolderNameIdList.add(
			new KeyValuePair(
				"HOME",
				String.valueOf(DLFolderConstants.DEFAULT_PARENT_FOLDER_ID)));

		for (DLFolder folder : dlFolderList) {
			dlFolderNameIdList.add(
				new KeyValuePair(
					folder.getName(), String.valueOf(folder.getFolderId())));
		}

		return dlFolderNameIdList;
	}

	private List<KeyValuePair> _getRepositoryNameIdPairs(long groupId)
		throws SystemException {

		List<Repository> repositoryList =
			RepositoryLocalServiceUtil.getRepositories(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<KeyValuePair> repositoryIdNamePairs =
			new ArrayList<KeyValuePair>(repositoryList.size() + 1);

		repositoryIdNamePairs.add(
			new KeyValuePair("", String.valueOf(groupId)));

		for (Repository repo : repositoryList) {
			repositoryIdNamePairs.add(
				new KeyValuePair(
					repo.getName(), String.valueOf(repo.getRepositoryId())));
		}

		return repositoryIdNamePairs;
	}

}