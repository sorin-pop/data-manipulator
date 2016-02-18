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

package com.liferay.tool.datamanipulator.datatype.wiki;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.service.WikiNodeLocalServiceUtil;
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
public class WikiHandler extends AbstractPortletHandler
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

		// Wiki Page

		int pageCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_WIKI_PAGE);

		int pageUpdate = requestProcessor.getUpdateLevel(
			EntryTypeKeys.GENERAL_WIKI_PAGE);

		int pageDepth = requestProcessor.getDepth(
			EntryTypeKeys.GENERAL_WIKI_PAGE);

		int pageSubCount = requestProcessor.getSubCount(
			EntryTypeKeys.GENERAL_WIKI_PAGE);

		EntryTypeReader pageEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_WIKI_PAGE);

		BaseEntry pageEntry = new BaseEntry(pageEntryType);

		EntryHandlerModel pageHandler = new WikiPageHandler(
			pageCount, pageUpdate, pageDepth, pageSubCount, pageEntry, null,
			requestProcessor);

		long parentWikinodeId = requestProcessor.getLong(
			WikiDisplayFields.WIKI_NODE_ID);

		if (parentWikinodeId == 0) {
			parentWikinodeId = requestProcessor.getLong(
				WikiDisplayFields.WIKI_NODE_LIST);
		}

		WikiNode parentWikiNode = WikiNodeLocalServiceUtil.fetchWikiNode(
			parentWikinodeId);

		if (Validator.isNotNull(parentWikiNode)) {
			pageHandler.generateEntries(parentWikinodeId);
		}
		else {

			// Wiki Node

			int nodeCount = requestProcessor.getCount(
				EntryTypeKeys.GENERAL_WIKI_NODE);

			int nodeUpdate = requestProcessor.getUpdateLevel(
				EntryTypeKeys.GENERAL_WIKI_NODE);

			EntryTypeReader nodeEntryType = EntryReaderUtil.getEntryType(
				EntryTypeKeys.GENERAL_WIKI_NODE);

			BaseEntry nodeEntry = new BaseEntry(nodeEntryType);

			EntryHandlerModel nodeHandler = new WikiNodeHandler(
				nodeCount, nodeUpdate, 0, 0, nodeEntry, pageHandler,
				requestProcessor);

			nodeHandler.generateEntries((long)0);
		}
	}

}