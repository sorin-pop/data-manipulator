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

package com.liferay.tool.datamanipulator.datatype.categories;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryConstants;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
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
public class AssetCategoryHandler extends AbstractEntryHandler
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
	public AssetCategoryHandler(
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

		long parentCategoryId =
			AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID;

		long parentVocabularyId = parentId;

		AssetCategory parentCategory =
			AssetCategoryLocalServiceUtil.fetchCategory(parentId);

		if (parentCategory != null) {
			parentCategoryId = parentCategory.getCategoryId();
			parentVocabularyId = parentCategory.getVocabularyId();
		}

		String title = "Test Category" + postString + " Title";
		String description = "Test Vocabulary" + postString + " Description";

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("parentCategoryId", parentCategoryId);
		args.setParameter(
			"titleMap", LocalizationUtil.getLocalizationMap(title));

		args.setParameter(
			"descriptionMap", LocalizationUtil.getLocalizationMap(description));

		args.setParameter("vocabularyId", parentVocabularyId);
		args.setParameter("categoryProperties", new String[0]);

		return args;
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getDataManipulatorFromObject(java.lang.Object)
	 */

	@Override
	public DataManipulator getDataManipulatorFromObject(Object createdEntry)
		throws PortalException, SystemException {

		return DataManipulatorLocalServiceUtil.addDataManipulator(
			((AssetCategory)createdEntry).getGroupId(),
			AssetCategory.class.getName(),
			((AssetCategory)createdEntry).getCategoryId());
	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel#getUpdateEntryArgs(long, java.lang.String, com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public EntryArgs getUpdateEntryArgs(
			long entryId, String postString, RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		AssetCategory category = AssetCategoryLocalServiceUtil.getCategory(
			entryId);

		String title = EntryUtil.getEditString(
			category.getTitle(LocaleUtil.getDefault()), postString);

		String description = EntryUtil.getEditString(
			category.getDescription(LocaleUtil.getDefault()), postString);

		EntryArgs args = new EntryArgs(requestProcessor);

		args.setParameter("categoryId", entryId);
		args.setParameter("parentCategoryId", category.getParentCategoryId());
		args.setParameter(
			"titleMap", LocalizationUtil.getLocalizationMap(title));

		args.setParameter(
			"descriptionMap", LocalizationUtil.getLocalizationMap(description));

		args.setParameter("vocabularyId", category.getVocabularyId());
		args.setParameter("categoryProperties", new String[0]);

		return args;
	}

}