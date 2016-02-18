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

package com.liferay.tool.datamanipulator.datatype.site;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.model.GroupConstants;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class SiteDisplayFields {

	public static List<Field> getDisplayFields() throws SystemException {
		List<KeyValuePair> siteGroupTypes = new ArrayList<KeyValuePair>(4);
		siteGroupTypes.add(
			new KeyValuePair(
				GroupConstants.TYPE_SITE_OPEN_LABEL,
				String.valueOf(GroupConstants.TYPE_SITE_OPEN)));

		siteGroupTypes.add(
			new KeyValuePair(
				GroupConstants.TYPE_SITE_PRIVATE_LABEL,
				String.valueOf(GroupConstants.TYPE_SITE_PRIVATE)));

		siteGroupTypes.add(
			new KeyValuePair(
				GroupConstants.TYPE_SITE_RESTRICTED_LABEL,
				String.valueOf(GroupConstants.TYPE_SITE_RESTRICTED)));

		siteGroupTypes.add(
			new KeyValuePair(
				GroupConstants.TYPE_SITE_SYSTEM_LABEL,
				String.valueOf(GroupConstants.TYPE_SITE_SYSTEM)));

		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addSelectList("site-type", siteGroupTypes);
		fields.addCheckbox("active-site", true);
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.PORTAL_SITE);
		fields.addUpdateLevel(EntryTypeKeys.PORTAL_SITE);
		fields.addDepth(EntryTypeKeys.PORTAL_SITE);
		fields.addSubCount(EntryTypeKeys.PORTAL_SITE);
		fields.addSeparator();

		return fields.getDisplayFields();
	}

}