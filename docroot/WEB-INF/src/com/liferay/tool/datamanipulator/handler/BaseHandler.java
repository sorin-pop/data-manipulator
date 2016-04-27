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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.bean.BeanUtil;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.liferay.tool.datamanipulator.context.RequestContext;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.handler.util.HandlerUtil;
import com.liferay.tool.datamanipulator.util.GetterUtil;
import com.liferay.tool.datamanipulator.util.RandomUtil;
import com.liferay.tool.datamanipulator.util.StringUtil;

/**
 * @author Yg0R2
 */
public abstract class BaseHandler {

	public BaseHandler(String entryName, String displayFieldName)
		throws Exception {

		_addArgClazzs = getAddArgClazzs();
		_addArgNames = getAddArgNames();
		_addClass = getAddClass();
		_addMethodName = getAddMethodName();

		_updateArgClazzs = getUpdateArgClazzs();
		_updateArgNames = getUpdateArgNames();
		_updateClass = getUpdateClass();
		_updateMethodName = getUpdateMethodName();

		setDisplayFieldName(displayFieldName);
		setEntryName(entryName);
	}

	public Object addEntry(RequestContext requestContext) throws Exception {
		Map<String, Object> argsMap = getAddEntrySpecifiedArgs(requestContext);

		Method method = _addClass.getMethod(
			_addMethodName, _addArgClazzs);

		Object[] args = _getArgs(
			_addArgNames, _addArgClazzs, requestContext, argsMap);

		return method.invoke(_addClass, args);
	}

	public String getDisplayFieldName() {
		return _displayFieldPreString;
	}

	public String getDisplayFieldName(String postfix) {
		return _displayFieldPreString + StringPool.DASH + postfix;
	}

	public abstract DisplayFields getDisplayFields(long groupId, long companyId)
		throws Exception;

	public String getEntryName() {
		return _entryName;
	}

	public void proceed(RequestContext requestContext) throws Exception {
		_requestContext = requestContext;

		int count = _requestContext.getInteger(
			getDisplayFieldName(FieldKeys.INPUT_COUNT));

		int depth = _requestContext.getInteger(
			getDisplayFieldName(FieldKeys.INPUT_DEPTH));

		int subCount = _requestContext.getInteger(
			getDisplayFieldName(FieldKeys.INPUT_SUBCOUNT));

		int updateCount = _requestContext.getInteger(
			getDisplayFieldName(FieldKeys.INPUT_UPDATE_COUNT));

		if (count != 0) {
			for (int i = 0; i < count; i++) {
				RequestContext addRequestContext = requestContext.clone();
				addRequestContext.set("entryCount", String.valueOf(i + 1));
				addRequestContext.set("rndString", RandomUtil.nextString());

				_addEntry(updateCount, depth, subCount, addRequestContext);
			}
		}
		else {
			String parentId = _getParentClassPK(_requestContext);

			_addChildHandlerEntries(Long.valueOf(parentId));
		}
	}

	public Object updateEntry(Object entry, RequestContext requestContext)
		throws Exception {

		if (_updateClass == null) {
			return entry;
		}

		Map<String, Object> argsMap = getUpdateEntrySpecifiedArgs(
			entry, requestContext);

		Method method = _updateClass.getMethod(
			_updateMethodName, _updateArgClazzs);

		Object[] args = _getArgs(
			_updateArgNames, _updateArgClazzs, requestContext, argsMap);

		return method.invoke(_updateClass, args);
	}

	protected abstract Class<?>[] getAddArgClazzs();

	protected abstract String[] getAddArgNames();

	protected abstract Class<?> getAddClass() throws ClassNotFoundException;

	protected abstract Map<String, Object> getAddEntrySpecifiedArgs(
		RequestContext requestContext) throws Exception;

	protected abstract String getAddMethodName();

	protected abstract List<String> getChildHandlerNames();

	/**
	 * If 'entry' is null it should return default parent value.
	 * 
	 * @param entry
	 * @return
	 */
	protected Object getClassPK(Object entry) {
		if (Validator.isNull(entry)) {
			return 0L;
		}

		return BeanUtil.getDeclaredProperty(entry, getClassPKName());
	}

	protected abstract String getClassPKName();

	protected abstract String getParentClassPKName();

	protected abstract Class<?>[] getUpdateArgClazzs();

	protected abstract String[] getUpdateArgNames();

	protected abstract Class<?> getUpdateClass() throws ClassNotFoundException;

	protected abstract Map<String, Object> getUpdateEntrySpecifiedArgs(
		Object entry, RequestContext requestContext) throws Exception;

	protected abstract String getUpdateMethodName();

	protected void setDisplayFieldName(String displayFieldName) {
		_displayFieldPreString = displayFieldName;
	}

	protected void setEntryName(String entryName) {
		_entryName = entryName;
	}

