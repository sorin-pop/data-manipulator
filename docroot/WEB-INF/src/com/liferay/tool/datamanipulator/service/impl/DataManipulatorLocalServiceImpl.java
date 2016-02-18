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

package com.liferay.tool.datamanipulator.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.tool.datamanipulator.model.DataManipulator;
import com.liferay.tool.datamanipulator.model.impl.DataManipulatorImpl;
import com.liferay.tool.datamanipulator.service.base.DataManipulatorLocalServiceBaseImpl;

import java.util.List;

/**
 * The implementation of the data manipulator local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.tool.datamanipulator.service.DataManipulatorLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Tibor KovĂˇcs
 * @see com.liferay.tool.datamanipulator.service.base.DataManipulatorLocalServiceBaseImpl
 * @see com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil
 */
public class DataManipulatorLocalServiceImpl
	extends DataManipulatorLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.tool.datamanipulator.service.DataManipulatorLocalServiceUtil} to access the data manipulator local service.
	 */
	public DataManipulator addDataManipulator(
			long groupId, String className, long classPK)
		throws SystemException {

		long dataManipulatorId = counterLocalService.increment(
			DataManipulator.class.getName());

		DataManipulator dataManipulator = dataManipulatorPersistence.create(
			dataManipulatorId);

		dataManipulator.setGroupId(groupId);
		dataManipulator.setClassName(className);
		dataManipulator.setClassPK(classPK);

		dataManipulatorPersistence.update(dataManipulator, false);

		return dataManipulator;
	}

	public List<?> getDataManipulatorClassNames() throws SystemException {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DataManipulator.class, DataManipulatorImpl.TABLE_NAME);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil. groupProperty("className"));

		return dataManipulatorPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public int getDataManipulatorCountByClassName(String className)
		throws SystemException {

		return dataManipulatorPersistence.countByClassName(className);
	}

	public int getDataManipulatorCountByG_C(long groupId, String className)
		throws SystemException {

		return dataManipulatorPersistence.countByG_C(groupId, className);
	}

	public int getDataManipulatorCountByGroupId(long groupId)
		throws SystemException {

		return dataManipulatorPersistence.countByGroupId(groupId);
	}

	public List<DataManipulator> getDataManipulatorsByClassName(
			String className)
		throws SystemException {

		return dataManipulatorPersistence.findByClassName(className);
	}

	public List<DataManipulator> getDataManipulatorsByG_C(
			long groupId, String className)
		throws SystemException {

		return dataManipulatorPersistence.findByG_C(groupId, className);
	}

	public List<DataManipulator> getDataManipulatorsByGroupId(long groupId)
		throws SystemException {

		return dataManipulatorPersistence.findByGroupId(groupId);
	}

}