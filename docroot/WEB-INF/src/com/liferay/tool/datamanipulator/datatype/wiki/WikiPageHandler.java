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
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.model.WikiPageConstants;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryArgs;
import com.liferay.tool.datamanipulator.entry.util.EntryUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.AbstractEntryHandler;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;
import com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil;

/**
 * @author Tibor Kovács
 *
 */
public class WikiPageHandler extends AbstractEntryHandler
	implements EntryHandlerModel {

	/**
	 * @param count
	 * @param update
	 * @param depth
	 * @param subCount
	 * @param baseEntry
	 * @param subEntryHandler
	 * @param requestProcessor
	 */
	public WikiPageHandler(
		int count, int update, int depth, int subCount, BaseEntry baseEntry,
		EntryHandlerModel subEntryHandler, RequestProcessor requestProcessor) {

		super(
			count, update, depth, subCount, baseEntry, subEntryHandler,
			requestProcessor);
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getCreateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getCreateEntryArgs(
			long parentId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		postString += EntryUtil.nextString();

		WikiPage wikiPage = WikiPageLocalServiceUtil.fetchWikiPage(parentId);

		EntryArgs args = new EntryArgs(requestProcessor);

		if (Validator.isNull(wikiPage)) {
			int formatIndex = EntryUtil.nextInt(
				WikiPageConstants.FORMATS.length);

			args.setParameter("nodeId", parentId);
			args.setParameter("version", WikiPageConstants.VERSION_DEFAULT);
			args.setParameter("format", WikiPageConstants.FORMATS[formatIndex]);
			args.setParameter("head", true);
			args.setParameter("parentTitle", null);
			args.setParameter("redirectTitle", null);
		}
		else {
			args.setParameter("nodeId", wikiPage.getNodeId());
			args.setParameter("version", wikiPage.getVersion());
			args.setParameter("format", wikiPage.getFormat());
			args.setParameter("head", false);
			args.setParameter("parentTitle", wikiPage.getTitle());
			args.setParameter("redirectTitle", wikiPage.getRedirectTitle());
		}

		args.setParameter("title", "Test Wiki Page" + postString + " Title");
		args.setParameter(
			"content", "Test Wiki Page" + postString + " Content");

		args.setParameter(
			"summary", "Test Wiki Page" + postString + " Summary");

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */

	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			((WikiPage)createdEntry).getGroupId(), WikiPage.class.getName(),
			((WikiPage)createdEntry).getPageId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		WikiPage wikiPage = WikiPageLocalServiceUtil.getPageByPageId(entryId);

		long resourcePrimKey = wikiPage.getResourcePrimKey();

		wikiPage = WikiPageLocalServiceUtil.getPage(resourcePrimKey);

		String content = wikiPage.getContent();
		content += "\n\rTest Wiki Page " + postString + ". Edit Content";

		String summary = EntryUtil.getEditString(
			wikiPage.getSummary(), postString);

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("nodeId", wikiPage.getNodeId());
		args.setParameter("title", wikiPage.getTitle());
		args.setParameter("version", wikiPage.getVersion());
		args.setParameter("content", content);
		args.setParameter("summary", summary);
		args.setParameter("format", wikiPage.getFormat());
		args.setParameter("parentTitle", wikiPage.getParentTitle());
		args.setParameter("redirectTitle", wikiPage.getRedirectTitle());

		return args;
	}

}