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

package com.liferay.tool.datamanipulator.handler.content;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.blogs.kernel.service.BlogsEntryLocalServiceUtil;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liferay.tool.datamanipulator.annotation.Handler;
import com.liferay.tool.datamanipulator.annotation.HandlerType;
import com.liferay.tool.datamanipulator.context.RequestContext;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.handler.BaseHandler;

/**
 * @author Yg0R2
 */
@Handler(type = HandlerType.CONTENT, displayName = "Blogs")
public class BlogEntryHandler extends BaseHandler {

	public BlogEntryHandler() throws Exception {
		super("Blog Entry", "blog-entry");
	}

	@Override
	public DisplayFields getDisplayFields(long groupId, long companyId) throws Exception {
		DisplayFields displayFields = new DisplayFields();

		displayFields.addUserMultiSelect(FieldKeys.MULTI_SELECT_USER_LIST);

		displayFields.addInfo(
			getDisplayFieldName(FieldKeys.MULTI_SELECT_USER_LIST));

		displayFields.addSeparator("");

		displayFields.addLabel(getDisplayFieldName());

		displayFields.addCount(
			getDisplayFieldName(FieldKeys.INPUT_COUNT), true);

		displayFields.addUpdateCount(
			getDisplayFieldName(FieldKeys.INPUT_UPDATE_COUNT));

		return displayFields;
	}

	@Override
	protected Class<?>[] getAddArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, String.class, String.class, String.class, Integer.TYPE,
			Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
			Boolean.TYPE, Boolean.TYPE,
			Array.newInstance(String.class, 0).getClass(), Boolean.TYPE,
			String.class, String.class, InputStream.class, ServiceContext.class
		};
	}

	@Override
	protected String[] getAddArgNames() {
		return new String[] {
			"userId", "title", "description", "content", "displayDateMonth",
			"displayDateDay", "displayDateYear", "displayDateHour",
			"displayDateMinute", "allowPingbacks", "allowTrackbacks",
			"trackbacks", "smallImage", "smallImageURL", "smallImageFileName",
			"smallImageInputStream", "serviceContext"
		};
	}

	@Override
	protected Class<?> getAddClass() throws ClassNotFoundException {
		return BlogsEntryLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getAddEntrySpecifiedArgs(
		RequestContext requestContext) throws Exception {

		StringBuilder urlTitleSB = new StringBuilder(4);
		urlTitleSB.append("blog-entry");
		urlTitleSB.append(requestContext.get("entryCount"));
		urlTitleSB.append("-title-");
		urlTitleSB.append(requestContext.getRandomString());

		Map<String, Object> args = new HashMap<String, Object>(1);

		args.put("urlTitle", urlTitleSB.toString());

		return args;
	}

	@Override
	protected String getAddMethodName() {
		return "addEntry";
	}

	@Override
	protected List<String> getChildHandlerNames() {
		return new ArrayList<String>(0);
	}

	@Override
	protected String getClassPKName() {
		return "entryId";
	}

	@Override
	protected String getParentClassPKName() {
		return null;
	}

	@Override
	protected Class<?>[] getUpdateArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, Long.TYPE, String.class, String.class, String.class,
			Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
			Integer.TYPE, Boolean.TYPE, Boolean.TYPE,
			Array.newInstance(String.class, 0).getClass(), Boolean.TYPE,
			String.class, String.class, InputStream.class, ServiceContext.class
		};
	}

	@Override
	protected String[] getUpdateArgNames() {
		return new String[] {
			"userId", "entryId", "title", "description", "content",
			"displayDateMonth", "displayDateDay", "displayDateYear",
			"displayDateHour", "displayDateMinute", "allowPingbacks",
			"allowTrackbacks", "trackbacks", "smallImage", "smallImageURL",
			"smallImageFileName", "smallImageInputStream", "serviceContext"
		};
	}

	@Override
	protected Map<String, Object> getUpdateEntrySpecifiedArgs(
		Object entry, RequestContext requestContext) throws Exception {

		StringBuilder urlTitleSB = new StringBuilder(6);
		urlTitleSB.append("blog-entry");
		urlTitleSB.append(requestContext.get("entryCount"));
		urlTitleSB.append("-title-edited-");
		urlTitleSB.append(requestContext.get("editCount"));
		urlTitleSB.append("-times-");
		urlTitleSB.append(requestContext.get("rndString"));

		Map<String, Object> args = new HashMap<String, Object>(1);

		args.put("urlTitle", urlTitleSB.toString());

		return args;
	}

	@Override
	protected Class<?> getUpdateClass() throws ClassNotFoundException {
		return BlogsEntryLocalServiceUtil.class;
	}

	@Override
	protected String getUpdateMethodName() {
		return "updateEntry";
	}

}