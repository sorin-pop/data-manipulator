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

package com.liferay.tool.datamanipulator.handler.content;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.bean.BeanUtil;

import com.liferay.tool.datamanipulator.annotation.Handler;
import com.liferay.tool.datamanipulator.annotation.HandlerType;
import com.liferay.tool.datamanipulator.context.RequestContext;
import com.liferay.tool.datamanipulator.displayfield.DisplayFields;
import com.liferay.tool.datamanipulator.displayfield.FieldKeys;
import com.liferay.tool.datamanipulator.handler.BaseHandler;
import com.liferay.tool.datamanipulator.util.RandomUtil;
import com.liferay.tool.datamanipulator.util.StringUtil;

/**
 * @author Yg0R2
 */
@Handler(type = HandlerType.CONTENT, displayName = "Web Content Articles")
public class JournalArticleHandler extends BaseHandler {

	public static final String AUTO_ARTICLE_ID = "auto-article-id";
	public static final String STRUCTURE_LIST = "structure-list";

	public JournalArticleHandler() throws Exception {
		super("Journal Article", "journal-article");
	}

	@Override
	public DisplayFields getDisplayFields(long groupId, long companyId) throws Exception {
		DisplayFields displayFields = new DisplayFields();
		
		displayFields.addInfo(
			getDisplayFieldName(FieldKeys.MULTI_SELECT_USER_LIST));
		displayFields.addUserMultiSelect(FieldKeys.MULTI_SELECT_USER_LIST);

		displayFields.addSeparator("");

		displayFields.addLabel(getDisplayFieldName());

		displayFields.addCheckbox(getDisplayFieldName(AUTO_ARTICLE_ID), true);

		displayFields.addSelectList(
			getDisplayFieldName(STRUCTURE_LIST),
			_getStructureNameKeys(groupId, companyId));

		displayFields.addCount(getDisplayFieldName(FieldKeys.INPUT_COUNT));

		displayFields.addUpdateCount(
			getDisplayFieldName(FieldKeys.INPUT_UPDATE_COUNT));

		/*displayFields.addSeparator("");

		displayFields.addLabel(getDisplayFieldName("display-date"));
		displayFields.addDate(getDisplayFieldName("display-date"));

		displayFields.addLabel(getDisplayFieldName("expiration-date"));
		displayFields.addDate(getDisplayFieldName("expiration-date"));

		displayFields.addLabel(getDisplayFieldName("review-date"));
		displayFields.addDate(getDisplayFieldName("review-date"));

		displayFields.addInfo(getDisplayFieldName("date-info"));
		displayFields.addSeparator("");*/

		return displayFields;
	}

	@Override
	protected Class<?>[] getAddArgClazzs() {
		if (buildNumber < 6200) {
			return new Class<?>[] {
				Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, String.class,
				Boolean.TYPE, Double.TYPE, Map.class, Map.class, String.class,
				String.class, String.class, String.class, String.class,
				Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
				Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
				Integer.TYPE, Integer.TYPE, Boolean.TYPE, Integer.TYPE,
				Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
				Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, String.class,
				File.class, Map.class, String.class, ServiceContext.class
			};
		}

		return new Class<?>[] {
			Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, Long.TYPE, String.class,
			Boolean.TYPE, Double.TYPE, Map.class, Map.class, String.class,
			String.class, String.class, String.class,
			Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
			Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
			Integer.TYPE, Integer.TYPE, Boolean.TYPE, Integer.TYPE,
			Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE,
			Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, String.class, File.class,
			Map.class, String.class, ServiceContext.class
		};
	}

	@Override
	protected String[] getAddArgNames() {
		if (buildNumber < 6200) {
			return new String[] {
				"userId", "groupId", "classNameId", "classPK", "articleId",
				"autoArticleId", "version", "titleMap", "descriptionMap",
				"content", "type", "structureId", "templateId", "layoutUuid",
				"displayDateMonth", "displayDateDay", "displayDateYear",
				"displayDateHour", "displayDateMinute", "expirationDateMonth",
				"expirationDateDay", "expirationDateYear", "expirationDateHour",
				"expirationDateMinute", "neverExpire", "reviewDateMonth",
				"reviewDateDay", "reviewDateYear", "reviewDateHour",
				"reviewDateMinute", "neverReview", "indexable", "smallImage",
				"smallImageURL", "smallImageFile", "images", "articleURL",
				"serviceContext"
			};
		}

		return new String[] {
			"userId", "groupId", "folderId", "classNameId", "classPK",
			"articleId", "autoArticleId", "version", "titleMap",
			"descriptionMap", "content", "ddmStructureKey",
			"ddmTemplateKey", "layoutUuid", "displayDateMonth",
			"displayDateDay", "displayDateYear", "displayDateHour",
			"displayDateMinute", "expirationDateMonth", "expirationDateDay",
			"expirationDateYear", "expirationDateHour", "expirationDateMinute",
			"neverExpire", "reviewDateMonth", "reviewDateDay", "reviewDateYear",
			"reviewDateHour", "reviewDateMinute", "neverReview", "indexable",
			"smallImage", "smallImageURL", "smallImageFile", "images",
			"articleURL", "serviceContext"
		};
	}

