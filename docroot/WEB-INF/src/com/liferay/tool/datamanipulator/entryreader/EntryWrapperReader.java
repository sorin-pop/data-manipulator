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

import com.liferay.tool.datamanipulator.entry.NoSuchEntryException;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tibor Kovács
 *
 */
@XmlRootElement(namespace = "com.liferay.tool.datamanipulator.entryreader")
public class EntryWrapperReader {

	public EntryTypeReader getEntry(String entryTypeKey)
		throws NoSuchEntryException {

		for (EntryTypeReader entry : this._entryList) {
			if (entryTypeKey.equals(entry.getName())) {
				return entry;
			}
		}

		throw new NoSuchEntryException(
			"The requested '" + entryTypeKey + "' entry not found.");
	}

	public ArrayList<EntryTypeReader> getEntryList() {
		return this._entryList;
	}

	@XmlElement(name = "entry")
	public void setEntryList(ArrayList<EntryTypeReader> entryList) {
		this._entryList = entryList;
	}

	private ArrayList<EntryTypeReader> _entryList;

}