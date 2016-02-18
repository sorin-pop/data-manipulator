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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleConstants;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.model.JournalTemplate;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalStructureLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalTemplateLocalServiceUtil;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryArgs;
import com.liferay.tool.datamanipulator.entry.util.EntryUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.AbstractEntryHandler;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;
import com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * @author Tibor Kovács
 *
 */
public class JournalArticleHandler extends AbstractEntryHandler
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
	public JournalArticleHandler(
		int count, int update, int depth, int subCount, BaseEntry baseEntry,
		EntryHandlerModel subEntryHandler, RequestProcessor requestProcessor) {

		super(
			count, update, depth, subCount, baseEntry, subEntryHandler,
			requestProcessor);

		_articleURL = requestProcessor.getString("editEntryTypeURL");

		_displayDateFrom = requestProcessor.getCalendar("display-date-from");

		_displayDateTo = requestProcessor.getCalendar("display-date-to");

		_expirationDateFrom = requestProcessor.getCalendar(
			"expiration-date-from");

		_expirationDateTo = requestProcessor.getCalendar("expiration-date-to");

		_reviewDateFrom = requestProcessor.getCalendar("review-date-from");

		_reviewDateTo = requestProcessor.getCalendar("review-date-to");

		try {
			_structures = JournalStructureLocalServiceUtil.getStructures(
				requestProcessor.getGroupId());
		}
		catch (SystemException e) {
			_structures = new ArrayList<JournalStructure>(0);
		}
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getCreateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getCreateEntryArgs(
			long parentId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		postString += EntryUtil.nextString();

		String articleId = StringPool.BLANK;
		boolean autoArticleId = EntryUtil.nextBoolean();

		if (!autoArticleId) {
			articleId = "Test-WebContent-Article" + postString + "-id";
		}

		String title = "Test WebContent Article" + postString + " Title";

		String description =
			"Test WebContent Article" + postString + " Description";

		String content = "Test WebContent Article" + postString + " Content";
		content = LocalizationUtil.updateLocalization(
			StringPool.BLANK, "static-content", content);

		int typeIndex = EntryUtil.nextInt(JournalArticleConstants.TYPES.length);

		String structureId = StringPool.BLANK;
		String templateId = StringPool.BLANK;

		if (_structures.size() > 0) {
			int index = EntryUtil.nextInt(_structures.size());

			JournalStructure structure = _structures.get(index);

			structureId = structure.getStructureId();

			List<JournalTemplate> structureTemplates =
				JournalTemplateLocalServiceUtil.getStructureTemplates(
					requestProcessor.getGroupId(), structureId);

			if (structureTemplates.size() > 0) {
				int templateIndex = EntryUtil.nextInt(
					structureTemplates.size());

				JournalTemplate template = structureTemplates.get(
					templateIndex);

				templateId = template.getTemplateId();
			}
		}

		Calendar displayDate = EntryUtil.getRandomCalendar(
			_displayDateFrom, _displayDateTo);

		Calendar expirationDate = EntryUtil.getRandomCalendar(
			_expirationDateFrom, _expirationDateTo);

		while (expirationDate.before(Calendar.getInstance())) {
			expirationDate.add(Calendar.YEAR, 1);
		}

		Calendar reviewDate = EntryUtil.getRandomCalendar(
			_reviewDateFrom, _reviewDateTo);

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("folderId", parentId);
		args.setParameter("classNameId", (long)0);
		args.setParameter("classPK", (long)0);
		args.setParameter("layoutUuid", null);
		args.setParameter("articleId", articleId);
		args.setParameter("autoArticleId", autoArticleId);
		args.setParameter("version", JournalArticleConstants.VERSION_DEFAULT);
		args.setParameter(
			"titleMap", LocalizationUtil.getLocalizationMap(title));

		args.setParameter(
			"descriptionMap", LocalizationUtil.getLocalizationMap(description));

		args.setParameter("LocalizedContent", content);
		args.setParameter("type", JournalArticleConstants.TYPES[typeIndex]);
		args.setParameter("ddmStructureKey", structureId);
		args.setParameter("ddmTemplateKey", templateId);
		args.setParameter("displayDateMonth", displayDate.get(Calendar.MONTH));
		args.setParameter("displayDateDay", displayDate.get(Calendar.DATE));
		args.setParameter("displayDateYear", displayDate.get(Calendar.YEAR));
		args.setParameter("displayDateHour", displayDate.get(Calendar.HOUR));
		args.setParameter(
			"displayDateMinute", displayDate.get(Calendar.MINUTE));

		args.setParameter(
			"expirationDateMonth", expirationDate.get(Calendar.MONTH));

		args.setParameter(
			"expirationDateDay", expirationDate.get(Calendar.DATE));

		args.setParameter(
			"expirationDateYear", expirationDate.get(Calendar.YEAR));

		args.setParameter(
			"expirationDateHour", expirationDate.get(Calendar.HOUR));

		args.setParameter(
			"expirationDateMinute", expirationDate.get(Calendar.MINUTE));

		args.setParameter("neverExpire", EntryUtil.nextBoolean());
		args.setParameter("reviewDateMonth", reviewDate.get(Calendar.MONTH));
		args.setParameter("reviewDateDay", reviewDate.get(Calendar.DATE));
		args.setParameter("reviewDateYear", reviewDate.get(Calendar.YEAR));
		args.setParameter("reviewDateHour", reviewDate.get(Calendar.HOUR));
		args.setParameter("reviewDateMinute", reviewDate.get(Calendar.MINUTE));
		args.setParameter("neverReview", EntryUtil.nextBoolean());
		args.setParameter("articleURL", _articleURL);

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */

	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			((JournalArticle)createdEntry).getGroupId(),
			JournalArticle.class.getName(),
			((JournalArticle)createdEntry).getId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		JournalArticle article = JournalArticleLocalServiceUtil.getArticle(
			entryId);

		JournalArticle latestArticle =
			JournalArticleLocalServiceUtil.getLatestArticle(
				article.getResourcePrimKey());

		String title = EntryUtil.getEditString(
			article.getTitle(Locale.getDefault()), postString);

		String description = EntryUtil.getEditString(
			article.getDescription(Locale.getDefault()), postString);

		String content = latestArticle.getContent();
		content = LocalizationUtil.getLocalization(
			content, requestProcessor.getLanguageId());

		content +=
			"</br>Test WebContent Article " + postString + ". Edit Content";

		content = LocalizationUtil.updateLocalization(
			StringPool.BLANK, "static-content", content);

		int typeIndex = EntryUtil.nextInt(JournalArticleConstants.TYPES.length);

		Calendar displayDate = EntryUtil.getRandomCalendar(
			_displayDateFrom, _displayDateTo);

		Calendar expirationDate = EntryUtil.getRandomCalendar(
			_expirationDateFrom, _expirationDateTo);

		while (expirationDate.before(Calendar.getInstance())) {
			expirationDate.add(Calendar.YEAR, 1);
		}

		Calendar reviewDate = EntryUtil.getRandomCalendar(
			_reviewDateFrom, _reviewDateTo);

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("folderId", article.getFolderId());
		args.setParameter("classNameId", (long)0);
		args.setParameter("classPK", (long)0);
		args.setParameter("layoutUuid", null);
		args.setParameter("articleURL", _articleURL);
		args.setParameter("articleId", article.getArticleId());
		args.setParameter("version", latestArticle.getVersion());

		args.setParameter(
			"titleMap", LocalizationUtil.getLocalizationMap(title));

		args.setParameter(
			"descriptionMap", LocalizationUtil.getLocalizationMap(description));

		args.setParameter("LocalizedContent", content);
		args.setParameter("type", JournalArticleConstants.TYPES[typeIndex]);
		args.setParameter("ddmStructureKey", article.getStructureId());
		args.setParameter("ddmTemplateKey", article.getTemplateId());
		args.setParameter("displayDateMonth", displayDate.get(Calendar.MONTH));
		args.setParameter("displayDateDay", displayDate.get(Calendar.DATE));
		args.setParameter("displayDateYear", displayDate.get(Calendar.YEAR));
		args.setParameter("displayDateHour", displayDate.get(Calendar.HOUR));
		args.setParameter(
			"displayDateMinute", displayDate.get(Calendar.MINUTE));

		args.setParameter(
			"expirationDateMonth", expirationDate.get(Calendar.MONTH));

		args.setParameter(
			"expirationDateDay", expirationDate.get(Calendar.DATE));

		args.setParameter(
			"expirationDateYear", expirationDate.get(Calendar.YEAR));

		args.setParameter(
			"expirationDateHour", expirationDate.get(Calendar.HOUR));

		args.setParameter(
			"expirationDateMinute", expirationDate.get(Calendar.MINUTE));

		args.setParameter("neverExpire", EntryUtil.nextBoolean());
		args.setParameter("reviewDateMonth", reviewDate.get(Calendar.MONTH));
		args.setParameter("reviewDateDay", reviewDate.get(Calendar.DATE));
		args.setParameter("reviewDateYear", reviewDate.get(Calendar.YEAR));
		args.setParameter("reviewDateHour", reviewDate.get(Calendar.HOUR));
		args.setParameter("reviewDateMinute", reviewDate.get(Calendar.MINUTE));
		args.setParameter("neverReview", EntryUtil.nextBoolean());

		return args;
	}

	private String _articleURL;
	private Calendar _displayDateFrom;
	private Calendar _displayDateTo;
	private Calendar _expirationDateFrom;
	private Calendar _expirationDateTo;
	private Calendar _reviewDateFrom;
	private Calendar _reviewDateTo;
	private List<JournalStructure> _structures;

}