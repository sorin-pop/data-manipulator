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

import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.document.library.kernel.service.DLAppServiceUtil;

import java.io.File;
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
public class DMDocumentHandler extends BaseHandler {

	public static final String UPLOAD_FILE = "upload-file";

	public DMDocumentHandler() throws Exception {
		super("Documents and Media Document", "documents-and-media-document");
	}

	@Override
	public DisplayFields getDisplayFields(long groupId, long companyId) throws Exception {
		DisplayFields displayFields = new DisplayFields();

		displayFields.addLabel(getDisplayFieldName() + "Settings");

		displayFields.addFile(getDisplayFieldName(UPLOAD_FILE), true);

		displayFields.addCount(getDisplayFieldName(FieldKeys.INPUT_COUNT));

		displayFields.addUpdateCount(
			getDisplayFieldName(FieldKeys.INPUT_UPDATE_COUNT));

		return displayFields;
	}

	@Override
	protected Class<?>[] getAddArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, Long.TYPE, String.class, String.class, String.class,
			String.class, String.class, File.class, ServiceContext.class
		};
	}

	@Override
	protected String[] getAddArgNames() {
		return new String[] {
			"repositoryId", "folderId", "sourceFileName", "mimeType", "title",
			"description", "changeLog", "file", "serviceContext"
		};
	}

	@Override
	protected Class<?> getAddClass() throws ClassNotFoundException {
		return DLAppServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getAddEntrySpecifiedArgs(
		RequestContext requestContext) throws Exception {

		long repositoryId = requestContext.getLong(
			DMFolderHandler.REPOSITORY_LIST, requestContext.getGroupId());

		File uploadedFile = requestContext.getFile(
			getDisplayFieldName(UPLOAD_FILE));

		Map<String, Object> args = new HashMap<String, Object>(2);

		args.put("file", uploadedFile);
		args.put("mimeType", MimeTypesUtil.getContentType(uploadedFile));
		args.put("repositoryId", repositoryId);
		args.put("sourceFileName", uploadedFile.getName());

		return args;
	}

	@Override
	protected String getAddMethodName() {
		return "addFileEntry";
	}

	@Override
	protected List<String> getChildHandlerNames() {
		return new ArrayList<String>(0);
	}

	@Override
	protected String getClassPKName() {
		return "fileEntryId";
	}

	@Override
	protected String getParentClassPKName() {
		return "folderId";
	}

	@Override
	protected Class<?>[] getUpdateArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, String.class, String.class, String.class, String.class,
			String.class, Boolean.TYPE, File.class, ServiceContext.class
		};
	}

	@Override
	protected String[] getUpdateArgNames() {
		return new String[] {
			"fileEntryId", "sourceFileName", "mimeType", "title", "description",
			"changeLog", "majorVersion", "file", "serviceContext"
		};
	}

	@Override
	protected Class<?> getUpdateClass() throws ClassNotFoundException {
		return DLAppServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getUpdateEntrySpecifiedArgs(
		Object entry, RequestContext requestContext) throws Exception {

		Map<String, Object> args = new HashMap<String, Object>(2);

		args.put("majorVersion", false);

		return args;
	}

	@Override
	protected String getUpdateMethodName() {
		return "updateFileEntry";
	}

}