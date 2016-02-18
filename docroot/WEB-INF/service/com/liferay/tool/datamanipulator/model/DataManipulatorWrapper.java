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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DataManipulator}.
 * </p>
 *
 * @author Tibor KovĂˇcs
 * @see DataManipulator
 * @generated
 */
public class DataManipulatorWrapper implements DataManipulator,
	ModelWrapper<DataManipulator> {
	public DataManipulatorWrapper(DataManipulator dataManipulator) {
		_dataManipulator = dataManipulator;
	}

	@Override
	public Class<?> getModelClass() {
		return DataManipulator.class;
	}

	@Override
	public String getModelClassName() {
		return DataManipulator.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("groupId", getGroupId());
		attributes.put("className", getClassName());
		attributes.put("classPK", getClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String className = (String)attributes.get("className");

		if (className != null) {
			setClassName(className);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}
	}

	/**
	* Returns the primary key of this data manipulator.
	*
	* @return the primary key of this data manipulator
	*/
	@Override
	public long getPrimaryKey() {
		return _dataManipulator.getPrimaryKey();
	}

	/**
	* Sets the primary key of this data manipulator.
	*
	* @param primaryKey the primary key of this data manipulator
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_dataManipulator.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the ID of this data manipulator.
	*
	* @return the ID of this data manipulator
	*/
	@Override
	public long getId() {
		return _dataManipulator.getId();
	}

	/**
	* Sets the ID of this data manipulator.
	*
	* @param id the ID of this data manipulator
	*/
	@Override
	public void setId(long id) {
		_dataManipulator.setId(id);
	}

	/**
	* Returns the group ID of this data manipulator.
	*
	* @return the group ID of this data manipulator
	*/
	@Override
	public long getGroupId() {
		return _dataManipulator.getGroupId();
	}

	/**
	* Sets the group ID of this data manipulator.
	*
	* @param groupId the group ID of this data manipulator
	*/
	@Override
	public void setGroupId(long groupId) {
		_dataManipulator.setGroupId(groupId);
	}

	/**
	* Returns the class name of this data manipulator.
	*
	* @return the class name of this data manipulator
	*/
	@Override
	public java.lang.String getClassName() {
		return _dataManipulator.getClassName();
	}

	/**
	* Sets the class name of this data manipulator.
	*
	* @param className the class name of this data manipulator
	*/
	@Override
	public void setClassName(java.lang.String className) {
		_dataManipulator.setClassName(className);
	}

	/**
	* Returns the class p k of this data manipulator.
	*
	* @return the class p k of this data manipulator
	*/
	@Override
	public long getClassPK() {
		return _dataManipulator.getClassPK();
	}

	/**
	* Sets the class p k of this data manipulator.
	*
	* @param classPK the class p k of this data manipulator
	*/
	@Override
	public void setClassPK(long classPK) {
		_dataManipulator.setClassPK(classPK);
	}

	@Override
	public boolean isNew() {
		return _dataManipulator.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_dataManipulator.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _dataManipulator.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_dataManipulator.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _dataManipulator.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _dataManipulator.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_dataManipulator.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _dataManipulator.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_dataManipulator.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_dataManipulator.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_dataManipulator.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new DataManipulatorWrapper((DataManipulator)_dataManipulator.clone());
	}

	@Override
	public int compareTo(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator) {
		return _dataManipulator.compareTo(dataManipulator);
	}

	@Override
	public int hashCode() {
		return _dataManipulator.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.tool.datamanipulator.model.DataManipulator> toCacheModel() {
		return _dataManipulator.toCacheModel();
	}

	@Override
	public com.liferay.tool.datamanipulator.model.DataManipulator toEscapedModel() {
		return new DataManipulatorWrapper(_dataManipulator.toEscapedModel());
	}

	@Override
	public com.liferay.tool.datamanipulator.model.DataManipulator toUnescapedModel() {
		return new DataManipulatorWrapper(_dataManipulator.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _dataManipulator.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _dataManipulator.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_dataManipulator.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DataManipulatorWrapper)) {
			return false;
		}

		DataManipulatorWrapper dataManipulatorWrapper = (DataManipulatorWrapper)obj;

		if (Validator.equals(_dataManipulator,
					dataManipulatorWrapper._dataManipulator)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public DataManipulator getWrappedDataManipulator() {
		return _dataManipulator;
	}

	@Override
	public DataManipulator getWrappedModel() {
		return _dataManipulator;
	}

	@Override
	public void resetOriginalValues() {
		_dataManipulator.resetOriginalValues();
	}

	private DataManipulator _dataManipulator;
}