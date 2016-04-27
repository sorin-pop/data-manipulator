/**
 * Copyright (c) 2014-present Yg0R2. All rights reserved.
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

package com.liferay.tool.datamanipulator.handler.portal;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
//import com.liferay.portal.kernel.staging.StagingUtil;
import com.liferay.exportimport.kernel.service.StagingLocalServiceUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liferay.tool.datamanipulator.annotation.Handler;
import com.liferay.tool.datamanipulator.annotation.HandlerType;
import com.liferay.tool.datamanipulator.context.RequestContext;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.handler.BaseHandler;
import com.liferay.tool.datamanipulator.handler.content.LayoutHandler;
import com.liferay.tool.datamanipulator.handler.util.HandlerUtil;

/**
 * @author Yg0R2
 */
//@Handler(type = HandlerType.PORTAL, displayName = "Site Handler")
@Handler(type = HandlerType.PORTAL, displayName = "Sites")
public class SiteHandler extends BaseHandler {

	public static final String ACTIVATE_STAGING_CHECKBOX =
		"activate-staging-checkbox";

	public static final String ADD_HOME_PAGE_CHECKBOX =
		"add-home-page-checkbox";

	public static final String SITE_CLASS_NAME = "site-class-name";
	public static final String SITE_CLASS_PK = "site-class-pk";
	public static final String SITE_TEMPLATE_LIST = "site-template-list";
	public static final String SITE_TYPE_SELECT_LIST = "site-type-select-list";

	public SiteHandler() throws Exception {
		super("Site", "site");

		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		updateLayoutSetPrototypesLinksClass = portalClassLoader.loadClass(
			"com.liferay.sites.kernel.util.SitesUtil");

		updateLayoutSetPrototypesLinksMethod =
			updateLayoutSetPrototypesLinksClass.getMethod(
				"updateLayoutSetPrototypesLinks",
				new Class<?>[] {
					Group.class, Long.TYPE, Long.TYPE, Boolean.TYPE,
					Boolean.TYPE
				});
	}

	private Class<?> updateLayoutSetPrototypesLinksClass;
	private Method updateLayoutSetPrototypesLinksMethod;

	@Override
	public Object addEntry(RequestContext requestContext) throws Exception {
		Group group = (Group) super.addEntry(requestContext);

		long privateLayoutSetPrototypeId =
			requestContext.getLong(
				getDisplayFieldName("private-" + SITE_TEMPLATE_LIST));

		long publicLayoutSetPrototypeId =
			requestContext.getLong(
				getDisplayFieldName("public-" + SITE_TEMPLATE_LIST));

		updateLayoutSetPrototypesLinksMethod.invoke(
			updateLayoutSetPrototypesLinksClass,
			new Object[] {
				group, publicLayoutSetPrototypeId, privateLayoutSetPrototypeId,
				true, false
			});

		UserLocalServiceUtil.addGroupUsers(
			group.getGroupId(), requestContext.getUserIds());

		boolean addHomePage = requestContext.getBoolean(
			getDisplayFieldName(ADD_HOME_PAGE_CHECKBOX));
			
		if (addHomePage) {
			RequestContext layoouRequestContext = requestContext.clone();

			layoouRequestContext.setGroupId(group.getGroupId());

			LayoutHandler layoutHandler = new LayoutHandler();

			layoutHandler.addEntry(layoouRequestContext);
		}

		boolean activateStaging = requestContext.getBoolean(
			getDisplayFieldName(ACTIVATE_STAGING_CHECKBOX));

		if (activateStaging) {
			long userId = requestContext.getUserId();

			//StagingUtil.enableLocalStaging(userId, null, group, false, false,HandlerUtil.getServiceContext(group.getGroupId(), userId));
			StagingLocalServiceUtil.enableLocalStaging(userId, group, false, false, HandlerUtil.getServiceContext(group.getGroupId(), userId));
		}

		return group;
	}

	@Override
	public DisplayFields getDisplayFields(long groupId, long companyId) throws Exception {
		DisplayFields displayFields = new DisplayFields();
		
		displayFields.addInfo(
			getDisplayFieldName(FieldKeys.MULTI_SELECT_USER_LIST));

		displayFields.addUserMultiSelect(FieldKeys.MULTI_SELECT_USER_LIST);

		displayFields.addSeparator("");

		//displayFields.addLabel(getDisplayFieldName());

		displayFields.addSelectList(
			getDisplayFieldName(SITE_TYPE_SELECT_LIST), _getSiteTypes());

		displayFields.addSelectList(
			getDisplayFieldName("public-" + SITE_TEMPLATE_LIST),
			_getSiteTemplates());

		displayFields.addSelectList(
			getDisplayFieldName("private-" + SITE_TEMPLATE_LIST),
			_getSiteTemplates());

		displayFields.addCheckbox(
			getDisplayFieldName(ADD_HOME_PAGE_CHECKBOX), true);

		displayFields.addCheckbox(
			getDisplayFieldName(ACTIVATE_STAGING_CHECKBOX), false);

		displayFields.addCount(
			getDisplayFieldName(FieldKeys.INPUT_COUNT), true);

		/*if (buildNumber >= 6200) {
			displayFields.addDepth(
				getDisplayFieldName(FieldKeys.INPUT_DEPTH));

			displayFields.addSubCount(
				getDisplayFieldName(FieldKeys.INPUT_SUBCOUNT));
		}*/

		return displayFields;
	}