	@Override
	protected Class<?> getAddClass() throws ClassNotFoundException {
		return JournalArticleLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getAddEntrySpecifiedArgs(
		RequestContext requestContext) throws Exception {

		boolean autoArticleId = requestContext.getBoolean(
			getDisplayFieldName(AUTO_ARTICLE_ID));

		String articleId;
		if (autoArticleId) {
			articleId = StringPool.BLANK;
		}
		else {
			StringBuilder sb = new StringBuilder();
			sb.append(requestContext.getString("entryName"));
			sb.append(requestContext.getString("entryCount"));
			sb.append("-ARTICLE-ID-");
			sb.append(RandomUtil.nextString());

			articleId = sb.toString().toUpperCase();
			articleId = articleId.replace(StringPool.SPACE, StringPool.DASH);
		}

		long structureId = requestContext.getLong(
			getDisplayFieldName(STRUCTURE_LIST));

		long templateId = 0;

		String structureKey = StringPool.BLANK;
		String templateKey = StringPool.BLANK;

		if (structureId != 0) {
			DDMStructure strucrute = DDMStructureLocalServiceUtil.getStructure(
				structureId);

			structureKey = strucrute.getStructureKey();

			List<DDMTemplate> templates =
				DDMTemplateLocalServiceUtil.getTemplates(structureId);

			DDMTemplate template = templates.get(0);

			if (Validator.isNotNull(templates) && templates.size() > 0) {
				templateId = template.getTemplateId();
			}

			if (templateId != 0) {
				try {
					templateKey = (String) BeanUtil.getProperty(
						template, "templateKey");
				}
				catch (Exception e) {
					templateKey = String.valueOf(templateId);
				}
			}
		}

		//int index = RandomUtil.nextInt(JournalArticleConstants.TYPES.length);
		//String type = JournalArticleConstants.TYPES[index];

		Map<String, Object> args = new HashMap<String, Object>();

		args.put("articleId", articleId);
		args.put("articleURL", requestContext.getString("createURL"));
		args.put("autoArticleId", autoArticleId);
		args.put("classNameId", (long)0);
		args.put("classPK", (long)0);
		args.put("content", StringUtil.getLocalizedContent(""));
		args.put("layoutUuid", null);
		//args.put("neverExpire", RandomUtil.nextBoolean());
		args.put("neverExpire", true);
		//args.put("neverReview", RandomUtil.nextBoolean());
		args.put("neverReview", true);
		args.put("structureId", structureKey);
		args.put("structureKey", structureKey);
		args.put("ddmStructureKey", structureKey);
		args.put("templateId", structureKey);
		args.put("templateKey", templateKey);
		args.put("ddmTemplateKey", templateKey);
		//args.put("type", type);
		args.put("version", JournalArticleConstants.VERSION_DEFAULT);

		return args;
	}

	@Override
	protected String getAddMethodName() {
		return "addArticle";
	}

	@Override
	protected List<String> getChildHandlerNames() {
		return new ArrayList<String>(0);
	}

	@Override
	protected Object getClassPK(Object entry) {
		if (Validator.isNull(entry)) {
			return StringPool.BLANK;
		}

		return BeanUtil.getDeclaredProperty(entry, getClassPKName());
	}

	@Override
	protected String getClassPKName() {
		return "articleId";
	}

	@Override
	protected String getParentClassPKName() {
		//if (buildNumber < 6200) {
			return null;
		//}

		//return "folderId";
	}

	@Override
	protected Class<?>[] getUpdateArgClazzs() {
		if (buildNumber < 6200) {
			return new Class<?>[] {
				Long.TYPE, Long.TYPE, String.class, Double.TYPE, Map.class,
				Map.class, String.class, String.class, ServiceContext.class
			};
		}

		return new Class<?>[] {
			Long.TYPE, Long.TYPE, Long.TYPE, String.class, Double.TYPE,
			Map.class, Map.class, String.class, String.class,
			ServiceContext.class
		};
	}

	@Override
	protected String[] getUpdateArgNames() {
		if (buildNumber < 6200) {
			return new String[] {
				"userId", "groupId", "articleId", "version", "titleMap",
				"descriptionMap", "content", "layoutUuid", "serviceContext"
			};
		}

		return new String[] {
			"userId", "groupId", "folderId", "articleId", "version", "titleMap",
			"descriptionMap", "content", "layoutUuid", "serviceContext"
		};
	}

	@Override
	protected Class<?> getUpdateClass() throws ClassNotFoundException {
		return JournalArticleLocalServiceUtil.class;
	}

	@Override
	protected Map<String, Object> getUpdateEntrySpecifiedArgs(
		Object entry, RequestContext requestContext) throws Exception {

		Map<String, Object> args = new HashMap<String, Object>(1);

		args.put("content", StringUtil.getLocalizedContent(""));

		return args;
	}

	@Override
	protected String getUpdateMethodName() {
		return "updateArticle";
	}

	private List<KeyValuePair> _getStructureNameKeys(long groupId, long companyId)
		throws SystemException, PortalException {
			
		long[] groups = new long[2];  //current site plus the Global site	
		groups[0]= GroupLocalServiceUtil.getCompanyGroup(companyId).getGroupId(); // global site
		groups[1]=groupId;		// current site

			
		List<DDMStructure> structures = DDMStructureLocalServiceUtil.getStructures(groups, _journalClassNameId);	

		List<KeyValuePair> structureNameKeyPairs = new ArrayList<KeyValuePair>(
			structures.size());

		for (DDMStructure structure : structures) {
			String structureKey = String.valueOf(
				structure.getStructureId());

			String structureName = structure.getNameCurrentValue();

			structureNameKeyPairs.add(
					new KeyValuePair(structureName, structureKey));
		}

		return structureNameKeyPairs;
	}

	private long _journalClassNameId = PortalUtil.getClassNameId(
		JournalArticle.class);

}