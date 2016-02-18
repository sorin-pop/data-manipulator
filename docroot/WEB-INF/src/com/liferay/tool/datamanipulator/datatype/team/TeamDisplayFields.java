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

package com.liferay.tool.datamanipulator.datatype.team;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class TeamDisplayFields {

	public static List<Field> getDisplayFields() throws SystemException {
		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addOrganizationMultiSelect();
		fields.addSeparator();

		fields.addSiteMultiSelect();
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.PORTAL_TEAM);
		fields.addUpdateLevel(EntryTypeKeys.PORTAL_TEAM);
		fields.addSeparator();

		return fields.getDisplayFields();
	}

}