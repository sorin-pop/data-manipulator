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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.tool.datamanipulator.model.DataManipulator;

/**
 * The persistence interface for the data manipulator service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Tibor KovĂˇcs
 * @see DataManipulatorPersistenceImpl
 * @see DataManipulatorUtil
 * @generated
 */
public interface DataManipulatorPersistence extends BasePersistence<DataManipulator> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DataManipulatorUtil} to access the data manipulator persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the data manipulators where className = &#63;.
	*
	* @param className the class name
	* @return the matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByClassName(
		java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByClassName(
		java.lang.String className, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByClassName(
		java.lang.String className, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data manipulator in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator findByClassName_First(
		java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	/**
	* Returns the first data manipulator in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator fetchByClassName_First(
		java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data manipulator in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator findByClassName_Last(
		java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	/**
	* Returns the last data manipulator in the ordered set where className = &#63;.
	*
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator fetchByClassName_Last(
		java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tool.datamanipulator.model.DataManipulator[] findByClassName_PrevAndNext(
		long id, java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	/**
	* Removes all the data manipulators where className = &#63; from the database.
	*
	* @param className the class name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByClassName(java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data manipulators where className = &#63;.
	*
	* @param className the class name
	* @return the number of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public int countByClassName(java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data manipulators where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first data manipulator in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	/**
	* Returns the first data manipulator in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last data manipulator in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	/**
	* Returns the last data manipulator in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tool.datamanipulator.model.DataManipulator[] findByGroupId_PrevAndNext(
		long id, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	/**
	* Removes all the data manipulators where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data manipulators where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data manipulator where className = &#63; and classPK = &#63; or throws a {@link com.liferay.tool.datamanipulator.NoSuchDataManipulatorException} if it could not be found.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the matching data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator findByC_C(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	/**
	* Returns the data manipulator where className = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator fetchByC_C(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data manipulator where className = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param className the class name
	* @param classPK the class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator fetchByC_C(
		java.lang.String className, long classPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the data manipulator where className = &#63; and classPK = &#63; from the database.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the data manipulator that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator removeByC_C(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	/**
	* Returns the number of data manipulators where className = &#63; and classPK = &#63;.
	*
	* @param className the class name
	* @param classPK the class p k
	* @return the number of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data manipulators where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @return the matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByG_C(
		long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByG_C(
		long groupId, java.lang.String className, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findByG_C(
		long groupId, java.lang.String className, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tool.datamanipulator.model.DataManipulator findByG_C_First(
		long groupId, java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	/**
	* Returns the first data manipulator in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator fetchByG_C_First(
		long groupId, java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tool.datamanipulator.model.DataManipulator findByG_C_Last(
		long groupId, java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	/**
	* Returns the last data manipulator in the ordered set where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator fetchByG_C_Last(
		long groupId, java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.tool.datamanipulator.model.DataManipulator[] findByG_C_PrevAndNext(
		long id, long groupId, java.lang.String className,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	/**
	* Removes all the data manipulators where groupId = &#63; and className = &#63; from the database.
	*
	* @param groupId the group ID
	* @param className the class name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_C(long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data manipulators where groupId = &#63; and className = &#63;.
	*
	* @param groupId the group ID
	* @param className the class name
	* @return the number of matching data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_C(long groupId, java.lang.String className)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the data manipulator in the entity cache if it is enabled.
	*
	* @param dataManipulator the data manipulator
	*/
	public void cacheResult(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator);

	/**
	* Caches the data manipulators in the entity cache if it is enabled.
	*
	* @param dataManipulators the data manipulators
	*/
	public void cacheResult(
		java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> dataManipulators);

	/**
	* Creates a new data manipulator with the primary key. Does not add the data manipulator to the database.
	*
	* @param id the primary key for the new data manipulator
	* @return the new data manipulator
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator create(
		long id);

	/**
	* Removes the data manipulator with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the data manipulator
	* @return the data manipulator that was removed
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator remove(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	public com.liferay.tool.datamanipulator.model.DataManipulator updateImpl(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the data manipulator with the primary key or throws a {@link com.liferay.tool.datamanipulator.NoSuchDataManipulatorException} if it could not be found.
	*
	* @param id the primary key of the data manipulator
	* @return the data manipulator
	* @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator findByPrimaryKey(
		long id)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;

	/**
	* Returns the data manipulator with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param id the primary key of the data manipulator
	* @return the data manipulator, or <code>null</code> if a data manipulator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.tool.datamanipulator.model.DataManipulator fetchByPrimaryKey(
		long id) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the data manipulators.
	*
	* @return the data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.tool.datamanipulator.model.DataManipulator> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the data manipulators from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of data manipulators.
	*
	* @return the number of data manipulators
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}