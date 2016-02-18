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
import com.liferay.tool.datamanipulator.entryreader.EntryMethodReader;
import com.liferay.tool.datamanipulator.entryreader.EntryTypeReader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Tibor Kovács
 *
 */
public class BaseEntry {

	public BaseEntry(EntryTypeReader entryType) {
		_entryType = entryType;
	}

	public Object createEntry(EntryArgs entryArgs)
		throws NoSuchMethodException, PortalException, SystemException {

		return _runInvoke(_getAddMethod(), entryArgs);
	}

	public void deleteEntry(EntryArgs entryArgs)
		throws NoSuchMethodException, PortalException, SystemException {

		_runInvoke(_getDeleteMethod(), entryArgs);
	}

	public String getEntryName() {
		return _entryType.getName();
	}

	public void updateEntry(EntryArgs entryArgs)
		throws NoSuchMethodException, PortalException, SystemException {

		_runInvoke(_getUpdateMethod(), entryArgs);
	}

	private EntryMethodReader _getAddMethod() throws NoSuchMethodException {
		return _entryType.getMethod("add");
	}

	private EntryMethodReader _getDeleteMethod() throws NoSuchMethodException {
		return _entryType.getMethod("delete");
	}

	private EntryMethodReader _getUpdateMethod() throws NoSuchMethodException {
		return _entryType.getMethod("update");
	}

	private Object _runInvoke(
			EntryMethodReader entryMethod, EntryArgs entryArgs)
		throws PortalException, SystemException {

		try {
			Class<?> clazz = Class.forName(entryMethod.getClazz());

			Method method = clazz.getMethod(
				entryMethod.getMethodName(),
				entryMethod.getParameterListClazzs());

			Object[] args = entryArgs.getArgs(
				entryMethod.getParameterListNames());

			return method.invoke(clazz, args);
		}
		catch (SecurityException e) {
			_log.error(e, e);
		}
		catch (NoSuchMethodException e) {
			_log.error(e, e);
		}
		catch (IllegalArgumentException e) {
			_log.error(e, e);
		}
		catch (IllegalAccessException e) {
			_log.error(e, e);
		}
		catch (ClassNotFoundException e) {
			_log.error(e, e);
		}
		catch (InvocationTargetException e) {
			if (e.getCause() instanceof SystemException) {
				throw (SystemException)e.getCause();
			}
			else if (e.getCause() instanceof PortalException) {
				throw (PortalException)e.getCause();
			}

			_log.error(e.getCause(), e.getCause());
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(BaseEntry.class);

	private EntryTypeReader _entryType;

}