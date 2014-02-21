/**
 * Copyright (c) 2014 Rivet Logic Corporation. All rights reserved.
 */

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

package com.hrportal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hrportal.service.base.WebArticleHelperLocalServiceBaseImpl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.theme.NavItem;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetVocabulary;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetVocabularyLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

/**
 * The implementation of the Web Article helper local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.hrportal.service.WebArticleHelperLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Rivet Logic
 * @see com.hrportal.service.base.WebArticleHelperLocalServiceBaseImpl
 * @see com.hrportal.service.WebArticleHelperLocalServiceUtil
 */
public class WebArticleHelperLocalServiceImpl
	extends WebArticleHelperLocalServiceBaseImpl {
	/**
	 * The code here was moved from navigation.vm in our blue-gray 
	 * theme. This is a workaround to bypass bugs in Liferay Security
	 * Manager that prevented proper execution of this code 
	 * in the navigation.vm
	 */
	public List<NavItem> getNavItemsFromLayouts(List<Layout> newMainLayouts, HttpServletRequest request,
			ThemeDisplay themeDisplay, long ancestorPlid, long ancestorLayoutId) {
		List<NavItem> navItems = new ArrayList<NavItem>();		
		navItems = NavItem.fromLayouts(request, newMainLayouts, null);
		return navItems;
	}
	
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.rosettastone.service.WebArticleHelperLocalServiceUtil} to access the Web Article helper local service.
	 */
	
	public List<JournalArticle> getJournalArticlesByCategoryIds(List<Long> cIds) {
		//ServiceContext sc = ServiceContextThreadLocal.getServiceContext();
		
		ArrayList<JournalArticle> jaList = new ArrayList<JournalArticle>();
		
		if (cIds != null && cIds.size() > 0) {
			AssetEntryQuery q = new AssetEntryQuery();
			q.setAllCategoryIds(ArrayUtil.toArray(cIds.toArray(new Long[cIds.size()])));
			q.setClassName(JournalArticle.class.getName());
			
			try {
				List<AssetEntry> assets = AssetEntryLocalServiceUtil.getEntries(q);
				for (AssetEntry asset:assets) {
					JournalArticle ja = JournalArticleLocalServiceUtil.getLatestArticle(asset.getClassPK());
					if (!jaList.contains(ja)) {
						jaList.add(ja);
					}
				}
			} catch (Exception e) {
				_log.error("", e);
			}
		}
		
		return jaList;
	}
	
	public List<JournalArticle> getJournalArticlesByCategoryXPaths(List<String> catXPathList) {
		List<Long> catIdList = convertCategoryHierarchyToIds(catXPathList);
		return getJournalArticlesByCategoryIds(catIdList);
	}	
	
	public ArrayList<Long> convertCategoryHierarchyToIds(List<String> catXPathArr) {
		ArrayList<Long> catIdList = new ArrayList<Long>(catXPathArr.size());
		
		Document doc = getAssetCategoryHierarchyDocument();
		
		for (String catXPath:catXPathArr) {
			List<Node> nodes = doc.selectNodes(catXPath);
			for (Node node:nodes) {
				String id = ((Element)node).attributeValue("id");
				if (id == null || id.length() > 0) {
					catIdList.add(Long.parseLong(id));
				}
			}
		}
		
		return catIdList;
	}
	
	public void clearCachedCategoryHierarchy() {
		//HrPortalCacheUtil.removeResult(ASSET_CATEGORY_XML_CACHE_KEY);
	}
	
	public Document getAssetCategoryHierarchyDocument() {
		Document doc = null; //(Document)HrPortalCacheUtil.getResult(ASSET_CATEGORY_XML_CACHE_KEY);
		
		if (doc == null) {
			synchronized (ASSET_CATEGORY_XML_CACHE_KEY) {
				if (doc == null) {
					doc = generateAssetCategoryHierarchyDocument();
					//HrPortalCacheUtil.putResult(ASSET_CATEGORY_XML_CACHE_KEY, doc);
				}
			}
		}
		
		return doc;
	}
	
	public Document generateAssetCategoryHierarchyDocument() {
		Document doc = SAXReaderUtil.createDocument();
		doc.add(SAXReaderUtil.createElement("v"));
		Element vocabulariesElem = doc.getRootElement();
		ServiceContext sc = ServiceContextThreadLocal.getServiceContext();
		HashMap<String, Element> map = new HashMap<String, Element>();
		
		try {
//			Group globalGroup = GroupLocalServiceUtil.getCompanyGroup(sc.getCompanyId());
			List<AssetVocabulary> vocabularies = AssetVocabularyLocalServiceUtil.getCompanyVocabularies(sc.getCompanyId());
			
			for (AssetVocabulary vocab:vocabularies) {
				Element vocabElem = generateAssetVocabularyElement(vocab);
				vocabulariesElem.add(vocabElem);
				map.put("v".concat(String.valueOf(vocab.getVocabularyId())), vocabElem);
			}
			
			List<AssetCategory> catList = AssetCategoryLocalServiceUtil.getCategories();
			
			for (AssetCategory cat:catList) {
				Element catElem = generateAssetCategoryElement(cat);
				map.put(String.valueOf(cat.getCategoryId()), catElem);
			}
			
			for (AssetCategory cat:catList) {
				Element catElem = map.get(String.valueOf(cat.getCategoryId()));
				Element vocabElem = map.get("v".concat(String.valueOf(cat.getVocabularyId())));
				Element parentCatElem = map.get(String.valueOf(cat.getParentCategoryId()));
				
				if (parentCatElem != null) {
					parentCatElem.add(catElem);
				} else {
					vocabElem.add(catElem);
				}
			}
		} catch (Exception e) {
			_log.error("", e);
		}
		
		return doc;
	}
	
	private Element generateAssetVocabularyElement(AssetVocabulary vocab) {
		Element vocabElem = SAXReaderUtil.createElement(vocab.getName());
		vocabElem.addAttribute("id", String.valueOf(vocab.getVocabularyId()));
		vocabElem.addAttribute("companyId", String.valueOf(vocab.getCompanyId()));
		vocabElem.addAttribute("groupId", String.valueOf(vocab.getGroupId()));
		return vocabElem;
	}
	
	private Element generateAssetCategoryElement(AssetCategory cat) {
		Element e = SAXReaderUtil.createElement(cat.getName());
		e.addAttribute("id", String.valueOf(cat.getCategoryId()));
		e.addAttribute("companyId", String.valueOf(cat.getCompanyId()));
		e.addAttribute("groupId", String.valueOf(cat.getGroupId()));
		e.addAttribute("vocabularyId", String.valueOf(cat.getVocabularyId()));
		e.addAttribute("parentCategoryId", String.valueOf(cat.getParentCategoryId()));
		return e;
	}
	
	public String test(String str) {
		return str;
	}
	
	public static final String ASSET_CATEGORY_XML_CACHE_KEY = WebArticleHelperLocalServiceImpl.class.getName().concat(".ASSET_CATEGORY_XML");
	
	private static Log _log = LogFactoryUtil.getLog(WebArticleHelperLocalServiceImpl.class); 
}