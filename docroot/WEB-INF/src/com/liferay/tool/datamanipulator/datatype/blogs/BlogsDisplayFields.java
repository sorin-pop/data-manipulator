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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class BlogsDisplayFields {

	public static List<Field> getDisplayFields() throws SystemException {
		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_BLOGS_ENTRY, true);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_BLOGS_ENTRY);
		fields.addSeparator();

		fields.addLabel("display-date");
		fields.addDate("display-date");
		fields.addSeparator();

		return fields.getDisplayFields();
	}

}