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

package com.liferay.tool.datamanipulator.entryreader.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.tool.datamanipulator.entry.NoSuchEntryException;
import com.liferay.tool.datamanipulator.entryreader.EntryTypeReader;
import com.liferay.tool.datamanipulator.entryreader.EntryWrapperReader;

import java.io.File;

import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author Tibor Kovács
 *
 */
public class EntryReaderUtil {

	public static EntryTypeReader getEntryType(String entryTypeKey) {
		ClassLoader classLoader = EntryReaderUtil.class.getClassLoader();

		URL entryWrapperFileURL = classLoader.getResource("EntryTypes.xml");

		File entryWrapperFile = new File(entryWrapperFileURL.getPath());

		EntryWrapperReader entryWrapper = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(
				EntryWrapperReader.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			entryWrapper = (EntryWrapperReader)jaxbUnmarshaller.unmarshal(
				entryWrapperFile);
		}
		catch (JAXBException e) {
			_log.error(e, e);
		}

		EntryTypeReader entryType = null;

		try {
			entryType = entryWrapper.getEntry(entryTypeKey);
		}
		catch (NoSuchEntryException e) {
			_log.error(e, e);
		}

		return entryType;
	}

	private static Log _log = LogFactoryUtil.getLog(EntryReaderUtil.class);

}