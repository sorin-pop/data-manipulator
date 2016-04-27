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

package com.liferay.tool.datamanipulator.util;

import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jodd.bean.BeanTemplateParser;

/**
 * @author Yg0R2
 */
public class StringUtil {

	public static String getLocalizedContent(String content) {
		return LocalizationUtil.updateLocalization(
			StringPool.BLANK, "static-content" ,content);
	}

	public static Map<Locale, String> getLocalizationMap(String content) {
		return LocalizationUtil.getLocalizationMap(content);
	}

	public static String getStringFieldValue( 
		String argName, Map<String, String> context, String template) {

		Map<String, String> tmpContect = new HashMap<String, String>();
		tmpContect.putAll(context);
		tmpContect.put(
			"fieldName", StringUtil.upperCaseFirstLetter(argName));

		BeanTemplateParser beanTemplateParser =
			new BeanTemplateParser();

		return beanTemplateParser.parse(template, tmpContect);
	}

	public static String lowerCaseFirstLetter(String s) {
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}

	public static String normalizeFriendlyURL(String friendlyURL) {
		if (Validator.isNull(friendlyURL)) {
			return friendlyURL;
		}

		friendlyURL = friendlyURL.toLowerCase();
		friendlyURL = Normalizer.normalize(friendlyURL, Normalizer.Form.NFD);
		friendlyURL = friendlyURL.replaceAll("[^\\p{ASCII}]", "");
		friendlyURL = friendlyURL.replaceAll("[^a-z0-9./_-]", StringPool.DASH);
		friendlyURL = friendlyURL.replaceAll("(-)\\1+", StringPool.DASH);

		return friendlyURL;
	}

	public static String upperCaseFirstLetter(String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

}