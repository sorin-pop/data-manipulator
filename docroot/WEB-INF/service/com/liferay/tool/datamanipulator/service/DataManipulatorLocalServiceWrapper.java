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

package com.liferay.tool.datamanipulator.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DataManipulatorLocalService}.
 *
 * @author Tibor KovĂˇcs
 * @see DataManipulatorLocalService
 * @generated
 */
public class DataManipulatorLocalServiceWrapper
	implements DataManipulatorLocalService,
		ServiceWrapper<DataManipulatorLocalService> {
	public DataManipulatorLocalServiceWrapper(
		DataManipulatorLocalService dataManipulatorLocalService) {
		_dataManipulatorLocalService = dataManipulatorLocalService;
	}

	/**
	* Adds the data manipulator to the database. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator
	* @return the data manipulator that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.tool.datamanipulator.model.DataManipulator addDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.addDataManipulator(dataManipulator);
	}

	/**
	* Creates a new data manipulator with the primary key. Does not add the data manipulator to the database.
	*
	* @param id the primary key for the new data manipulator
	* @return the new data manipulator
	*/
	@Override
	public com.liferay.tool.datamanipulator.model.DataManipulator createDataManipulator(
		long id) {
		return _dataManipulatorLocalService.createDataManipulator(id);
	}

	/**
	* Deletes the data manipulator with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the data manipulator
	* @return the data manipulator that was removed
	* @throws PortalException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.tool.datamanipulator.model.DataManipulator deleteDataManipulator(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.deleteDataManipulator(id);
	}

	/**
	* Deletes the data manipulator from the database. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator
	* @return the data manipulator that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.tool.datamanipulator.model.DataManipulator deleteDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.deleteDataManipulator(dataManipulator);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dataManipulatorLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tool.datamanipulator.model.impl.DataManipulatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tool.datamanipulator.model.impl.DataManipulatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.tool.datamanipulator.model.DataManipulator fetchDataManipulator(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.fetchDataManipulator(id);
	}

	/**
	* Returns the data manipulator with the primary key.
	*
	* @param id the primary key of the data manipulator
	* @return the data manipulator
	* @throws PortalException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.tool.datamanipulator.model.DataManipulator getDataManipulator(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulator(id);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the data manipulators.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tool.datamanipulator.model.impl.DataManipulatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data manipulators
	* @param end the upper bound of the range of data manipulators (not inclusive)
	* @return the range of data manipulators
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulators(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulators(start, end);
	}

	/**
	* Returns the number of data manipulators.
	*
	* @return the number of data manipulators
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getDataManipulatorsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorsCount();
	}

	/**
	* Updates the data manipulator in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator
	* @return the data manipulator that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public com.liferay.tool.datamanipulator.model.DataManipulator updateDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.updateDataManipulator(dataManipulator);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _dataManipulatorLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_dataManipulatorLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _dataManipulatorLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this interface directly. Always use {@link com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil} to access the data manipulator local service.
	*/
	@Override
	public com.liferay.tool.datamanipulator.model.DataManipulator addDataManipulator(
		long groupId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.addDataManipulator(groupId,
			className, classPK);
	}

	@Override
	public java.util.List<?> getDataManipulatorClassNames()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorClassNames();
	}

	@Override
	public int getDataManipulatorCountByClassName(java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorCountByClassName(className);
	}

	@Override
	public int getDataManipulatorCountByG_C(long groupId,
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorCountByG_C(groupId,
			className);
	}

	@Override
	public int getDataManipulatorCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorCountByGroupId(groupId);
	}

	@Override
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulatorsByClassName(
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorsByClassName(className);
	}

	@Override
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulatorsByG_C(
		long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorsByG_C(groupId,
			className);
	}

	@Override
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulatorsByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _dataManipulatorLocalService.getDataManipulatorsByGroupId(groupId);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public DataManipulatorLocalService getWrappedDataManipulatorLocalService() {
		return _dataManipulatorLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedDataManipulatorLocalService(
		DataManipulatorLocalService dataManipulatorLocalService) {
		_dataManipulatorLocalService = dataManipulatorLocalService;
	}

	@Override
	public DataManipulatorLocalService getWrappedService() {
		return _dataManipulatorLocalService;
	}

	@Override
	public void setWrappedService(
		DataManipulatorLocalService dataManipulatorLocalService) {
		_dataManipulatorLocalService = dataManipulatorLocalService;
	}

	private DataManipulatorLocalService _dataManipulatorLocalService;
}