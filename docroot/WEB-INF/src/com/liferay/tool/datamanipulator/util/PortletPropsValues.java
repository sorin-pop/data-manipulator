/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.tool.datamanipulator.util;

//import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;
import com.liferay.tool.datamanipulator.util.PortletPropsKeys;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 * @author Andrea Di Giorgi
 * @author Bruno Basto
 */
public class PortletPropsValues {

	public static final String ARTICLES_DEFAULT_COUNT = PortletProps.get(PortletPropsKeys.ARTICLES_DEFAULT_COUNT);
	public static final String DOCUMENTS_DEFAULT_COUNT = PortletProps.get(PortletPropsKeys.DOCUMENTS_DEFAULT_COUNT);
	
}