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

package com.liferay.tool.datamanipulator.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liferay.tool.datamanipulator.context.RequestContext;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.thread.HandlerThread;
import com.liferay.tool.datamanipulator.util.RandomUtil;

/**
 * @author Yg0R2
 */
public abstract class BaseCustomHandler extends BaseHandler {

	public BaseCustomHandler(String entryName, String displayFieldName)
		throws Exception {

		super(entryName, displayFieldName);
	}

	@Override
	protected Class<?>[] getAddArgClazzs() {
		return new Class<?>[0];
	}

	@Override
	protected String[] getAddArgNames() {
		return new String[0];
	}

	@Override
	protected Class<?> getAddClass() throws ClassNotFoundException {
		return null;
	}

	@Override
	protected Map<String, Object> getAddEntrySpecifiedArgs(
		RequestContext requestContext) throws Exception {
		return new HashMap<String, Object>(0);
	}

	@Override
	protected String getAddMethodName() {
		return null;
	}

	@Override
	protected List<String> getChildHandlerNames() {
		return new ArrayList<String>(0);
	}

	@Override
	protected String getClassPKName() {
		return null;
	}

	@Override
	protected String getParentClassPKName() {
		return null;
	}

	@Override
	protected Class<?>[] getUpdateArgClazzs() {
		return new Class<?>[0];
	}

	@Override
	protected String[] getUpdateArgNames() {
		return new String[0];
	}

	@Override
	protected Class<?> getUpdateClass() throws ClassNotFoundException {
		return null;
	}

	@Override
	protected Map<String, Object> getUpdateEntrySpecifiedArgs(
		Object entry, RequestContext requestContext) throws Exception {

		return new HashMap<String, Object>(0);
	}

	@Override
	protected String getUpdateMethodName() {
		return null;
	}

	@Override
	public abstract void proceed(RequestContext requestContext)
		throws Exception;

	protected void generate(
			BaseHandler handler, RequestContext requestContext,
			List<Long> userIds)
		throws Exception {

		ThreadGroup threadGroup = new ThreadGroup(
			handler.getDisplayFieldName() + RandomUtil.nextString());

		for (long userId : userIds) {
			requestContext.setUserId(userId);

			HandlerThread handlerThread = new HandlerThread(
				threadGroup, handler, requestContext);

			handlerThread.start();
		}

		while(threadGroup.activeCount() > 0) {
		}
	}

	protected void setParentLevel(
		BaseHandler handler, RequestContext requestContext, int counts) {

		requestContext.set(
			handler.getDisplayFieldName(FieldKeys.INPUT_COUNT), counts);

		requestContext.set(
			handler.getDisplayFieldName(FieldKeys.INPUT_UPDATE_COUNT), counts);

		requestContext.set(
			handler.getDisplayFieldName(FieldKeys.INPUT_DEPTH), counts);

		requestContext.set(
			handler.getDisplayFieldName(FieldKeys.INPUT_SUBCOUNT), counts);
	}

	protected void setChildLevel(
		BaseHandler handler, RequestContext requestContext, int counts) {

		requestContext.set(
			handler.getDisplayFieldName(FieldKeys.INPUT_COUNT), counts);

		requestContext.set(
			handler.getDisplayFieldName(FieldKeys.INPUT_UPDATE_COUNT), counts);
	}

}