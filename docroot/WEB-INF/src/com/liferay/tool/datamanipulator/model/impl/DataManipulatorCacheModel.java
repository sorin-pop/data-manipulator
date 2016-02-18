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

package com.liferay.tool.datamanipulator.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.tool.datamanipulator.model.DataManipulator;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing DataManipulator in entity cache.
 *
 * @author Tibor KovĂˇcs
 * @see DataManipulator
 * @generated
 */
public class DataManipulatorCacheModel implements CacheModel<DataManipulator>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{id=");
		sb.append(id);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", className=");
		sb.append(className);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DataManipulator toEntityModel() {
		DataManipulatorImpl dataManipulatorImpl = new DataManipulatorImpl();

		dataManipulatorImpl.setId(id);
		dataManipulatorImpl.setGroupId(groupId);

		if (className == null) {
			dataManipulatorImpl.setClassName(StringPool.BLANK);
		}
		else {
			dataManipulatorImpl.setClassName(className);
		}

		dataManipulatorImpl.setClassPK(classPK);

		dataManipulatorImpl.resetOriginalValues();

		return dataManipulatorImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readLong();
		groupId = objectInput.readLong();
		className = objectInput.readUTF();
		classPK = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(id);
		objectOutput.writeLong(groupId);

		if (className == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(className);
		}

		objectOutput.writeLong(classPK);
	}

	public long id;
	public long groupId;
	public String className;
	public long classPK;
}