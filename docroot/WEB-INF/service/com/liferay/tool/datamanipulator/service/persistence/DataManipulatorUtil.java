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

package com.liferay.tool.datamanipulator.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.tool.datamanipulator.model.DataManipulator;

import java.util.List;

/**
 * The persistence utility for the data manipulator service. This utility wraps {@link DataManipulatorPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Tibor KovĂˇcs
 * @see DataManipulatorPersistence
 * @see DataManipulatorPersistenceImpl
 * @generated
 */
public class DataManipulatorUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(DataManipulator dataManipulator) {
		getPersistence().clearCache(dataManipulator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DataManipulator> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DataManipulator> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DataManipulator> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static DataManipulator update(DataManipulator dataManipulator)
		throws SystemException {
		return getPersistence().update(dataManipulator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static DataManipulator update(DataManipulator dataManipulator,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(dataManipulator, serviceContext);
	}

	/**
	* Returns all the data manipulators where className = &#63;.
	*
	* @param className the class name
	* @return the matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByClassName(
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByClassName(className);
	}

	/**
	* Returns a range of all the data manipulators where className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tool.datamanipulator.model.impl.DataManipulatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param start the lower bound of the range of data manipulators
	* @param end the upper bound of the range of data manipulators (not inclusive)
	* @return the range of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByClassName(
		java.lang.String className, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByClassName(className, start, end);
	}

	/**
	* Returns an ordered range of all the data manipulators where className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tool.datamanipulator.model.impl.DataManipulatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param className the class name
	* @param start the lower bound of the range of data manipulators
	* @param end the upper bound of the range of data manipulators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByClassName(
		java.lang.String className, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByClassName(className, start, end, orderByComparator);
	}

	/**
	* Returns the first data manipulator in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator findByClassName_First(
		java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence()
				   .findByClassName_First(className, orderByComparator);
	}

	/**
	* Returns the first data manipulator in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator fetchByClassName_First(
		java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByClassName_First(className, orderByComparator);
	}

	/**
	* Returns the last data manipulator in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator findByClassName_Last(
		java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence()
				   .findByClassName_Last(className, orderByComparator);
	}

	/**
	* Returns the last data manipulator in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator fetchByClassName_Last(
		java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByClassName_Last(className, orderByComparator);
	}

	/**
	* Returns the data manipulators before and after the current data manipulator in the ordered set where className = &#63;.
	*
	* @param id the primary key of the current data manipulator
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator[] findByClassName_PrevAndNext(
		long id, java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence()
				   .findByClassName_PrevAndNext(id, className, orderByComparator);
	}

	/**
	* Removes all the data manipulators where className = &#63; from the database.
	*
	* @param className the class name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByClassName(java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByClassName(className);
	}

	/**
	* Returns the number of data manipulators where className = &#63;.
	*
	* @param className the class name
	* @return the number of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static int countByClassName(java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByClassName(className);
	}

	/**
	* Returns all the data manipulators where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Returns a range of all the data manipulators where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tool.datamanipulator.model.impl.DataManipulatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of data manipulators
	* @param end the upper bound of the range of data manipulators (not inclusive)
	* @return the range of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the data manipulators where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tool.datamanipulator.model.impl.DataManipulatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of data manipulators
	* @param end the upper bound of the range of data manipulators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first data manipulator in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first data manipulator in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last data manipulator in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last data manipulator in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the data manipulators before and after the current data manipulator in the ordered set where groupId = &#63;.
	*
	* @param id the primary key of the current data manipulator
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator[] findByGroupId_PrevAndNext(
		long id, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(id, groupId, orderByComparator);
	}

	/**
	* Removes all the data manipulators where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of data manipulators where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the data manipulator where className = &#63; and classPK = &#63; or throws a {@link com.liferay.tool.datamanipulator.NoSuchDataManipulatorException} if it could not be found.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the matching data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator findByC_C(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence().findByC_C(className, classPK);
	}

	/**
	* Returns the data manipulator where className = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator fetchByC_C(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_C(className, classPK);
	}

	/**
	* Returns the data manipulator where className = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator fetchByC_C(
		java.lang.String className, long classPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_C(className, classPK, retrieveFromCache);
	}

	/**
	* Removes the data manipulator where className = &#63; and classPK = &#63; from the database.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the data manipulator that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator removeByC_C(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence().removeByC_C(className, classPK);
	}

	/**
	* Returns the number of data manipulators where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the number of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(className, classPK);
	}

	/**
	* Returns all the data manipulators where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @return the matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByG_C(
		long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupId, className);
	}

	/**
	* Returns a range of all the data manipulators where groupId = &#63; and className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tool.datamanipulator.model.impl.DataManipulatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param start the lower bound of the range of data manipulators
	* @param end the upper bound of the range of data manipulators (not inclusive)
	* @return the range of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByG_C(
		long groupId, java.lang.String className, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_C(groupId, className, start, end);
	}

	/**
	* Returns an ordered range of all the data manipulators where groupId = &#63; and className = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tool.datamanipulator.model.impl.DataManipulatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param className the class name
	* @param start the lower bound of the range of data manipulators
	* @param end the upper bound of the range of data manipulators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByG_C(
		long groupId, java.lang.String className, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_C(groupId, className, start, end, orderByComparator);
	}

	/**
	* Returns the first data manipulator in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator findByG_C_First(
		long groupId, java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence()
				   .findByG_C_First(groupId, className, orderByComparator);
	}

	/**
	* Returns the first data manipulator in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator fetchByG_C_First(
		long groupId, java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_C_First(groupId, className, orderByComparator);
	}

	/**
	* Returns the last data manipulator in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator findByG_C_Last(
		long groupId, java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence()
				   .findByG_C_Last(groupId, className, orderByComparator);
	}

	/**
	* Returns the last data manipulator in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator fetchByG_C_Last(
		long groupId, java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_C_Last(groupId, className, orderByComparator);
	}

	/**
	* Returns the data manipulators before and after the current data manipulator in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param id the primary key of the current data manipulator
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator[] findByG_C_PrevAndNext(
		long id, long groupId, java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence()
				   .findByG_C_PrevAndNext(id, groupId, className,
			orderByComparator);
	}

	/**
	* Removes all the data manipulators where groupId = &#63; and className = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_C(long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_C(groupId, className);
	}

	/**
	* Returns the number of data manipulators where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @return the number of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_C(long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_C(groupId, className);
	}

	/**
	* Caches the data manipulator in the entity cache if it is enabled.
	*
	* @param dataManipulator the data manipulator
	*/
	public static void cacheResult(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator) {
		getPersistence().cacheResult(dataManipulator);
	}

	/**
	* Caches the data manipulators in the entity cache if it is enabled.
	*
	* @param dataManipulators the data manipulators
	*/
	public static void cacheResult(
		java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> dataManipulators) {
		getPersistence().cacheResult(dataManipulators);
	}

	/**
	* Creates a new data manipulator with the primary key. Does not add the data manipulator to the database.
	*
	* @param id the primary key for the new data manipulator
	* @return the new data manipulator
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator create(
		long id) {
		return getPersistence().create(id);
	}

	/**
	* Removes the data manipulator with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the data manipulator
	* @return the data manipulator that was removed
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator remove(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence().remove(id);
	}

	public static com.liferay.tool.datamanipulator.model.DataManipulator updateImpl(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(dataManipulator);
	}

	/**
	* Returns the data manipulator with the primary key or throws a {@link com.liferay.tool.datamanipulator.NoSuchDataManipulatorException} if it could not be found.
	*
	* @param id the primary key of the data manipulator
	* @return the data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator findByPrimaryKey(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException {
		return getPersistence().findByPrimaryKey(id);
	}

	/**
	* Returns the data manipulator with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the data manipulator
	* @return the data manipulator, or <code>null</code> if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.tool.datamanipulator.model.DataManipulator fetchByPrimaryKey(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(id);
	}

	/**
	* Returns all the data manipulators.
	*
	* @return the data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the data manipulators.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.tool.datamanipulator.model.impl.DataManipulatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of data manipulators
	* @param end the upper bound of the range of data manipulators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the data manipulators from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of data manipulators.
	*
	* @return the number of data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static DataManipulatorPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (DataManipulatorPersistence)PortletBeanLocatorUtil.locate(com.liferay.tool.datamanipulator.service.ClpSerializer.getServletContextName(),
					DataManipulatorPersistence.class.getName());

			ReferenceRegistry.registerReference(DataManipulatorUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setPersistence(DataManipulatorPersistence persistence) {
	}

	private static DataManipulatorPersistence _persistence;
}