	@Override
	protected Class<?>[] getAddArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, Long.TYPE, String.class, Long.TYPE, Long.TYPE, String.class,
			String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, String.class, Boolean.TYPE,
			Boolean.TYPE, ServiceContext.class
		};
	}

	@Override
	protected String[] getAddArgNames() {
		return new String[] {
			"userId", "parentGroupId", "className", "classPK", "liveGroupId", "name",
			"description", "type", "manualMembership", "membershipRestriction", "friendlyURL", "site", "active",
			"serviceContext"
		};
	}

	@Override
	protected Class<?> getAddClass() throws ClassNotFoundException {
		return GroupLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getAddEntrySpecifiedArgs(
		RequestContext requestContext) throws Exception {

		String className;
		if (requestContext.contains(getDisplayFieldName(SITE_CLASS_NAME))) {
			className = requestContext.getString(
				getDisplayFieldName(SITE_CLASS_NAME));
		}
		else {
			className = Group.class.getName();
		}

		long classPK;
		if (requestContext.contains(getDisplayFieldName(SITE_CLASS_PK))) {
			classPK = requestContext.getLong(
				getDisplayFieldName(SITE_CLASS_PK));
		}
		else {
			classPK = (long)0;
		}

		int type = requestContext.getInteger(
			getDisplayFieldName(SITE_TYPE_SELECT_LIST),
			GroupConstants.TYPE_SITE_OPEN);

		Map<String, Object> args = new HashMap<String, Object>(6);

		args.put("active", true);
		args.put("className", className);
		args.put("classPK", classPK);
		args.put("liveGroupId", GroupConstants.DEFAULT_LIVE_GROUP_ID);
		args.put("site", true);
		args.put("type", type);

		return args;
	}

	@Override
	protected String getAddMethodName() {
		return "addGroup";
	}

	@Override
	protected List<String> getChildHandlerNames() {
		return new ArrayList<String>(0);
	}

	@Override
	protected Object getClassPK(Object entry) {
		if (Validator.isNull(entry)) {
			return 0L;
		}

		return ((Group)entry).getClassPK();
	}

	@Override
	protected String getClassPKName() {
		return "groupId";
	}

	@Override
	protected String getParentClassPKName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getUpdateArgClazzs() {
		return new Class<?>[] {
			Long.TYPE, String.class, String.class, Integer.TYPE, String.class,
			Boolean.TYPE, ServiceContext.class
		};
	}

	@Override
	protected String[] getUpdateArgNames() {
		return new String[] {
			"groupId", "name", "description", "type", "friendlyURL", "active",
			"serviceContext"
		};
	}

	@Override
	protected Class<?> getUpdateClass() throws ClassNotFoundException {
		return GroupLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getUpdateEntrySpecifiedArgs(
		Object entry, RequestContext requestContext) throws Exception {

		return new HashMap<String, Object>(0);
	}

	@Override
	protected String getUpdateMethodName() {
		return "updateGroup";
	}

	private List<KeyValuePair> _getSiteTemplates() throws SystemException {
		List<KeyValuePair> siteTemplates = new ArrayList<KeyValuePair>();

		siteTemplates.add(new KeyValuePair("", ""));

		List<LayoutSetPrototype> layoutSetPrototypes =
			LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototypes(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
			siteTemplates.add(
				new KeyValuePair(
					layoutSetPrototype.getNameCurrentValue(),
					String.valueOf(
						layoutSetPrototype.getLayoutSetPrototypeId())));
		}

		return siteTemplates;
	}

	private  List<KeyValuePair> _getSiteTypes() throws SystemException {
		List<KeyValuePair> siteTypes = new ArrayList<KeyValuePair>();

		siteTypes.add(
			new KeyValuePair(
				GroupConstants.TYPE_SITE_OPEN_LABEL,
				String.valueOf(GroupConstants.TYPE_SITE_OPEN)));

		siteTypes.add(
			new KeyValuePair(
				GroupConstants.TYPE_SITE_PRIVATE_LABEL,
				String.valueOf(GroupConstants.TYPE_SITE_PRIVATE)));

		siteTypes.add(
			new KeyValuePair(
				GroupConstants.TYPE_SITE_RESTRICTED_LABEL,
				String.valueOf(GroupConstants.TYPE_SITE_RESTRICTED)));

		return siteTypes;
	}

}