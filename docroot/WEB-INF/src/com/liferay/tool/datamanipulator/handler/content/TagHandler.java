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
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;

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
@Handler(type = HandlerType.CONTENT, displayName = "Tags")
public class TagHandler extends BaseHandler {

	public TagHandler() throws Exception {
		super("Tag", "tag");
	}

	@Override
	public DisplayFields getDisplayFields(long groupId, long companyId) throws Exception {
		DisplayFields displayFields = new DisplayFields();
		
		displayFields.addInfo(
			getDisplayFieldName(FieldKeys.MULTI_SELECT_USER_LIST));

		displayFields.addUserMultiSelect(FieldKeys.MULTI_SELECT_USER_LIST);

		displayFields.addSeparator("");

		//displayFields.addLabel(getDisplayFieldName());

		displayFields.addCount(
			getDisplayFieldName(FieldKeys.INPUT_COUNT), true);

		displayFields.addUpdateCount(
			getDisplayFieldName(FieldKeys.INPUT_UPDATE_COUNT));

		return displayFields;
	}

	@Override
	protected Class<?>[] getAddArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, Long.TYPE, String.class, ServiceContext.class
		};
	}

	@Override
	protected String[] getAddArgNames() {
		return new String[] {
			"userId", "groupId", "name", "serviceContext"
		};
	}

	@Override
	protected Class<?> getAddClass() throws ClassNotFoundException {
		return AssetTagLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getAddEntrySpecifiedArgs(
		RequestContext requestContext) throws Exception {

		return new HashMap<String, Object>(0);
	}

	@Override
	protected String getAddMethodName() {
		return "addTag";
	}

	@Override
	protected List<String> getChildHandlerNames() {
		return new ArrayList<String>(0);
	}

	@Override
	protected String getClassPKName() {
		return "tagId";
	}

	@Override
	protected String getParentClassPKName() {
		return null;
	}

	@Override
	protected Class<?>[] getUpdateArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, Long.TYPE, String.class,
			Array.newInstance(String.class, 0).getClass(), ServiceContext.class
		};
	}

	@Override
	protected String[] getUpdateArgNames() {
		return new String[] {
			"userId", "tagId", "name", "tagProperties", "serviceContext"
		};
	}

	@Override
	protected Class<?> getUpdateClass() throws ClassNotFoundException {
		return AssetTagLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getUpdateEntrySpecifiedArgs(
		Object entry, RequestContext requestContext) throws Exception {

		return new HashMap<String, Object>(0);
	}

	@Override
	protected String getUpdateMethodName() {
		return "updateTag";
	}

}