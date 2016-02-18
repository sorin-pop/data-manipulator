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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CountryConstants;
import com.liferay.portal.model.RegionConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.tool.datamanipulator.entry.util.EntryUtil;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Tibor Kovács
 *
 */
public class EntryArgs {

	public EntryArgs(RequestProcessor requestProcessor) {
		_parameters = new HashMap<String, Object>();

		_requestProcessor = requestProcessor;

		_setBaseParameters();
	}

	public Object[] getArgs(String[] parameterNames) {
		ArrayList<Object> args = new ArrayList<Object>();

		for (String parameterName : parameterNames) {
			args.add(getParameter(parameterName));
		}

		return args.toArray(new Object[args.size()]);
	}

	public Object getParameter(String key) {
		return _parameters.get(key);
	}

	public void setParameter(String key, Object value) {
		if (Validator.isNull(value) && _parameters.containsKey(key)) {
			_parameters.remove(key);
		}
		else {
			_parameters.put(key, value);
		}
	}

	public void setParameters(HashMap<String, Object> parameters) {
		if (Validator.isNotNull(parameters)) {
			_parameters.putAll(parameters);
		}
	}

	private void _setBaseParameters() {
		User user = null;
		try {
			user = UserLocalServiceUtil.getUser(_requestProcessor.getUserId());
		}
		catch (PortalException e) {
			_log.error(e, e);
		}
		catch (SystemException e) {
			_log.error(e, e);
		}

		setParameter("allDay", EntryUtil.nextBoolean());
		setParameter("allowPingbacks", true);
		setParameter("allowTrackbacks", false);
		setParameter("allowAnonymous", false);
		setParameter("anonymous", false);
		setParameter("autoPassword", false);
		setParameter("autoScreenName", false);
		setParameter("changeLog", StringPool.BLANK);
		setParameter("comments", StringPool.BLANK);
		setParameter(Field.COMPANY_ID, _requestProcessor.getCompanyId());
		setParameter("countryId", CountryConstants.DEFAULT_COUNTRY_ID);
		setParameter("extraSettings", StringPool.BLANK);
		setParameter("files", new ArrayList<ObjectValuePair<String,byte[]>>(1));
		setParameter("friendlyURL", StringPool.BLANK);
		setParameter(Field.GROUP_ID, _requestProcessor.getGroupId());
		setParameter("jobTitle", StringPool.BLANK);
		setParameter("images", null);
		setParameter("indexable", true);
		setParameter(
			"inputStreamOVPs",
			new ArrayList<ObjectValuePair<String, InputStream>>(1));

		setParameter("locale", LocaleUtil.getDefault());
		setParameter("location", StringPool.BLANK);
		setParameter("male", EntryUtil.nextBoolean());
		setParameter("mergeWithParent", false);
		setParameter("mergeWithParentFolder", false);
		setParameter("mergeWithParentCategory", false);
		setParameter("minorEdit", false);
		setParameter("recursable", false);
		setParameter("regionId", RegionConstants.DEFAULT_REGION_ID);
		setParameter(
			"serviceContext",
				EntryUtil.getNewServiceContext(
					_requestProcessor.getGroupId(),
					_requestProcessor.getUserId()));

		setParameter("smallFile", null);
		setParameter("smallImage", false);
		setParameter("smallImageFileName", StringPool.BLANK);
		setParameter("smallImageFile", null);
		setParameter("smallImageInputStream", null);
		setParameter("smallImageURL", StringPool.BLANK);
		setParameter("trackbacks", null);
		setParameter(Field.USER_ID, user.getUserId());
		setParameter(Field.USER_NAME, user.getFullName());
	}

	private static Log _log = LogFactoryUtil.getLog(EntryArgs.class);

	private HashMap<String, Object> _parameters;
	private RequestProcessor _requestProcessor;

}