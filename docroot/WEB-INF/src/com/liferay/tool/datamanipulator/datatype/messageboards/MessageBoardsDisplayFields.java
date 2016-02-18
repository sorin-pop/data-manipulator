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

package com.liferay.tool.datamanipulator.datatype.messageboards;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBCategoryConstants;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
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
public class MessageBoardsDisplayFields {

	public static final String MESSAGE_BOARDS_CATEGORY_ID =
		"message-boards-category-id";

	public static final String MESSAGE_BOARDS_CATEGORY_LIST =
		"message-boards-category-list";

	public static List<Field> getDisplayFields(long groupId)
		throws SystemException {

		List<MBCategory> mbCategoryList =
			MBCategoryLocalServiceUtil.getCategories(
				groupId, MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		List<KeyValuePair> mbCategoryNameId = new ArrayList<KeyValuePair>(
			mbCategoryList.size() + 1);

		mbCategoryNameId.add(
			new KeyValuePair(
				EntryTypeKeys.GENERAL_MESSAGE_BOARDS + StringPool.DASH +
					FieldKeys.DEFAULT_PARENT,
				String.valueOf(
					MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID)));

		for (MBCategory category : mbCategoryList) {
			mbCategoryNameId.add(
				new KeyValuePair(
					category.getName(),
					String.valueOf(category.getCategoryId())));
		}

		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addSelectList(MESSAGE_BOARDS_CATEGORY_LIST, mbCategoryNameId);
		fields.addInput(MESSAGE_BOARDS_CATEGORY_ID);
		fields.addInfo(EntryTypeKeys.GENERAL_MESSAGE_BOARDS + "-add-to-exits");
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);
		fields.addDepth(EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);
		fields.addSubCount(EntryTypeKeys.GENERAL_MESSAGE_BOARDS_CATEGORY);
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_MESSAGE_BOARDS_THREAD);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_MESSAGE_BOARDS_THREAD);
		fields.addToParent(EntryTypeKeys.GENERAL_MESSAGE_BOARDS_THREAD);
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);
		fields.addToParent(EntryTypeKeys.GENERAL_MESSAGE_BOARDS_MESSAGE);
		fields.addSeparator();

		return fields.getDisplayFields();
	}

}