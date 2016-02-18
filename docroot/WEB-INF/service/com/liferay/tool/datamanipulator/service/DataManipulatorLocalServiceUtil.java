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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for DataManipulator. This utility wraps
 * {@link com.liferay.tool.datamanipulator.service.impl.DataManipulatorLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Tibor KovĂˇcs
 * @see DataManipulatorLocalService
 * @see com.liferay.tool.datamanipulator.service.base.DataManipulatorLocalServiceBaseImpl
 * @see com.liferay.tool.datamanipulator.service.impl.DataManipulatorLocalServiceImpl
 * @generated
 */
public class DataManipulatorLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.tool.datamanipulator.service.impl.DataManipulatorLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the data manipulator to the database. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator
	* @return the data manipulator that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator addDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addDataManipulator(dataManipulator);
	}

	/**
	* Creates a new data manipulator with the primary key. Does not add the data manipulator to the database.
	*
	* @param id the primary key for the new data manipulator
	* @return the new data manipulator
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator createDataManipulator(
		long id) {
		return getService().createDataManipulator(id);
	}

	/**
	* Deletes the data manipulator with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the data manipulator
	* @return the data manipulator that was removed
	* @throws PortalException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator deleteDataManipulator(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteDataManipulator(id);
	}

	/**
	* Deletes the data manipulator from the database. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator
	* @return the data manipulator that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator deleteDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteDataManipulator(dataManipulator);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.tool.datamanipulator.model.DataManipulator fetchDataManipulator(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchDataManipulator(id);
	}

	/**
	* Returns the data manipulator with the primary key.
	*
	* @param id the primary key of the data manipulator
	* @return the data manipulator
	* @throws PortalException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator getDataManipulator(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulator(id);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulators(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulators(start, end);
	}

	/**
	* Returns the number of data manipulators.
	*
	* @return the number of data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static int getDataManipulatorsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorsCount();
	}

	/**
	* Updates the data manipulator in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dataManipulator the data manipulator
	* @return the data manipulator that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator updateDataManipulator(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateDataManipulator(dataManipulator);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this interface directly. Always use {@link com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil} to access the data manipulator local service.
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator addDataManipulator(
		long groupId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addDataManipulator(groupId, className, classPK);
	}

	public static java.util.List<?> getDataManipulatorClassNames()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorClassNames();
	}

	public static int getDataManipulatorCountByClassName(
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorCountByClassName(className);
	}

	public static int getDataManipulatorCountByG_C(long groupId,
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorCountByG_C(groupId, className);
	}

	public static int getDataManipulatorCountByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorCountByGroupId(groupId);
	}

	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulatorsByClassName(
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorsByClassName(className);
	}

	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulatorsByG_C(
		long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorsByG_C(groupId, className);
	}

	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> getDataManipulatorsByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getDataManipulatorsByGroupId(groupId);
	}

	public static void clearService() {
		_service = null;
	}

	public static DataManipulatorLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					DataManipulatorLocalService.class.getName());

			if (invokableLocalService instanceof DataManipulatorLocalService) {
				_service = (DataManipulatorLocalService)invokableLocalService;
			}
			else {
				_service = new DataManipulatorLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(DataManipulatorLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(DataManipulatorLocalService service) {
	}

	private static DataManipulatorLocalService _service;
}