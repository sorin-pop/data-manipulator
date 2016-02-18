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

package com.liferay.tool.datamanipulator.displayfield;

import com.liferay.portal.kernel.util.KeyValuePair;

import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class Field {

	public Field() {
		this(null, null, null);
	}

	public Field(String key, String type, Object values) {
		set(key, type, values);
	}

	public String getKey() {
		return _key;
	}

	public String getValue() {
		return String.valueOf(_values);
	}

	@SuppressWarnings("unchecked")
	public List<KeyValuePair> getValues() {
		return (List<KeyValuePair>)_values;
	}

	public boolean isTypeOf(String type) {
		return _type.equals(type);
	}

	public void set(String key, String type, Object values) {
		setKey(key);
		setType(type);
		setValues(values);
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setType(String type) {
		_type = type;
	}

	public void setValues(Object values) {
		_values = values;
	}

	private String _key;
	private String _type;
	private Object _values;

}