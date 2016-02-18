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

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tibor Kovács
 *
 */
@XmlRootElement(name = "entry")
public class EntryTypeReader {

	public EntryMethodReader getMethod(String methodType)
		throws NoSuchMethodException {

		for (EntryMethodReader method : this._methods) {
			if (methodType.equals(method.getMethodType())) {
				return method;
			}
		}

		throw new NoSuchMethodException(
			"The requested '" + methodType + "' not exits.");
	}

	public ArrayList<EntryMethodReader> getMethods() {
		return this._methods;
	}

	public String getName() {
		return _name;
	}

	@XmlElement(name = "method")
	public void setMethods(ArrayList<EntryMethodReader> methods) {
		this._methods = methods;
	}

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this._name = name;
	}

	private ArrayList<EntryMethodReader> _methods;
	private String _name;

}