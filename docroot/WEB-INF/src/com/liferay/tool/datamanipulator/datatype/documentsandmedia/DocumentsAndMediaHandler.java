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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalThreadLocal;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
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
public class DocumentsAndMediaHandler extends AbstractPortletHandler
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

		PermissionThreadLocal.setIndexEnabled(false);
		PermissionThreadLocal.setPermissionChecker(
			requestProcessor.getPermissionChecker());

		PrincipalThreadLocal.setName(requestProcessor.getUserId());

		PortalSessionThreadLocal.setHttpSession(requestProcessor.getSession());

		// Documents And Media File Entry

		int fileCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FILE);

		int fileUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FILE);

		EntryTypeReader fileEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FILE);

		BaseEntry fileEntry = new BaseEntry(fileEntryType);

		EntryHandlerModel fileEntryHandler = new DocumentsAndMediaFileHandler(
			fileCount, fileUpdate, 0, 0, fileEntry, null, requestProcessor);

		// Documents And Media Folder

		int folderCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);

		int folderUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);

		int folderDepth = requestProcessor.getDepth(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);

		int folderSubCount = requestProcessor.getSubCount(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);

		EntryTypeReader folderEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA_FOLDER);

		BaseEntry folderEntry = new BaseEntry(folderEntryType);

		EntryHandlerModel folderHandler = new DocumentsAndMediaFolderHandler(
			folderCount, folderUpdate, folderDepth, folderSubCount, folderEntry,
			fileEntryHandler, requestProcessor);

		long parentFolderId = requestProcessor.getLong(
			DocumentsAndMediaDisplayFields.DOCUMENTS_AND_MEDIA_FOLDER_ID);

		if (parentFolderId == 0) {
			parentFolderId = requestProcessor.getLong(
				DocumentsAndMediaDisplayFields.DOCUMENTS_AND_MEDIA_FOLDER_LIST);
		}

		DLFolder folder = DLFolderLocalServiceUtil.fetchDLFolder(
			parentFolderId);

		if (Validator.isNotNull(folder)) {
			folderHandler.generateEntries(parentFolderId);
		}
		else {
			folderHandler.generateEntries((long)0);
		}
	}

}