	private void _addChildHandlerEntries(Object parentClassPK)
		throws Exception {

		for (String childHandlerName : _childHandlerNames) {
			RequestContext childRequestContext = _requestContext.clone();

			BaseHandler childHandler = HandlerUtil.getHandler(childHandlerName);

			childRequestContext.set(
				childHandler.getDisplayFieldName(
					childHandler.getParentClassPKName()), parentClassPK);

			childHandler.proceed(childRequestContext);
		}
	}

	private void _addEntry(
			int updateCount, int depth, int subCount,
			RequestContext requestContext)
		throws Exception {

		Object entry = null;
		while (true) {
			requestContext.set(
				RequestContext.RANDOM_STRING, RandomUtil.nextString());

			try {
				entry = addEntry(requestContext);

				break;
			}
			catch (Exception e) {
				String stackTrace = ExceptionUtils.getStackTrace(e);

				if (stackTrace.contains("Duplicate entry")) {
					_log.info("Handled ;)");
				}
				else {
					throw e;
				}
			}
		}

		RequestContext updateRequestContext = requestContext.clone();
		for (int i = 0; i < updateCount; i++) {
			for (String argName : _updateArgNames) {
				if (entryDateFields.contains(argName) ||
					entryMapFields.contains(argName) ||
					entryStringFields.contains(argName)) {

					continue;
				}

				Object property = BeanUtil.getPropertySilently(entry, argName);

				if (Validator.isNotNull(property)) {
					updateRequestContext.set(argName, property);
				}
			}

			updateRequestContext.set(getClassPKName(), getClassPK(entry));
			updateRequestContext.set("editCount", String.valueOf(i + 1));

			while (true) {
				updateRequestContext.set(
					RequestContext.RANDOM_STRING, RandomUtil.nextString());

				try {
					entry = updateEntry(entry, updateRequestContext);

					break;
				}
				catch (Exception e) {
					String stackTrace = ExceptionUtils.getStackTrace(e);

					if (stackTrace.contains("Duplicate entry")) {
						_log.info("Handled ;)");
					}
					else {
						throw e;
					}
				}
			}
		}

		Object entryClassPK = getClassPK(entry);

		_addChildHandlerEntries(entryClassPK);

		if (depth > 0) {
			RequestContext subRequestContext = requestContext.clone();

			subRequestContext.set(
				getDisplayFieldName(getParentClassPKName()), entryClassPK);

			for (int i = 0; i < subCount; i++) {
				subRequestContext.set("entryCount", String.valueOf(i + 1));
				subRequestContext.set(
					RequestContext.RANDOM_STRING, RandomUtil.nextString());

				_addEntry(
					updateCount, (depth - 1), subCount, subRequestContext);
			}
		}
	}

