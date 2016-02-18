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

package com.liferay.tool.datamanipulator.datatype;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.tool.datamanipulator.datatype.blogs.BlogsDisplayFields;
import com.liferay.tool.datamanipulator.datatype.bookmarks.BookmarksDisplayFields;
import com.liferay.tool.datamanipulator.datatype.calendar.CalendarDisplayFields;
import com.liferay.tool.datamanipulator.datatype.categories.CategoriesDisplayFields;
import com.liferay.tool.datamanipulator.datatype.documentsandmedia.DocumentsAndMediaDisplayFields;
import com.liferay.tool.datamanipulator.datatype.journal.JournalDisplayFields;
import com.liferay.tool.datamanipulator.datatype.layout.LayoutDisplayFields;
import com.liferay.tool.datamanipulator.datatype.messageboards.MessageBoardsDisplayFields;
import com.liferay.tool.datamanipulator.datatype.organization.OrganizationDisplayFields;
import com.liferay.tool.datamanipulator.datatype.site.SiteDisplayFields;
import com.liferay.tool.datamanipulator.datatype.team.TeamDisplayFields;
import com.liferay.tool.datamanipulator.datatype.user.UserDisplayFields;
import com.liferay.tool.datamanipulator.datatype.wiki.WikiDisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public final class EntryDisplayFieldsFactory {

	public static List<Field> getDisplayFields(
			String entryTypeKey, long groupId)
		throws PortalException, SystemException {

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_ASSET_CATEGORIES)) {
			return CategoriesDisplayFields.getDisplayFields(groupId);
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_BLOGS)) {
			return BlogsDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_BOOKMARKS)) {
			return BookmarksDisplayFields.getDisplayFields(groupId);
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_CALENDAR)) {
			return CalendarDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_DOCUMENTS_AND_MEDIA)) {
			return DocumentsAndMediaDisplayFields.getDisplayFields(groupId);
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_JOURNAL)) {
			return JournalDisplayFields.getDisplayFields(groupId);
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_LAYOUT)) {
			return LayoutDisplayFields.getDisplayFields(groupId);
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_MESSAGE_BOARDS)) {
			return MessageBoardsDisplayFields.getDisplayFields(groupId);
		}

		if (entryTypeKey.equals(EntryTypeKeys.GENERAL_WIKI)) {
			return WikiDisplayFields.getDisplayFields(groupId);
		}

		if (entryTypeKey.equals(EntryTypeKeys.PORTAL_ORGANIZATION)) {
			return OrganizationDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.PORTAL_SITE)) {
			return SiteDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.PORTAL_TEAM)) {
			return TeamDisplayFields.getDisplayFields();
		}

		if (entryTypeKey.equals(EntryTypeKeys.PORTAL_USER)) {
			return UserDisplayFields.getDisplayFields();
		}

		return null;
	}

}