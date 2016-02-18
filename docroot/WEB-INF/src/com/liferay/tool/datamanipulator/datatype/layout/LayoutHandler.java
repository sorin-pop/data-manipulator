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

package com.liferay.tool.datamanipulator.datatype.layout;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;
import com.liferay.tool.datamanipulator.entryreader.EntryTypeReader;
import com.liferay.tool.datamanipulator.entryreader.util.EntryReaderUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.handler.portlethandler.AbstractPortletHandler;
import com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;

/**
 * @author Tibor Kovács
 *
 */
public class LayoutHandler extends AbstractPortletHandler
	implements PortletHandlerModel {

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel#startErase(com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public void startErase(RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel#startGenerate(com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor)
	 */

	@Override
	public void startGenerate(RequestProcessor requestProcessor)
		throws PortalException, SystemException {

		long publicLayoutId = requestProcessor.getLong(
			LayoutDisplayFields.PUBLIC_LAYOUT_ID);

		if (publicLayoutId == 0) {
			publicLayoutId = requestProcessor.getLong(
				LayoutDisplayFields.PUBLIC_LAYOUT_LIST);
		}

		Layout publicLayout = LayoutLocalServiceUtil.fetchLayout(
			publicLayoutId);

		long privateLayoutId = requestProcessor.getLong(
			LayoutDisplayFields.PRIVATE_LAYOUT_ID);

		if (privateLayoutId == 0) {
			privateLayoutId = requestProcessor.getLong(
				LayoutDisplayFields.PRIVATE_LAYOUT_LIST);
		}

		Layout privateLayout = LayoutLocalServiceUtil.fetchLayout(
			privateLayoutId);

		if (Validator.isNull(publicLayout) &&
			Validator.isNotNull(privateLayout)) {

			requestProcessor.set(EntryTypeKeys.GENERAL_LAYOUT_ENTRY, true);
		}

		int layoutCount = requestProcessor.getCount(
			EntryTypeKeys.GENERAL_LAYOUT_ENTRY);

		int layoutDepth = requestProcessor.getDepth(
			EntryTypeKeys.GENERAL_LAYOUT_ENTRY);

		int layoutSubCount = requestProcessor.getSubCount(
			EntryTypeKeys.GENERAL_LAYOUT_ENTRY);

		EntryTypeReader layoutEntryType = EntryReaderUtil.getEntryType(
			EntryTypeKeys.GENERAL_LAYOUT_ENTRY);

		BaseEntry layoutEntry = new BaseEntry(layoutEntryType);

		EntryHandlerModel layoutHandler = new LayoutEntryHandler(
			layoutCount, 0, layoutDepth, layoutSubCount, layoutEntry, null,
			requestProcessor);

		if (Validator.isNotNull(publicLayout)) {
			layoutHandler.generateEntries(publicLayoutId);
		}
		else if (Validator.isNotNull(privateLayout)) {
			layoutHandler.generateEntries(privateLayoutId);
		}
		else {
			layoutHandler.generateEntries((long)0);
		}
	}

}