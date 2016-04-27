/**
 * Copyright (c) 2014-present Yg0R2. All rights reserved.
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

package com.liferay.tool.datamanipulator.handler.custom;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.liferay.tool.datamanipulator.annotation.Handler;
import com.liferay.tool.datamanipulator.annotation.HandlerType;
import com.liferay.tool.datamanipulator.context.RequestContext;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.handler.BaseCustomHandler;
import com.liferay.tool.datamanipulator.handler.content.BlogEntryHandler;
import com.liferay.tool.datamanipulator.handler.content.BookmarkEntryHandler;
import com.liferay.tool.datamanipulator.handler.content.BookmarkFolderHandler;
import com.liferay.tool.datamanipulator.handler.content.DMDocumentHandler;
import com.liferay.tool.datamanipulator.handler.content.DMFolderHandler;
import com.liferay.tool.datamanipulator.handler.content.JournalArticleHandler;
import com.liferay.tool.datamanipulator.handler.content.LayoutHandler;
import com.liferay.tool.datamanipulator.handler.content.MBCategoryHandler;
import com.liferay.tool.datamanipulator.handler.content.MBMessageHandled;
import com.liferay.tool.datamanipulator.handler.content.MBThreadHandler;
import com.liferay.tool.datamanipulator.handler.content.WikiNodeHandler;
import com.liferay.tool.datamanipulator.handler.content.WikiPageHandler;
import com.liferay.tool.datamanipulator.handler.portal.SiteHandler;
import com.liferay.tool.datamanipulator.handler.portal.UserHandler;

/**
 * @author Yg0R2
 */
@Handler(type = HandlerType.CUSTOM, displayName = "Example Handler")
public class ExampleCustomHandler extends BaseCustomHandler {

	public ExampleCustomHandler() throws Exception {
		super("Example", "example");
	}

	@Override
	public DisplayFields getDisplayFields(long groupId, long companyId) throws Exception {
		DisplayFields displayFields = new DisplayFields();

		displayFields.addInfo(getDisplayFieldName(FieldKeys.INFO));

		return displayFields;
	}

	@Override
	public void proceed(RequestContext requestContext) throws Exception {
		// Create one Site

		SiteHandler siteHandler = new SiteHandler();

		Group site = (Group) siteHandler.addEntry(requestContext.clone());

		requestContext.setGroupId(site.getGroupId());
		requestContext.setGroupIds(new long[] {site.getGroupId()});

		// Create 5 users

		UserHandler userHandler = new UserHandler();

		List<Long> userIds = new ArrayList<Long>(5);

		for (int i = 0; i < 5; i++) {
			RequestContext userRequestContext = requestContext.clone();
			userRequestContext.set("entryCount", String.valueOf(i + 1));

			User user = (User) userHandler.addEntry(userRequestContext);

			userIds.add(user.getUserId());
		}

		// Each user will create:

		createBlogs(requestContext.clone(), userIds);

		createBookmarks(requestContext.clone(), userIds);

		createDocumentsAndMedia(requestContext.clone(), userIds);

		createJournal(requestContext, userIds);

		createLayout(requestContext, userIds);

		createMessageBoards(requestContext, userIds);

		createWiki(requestContext, userIds);
	}

	/**
	 * Create 15 Blog Entries and edit them 15 times
	 */
	protected void createBlogs(
		RequestContext requestContext, List<Long> userIds) throws Exception {

		BlogEntryHandler blogEntryHandler = new BlogEntryHandler();

		setChildLevel(blogEntryHandler, requestContext, 15);

		generate(blogEntryHandler, requestContext, userIds);
	}

