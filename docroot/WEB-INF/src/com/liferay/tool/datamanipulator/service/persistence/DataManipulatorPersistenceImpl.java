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

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.tool.datamanipulator.NoSuchDataManipulatorException;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.model.impl.DataManipulatorImpl;
import com.liferay.tool.datamanipulator.model.impl.DataManipulatorModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the data manipulator service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Tibor KovĂˇcs
 * @see DataManipulatorPersistence
 * @see DataManipulatorUtil
 * @generated
 */
public class DataManipulatorPersistenceImpl extends BasePersistenceImpl<DataManipulator>
	implements DataManipulatorPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DataManipulatorUtil} to access the data manipulator persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = DataManipulatorImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED,
			DataManipulatorImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED,
			DataManipulatorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAME =
		new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED,
			DataManipulatorImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByClassName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAME =
		new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED,
			DataManipulatorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByClassName",
			new String[] { String.class.getName() },
			DataManipulatorModelImpl.CLASSNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CLASSNAME = new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByClassName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the data manipulators where className = &#63;.
	 *
	 * @param className the class name
	 * @return the matching data manipulators
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataManipulator> findByClassName(String className)
		throws SystemException {
		return findByClassName(className, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
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
	@Override
	public List<DataManipulator> findByClassName(String className, int start,
		int end) throws SystemException {
		return findByClassName(className, start, end, null);
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
	@Override
	public List<DataManipulator> findByClassName(String className, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAME;
			finderArgs = new Object[] { className };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CLASSNAME;
			finderArgs = new Object[] { className, start, end, orderByComparator };
		}

		List<DataManipulator> list = (List<DataManipulator>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataManipulator dataManipulator : list) {
				if (!Validator.equals(className, dataManipulator.getClassName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DATAMANIPULATOR_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_1);
			}
			else if (className.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataManipulatorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				if (!pagination) {
					list = (List<DataManipulator>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataManipulator>(list);
				}
				else {
					list = (List<DataManipulator>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public DataManipulator findByClassName_First(String className,
		OrderByComparator orderByComparator)
		throws NoSuchDataManipulatorException, SystemException {
		DataManipulator dataManipulator = fetchByClassName_First(className,
				orderByComparator);

		if (dataManipulator != null) {
			return dataManipulator;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("className=");
		msg.append(className);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataManipulatorException(msg.toString());
	}

	/**
	 * Returns the first data manipulator in the ordered set where className = &#63;.
	 *
	 * @param className the class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator fetchByClassName_First(String className,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataManipulator> list = findByClassName(className, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public DataManipulator findByClassName_Last(String className,
		OrderByComparator orderByComparator)
		throws NoSuchDataManipulatorException, SystemException {
		DataManipulator dataManipulator = fetchByClassName_Last(className,
				orderByComparator);

		if (dataManipulator != null) {
			return dataManipulator;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("className=");
		msg.append(className);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataManipulatorException(msg.toString());
	}

	/**
	 * Returns the last data manipulator in the ordered set where className = &#63;.
	 *
	 * @param className the class name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator fetchByClassName_Last(String className,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByClassName(className);

		if (count == 0) {
			return null;
		}

		List<DataManipulator> list = findByClassName(className, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public DataManipulator[] findByClassName_PrevAndNext(long id,
		String className, OrderByComparator orderByComparator)
		throws NoSuchDataManipulatorException, SystemException {
		DataManipulator dataManipulator = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			DataManipulator[] array = new DataManipulatorImpl[3];

			array[0] = getByClassName_PrevAndNext(session, dataManipulator,
					className, orderByComparator, true);

			array[1] = dataManipulator;

			array[2] = getByClassName_PrevAndNext(session, dataManipulator,
					className, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataManipulator getByClassName_PrevAndNext(Session session,
		DataManipulator dataManipulator, String className,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATAMANIPULATOR_WHERE);

		boolean bindClassName = false;

		if (className == null) {
			query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_1);
		}
		else if (className.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_3);
		}
		else {
			bindClassName = true;

			query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(DataManipulatorModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindClassName) {
			qPos.add(className);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataManipulator);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataManipulator> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data manipulators where className = &#63; from the database.
	 *
	 * @param className the class name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByClassName(String className) throws SystemException {
		for (DataManipulator dataManipulator : findByClassName(className,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataManipulator);
		}
	}

	/**
	 * Returns the number of data manipulators where className = &#63;.
	 *
	 * @param className the class name
	 * @return the number of matching data manipulators
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByClassName(String className) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CLASSNAME;

		Object[] finderArgs = new Object[] { className };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATAMANIPULATOR_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_1);
			}
			else if (className.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_CLASSNAME_CLASSNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CLASSNAME_CLASSNAME_1 = "dataManipulator.className IS NULL";
	private static final String _FINDER_COLUMN_CLASSNAME_CLASSNAME_2 = "dataManipulator.className = ?";
	private static final String _FINDER_COLUMN_CLASSNAME_CLASSNAME_3 = "(dataManipulator.className IS NULL OR dataManipulator.className = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED,
			DataManipulatorImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED,
			DataManipulatorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			DataManipulatorModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the data manipulators where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching data manipulators
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataManipulator> findByGroupId(long groupId)
		throws SystemException {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<DataManipulator> findByGroupId(long groupId, int start, int end)
		throws SystemException {
		return findByGroupId(groupId, start, end, null);
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
	@Override
	public List<DataManipulator> findByGroupId(long groupId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<DataManipulator> list = (List<DataManipulator>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataManipulator dataManipulator : list) {
				if ((groupId != dataManipulator.getGroupId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DATAMANIPULATOR_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataManipulatorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<DataManipulator>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataManipulator>(list);
				}
				else {
					list = (List<DataManipulator>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public DataManipulator findByGroupId_First(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchDataManipulatorException, SystemException {
		DataManipulator dataManipulator = fetchByGroupId_First(groupId,
				orderByComparator);

		if (dataManipulator != null) {
			return dataManipulator;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataManipulatorException(msg.toString());
	}

	/**
	 * Returns the first data manipulator in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator fetchByGroupId_First(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataManipulator> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public DataManipulator findByGroupId_Last(long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchDataManipulatorException, SystemException {
		DataManipulator dataManipulator = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (dataManipulator != null) {
			return dataManipulator;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataManipulatorException(msg.toString());
	}

	/**
	 * Returns the last data manipulator in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator fetchByGroupId_Last(long groupId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<DataManipulator> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public DataManipulator[] findByGroupId_PrevAndNext(long id, long groupId,
		OrderByComparator orderByComparator)
		throws NoSuchDataManipulatorException, SystemException {
		DataManipulator dataManipulator = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			DataManipulator[] array = new DataManipulatorImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, dataManipulator,
					groupId, orderByComparator, true);

			array[1] = dataManipulator;

			array[2] = getByGroupId_PrevAndNext(session, dataManipulator,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataManipulator getByGroupId_PrevAndNext(Session session,
		DataManipulator dataManipulator, long groupId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATAMANIPULATOR_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(DataManipulatorModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataManipulator);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataManipulator> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data manipulators where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByGroupId(long groupId) throws SystemException {
		for (DataManipulator dataManipulator : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataManipulator);
		}
	}

	/**
	 * Returns the number of data manipulators where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching data manipulators
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByGroupId(long groupId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DATAMANIPULATOR_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "dataManipulator.groupId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C = new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED,
			DataManipulatorImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByC_C",
			new String[] { String.class.getName(), Long.class.getName() },
			DataManipulatorModelImpl.CLASSNAME_COLUMN_BITMASK |
			DataManipulatorModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the data manipulator where className = &#63; and classPK = &#63; or throws a {@link com.liferay.tool.datamanipulator.NoSuchDataManipulatorException} if it could not be found.
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 * @return the matching data manipulator
	 * @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a matching data manipulator could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator findByC_C(String className, long classPK)
		throws NoSuchDataManipulatorException, SystemException {
		DataManipulator dataManipulator = fetchByC_C(className, classPK);

		if (dataManipulator == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("className=");
			msg.append(className);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDataManipulatorException(msg.toString());
		}

		return dataManipulator;
	}

	/**
	 * Returns the data manipulator where className = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 * @return the matching data manipulator, or <code>null</code> if a matching data manipulator could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator fetchByC_C(String className, long classPK)
		throws SystemException {
		return fetchByC_C(className, classPK, true);
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
	@Override
	public DataManipulator fetchByC_C(String className, long classPK,
		boolean retrieveFromCache) throws SystemException {
		Object[] finderArgs = new Object[] { className, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C,
					finderArgs, this);
		}

		if (result instanceof DataManipulator) {
			DataManipulator dataManipulator = (DataManipulator)result;

			if (!Validator.equals(className, dataManipulator.getClassName()) ||
					(classPK != dataManipulator.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DATAMANIPULATOR_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_C_C_CLASSNAME_1);
			}
			else if (className.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_C_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_C_C_CLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				qPos.add(classPK);

				List<DataManipulator> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"DataManipulatorPersistenceImpl.fetchByC_C(String, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					DataManipulator dataManipulator = list.get(0);

					result = dataManipulator;

					cacheResult(dataManipulator);

					if ((dataManipulator.getClassName() == null) ||
							!dataManipulator.getClassName().equals(className) ||
							(dataManipulator.getClassPK() != classPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
							finderArgs, dataManipulator);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (DataManipulator)result;
		}
	}

	/**
	 * Removes the data manipulator where className = &#63; and classPK = &#63; from the database.
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 * @return the data manipulator that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator removeByC_C(String className, long classPK)
		throws NoSuchDataManipulatorException, SystemException {
		DataManipulator dataManipulator = findByC_C(className, classPK);

		return remove(dataManipulator);
	}

	/**
	 * Returns the number of data manipulators where className = &#63; and classPK = &#63;.
	 *
	 * @param className the class name
	 * @param classPK the class p k
	 * @return the number of matching data manipulators
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_C(String className, long classPK)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { className, classPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DATAMANIPULATOR_WHERE);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_C_C_CLASSNAME_1);
			}
			else if (className.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_C_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_C_C_CLASSNAME_2);
			}

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindClassName) {
					qPos.add(className);
				}

				qPos.add(classPK);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_C_CLASSNAME_1 = "dataManipulator.className IS NULL AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSNAME_2 = "dataManipulator.className = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSNAME_3 = "(dataManipulator.className IS NULL OR dataManipulator.className = '') AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "dataManipulator.classPK = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C = new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED,
			DataManipulatorImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByG_C",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C = new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED,
			DataManipulatorImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_C",
			new String[] { Long.class.getName(), String.class.getName() },
			DataManipulatorModelImpl.GROUPID_COLUMN_BITMASK |
			DataManipulatorModelImpl.CLASSNAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_C = new FinderPath(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_C",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the data manipulators where groupId = &#63; and className = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @return the matching data manipulators
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataManipulator> findByG_C(long groupId, String className)
		throws SystemException {
		return findByG_C(groupId, className, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	@Override
	public List<DataManipulator> findByG_C(long groupId, String className,
		int start, int end) throws SystemException {
		return findByG_C(groupId, className, start, end, null);
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
	@Override
	public List<DataManipulator> findByG_C(long groupId, String className,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C;
			finderArgs = new Object[] { groupId, className };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_C;
			finderArgs = new Object[] {
					groupId, className,
					
					start, end, orderByComparator
				};
		}

		List<DataManipulator> list = (List<DataManipulator>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (DataManipulator dataManipulator : list) {
				if ((groupId != dataManipulator.getGroupId()) ||
						!Validator.equals(className,
							dataManipulator.getClassName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DATAMANIPULATOR_WHERE);

			query.append(_FINDER_COLUMN_G_C_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_G_C_CLASSNAME_1);
			}
			else if (className.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_C_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_G_C_CLASSNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(DataManipulatorModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindClassName) {
					qPos.add(className);
				}

				if (!pagination) {
					list = (List<DataManipulator>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataManipulator>(list);
				}
				else {
					list = (List<DataManipulator>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public DataManipulator findByG_C_First(long groupId, String className,
		OrderByComparator orderByComparator)
		throws NoSuchDataManipulatorException, SystemException {
		DataManipulator dataManipulator = fetchByG_C_First(groupId, className,
				orderByComparator);

		if (dataManipulator != null) {
			return dataManipulator;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", className=");
		msg.append(className);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataManipulatorException(msg.toString());
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
	@Override
	public DataManipulator fetchByG_C_First(long groupId, String className,
		OrderByComparator orderByComparator) throws SystemException {
		List<DataManipulator> list = findByG_C(groupId, className, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public DataManipulator findByG_C_Last(long groupId, String className,
		OrderByComparator orderByComparator)
		throws NoSuchDataManipulatorException, SystemException {
		DataManipulator dataManipulator = fetchByG_C_Last(groupId, className,
				orderByComparator);

		if (dataManipulator != null) {
			return dataManipulator;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", className=");
		msg.append(className);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataManipulatorException(msg.toString());
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
	@Override
	public DataManipulator fetchByG_C_Last(long groupId, String className,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByG_C(groupId, className);

		if (count == 0) {
			return null;
		}

		List<DataManipulator> list = findByG_C(groupId, className, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public DataManipulator[] findByG_C_PrevAndNext(long id, long groupId,
		String className, OrderByComparator orderByComparator)
		throws NoSuchDataManipulatorException, SystemException {
		DataManipulator dataManipulator = findByPrimaryKey(id);

		Session session = null;

		try {
			session = openSession();

			DataManipulator[] array = new DataManipulatorImpl[3];

			array[0] = getByG_C_PrevAndNext(session, dataManipulator, groupId,
					className, orderByComparator, true);

			array[1] = dataManipulator;

			array[2] = getByG_C_PrevAndNext(session, dataManipulator, groupId,
					className, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected DataManipulator getByG_C_PrevAndNext(Session session,
		DataManipulator dataManipulator, long groupId, String className,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DATAMANIPULATOR_WHERE);

		query.append(_FINDER_COLUMN_G_C_GROUPID_2);

		boolean bindClassName = false;

		if (className == null) {
			query.append(_FINDER_COLUMN_G_C_CLASSNAME_1);
		}
		else if (className.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_G_C_CLASSNAME_3);
		}
		else {
			bindClassName = true;

			query.append(_FINDER_COLUMN_G_C_CLASSNAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(DataManipulatorModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (bindClassName) {
			qPos.add(className);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dataManipulator);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<DataManipulator> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the data manipulators where groupId = &#63; and className = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByG_C(long groupId, String className)
		throws SystemException {
		for (DataManipulator dataManipulator : findByG_C(groupId, className,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(dataManipulator);
		}
	}

	/**
	 * Returns the number of data manipulators where groupId = &#63; and className = &#63;.
	 *
	 * @param groupId the group ID
	 * @param className the class name
	 * @return the number of matching data manipulators
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByG_C(long groupId, String className)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_C;

		Object[] finderArgs = new Object[] { groupId, className };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DATAMANIPULATOR_WHERE);

			query.append(_FINDER_COLUMN_G_C_GROUPID_2);

			boolean bindClassName = false;

			if (className == null) {
				query.append(_FINDER_COLUMN_G_C_CLASSNAME_1);
			}
			else if (className.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_C_CLASSNAME_3);
			}
			else {
				bindClassName = true;

				query.append(_FINDER_COLUMN_G_C_CLASSNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (bindClassName) {
					qPos.add(className);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_G_C_GROUPID_2 = "dataManipulator.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_C_CLASSNAME_1 = "dataManipulator.className IS NULL";
	private static final String _FINDER_COLUMN_G_C_CLASSNAME_2 = "dataManipulator.className = ?";
	private static final String _FINDER_COLUMN_G_C_CLASSNAME_3 = "(dataManipulator.className IS NULL OR dataManipulator.className = '')";

	public DataManipulatorPersistenceImpl() {
		setModelClass(DataManipulator.class);
	}

	/**
	 * Caches the data manipulator in the entity cache if it is enabled.
	 *
	 * @param dataManipulator the data manipulator
	 */
	@Override
	public void cacheResult(DataManipulator dataManipulator) {
		EntityCacheUtil.putResult(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorImpl.class, dataManipulator.getPrimaryKey(),
			dataManipulator);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] {
				dataManipulator.getClassName(), dataManipulator.getClassPK()
			}, dataManipulator);

		dataManipulator.resetOriginalValues();
	}

	/**
	 * Caches the data manipulators in the entity cache if it is enabled.
	 *
	 * @param dataManipulators the data manipulators
	 */
	@Override
	public void cacheResult(List<DataManipulator> dataManipulators) {
		for (DataManipulator dataManipulator : dataManipulators) {
			if (EntityCacheUtil.getResult(
						DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
						DataManipulatorImpl.class,
						dataManipulator.getPrimaryKey()) == null) {
				cacheResult(dataManipulator);
			}
			else {
				dataManipulator.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all data manipulators.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(DataManipulatorImpl.class.getName());
		}

		EntityCacheUtil.clearCache(DataManipulatorImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the data manipulator.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DataManipulator dataManipulator) {
		EntityCacheUtil.removeResult(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorImpl.class, dataManipulator.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(dataManipulator);
	}

	@Override
	public void clearCache(List<DataManipulator> dataManipulators) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DataManipulator dataManipulator : dataManipulators) {
			EntityCacheUtil.removeResult(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
				DataManipulatorImpl.class, dataManipulator.getPrimaryKey());

			clearUniqueFindersCache(dataManipulator);
		}
	}

	protected void cacheUniqueFindersCache(DataManipulator dataManipulator) {
		if (dataManipulator.isNew()) {
			Object[] args = new Object[] {
					dataManipulator.getClassName(), dataManipulator.getClassPK()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C, args,
				dataManipulator);
		}
		else {
			DataManipulatorModelImpl dataManipulatorModelImpl = (DataManipulatorModelImpl)dataManipulator;

			if ((dataManipulatorModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataManipulator.getClassName(),
						dataManipulator.getClassPK()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C, args,
					dataManipulator);
			}
		}
	}

	protected void clearUniqueFindersCache(DataManipulator dataManipulator) {
		DataManipulatorModelImpl dataManipulatorModelImpl = (DataManipulatorModelImpl)dataManipulator;

		Object[] args = new Object[] {
				dataManipulator.getClassName(), dataManipulator.getClassPK()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C, args);

		if ((dataManipulatorModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C.getColumnBitmask()) != 0) {
			args = new Object[] {
					dataManipulatorModelImpl.getOriginalClassName(),
					dataManipulatorModelImpl.getOriginalClassPK()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C, args);
		}
	}

	/**
	 * Creates a new data manipulator with the primary key. Does not add the data manipulator to the database.
	 *
	 * @param id the primary key for the new data manipulator
	 * @return the new data manipulator
	 */
	@Override
	public DataManipulator create(long id) {
		DataManipulator dataManipulator = new DataManipulatorImpl();

		dataManipulator.setNew(true);
		dataManipulator.setPrimaryKey(id);

		return dataManipulator;
	}

	/**
	 * Removes the data manipulator with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the data manipulator
	 * @return the data manipulator that was removed
	 * @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a data manipulator with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator remove(long id)
		throws NoSuchDataManipulatorException, SystemException {
		return remove((Serializable)id);
	}

	/**
	 * Removes the data manipulator with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the data manipulator
	 * @return the data manipulator that was removed
	 * @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a data manipulator with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator remove(Serializable primaryKey)
		throws NoSuchDataManipulatorException, SystemException {
		Session session = null;

		try {
			session = openSession();

			DataManipulator dataManipulator = (DataManipulator)session.get(DataManipulatorImpl.class,
					primaryKey);

			if (dataManipulator == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataManipulatorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(dataManipulator);
		}
		catch (NoSuchDataManipulatorException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected DataManipulator removeImpl(DataManipulator dataManipulator)
		throws SystemException {
		dataManipulator = toUnwrappedModel(dataManipulator);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(dataManipulator)) {
				dataManipulator = (DataManipulator)session.get(DataManipulatorImpl.class,
						dataManipulator.getPrimaryKeyObj());
			}

			if (dataManipulator != null) {
				session.delete(dataManipulator);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (dataManipulator != null) {
			clearCache(dataManipulator);
		}

		return dataManipulator;
	}

	@Override
	public DataManipulator updateImpl(
		com.liferay.tool.datamanipulator.model.DataManipulator dataManipulator)
		throws SystemException {
		dataManipulator = toUnwrappedModel(dataManipulator);

		boolean isNew = dataManipulator.isNew();

		DataManipulatorModelImpl dataManipulatorModelImpl = (DataManipulatorModelImpl)dataManipulator;

		Session session = null;

		try {
			session = openSession();

			if (dataManipulator.isNew()) {
				session.save(dataManipulator);

				dataManipulator.setNew(false);
			}
			else {
				session.merge(dataManipulator);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !DataManipulatorModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((dataManipulatorModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataManipulatorModelImpl.getOriginalClassName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAME,
					args);

				args = new Object[] { dataManipulatorModelImpl.getClassName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CLASSNAME,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CLASSNAME,
					args);
			}

			if ((dataManipulatorModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataManipulatorModelImpl.getOriginalGroupId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { dataManipulatorModelImpl.getGroupId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((dataManipulatorModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						dataManipulatorModelImpl.getOriginalGroupId(),
						dataManipulatorModelImpl.getOriginalClassName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C,
					args);

				args = new Object[] {
						dataManipulatorModelImpl.getGroupId(),
						dataManipulatorModelImpl.getClassName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_C,
					args);
			}
		}

		EntityCacheUtil.putResult(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
			DataManipulatorImpl.class, dataManipulator.getPrimaryKey(),
			dataManipulator);

		clearUniqueFindersCache(dataManipulator);
		cacheUniqueFindersCache(dataManipulator);

		return dataManipulator;
	}

	protected DataManipulator toUnwrappedModel(DataManipulator dataManipulator) {
		if (dataManipulator instanceof DataManipulatorImpl) {
			return dataManipulator;
		}

		DataManipulatorImpl dataManipulatorImpl = new DataManipulatorImpl();

		dataManipulatorImpl.setNew(dataManipulator.isNew());
		dataManipulatorImpl.setPrimaryKey(dataManipulator.getPrimaryKey());

		dataManipulatorImpl.setId(dataManipulator.getId());
		dataManipulatorImpl.setGroupId(dataManipulator.getGroupId());
		dataManipulatorImpl.setClassName(dataManipulator.getClassName());
		dataManipulatorImpl.setClassPK(dataManipulator.getClassPK());

		return dataManipulatorImpl;
	}

	/**
	 * Returns the data manipulator with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the data manipulator
	 * @return the data manipulator
	 * @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a data manipulator with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDataManipulatorException, SystemException {
		DataManipulator dataManipulator = fetchByPrimaryKey(primaryKey);

		if (dataManipulator == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataManipulatorException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return dataManipulator;
	}

	/**
	 * Returns the data manipulator with the primary key or throws a {@link com.liferay.tool.datamanipulator.NoSuchDataManipulatorException} if it could not be found.
	 *
	 * @param id the primary key of the data manipulator
	 * @return the data manipulator
	 * @throws com.liferay.tool.datamanipulator.NoSuchDataManipulatorException if a data manipulator with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator findByPrimaryKey(long id)
		throws NoSuchDataManipulatorException, SystemException {
		return findByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns the data manipulator with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the data manipulator
	 * @return the data manipulator, or <code>null</code> if a data manipulator with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		DataManipulator dataManipulator = (DataManipulator)EntityCacheUtil.getResult(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
				DataManipulatorImpl.class, primaryKey);

		if (dataManipulator == _nullDataManipulator) {
			return null;
		}

		if (dataManipulator == null) {
			Session session = null;

			try {
				session = openSession();

				dataManipulator = (DataManipulator)session.get(DataManipulatorImpl.class,
						primaryKey);

				if (dataManipulator != null) {
					cacheResult(dataManipulator);
				}
				else {
					EntityCacheUtil.putResult(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
						DataManipulatorImpl.class, primaryKey,
						_nullDataManipulator);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(DataManipulatorModelImpl.ENTITY_CACHE_ENABLED,
					DataManipulatorImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return dataManipulator;
	}

	/**
	 * Returns the data manipulator with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the data manipulator
	 * @return the data manipulator, or <code>null</code> if a data manipulator with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public DataManipulator fetchByPrimaryKey(long id) throws SystemException {
		return fetchByPrimaryKey((Serializable)id);
	}

	/**
	 * Returns all the data manipulators.
	 *
	 * @return the data manipulators
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<DataManipulator> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<DataManipulator> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	@Override
	public List<DataManipulator> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<DataManipulator> list = (List<DataManipulator>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DATAMANIPULATOR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DATAMANIPULATOR;

				if (pagination) {
					sql = sql.concat(DataManipulatorModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<DataManipulator>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<DataManipulator>(list);
				}
				else {
					list = (List<DataManipulator>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the data manipulators from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (DataManipulator dataManipulator : findAll()) {
			remove(dataManipulator);
		}
	}

	/**
	 * Returns the number of data manipulators.
	 *
	 * @return the number of data manipulators
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DATAMANIPULATOR);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the data manipulator persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.tool.datamanipulator.model.DataManipulator")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<DataManipulator>> listenersList = new ArrayList<ModelListener<DataManipulator>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<DataManipulator>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(DataManipulatorImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_DATAMANIPULATOR = "SELECT dataManipulator FROM DataManipulator dataManipulator";
	private static final String _SQL_SELECT_DATAMANIPULATOR_WHERE = "SELECT dataManipulator FROM DataManipulator dataManipulator WHERE ";
	private static final String _SQL_COUNT_DATAMANIPULATOR = "SELECT COUNT(dataManipulator) FROM DataManipulator dataManipulator";
	private static final String _SQL_COUNT_DATAMANIPULATOR_WHERE = "SELECT COUNT(dataManipulator) FROM DataManipulator dataManipulator WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dataManipulator.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DataManipulator exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DataManipulator exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(DataManipulatorPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"id"
			});
	private static DataManipulator _nullDataManipulator = new DataManipulatorImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<DataManipulator> toCacheModel() {
				return _nullDataManipulatorCacheModel;
			}
		};

	private static CacheModel<DataManipulator> _nullDataManipulatorCacheModel = new CacheModel<DataManipulator>() {
			@Override
			public DataManipulator toEntityModel() {
				return _nullDataManipulator;
			}
		};
}