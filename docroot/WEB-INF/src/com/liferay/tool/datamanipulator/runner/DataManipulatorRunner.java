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

package com.liferay.tool.datamanipulator.runner;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.tool.datamanipulator.handler.portlethandler.model.PortletHandlerModel;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;
import org.apache.commons.lang3.time.DurationFormatUtils;

/**
 * @author Tibor Kovács
 *
 */
public class DataManipulatorRunner extends Thread {

	public DataManipulatorRunner(
		PortletHandlerModel handler, RequestProcessor requestProcessor) {

		_handler = handler;
		_requestProcessor = requestProcessor;
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();

		_log.error(
			(Thread.currentThread()).getName() + " start add entries at " +
			String.valueOf(startTime) + StringPool.PERIOD);

		try {
			_handler.startGenerate(_requestProcessor);
		}
		catch (PortalException e) {
			_log.error(e, e);
		}
		catch (SystemException e) {
			_log.error(e, e);
		}

		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;

		_log.error(
			(Thread.currentThread()).getName() + " finish add entries at " +
			String.valueOf(endTime)+ StringPool.PERIOD);

			
		_log.error(
			"The generation process took " +
			DurationFormatUtils.formatDurationWords(duration, true, true));	
	}

	private static Log _log = LogFactoryUtil.getLog(
		DataManipulatorRunner.class);

	private PortletHandlerModel _handler;
	private RequestProcessor _requestProcessor;

}