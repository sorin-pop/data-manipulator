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

package com.liferay.tool.datamanipulator.handler.entryhandler;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.entry.BaseEntry;
import com.liferay.tool.datamanipulator.entry.EntryArgs;
import com.liferay.tool.datamanipulator.entry.util.EntryUtil;
import com.liferay.tool.datamanipulator.handler.entryhandler.model.EntryHandlerModel;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.requestprocessor.RequestProcessor;

/**
 * @author Tibor Kovács
 *
 */
public abstract class AbstractEntryHandler implements EntryHandlerModel {

	public AbstractEntryHandler(
		int count, int update, int depth, int subCount, BaseEntry baseEntry,
		EntryHandlerModel subEntryHandler, RequestProcessor requestProcessor) {

		_baseEntry = baseEntry;
		_count = count;
		_depth = depth;
		_subEntryHandler = subEntryHandler;
		_requestProcessor = requestProcessor;
		_subCount = subCount;
		_update = update;
	}

	@Override
	public boolean enableAddToParent(int depth) {
		String s = _requestProcessor.getAddToParent(_baseEntry.getEntryName());

		if (s.equals(StringPool.BLANK)) {
			return true;
		}

		if (s.equals(FieldKeys.ADD_TO_ALL_PARENT)) {
			return true;
		}

		if (s.equals(FieldKeys.ADD_TO_INNERMOST_PARENT) && (depth == 0)) {
			return true;
		}

		if (s.equals(FieldKeys.ADD_TO_RANDOM_PARENT) &&
			EntryUtil.nextBoolean()) {

			return true;
		}

		return false;
	}

	@Override
	public void eraseEntries(long parentId)
		throws PortalException, SystemException {

		// TODO Auto-generated method stub

	}

	@Override
	public void generateEntries(long parentId)
		throws PortalException, SystemException {

		if (_count <= 0) {
			if (Validator.isNotNull(_subEntryHandler)) {
				_subEntryHandler.generateEntries(parentId);
			}
		}
		else {
			createEntry(parentId, _depth, _count);
		}
	}

	protected void createEntry(long parentId, int depth, int count)
		throws PortalException, SystemException {

		String postString = StringPool.BLANK;
		int shift = 1;

		int i = 1;
		while (i <= count) {
			String post = StringPool.DASH + String.valueOf(i) + postString;

			DataManipulator dataM = null;

			try {
				EntryArgs args = getCreateEntryArgs(
					parentId, post, _requestProcessor);

				Object entry = _baseEntry.createEntry(args);

				dataM = getDataManipulatorFromObject(entry);
			}
			catch (Exception e) {
				if (!isDuplicateException(e)) {
					if (e instanceof PortalException) {
						throw new PortalException(e);
					}
					else if (e instanceof SystemException) {
						throw new SystemException(e);
					}

					return;
				}

				postString = StringPool.DASH + String.valueOf(shift);

				shift++;

				continue;
			}

			String editPostString = StringPool.BLANK;
			int editShift = 1;

			int j = 1;
			while (j <= _update) {
				String editPost = String.valueOf(j) + editPostString;

				try {
					EntryArgs args = getUpdateEntryArgs(
						dataM.getClassPK(), editPost, _requestProcessor);

					_baseEntry.updateEntry(args);
				}
				catch (Exception e) {
					if (!isDuplicateException(e)) {
						if (e instanceof PortalException) {
							throw new PortalException(e);
						}
						else if (e instanceof SystemException) {
							throw new SystemException(e);
						}

						return;
					}

					editPostString =
						StringPool.DASH + String.valueOf(editShift);

					editShift++;

					continue;
				}

				j++;
			}

			if (depth > 0) {
				createEntry(dataM.getClassPK(), depth - 1, _subCount);
			}

			if (Validator.isNotNull(_subEntryHandler) &&
				_subEntryHandler.enableAddToParent(depth)) {

				_subEntryHandler.generateEntries(dataM.getClassPK());
			}

			i++;
		}
	}

	private static boolean isDuplicateException(Exception e) {
		Exception eCause = e;

		int k = 0;
		while (true) {
			String exceptionString = eCause.toString();
			String exceptionMessage = eCause.getMessage();

			if (exceptionString.contains("Duplicate") ||
				(Validator.isNotNull(exceptionMessage) &&
				 exceptionMessage.contains("already exists"))) {

				return true;
			}

			if ((k > 3) || Validator.isNull(eCause.getCause())) {
				_log.error(eCause, eCause);

				return false;
			}

			eCause = (Exception)eCause.getCause();

			k++;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AbstractEntryHandler.class);

	private BaseEntry _baseEntry;
	private int _count;
	private int _depth;
	private EntryHandlerModel _subEntryHandler;
	private RequestProcessor _requestProcessor;
	private int _subCount;
	private int _update;

}