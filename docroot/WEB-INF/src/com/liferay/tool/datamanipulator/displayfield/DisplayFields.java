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

package com.liferay.tool.datamanipulator.displayfield;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yg0R2
 */
public class DisplayFields {

	public DisplayFields() {
		_names = new ArrayList<String>();

		_nameTypeMap = new HashMap<String, String>();

		_nameValueMap = new HashMap<String,Object>();
	}

	public void addAddToParentSelect(String name) {
		List<KeyValuePair> nameValuePairs = new ArrayList<KeyValuePair>();

		nameValuePairs.add(
			new KeyValuePair(
				name + FieldKeys.ADD_TO_ALL_PARENT,
				FieldKeys.ADD_TO_ALL_PARENT));

		nameValuePairs.add(
			new KeyValuePair(
				name + FieldKeys.ADD_TO_INNERMOST_PARENT,
				FieldKeys.ADD_TO_INNERMOST_PARENT));

		nameValuePairs.add(
			new KeyValuePair(
				name + FieldKeys.ADD_TO_RANDOM_PARENT,
				FieldKeys.ADD_TO_RANDOM_PARENT));

		_put(name, FieldKeys.SELECT_ADD_TO_PARENT, nameValuePairs);
	}

	public void addAll(DisplayFields displayFields) {
		_names.addAll(displayFields._getDisplayFieldNames());

		_nameTypeMap.putAll(displayFields._getDisplayFields());

		_nameValueMap.putAll(displayFields._getDisplayFieldValues());
	}

	public void addCheckbox(String name) {
		addCheckbox(name, false);
	}

	public void addCheckbox(String name, boolean checked) {
		_put(name, FieldKeys.CHECK_BOX, checked);
	}

	public void addCount(String name) {
		addCount(name, false);
	}

	public void addCount(String name, boolean required) {
		if (required) {
			_put(name, FieldKeys.INPUT_REQUIRED_COUNT, 0);
		}
		else {
			_put(name, FieldKeys.INPUT_COUNT, 0);
		}
	}

	public void addDate(String name) {
		_put(name, FieldKeys.DATE, StringPool.BLANK);
	}

	public void addDepth(String name) {
		addDepth(name, false);
	}

	public void addDepth(String name, boolean required) {
		if (required) {
			_put(name, FieldKeys.INPUT_REQUIRED_DEPTH, 0);
		}
		else {
			_put(name, FieldKeys.INPUT_DEPTH, 0);
		}
	}

	public void addFile(String name) {
		addFile(name, false);
	}

	public void addFile(String name, boolean required) {
		if (required) {
			_put(name, FieldKeys.FILE_REQUIRED, StringPool.BLANK);
		}
		else {
			_put(name, FieldKeys.FILE, StringPool.BLANK);
		}
	}

	public void addHidden(String name) {
		_put(name, FieldKeys.HIDDEN, StringPool.BLANK);
	}

	public void addHidden(String name, Object value) {
		_put(name, FieldKeys.HIDDEN, value);
	}

	public void addInfo(String name) {
		_put(name, FieldKeys.INFO, StringPool.BLANK);
	}

	public void addInput(String name) {
		addInput(name, StringPool.BLANK, false);
	}

	public void addInput(String name, boolean required) {
		addInput(name, StringPool.BLANK, required);
	}

	public void addInput(String name, String value) {
		addInput(name, value, false);
	}

	public void addInput(String name, String value, boolean required) {
		if (required) {
			_put(name, FieldKeys.INPUT_REQUIRED, value);
		}
		else {
			_put(name, FieldKeys.INPUT, value);
		}
	}

	public void addLabel(String name) {
		_put(name, FieldKeys.LABEL, StringPool.BLANK);
	}

	public void addMultiSelectList(String name, List<KeyValuePair> values) {
		_put(name, FieldKeys.MULTI_SELECT_LIST, values);
	}

