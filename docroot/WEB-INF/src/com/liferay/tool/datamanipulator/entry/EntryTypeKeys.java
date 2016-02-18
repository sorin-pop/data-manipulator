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

package com.liferay.tool.datamanipulator.entry;

import java.util.ArrayList;

/**
 * @author Tibor Kovács
 *
 */
public class EntryTypeKeys {
	//Lists and base names

	public static final String ENTRY_TYPE_CUSTOM = "custom-entry-type";

	public static final ArrayList<String> ENTRY_TYPE_CUSTOM_LIST =
		_customEntryTypes();

	public static final String ENTRY_TYPE_GENERAL = "general-entry-type";

	public static final ArrayList<String> ENTRY_TYPE_GENERAL_LIST =
		_generalEntryTypes();

	public static final String ENTRY_TYPE_PLUGIN = "plugin-entry-type";

	public static final ArrayList<String> ENTRY_TYPE_PLUGIN_LIST =
		_pluginEntryTypes();

	public static final String ENTRY_TYPE_PORTAL = "portal-entry-type";

	public static final ArrayList<String> ENTRY_TYPE_PORTAL_LIST =
		_portalEntryTypes();

	// General Entry types

	public static final String GENERAL_ASSET_CATEGORIES = "categories";

	public static final String GENERAL_ASSET_CATEGORY = "category";

	public static final String GENERAL_ASSET_VOCABULARY = "vocabulary";

	public static final String GENERAL_BLOGS = "blogs";

	public static final String GENERAL_BLOGS_ENTRY = "blogs-entry";

	public static final String GENERAL_BOOKMARKS = "bookmarks";

	public static final String GENERAL_BOOKMARKS_ENTRY = "bookmarks-entry";

	public static final String GENERAL_BOOKMARKS_FOLDER = "bookmarks-folder";

	public static final String GENERAL_CALENDAR = "calendar";

	public static final String GENERAL_CALENDAR_EVENT = "calendar-event";

	public static final String GENERAL_DOCUMENTS_AND_MEDIA =
		"documents-and-media";

	public static final String GENERAL_DOCUMENTS_AND_MEDIA_FILE =
		"documents-and-media-file";

	public static final String GENERAL_DOCUMENTS_AND_MEDIA_FOLDER =
		"documents-and-media-folder";

	public static final String GENERAL_JOURNAL = "journal";

	public static final String GENERAL_JOURNAL_ARTICLE = "journal-article";

	public static final String GENERAL_JOURNAL_FOLDER = "journal-folder";

	public static final String GENERAL_JOURNAL_STRUCTURE = "journal-structure";

	public static final String GENERAL_JOURNAL_TEMPLATE = "journal-template";

	public static final String GENERAL_LAYOUT = "layout";

	public static final String GENERAL_LAYOUT_ENTRY = "layout-entry";

	public static final String GENERAL_LAYOUT_PORTLET = "layout-portlet";

	public static final String GENERAL_MESSAGE_BOARDS = "message-boards";

	public static final String GENERAL_MESSAGE_BOARDS_CATEGORY =
		"message-boards-category";

	public static final String GENERAL_MESSAGE_BOARDS_MESSAGE =
		"message-boards-message";

	public static final String GENERAL_MESSAGE_BOARDS_THREAD =
		"message-boards-thread";

	public static final String GENERAL_WIKI = "wiki";

	public static final String GENERAL_WIKI_NODE = "wiki-node";

	public static final String GENERAL_WIKI_PAGE = "wiki-page";

	//Plugin Entry types

	//Poral Entry types

	public static final String PORTAL_ORGANIZATION = "organization";

	public static final String PORTAL_ROLE = "role";

	public static final String PORTAL_SITE = "site";

	public static final String PORTAL_TEAM = "team";

	public static final String PORTAL_USER = "user";

	public static final String PORTAL_USERGROUP = "usergroup";

	//Private methods for declare the DataTypes location

	private static ArrayList<String> _customEntryTypes() {
		ArrayList<String> types = new ArrayList<String>();

		return types;
	}

	private static ArrayList<String> _generalEntryTypes() {
		ArrayList<String> types = new ArrayList<String>();

		types.add(GENERAL_BLOGS);
		types.add(GENERAL_BOOKMARKS);
		types.add(GENERAL_CALENDAR);
		types.add(GENERAL_ASSET_CATEGORIES);
		types.add(GENERAL_DOCUMENTS_AND_MEDIA);
		types.add(GENERAL_JOURNAL);
		types.add(GENERAL_LAYOUT);
		types.add(GENERAL_MESSAGE_BOARDS);
		types.add(GENERAL_WIKI);

		return types;
	}

	private static ArrayList<String> _pluginEntryTypes() {
		ArrayList<String> types = new ArrayList<String>();

		return types;
	}

	private static ArrayList<String> _portalEntryTypes() {
		ArrayList<String> types = new ArrayList<String>();

		types.add(PORTAL_ORGANIZATION);
		types.add(PORTAL_SITE);
		types.add(PORTAL_TEAM);
		types.add(PORTAL_USER);

		return types;
	}

}