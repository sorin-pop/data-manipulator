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

package com.liferay.tool.datamanipulator.datatype.calendar;

import com.liferay.portal.kernel.cal.Duration;
import com.liferay.portal.kernel.cal.Recurrence;
import com.liferay.portal.kernel.cal.TZSRecurrence;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.model.CalEventConstants;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;
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
public class CalendarEventHandler extends AbstractEntryHandler
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
	public CalendarEventHandler(
		int count, int update, int depth, int subCount, BaseEntry baseEntry,
		EntryHandlerModel subEntryHandler, RequestProcessor requestProcessor) {

		super(
			count, update, depth, subCount, baseEntry, subEntryHandler,
			requestProcessor);

		int minutes = requestProcessor.getMinutes("duration");

		if (minutes < 5) {
			minutes = 5;
		}

		_durationHour = ((int)(minutes / 60));
		_durationMinute = minutes%60;

		_endDateFrom = requestProcessor.getCalendar("end-date-from");
		_endDateTo = requestProcessor.getCalendar("end-date-to");

		_startDateFrom = requestProcessor.getCalendar("start-date-from");
		_startDateTo = requestProcessor.getCalendar("start-date-to");
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getCreateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getCreateEntryArgs(
			long parentId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		postString += EntryUtil.nextString();

		Calendar startDate = EntryUtil.getRandomCalendar(
			_startDateFrom, _startDateTo);

		Calendar endDate = EntryUtil.getRandomCalendar(
			_endDateFrom, _endDateTo);

		int durationHour = EntryUtil.nextInt(_durationHour);
		int durationMinute = EntryUtil.nextInt(_durationMinute);

		if ((durationHour <= 0) && (durationMinute <= 0)) {
			durationMinute = 5;
		}

		String type = StringPool.BLANK;

		if (CalEventConstants.TYPES.length > 0) {
			int typeIndex = EntryUtil.nextInt(CalEventConstants.TYPES.length);

			type = CalEventConstants.TYPES[typeIndex];
		}

		boolean repeating = EntryUtil.nextBoolean();

		TZSRecurrence recurrence = null;

		if (repeating) {
			recurrence = getNewRecurrence(startDate);
		}

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter(
			"title", "Test Calendar Event" + postString + " Title");

		args.setParameter(
			"description", "Test Calendar Event" + postString + " Description");

		args.setParameter("startDateMonth", startDate.get(Calendar.MONTH));
		args.setParameter("startDateDay", startDate.get(Calendar.DATE));
		args.setParameter("startDateYear", startDate.get(Calendar.YEAR));
		args.setParameter("startDateHour", startDate.get(Calendar.HOUR));
		args.setParameter("startDateMinute", startDate.get(Calendar.MINUTE));
		args.setParameter("endDateMonth", endDate.get(Calendar.MONTH));
		args.setParameter("endDateDay", endDate.get(Calendar.DATE));
		args.setParameter("endDateYear", endDate.get(Calendar.YEAR));
		args.setParameter("endDateHour", endDate.get(Calendar.HOUR));
		args.setParameter("endDateMinute", endDate.get(Calendar.MINUTE));
		args.setParameter("durationHour",durationHour);
		args.setParameter("durationMinute", durationMinute);
		args.setParameter("timeZoneSensitive", EntryUtil.nextBoolean());
		args.setParameter("type", type);
		args.setParameter("repeating", repeating);
		args.setParameter("recurrence", recurrence);
		args.setParameter("remindBy", CalEventConstants.REMIND_BY_NONE);
		args.setParameter("firstReminder", EntryUtil.nextInt(0, 20));
		args.setParameter("secondReminder", EntryUtil.nextInt(20, 60));

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */

	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			((CalEvent)createdEntry).getGroupId(), CalEvent.class.getName(),
			((CalEvent)createdEntry).getEventId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		CalEvent calEvent = CalEventLocalServiceUtil.getCalEvent(entryId);

		String title = EntryUtil.getEditString(calEvent.getTitle(), postString);

		String description = EntryUtil.getEditString(
			calEvent.getDescription(), postString);

		Calendar startDate = EntryUtil.getRandomCalendar(
			_startDateFrom, _startDateTo);

		Calendar endDate = EntryUtil.getRandomCalendar(
			_endDateFrom, _endDateTo);

		int durationHour = EntryUtil.nextInt(_durationHour);
		int durationMinute = EntryUtil.nextInt(_durationMinute);

		if ((durationHour <= 0) && (durationMinute <= 0)) {
			durationMinute = 5;
		}

		String type = StringPool.BLANK;

		if (CalEventConstants.TYPES.length > 0) {
			int typeIndex = EntryUtil.nextInt(CalEventConstants.TYPES.length);

			type = CalEventConstants.TYPES[typeIndex];
		}

		boolean repeating = EntryUtil.nextBoolean();

		TZSRecurrence recurrence = null;

		if (repeating) {
			recurrence = getNewRecurrence(startDate);
		}

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("eventId", entryId);
		args.setParameter("title", title);
		args.setParameter("description", description);
		args.setParameter("startDateMonth", startDate.get(Calendar.MONTH));
		args.setParameter("startDateDay", startDate.get(Calendar.DATE));
		args.setParameter("startDateYear", startDate.get(Calendar.YEAR));
		args.setParameter("startDateHour", startDate.get(Calendar.HOUR));
		args.setParameter("startDateMinute", startDate.get(Calendar.MINUTE));
		args.setParameter("endDateMonth", endDate.get(Calendar.MONTH));
		args.setParameter("endDateDay", endDate.get(Calendar.DATE));
		args.setParameter("endDateYear", endDate.get(Calendar.YEAR));
		args.setParameter("endDateHour", endDate.get(Calendar.HOUR));
		args.setParameter("endDateMinute", endDate.get(Calendar.MINUTE));
		args.setParameter("durationHour", durationHour);
		args.setParameter("durationMinute", durationMinute);
		args.setParameter("timeZoneSensitive", EntryUtil.nextBoolean());
		args.setParameter("type", type);
		args.setParameter("repeating", repeating);
		args.setParameter("recurrence", recurrence);
		args.setParameter("remindBy", CalEventConstants.REMIND_BY_NONE);
		args.setParameter("firstReminder", EntryUtil.nextInt(0, 20));
		args.setParameter("secondReminder", EntryUtil.nextInt(20, 60));

		return args;
	}

	private static TZSRecurrence getNewRecurrence(Calendar startDate) {
		int[] frequency = new int[5];

		frequency[0] = Recurrence.DAILY;
		frequency[1] = Recurrence.MONTHLY;
		frequency[2] = Recurrence.NO_RECURRENCE;
		frequency[3] = Recurrence.WEEKLY;
		frequency[4] = Recurrence.YEARLY;

		Duration duration;
		int randomFrequency = frequency[EntryUtil.nextInt(frequency.length)];

		switch (randomFrequency) {
			case Recurrence.DAILY : {
				duration = new Duration(EntryUtil.nextInt(365), 0, 0, 0);

				break;
			}

			case Recurrence.MONTHLY : {
				duration = new Duration(EntryUtil.nextInt(52));
				break;
			}

			case Recurrence.WEEKLY : {
				duration = new Duration(EntryUtil.nextInt(52));
				break;
			}

			case Recurrence.YEARLY : {
				duration = new Duration(EntryUtil.nextInt(52));
				break;
			}

			default : {
				duration = new Duration();
				break;
			}
		}

		return new TZSRecurrence(startDate, duration, randomFrequency);
	}

	private int _durationHour;
	private int _durationMinute;
	private Calendar _endDateFrom;
	private Calendar _endDateTo;
	private Calendar _startDateFrom;
	private Calendar _startDateTo;

}