	private Object[] _getArgs(
			String[] argNames, Class<?>[] argClazzs,
			RequestContext requestContext,
			Map<String, Object> entrySpecifiedArgs)
		throws Exception {

		long companyId = requestContext.getCompanyId();
		long groupId = requestContext.getGroupId();
		long userId = requestContext.getUserId();

		Date now = new Date();

		ServiceContext serviceContext = HandlerUtil.getServiceContext(
			groupId, userId);

		Map<String, Calendar> dateMap = new HashMap<String, Calendar>(
			entryDateFields.size());

		for (String entryDateField : entryDateFields) {
			String dateVarName = entryDateField + "Date"; 
			String dateKeyName = entryDateField + "-date"; 

			Date serviceContextDate = (Date)BeanUtil.getPropertySilently(
				serviceContext, dateVarName);

			Calendar dateVarValue;
			if (requestContext.contains(dateKeyName + "-from") &&
					requestContext.contains(dateKeyName + "-to")) {

				dateVarValue = requestContext.getBetweenCalendar(dateKeyName);
			}
			else if (Validator.isNotNull(serviceContextDate)) {
				dateVarValue = Calendar.getInstance();

				dateVarValue.setTime(serviceContextDate);
			}
			else {
				dateVarValue = Calendar.getInstance();

				dateVarValue.setTime(now);
			}

			dateMap.put(dateVarName, dateVarValue);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(_entryName);
		sb.append(requestContext.getString("entryCount"));
		sb.append(" ${fieldName} ");

		if (requestContext.contains("editCount")) {
			sb.append("Edited ");
			sb.append(requestContext.getString("editCount"));
			sb.append(" times ");
		}

		sb.append(requestContext.getRandomString());

		String entryTemplate = sb.toString();

		User user = UserLocalServiceUtil.fetchUser(userId);

		List<Object> argValues = new ArrayList<Object>(argNames.length);

		for (int i = 0; i < argNames.length; i++) {
			String argName = argNames[i];
			Class<?> argClazz = argClazzs[i];

			if (entrySpecifiedArgs.containsKey(argName)) {
				argValues.add(entrySpecifiedArgs.get(argName));
			}
			else if (argName.equals(Field.COMPANY_ID)) {
				argValues.add(companyId);
			}
			else if (argName.matches(".*Date.*")) {
				int x = argName.indexOf("Date") + 4;

				String dateKey = argName.substring(0, x);

				Calendar calendar = dateMap.get(dateKey);

				String calendarFieldName = argName.substring(x).toUpperCase();
				if (calendarFieldName.equals("DAY")) {
					calendarFieldName = "DATE";
				}

				int calendarFieldValue = (Integer)GetterUtil.getFieldValue(
					Calendar.class.getName(), calendarFieldName);

				argValues.add(calendar.get(calendarFieldValue));
			}
			else if (entryStringFields.contains(argName)) {
				Map<String, String> context = new HashMap<String, String>(1);
				context.put("fieldName", argName);

				String content = StringUtil.getStringFieldValue(
					argName, context, entryTemplate);

				argValues.add(content);
			}
			else if (entryMapFields.contains(argName)) {
				argName = argName.substring(0, (argName.length() - 3));

				Map<String, String> context = new HashMap<String, String>(1);
				context.put("fieldName", argName);

				String content = StringUtil.getStringFieldValue(
					argName, context, entryTemplate);

				argValues.add(StringUtil.getLocalizationMap(content));
			}
			else if (argName.equals("friendlyURL")) {
				Map<String, String> context = new HashMap<String, String>(1);
				context.put("fieldName", "name");

				String friendlyURL = StringUtil.getStringFieldValue(
					argName, context, entryTemplate);

				friendlyURL =
					StringPool.SLASH + FriendlyURLNormalizerUtil.normalize(
						friendlyURL);

				argValues.add(friendlyURL);
			}
			else if (argName.equals(Field.GROUP_ID)) {
				argValues.add(groupId);
			}
			else if (argName.equals(getParentClassPKName())) {
				argValues.add(Long.valueOf(_getParentClassPK(requestContext)));
			}
			else if (argName.equals("locale")) {
				argValues.add(LocaleUtil.getDefault());
			}
			else if (argName.equals("serviceContext")) {
				argValues.add(serviceContext);
			}
			else if (argName.equals(Field.USER_ID)) {
				argValues.add(userId);
			}
			else if (argName.equals(Field.USER_NAME)) {
				argValues.add(user.getFullName());
			}
			else if (requestContext.contains(argName)) {
				argValues.add(requestContext.get(argName));
			}
			else {
				Object argValue = null;
				try {
					Object object = argClazz.newInstance();

					if (object instanceof String) {
						argValue = StringPool.BLANK;
					}
				}
				catch (InstantiationException e) {
					Type type = argClazz;

					if (type.equals(Boolean.TYPE)) {
						argValue = false;
					}
					else if (type.equals(Integer.TYPE)) {
						argValue = (int)0;
					}
					else if (type.equals(List.class)) {
						argValue = new ArrayList<Object>(0);
					}
					else if (type.equals(Long.TYPE)) {
						argValue = (long)0;
					}
				}

				argValues.add(argValue);
			}
		}

		return (Object[]) argValues.toArray(new Object[argValues.size()]);
	}

	private String _getParentClassPK(RequestContext requestContext) {
		String parentId = null;

		String parentClassPKName = getParentClassPKName();

		if (Validator.isNotNull(parentClassPKName)) {
			parentId = requestContext.getString(
				getDisplayFieldName(parentClassPKName));

			if (Validator.isNull(parentId)) {
				parentId = requestContext.getString(
					getDisplayFieldName(FieldKeys.SELECT_PARENT_LIST));
			}
		}

		if (Validator.isNull(parentId)) {
			parentId = String.valueOf(getClassPK(null));
		}

		return parentId;
	}

	protected static final List<String> entryDateFields = ListUtil.fromArray(
		new String [] {
			"create", "display", "expiration", "lastPost", "modified",
			"review", "start", "status"
		}
	);
	protected static final List<String> entryStringFields = ListUtil.fromArray(
		new String [] {
			"body", "content", "description", "name", "subject", "title"
		}
	);
	protected static final List<String> entryMapFields = ListUtil.fromArray(
		new String [] {
			"contentMap", "descriptionMap", "nameMap", "titleMap"
		}
	);

	protected static final int buildNumber = ReleaseInfo.getBuildNumber();

	private static Log _log = LogFactoryUtil.getLog(BaseHandler.class);

	private Class<?>[] _addArgClazzs;
	private String[] _addArgNames;
	private Class<?> _addClass;
	private String _addMethodName;
	private List<String> _childHandlerNames = getChildHandlerNames();
	private String _displayFieldPreString;
	private String _entryName;
	private RequestContext _requestContext;
	private Class<?>[] _updateArgClazzs;
	private String[] _updateArgNames;
	private Class<?> _updateClass;
	private String _updateMethodName;

}