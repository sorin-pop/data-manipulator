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

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tibor Kovács
 *
 */
@XmlRootElement(name = "parameter")
public class EntryParameterReader {

	public String getName() {
		return _name;
	}

	public String getType() throws ClassNotFoundException {
		String type = _type.replace("&lt;", "<");
		type = type.replace("&gt;", ">");

		return type;
	}

	@XmlAttribute(name = "name")
	public void setName(String name) {
		this._name = name;
	}

	@XmlAttribute(name = "type")
	public void setType(String type) {
		type = type.replace("<", "&lt;");
		type = type.replace(">", "&gt;");

		this._type = type;
	}

	private String _name;
	private String _type;

}