	/**
	 * Create 2 Bookmark Folders, 2 Sub Folders for 2 level deep in each and
	 * edit them 2 times.<br>
	 * Create 3 Bookmark Entry into all Folders and edit them 3 times.
	 */
	protected void createBookmarks(
		RequestContext requestContext, List<Long> userIds) throws Exception {

		BookmarkFolderHandler bookmarkFolderHandler =
			new BookmarkFolderHandler();

		setParentLevel(bookmarkFolderHandler, requestContext, 2);

		BookmarkEntryHandler bookmarkEntryHandler = new BookmarkEntryHandler();

		setChildLevel(bookmarkEntryHandler, requestContext, 3);

		requestContext.set(
			bookmarkEntryHandler.getDisplayFieldName(BookmarkEntryHandler.URL),
			"http://www.liferay.com");

		generate(bookmarkFolderHandler, requestContext, userIds);
	}

	/**
	 * Create 2 DM Folders, 2 Sub Folders for 2 level deep in each and edit them
	 * 2 times.<br>
	 * Create 3 DM Documents into all Folders and edit them 3 times.
	 */
	protected void createDocumentsAndMedia(
		RequestContext requestContext, List<Long> userIds) throws Exception {

		File uploadFile = File.createTempFile("dm-document", ".txt");

		FileWriter fileWriter = new FileWriter(uploadFile.getAbsoluteFile());

		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		bufferedWriter.write("Sample text content.");

		bufferedWriter.close();

		DMFolderHandler dmFolderHandler = new DMFolderHandler();

		setParentLevel(dmFolderHandler, requestContext, 2);

		DMDocumentHandler dmDocumentHandler = new DMDocumentHandler();

		setChildLevel(dmDocumentHandler, requestContext, 3);

		requestContext.set(
			dmDocumentHandler.getDisplayFieldName(
				DMDocumentHandler.UPLOAD_FILE), uploadFile);

		generate(dmFolderHandler, requestContext, userIds);
	}

	/**
	 * Create 15 Journal Article and edit them 15 times
	 */
	protected void createJournal(
		RequestContext requestContext, List<Long> userIds) throws Exception {

		JournalArticleHandler journalArticleHandler =
			new JournalArticleHandler();

		setChildLevel(journalArticleHandler, requestContext, 15);

		generate(journalArticleHandler, requestContext, userIds);
	}

	/**
	 * Create 2 Layouts, 2 Sub Layouts for 2 level deep in each and edit them
	 * 2 times.
	 */
	protected void createLayout(
		RequestContext requestContext, List<Long> userIds) throws Exception {

		LayoutHandler layoutHandler = new LayoutHandler();

		setParentLevel(layoutHandler, requestContext, 2);

		generate(layoutHandler, requestContext, userIds);
	}

	/**
	 * Create 2 MB Category, 2 Sub MB Category for 2 level deep in each and edit
	 * them 2 times.<br>
	 * Create 3 MB Thread into all Category and edit them 3 times.<br>
	 * Create 4 MB Message in each Thread and edit them 4 times.
	 */
	protected void createMessageBoards(
		RequestContext requestContext, List<Long> userIds) throws Exception {

		MBCategoryHandler mbCategoryHandler = new MBCategoryHandler();

		setParentLevel(mbCategoryHandler, requestContext, 2);

		MBThreadHandler mbThreadHandler = new MBThreadHandler();

		setChildLevel(mbThreadHandler, requestContext, 3);

		MBMessageHandled mbMessageHandled = new MBMessageHandled();

		setChildLevel(mbMessageHandled, requestContext, 4);

		generate(mbCategoryHandler, requestContext, userIds);
	}

	/**
	 * Create 2 Wiki Nodes and edit them 2 times.<br>
	 * Create 3 Wiki Pages into all Nodes and edit them 3 times.
	 */
	protected void createWiki(
		RequestContext requestContext, List<Long> userIds) throws Exception {

		WikiNodeHandler wikiNodeHandler = new WikiNodeHandler();

		setChildLevel(wikiNodeHandler, requestContext, 2);

		WikiPageHandler wikiPageHandler = new WikiPageHandler();

		setChildLevel(wikiPageHandler, requestContext, 3);

		generate(wikiNodeHandler, requestContext, userIds);
	}

}