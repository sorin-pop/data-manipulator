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

package com.liferay.tool.datamanipulator.context;

import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.io.File;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpSession;

import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.util.RandomUtil;

/**
 * @author Yg0R2
 */
public class RequestContext {

	public static final String RANDOM_STRING = "rnd-string";

	public RequestContext(UploadPortletRequest uploadRequest) {
		_uploadRequest = uploadRequest;

		_themeDisplay =(ThemeDisplay)_uploadRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		_parameters = new HashMap<String, Object>();

		_session = _uploadRequest.getSession(true);;
	}

	public boolean contains(String key) {
		if (_parameters.containsKey(key)) {
			return true;
		}

		Object value = ParamUtil.get(_uploadRequest, key, "not-contains");

		if (!value.equals("not-contains")) {
			return true;
		}

		return false;
	}

	public Object get(String key) {
		if (_parameters.containsKey(key)) {
			return _parameters.get(key);
		}

		Object value = ParamUtil.get(_uploadRequest, key, "not-contains");

		if (!value.equals("not-contains")) {
			return value;
		}

		return null;
	}

	public boolean getBoolean(String key) {
		if (_parameters.containsKey(key)) {
			return (Boolean)_parameters.get(key);
		}

		return ParamUtil.getBoolean(_uploadRequest, key, false);
	}

	public Calendar getBetweenCalendar(String key) {
		Calendar calendarFrom = getCalendar(key + "-from");
		Calendar calendarTo = getCalendar(key + "-to");

		return RandomUtil.nextCalendar(calendarFrom, calendarTo);
	}

	public Calendar getCalendar(String key) {
		Calendar cal = Calendar.getInstance();

		cal.set(
			Calendar.YEAR, ParamUtil.getInteger(_uploadRequest, key + "Year"));

		cal.set(
			Calendar.MONTH,
			ParamUtil.getInteger(_uploadRequest, key + "Month"));

		cal.set(
			Calendar.DATE, ParamUtil.getInteger(_uploadRequest, key + "Day"));

		cal.set(
			Calendar.HOUR, ParamUtil.getInteger(_uploadRequest, key + "Hour"));

		cal.set(
			Calendar.MINUTE,
			ParamUtil.getInteger(_uploadRequest, key + "Minute"));

		cal.set(
			Calendar.AM_PM, ParamUtil.getInteger(_uploadRequest, key + "AmPm"));

		return cal;
	}

	public long getCompanyId() {
		return _themeDisplay.getCompanyId();
	}

	public double getDouble(String key) {
		return getDouble(key, (double)0);
	}

	public double getDouble(String key, double defaultValue) {
		return ParamUtil.getDouble(_uploadRequest, key, defaultValue);
	}

	public File getFile(String key) {
		if (_parameters.containsKey(key)) {
			return (File) _parameters.get(key);
		}

		return _uploadRequest.getFile(key);
	}

	public long getGroupId() {
		return (Long) _parameters.get(Field.GROUP_ID);
	}

	public long[] getGroupIds() {
		return getLongValues(FieldKeys.MULTI_SELECT_SITE_LIST);
	}

	public int getInteger(String key) {
		return getInteger(key, 0);
	}

	public int getInteger(String key, int defaultValue) {
		if (_parameters.containsKey(key)) {
			return (Integer) _parameters.get(key);
		}

		return ParamUtil.getInteger(_uploadRequest, key, defaultValue);
	}

	public String getLanguageId() {
		return _themeDisplay.getLanguageId();
	}

	public long getLong(String key) {
		return getLong(key, 0L);
	}

	public long getLong(String key, long defaultValue) {
		if (_parameters.containsKey(key)) {
			return Long.valueOf(String.valueOf(_parameters.get(key)));
		}

		return ParamUtil.getLong(_uploadRequest, key, defaultValue);
	}

	public long[] getLongValues(String key) {
		if (_parameters.containsKey(key)) {
			return (long[]) _parameters.get(key);
		}

		return ParamUtil.getLongValues(_uploadRequest, key, new long[0]);
	}

	public int getMinutes(String key) {
		int h = ParamUtil.getInteger(_uploadRequest, key + "-hour", 0);
		int m = ParamUtil.getInteger(_uploadRequest, key + "-minute", 0);

		if ((h <= 0) && (m <= 0)) {
			m = 5;
		}

		return h * 60 + m;
	}

	public long[] getOrganizationIds() {
		return getLongValues(FieldKeys.MULTI_SELECT_ORGANIZATION_LIST);
	}

	public PermissionChecker getPermissionChecker() {
		return _themeDisplay.getPermissionChecker();
	}

	public Object getRandomString() {
		String rndString = getString(RANDOM_STRING);

		if (Validator.isNull(rndString)) {
			rndString = RandomUtil.nextString();
		}

		return rndString;
	}

	public PortletRequest getRequest() {
		return (PortletRequest) _uploadRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);
	}

	public long[] getRoleIds() {
		return getLongValues(FieldKeys.MULTI_SELECT_ROLE_LIST);
	}

	public HttpSession getSession() {
		return _session;
	}

	public String getString(String key) {
		return getString(key, StringPool.BLANK);
	}

	public String getString(String key, String defaultValue) {
		if (_parameters.containsKey(key)) {
			return String.valueOf(_parameters.get(key));
		}

		return ParamUtil.getString(_uploadRequest, key, defaultValue);
	}

	public String[] getStringValues(String key) {
		String[] values = _uploadRequest.getParameterValues(key);

		if (values == null) {
			return new String[0];
		}

		if (values.length == 1) {
			return StringUtil.split(values[0]);
		}

		return values;
	}

	public long[] getUserGroupIds() {
		return getLongValues(FieldKeys.MULTI_SELECT_USERGROUP_LIST);
	}

	public long getUserId() {
		return (Long)_parameters.get(Field.USER_ID);
	}

	public long[] getUserIds() {
		return getLongValues(FieldKeys.MULTI_SELECT_USER_LIST);
	}

	public void remove(String key) {
		_parameters.remove(key);
	}

	public void set(String key, Object value) {
		_parameters.put(key, value);
	}

	public void setGroupId(long groupId) {
		set(Field.GROUP_ID, groupId);
	}

	public void setGroupIds(long[] groupIds) {
		set(FieldKeys.MULTI_SELECT_SITE_LIST, groupIds);
	}

	public void setUserId(long userId) {
		set(Field.USER_ID, userId);
	}

	public RequestContext clone() {
		RequestContext clonedRequestProcessor = new RequestContext(
			_uploadRequest);

		clonedRequestProcessor._parameters.putAll(this._parameters);
		clonedRequestProcessor._session = this._session;
		clonedRequestProcessor._themeDisplay = this._themeDisplay;
		clonedRequestProcessor._uploadRequest = this._uploadRequest;

		return clonedRequestProcessor;
	}

	private Map<String, Object> _parameters;
	private HttpSession _session;
	private ThemeDisplay _themeDisplay;
	private UploadPortletRequest _uploadRequest;

}