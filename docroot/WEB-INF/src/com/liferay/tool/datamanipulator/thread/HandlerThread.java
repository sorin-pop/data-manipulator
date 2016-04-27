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

package com.liferay.tool.datamanipulator.thread;

import javax.servlet.http.HttpSession;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.PortalSessionThreadLocal;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;

import com.liferay.tool.datamanipulator.context.RequestContext;
import com.liferay.tool.datamanipulator.handler.BaseHandler;
import com.liferay.tool.datamanipulator.handler.util.HandlerUtil;
import org.apache.commons.lang.time.DurationFormatUtils;

/**
 * @author Yg0R2
 */
public class HandlerThread extends Thread {

	public HandlerThread(
		ThreadGroup threadGroup, BaseHandler handler,
		RequestContext requestContext) {

		super(threadGroup, new HandlerThread(handler, requestContext));

		_handler = handler;
		_requestContext = requestContext;
	}

	public HandlerThread(BaseHandler handler, RequestContext requestContext) {
		_handler = handler;
		_requestContext = requestContext;
	}

	@Override
	public void run() {
		HttpSession httpSession = _requestContext.getSession();
		httpSession.setMaxInactiveInterval(-1);

		//PermissionThreadLocal.setIndexEnabled(false);
		PermissionThreadLocal.setPermissionChecker(
			_requestContext.getPermissionChecker());

		PrincipalThreadLocal.setName(_requestContext.getUserId());

		PortalSessionThreadLocal.setHttpSession(httpSession);

		String threadName = this.getName();

		long startTime = System.currentTimeMillis();

		StringBuilder startSB = new StringBuilder(5);
		startSB.append(threadName);
		startSB.append(": Started generating '");
		startSB.append(HandlerUtil.getHandlerDisplayName(_handler.getClass()));
		startSB.append("' entries at ");
		startSB.append(new java.util.Date(startTime));

		_log.info(startSB.toString());

		try {
			_handler.proceed(_requestContext);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		long endTime = System.currentTimeMillis();

		StringBuilder finishSB = new StringBuilder(5);
		finishSB.append(threadName);
		finishSB.append(": Finished generating '");
		finishSB.append(HandlerUtil.getHandlerDisplayName(_handler.getClass()));
		finishSB.append("' entries at ");
		finishSB.append(new java.util.Date(endTime));


		_log.info(finishSB.toString());

		StringBuilder tookSB = new StringBuilder(4);
		tookSB.append(threadName);
		tookSB.append(": The generation process took ");
		tookSB.append(DurationFormatUtils.formatDurationWords(endTime - startTime, true, true));

		_log.info(tookSB.toString());
	}

	private static Log _log = LogFactoryUtil.getLog(HandlerThread.class);

	private BaseHandler _handler;
	private RequestContext _requestContext;

}