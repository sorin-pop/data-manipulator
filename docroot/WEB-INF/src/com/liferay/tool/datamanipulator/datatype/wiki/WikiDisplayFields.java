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

package com.liferay.tool.datamanipulator.datatype.wiki;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.wiki.model.WikiNode;
import com.liferay.portlet.wiki.service.WikiNodeLocalServiceUtil;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.Field;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.entry.EntryTypeKeys;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tibor Kovács
 *
 */
public class WikiDisplayFields {

	public static final String WIKI_NODE_ID = "wiki-node-id";

	public static final String WIKI_NODE_LIST = "wiki-node-list";

	public static List<Field> getDisplayFields(long groupId)
		throws PortalException, SystemException {

		List<WikiNode> wikiNodeList = WikiNodeLocalServiceUtil.getNodes(
			groupId);

		List<KeyValuePair> wikiNodeNameId = new ArrayList<KeyValuePair>(
			wikiNodeList.size() + 1);

		wikiNodeNameId.add(
			new KeyValuePair(
				EntryTypeKeys.GENERAL_WIKI + StringPool.DASH +
					FieldKeys.DEFAULT_PARENT, "0"));

		for (WikiNode node : wikiNodeList) {
			wikiNodeNameId.add(
				new KeyValuePair(
					node.getName(), String.valueOf(node.getNodeId())));
		}

		DisplayFields fields = new DisplayFields();

		fields.addUserMultiSelect();
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_WIKI_NODE);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_WIKI_NODE);
		fields.addSeparator();

		fields.addSelectList(WIKI_NODE_LIST, wikiNodeNameId);
		fields.addInput(WIKI_NODE_ID);
		fields.addInfo(EntryTypeKeys.GENERAL_WIKI + "-ad-to-exist");
		fields.addSeparator();

		fields.addCount(EntryTypeKeys.GENERAL_WIKI_PAGE);
		fields.addUpdateLevel(EntryTypeKeys.GENERAL_WIKI_PAGE);
		fields.addDepth(EntryTypeKeys.GENERAL_WIKI_PAGE);
		fields.addSubCount(EntryTypeKeys.GENERAL_WIKI_PAGE);
		fields.addHidden(
			EntryTypeKeys.GENERAL_WIKI_PAGE + StringPool.DASH +
				FieldKeys.ADD_TO_PARENT, FieldKeys.ADD_TO_ALL_PARENT);

		fields.addSeparator();

		return fields.getDisplayFields();
	}

}