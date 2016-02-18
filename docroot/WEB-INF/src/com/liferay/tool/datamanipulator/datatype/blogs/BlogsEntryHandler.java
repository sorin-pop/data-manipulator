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

package com.liferay.tool.datamanipulator.datatype.blogs;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryArgs;
import com.liferay.tool.datamanipulator.entry.util.EntryUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.AbstractEntryHandler;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;
import com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil;

import java.util.Calendar;

/**
 * @author Tibor Kovács
 *
 */
public class BlogsEntryHandler extends AbstractEntryHandler
	implements EntryHandlerModel {

	/**
	 * @param count
	 * @param update
	 * @param depth
	 * @param subCount
	 * @param baseEntry
	 * @param messageHandler
	 * @param requestProcessor
	 */
	public BlogsEntryHandler(
		int count, int update, int depth, int subCount, BaseEntry baseEntry,
		EntryHandlerModel messageHandler, RequestProcessor requestProcessor) {

		super(
			count, update, depth, subCount, baseEntry, messageHandler,
			requestProcessor);

		_displayDataFrom = requestProcessor.getCalendar("display-date-from");
		_displayDataTo = requestProcessor.getCalendar("display-date-to");
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getCreateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getCreateEntryArgs(
			long parentId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		postString += EntryUtil.nextString();

		Calendar newCalendar = EntryUtil.getRandomCalendar(
			_displayDataFrom, _displayDataTo);

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("title", "Test Blogs Entry" + postString + " Title");
		args.setParameter(
			"description", "Test Blogs Entry" + postString + " Description");

		args.setParameter(
			"content", "Test Blogs Entry" + postString + " Content");

		args.setParameter("displayDateMonth", newCalendar.get(Calendar.MONTH));
		args.setParameter("displayDateDay", newCalendar.get(Calendar.DATE));
		args.setParameter("displayDateYear", newCalendar.get(Calendar.YEAR));
		args.setParameter("displayDateHour", newCalendar.get(Calendar.HOUR));
		args.setParameter(
			"displayDateMinute", newCalendar.get(Calendar.MINUTE));

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */

	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			((BlogsEntry)createdEntry).getGroupId(), BlogsEntry.class.getName(),
			((BlogsEntry)createdEntry).getEntryId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		BlogsEntry bEntry = BlogsEntryLocalServiceUtil.getEntry(entryId);

		Calendar newCalendar = EntryUtil.getRandomCalendar(
			_displayDataFrom, _displayDataTo);

		EntryArgs args = new EntryArgs(requestProcessor);

		String title = EntryUtil.getEditString(bEntry.getTitle(), postString);

		String description = EntryUtil.getEditString(
			bEntry.getDescription(), postString);

		String content = bEntry.getContent();
		content += "</br>Test Blogs Entry " + postString + ". Edit Content";

		args.setParameter("entryId", entryId);
		args.setParameter("title", title);
		args.setParameter("description", description);
		args.setParameter("content", content);
		args.setParameter("displayDateMonth", newCalendar.get(Calendar.MONTH));
		args.setParameter("displayDateDay", newCalendar.get(Calendar.DATE));
		args.setParameter("displayDateYear", newCalendar.get(Calendar.YEAR));
		args.setParameter("displayDateHour", newCalendar.get(Calendar.HOUR));
		args.setParameter(
			"displayDateMinute", newCalendar.get(Calendar.MINUTE));

		return args;
	}

	private static Calendar _displayDataFrom;
	private static Calendar _displayDataTo;

}