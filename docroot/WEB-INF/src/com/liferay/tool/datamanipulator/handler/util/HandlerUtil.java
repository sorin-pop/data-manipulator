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

package com.liferay.tool.datamanipulator.handler.util;

import com.liferay.portal.kernel.service.ServiceContext;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import jodd.bean.BeanUtil;

import com.liferay.tool.datamanipulator.annotation.Handler;
import com.liferay.tool.datamanipulator.annotation.HandlerType;
import com.liferay.tool.datamanipulator.handler.BaseHandler;

/**
 * @author Yg0R2
 */
public class HandlerUtil {

	public static BaseHandler getHandler(String handlerName) throws Exception {

		Class<?> handlerClazz = getHandlerClass(handlerName);

		Constructor<?> handlerConstructor = handlerClazz.getConstructor();

		return (BaseHandler)handlerConstructor.newInstance();
	}

	public static Class<?> getHandlerClass(String handlerName)
		throws ClassNotFoundException {

		for (PojoClass extendClass : _extendClasses) {
			String simpleClassName = extendClass.getClazz().getSimpleName();

			if (simpleClassName.equals(handlerName)) {
				return extendClass.getClazz();
			}
		}

		throw new ClassNotFoundException(
			"The requested '" + handlerName + "' handler class does not exist.");
	}

	public static String getHandlerDisplayName(Class<?> handlerClazz) {
		Handler handlerAnnotation = handlerClazz.getAnnotation(Handler.class);

		return handlerAnnotation.displayName();
	}

	public static String getHandlerDisplayName(String handlerName) {
		try {
			Class<?> handlerClazz = getHandlerClass(handlerName);

			Handler handlerAnnotation = handlerClazz.getAnnotation(
				Handler.class);

			return handlerAnnotation.displayName();
		}
		catch (ClassNotFoundException cnfe) {
			return handlerName;
		}
	}

	public static Set<String> getHandlerNames(String handlerType) {
		Set<String> handlerClassNames = new TreeSet<String>();
		for (PojoClass extendClass : _extendClasses) {
			Class<?> clazz = extendClass.getClazz();

			Handler handlerAnnotation = clazz.getAnnotation(Handler.class); 

			if ((handlerAnnotation != null) &&
				handlerType.equals(handlerAnnotation.type().toString())) {

				handlerClassNames.add(clazz.getSimpleName());
			}
		}

		return handlerClassNames;
	}

	public static ServiceContext getServiceContext(long groupId, long userId) {
		ServiceContext serviceContext = new ServiceContext();

		BeanUtil.setPropertySilent(
			serviceContext, "addCommunityPermissions", false);

		BeanUtil.setPropertySilent(
			serviceContext, "addGroupPermissions", false);

		BeanUtil.setPropertySilent(
			serviceContext, "addGuestPermissions", false);

		BeanUtil.setPropertySilent(
			serviceContext, "deriveDefaultPermissions", true);

		BeanUtil.setPropertySilent(serviceContext, "scopeGroupId", groupId);

		BeanUtil.setPropertySilent(serviceContext, "indexingEnabled", false);

		BeanUtil.setPropertySilent(serviceContext, "userId", userId);

		return serviceContext;
	}

	public static boolean isPortalType(String handlerName)
		throws ClassNotFoundException {

		Class<?> handlerClazz = getHandlerClass(handlerName);

		Handler handlerAnnotation = handlerClazz.getAnnotation(Handler.class);

		HandlerType handlerType = handlerAnnotation.type();

		return handlerType.equals(HandlerType.PORTAL);
	}

	private static List<PojoClass> _extendClasses =
		PojoClassFactory.enumerateClassesByExtendingType(
			BaseHandler.class.getPackage().getName(), BaseHandler.class, null);
		
}