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

package com.liferay.tool.datamanipulator.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Tibor KovĂˇcs
 * @generated
 */
public class DataManipulatorSoap implements Serializable {
	public static DataManipulatorSoap toSoapModel(DataManipulator model) {
		DataManipulatorSoap soapModel = new DataManipulatorSoap();

		soapModel.setId(model.getId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());

		return soapModel;
	}

	public static DataManipulatorSoap[] toSoapModels(DataManipulator[] models) {
		DataManipulatorSoap[] soapModels = new DataManipulatorSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DataManipulatorSoap[][] toSoapModels(
		DataManipulator[][] models) {
		DataManipulatorSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DataManipulatorSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DataManipulatorSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DataManipulatorSoap[] toSoapModels(
		List<DataManipulator> models) {
		List<DataManipulatorSoap> soapModels = new ArrayList<DataManipulatorSoap>(models.size());

		for (DataManipulator model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DataManipulatorSoap[soapModels.size()]);
	}

	public DataManipulatorSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	private long _id;
	private long _groupId;
	private String _className;
	private long _classPK;
}