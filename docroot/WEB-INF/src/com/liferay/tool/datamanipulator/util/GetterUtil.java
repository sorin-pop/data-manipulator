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

import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

import java.lang.reflect.Field;

import org.apache.commons.lang.ArrayUtils;

/**
 * @author Yg0R2
 */
public class GetterUtil {

	public static Class<?> getClass(String className)
		throws ClassNotFoundException {

		return Class.forName(
			className, false, PortalClassLoaderUtil.getClassLoader());
	}

	public static Class<?> getClass(String... classNames)
		throws ClassNotFoundException {

		for (String className : classNames) {
			try {
				return getClass(className);
			}
			catch (ClassNotFoundException cnfe) {
			}
		}

		throw new ClassNotFoundException(
			"Requested class not found: " + ArrayUtils.toString(classNames));
	}

	public static Object getFieldValue(String className, String fieldName)
		throws ClassNotFoundException, SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {

		Class<?> clazz = getClass(className);

		Field field = clazz.getField(fieldName);

		return field.get(null);
	}

	public static Object getNewInstance(String className)
		throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {

		Class<?> clazz = getClass(className);

		return clazz.newInstance();
	}

}