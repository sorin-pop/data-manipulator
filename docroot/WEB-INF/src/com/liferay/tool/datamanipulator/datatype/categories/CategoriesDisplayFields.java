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
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.asset.model.AssetCategoryConstants;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class CategoriesDisplayFields {

	public static final String ASSET_VOCABULARIES_LIST = "vocabularies-list";

	public static final String ASSET_VOCABULARY_ID = "vocabulary-id";

	public static List<Field> getDisplayFields(long groupId)
		throws PortalException, SystemException {

		List<AssetVocabulary> vocabularyList =
			AssetVocabularyLocalServiceUtil.getGroupVocabularies(groupId);

		List<KeyValuePair> vocabulariesNameId = new ArrayList<KeyValuePair>(
			vocabularyList.size() + 1);

		vocabulariesNameId.add(
			new KeyValuePair(
				EntryTypeKeys.GENERAL_ASSET_CATEGORY + StringPool.DASH +
					FieldKeys.DEFAULT_PARENT,
				String.valueOf(
					AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID)));

		for (AssetVocabulary vocabulary : vocabularyList) {
			vocabulariesNameId.add(
				new KeyValuePair(
					vocabulary.getName(),
					String.valueOf(vocabulary.getVocabularyId())));
		}

		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_ASSET_VOCABULARY);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_ASSET_VOCABULARY);
		fields.addInfo(EntryTypeKeys.GENERAL_ASSET_VOCABULARY + "-add-to-new");
		fields.addSeparator();

		fields.addSelectList(ASSET_VOCABULARIES_LIST, vocabulariesNameId);
		fields.addInput(ASSET_VOCABULARY_ID);
		fields.addInfo(
			EntryTypeKeys.GENERAL_ASSET_VOCABULARY + "-add-to-exist");

		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_ASSET_CATEGORY);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_ASSET_CATEGORY);
		fields.addDepth(EntryTypeKeys.GENERAL_ASSET_CATEGORY);
		fields.addSubCount(EntryTypeKeys.GENERAL_ASSET_CATEGORY);
		fields.addHidden(
			EntryTypeKeys.GENERAL_ASSET_CATEGORY + StringPool.DASH +
				FieldKeys.ADD_TO_PARENT, FieldKeys.ADD_TO_ALL_PARENT);

		fields.addSeparator();

		return fields.getDisplayFields();
	}

}