	public void addOrganizationMultiSelect(String name) throws SystemException {
		List<Organization> orgs =
			OrganizationLocalServiceUtil.getOrganizations(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<KeyValuePair> values = new ArrayList<KeyValuePair>(orgs.size());

		for (Organization org : orgs) {
			long orgId = org.getOrganizationId();
			String orgName = org.getName();

			if (Validator.isNull(orgId) || Validator.isNull(orgName)) {
				continue;
			}

			values.add(new KeyValuePair(orgName, String.valueOf(orgId)));
		}

		addMultiSelectList(name, values);
	}

	public void addRoleMultiSelect(String name) throws SystemException {
		List<Role> roles = RoleLocalServiceUtil.getRoles(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<KeyValuePair> values = new ArrayList<KeyValuePair>(roles.size());

		for (Role role : roles) {
			if (role.getType() == RoleConstants.TYPE_REGULAR) {
				long roleId = role.getRoleId();
				String roleName = role.getName();

				if (Validator.isNull(roleId) || Validator.isNull(roleName)) {
					continue;
				}

				values.add(new KeyValuePair(roleName, String.valueOf(roleId)));
			}
		}

		addMultiSelectList(name, values);
	}

	public void addSelectGroupedList(
		String name, Map<String, List<KeyValuePair>> values) {

		_put(name, FieldKeys.SELECT_GROUPED_LIST, values);
	}

	public void addSelectList(String name, List<KeyValuePair> values) {
		_put(name, FieldKeys.SELECT_LIST, values);
	}

	public void addSeparator(String name) {
		_put(name, FieldKeys.SEPARATOR, StringPool.BLANK);
	}

	public void addSiteMultiSelect(String name) throws SystemException {
		List<Group> groups =
			GroupLocalServiceUtil.getGroups(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<KeyValuePair> values = new ArrayList<KeyValuePair>(groups.size());

		for (Group group : groups) {
			//if ((group.isCommunity() || group.isSite()) &&
			//	!group.isControlPanel() && !group.isStaged()) {
            if (group.isRegularSite() && !group.isControlPanel() && !group.isStaged()) {
				long siteId = group.getGroupId();
				String siteName = group.getName();

				if (Validator.isNull(siteId) || Validator.isNull(siteName)) {
					continue;
				}

				values.add(new KeyValuePair(siteName, String.valueOf(siteId)));
			}
		}

		addMultiSelectList(name, values);
	}

	public void addSubCount(String name) {
		addSubCount(name, false);
	}

	public void addSubCount(String name, boolean required) {
		if (required) {
			_put(name, FieldKeys.INPUT_REQUIRED_SUBCOUNT, 0);
		}
		else {
			_put(name, FieldKeys.INPUT_SUBCOUNT, 0);
		}
	}

	public void addTime(String name) {
		_put(name, FieldKeys.TIME, StringPool.BLANK);
	}

	public void addUserGroupMultiSelect(String name) throws SystemException {
		List<UserGroup> userGroups =
			UserGroupLocalServiceUtil.getUserGroups(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<KeyValuePair> values = new ArrayList<KeyValuePair>(
			userGroups.size());

		for (UserGroup userGroup : userGroups) {
			long userGroupId = userGroup.getUserGroupId();
			String userGroupName = userGroup.getName();

			if (Validator.isNull(userGroupId) ||
				Validator.isNull(userGroupName)) {

				continue;
			}

			values.add(
				new KeyValuePair(userGroupName, String.valueOf(userGroupId)));
		}

		addMultiSelectList(name, values);
	}

	public void addUserMultiSelect(String name) throws SystemException {
		List<User> users = UserLocalServiceUtil.getUsers(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<KeyValuePair> values = new ArrayList<KeyValuePair>(users.size());

		for (User user : users) {
			long userId = user.getUserId();
			String userName = user.getFullName();

			if (Validator.isNull(userId) || Validator.isNull(userName)) {
				continue;
			}

			values.add(new KeyValuePair(userName, String.valueOf(userId)));
		}

		addMultiSelectList(name, values);
	}

	public void addUpdateCount(String name) {
		addUpdateCount(name, false);
	}

	public void addUpdateCount(String name, boolean required) {
		if (required) {
			_put(name, FieldKeys.INPUT_REQUIRED_UPDATE_COUNT, 0);
		}
		else {
			_put(name, FieldKeys.INPUT_UPDATE_COUNT, 0);
		}
	}

	public List<String> getDisplayFieldNames() {
		return _names;
	}

	public String getDisplayFieldType(String name) {
		return _nameTypeMap.get(name);
	}

	public Object getDisplayFieldValue(String name) {
		return _nameValueMap.get(name);
	}

	private List<String> _getDisplayFieldNames() {
		return _names;
	}

	private Map<String, String> _getDisplayFields() {
		return _nameTypeMap;
	}

	private Map<String, Object> _getDisplayFieldValues() {
		return _nameValueMap;
	}

	private void _put(String name, String type, Object values) {
		if (Validator.isNull(values)) {
			values = StringPool.BLANK;
		}

		_names.add(name);

		_nameTypeMap.put(name, type);

		_nameValueMap.put(name, values);
	}

	private List<String> _names;
	private Map<String, String> _nameTypeMap;
	private Map<String, Object> _nameValueMap;

}