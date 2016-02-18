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

package com.liferay.tool.datamanipulator.entryreader;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tibor Kovács
 *
 */
@XmlRootElement(name = "method")
public class EntryMethodReader {

	/* public Class<?> getClazz() throws ClassNotFoundException {
		return Class.forName(this._clazz);
	}*/

	public String getClazz() {
		return this._clazz;
	}

	public String getMethodName() {
		return this._methodName;
	}

	public String getMethodType() {
		return this._type;
	}

	public ArrayList<EntryParameterReader> getParameterList() {
		return this._parameters;
	}

	public Class<?>[] getParameterListClazzs() {
		ArrayList<Class<?>> result = new ArrayList<Class<?>>();

		try {
			for (EntryParameterReader parameter : _parameters) {
				String type = parameter.getType();

				Class<?> clazz;

				if (type.equals("boolean")) {
					clazz = Boolean.TYPE;
				}
				else if (type.equals("boolean[]")) {
					clazz = boolean[].class;
				}
				else if (type.equals("byte")) {
					clazz = Byte.TYPE;
				}
				else if (type.equals("byte[]")) {
					clazz = byte[].class;
				}
				else if (type.equals("char")) {
					clazz = Character.TYPE;
				}
				else if (type.equals("char[]")) {
					clazz = char[].class;
				}
				else if (type.equals("double")) {
					clazz = Double.TYPE;
				}
				else if (type.equals("double[]")) {
					clazz = double[].class;
				}
				else if (type.equals("float")) {
					clazz = Float.TYPE;
				}
				else if (type.equals("float[]")) {
					clazz = float[].class;
				}
				else if (type.equals("int")) {
					clazz = Integer.TYPE;
				}
				else if (type.equals("int[]")) {
					clazz = int[].class;
				}
				else if (type.equals("long")) {
					clazz = Long.TYPE;
				}
				else if (type.equals("long[]")) {
					clazz = long[].class;
				}
				else if (type.equals("short")) {
					clazz = Short.TYPE;
				}
				else if (type.equals("short[]")) {
					clazz = short[].class;
				}
				else if (type.endsWith("[]")) {
					type = type.substring(0, type.length() -2);

					clazz = Array.newInstance(
						Class.forName(type), 0).getClass();
				}
				else if (type.matches("java.util.ArrayList<.*>")) {
					clazz = ArrayList.class;
				}
				else if (type.matches("java.util.List<.*>")) {
					clazz = List.class;
				}
				else if (type.matches("java.util.Map<.*>")) {
					clazz = Map.class;
				}
				else {
					clazz = Class.forName(type);
				}

				result.add(clazz);
			}
		}
		catch (ClassNotFoundException e) {
			_log.error(e, e);
		}

		return result.toArray(new Class<?>[result.size()]);
	}

	public String[] getParameterListNames() {
		ArrayList<String> result = new ArrayList<String>();

		for (EntryParameterReader parameter : this._parameters) {
			result.add(parameter.getName());
		}

		return result.toArray(new String[result.size()]);
	}

	@XmlElement(name = "clazz")
	public void setClazz(String clazz) {
		this._clazz = clazz;
	}

	@XmlElement(name = "method-name")
	public void setMethodName(String methodName) {
		this._methodName = methodName;
	}

	@XmlAttribute(name = "type")
	public void setMethodType(String methodType) {
		this._type = methodType;
	}

	@XmlElement(name = "parameter")
	@XmlElementWrapper(name = "parameter-list")
	public void setParameterList(ArrayList<EntryParameterReader> parameters) {
		this._parameters = parameters;
	}

	private static Log _log = LogFactoryUtil.getLog(EntryMethodReader.class);

	private String _clazz;
	private String _methodName;
	private ArrayList<EntryParameterReader> _parameters;
	private String _type;

}