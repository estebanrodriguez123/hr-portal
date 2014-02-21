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

package com.hrportal.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for WebArticleHelper. This utility wraps
 * {@link com.hrportal.service.impl.WebArticleHelperLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Rivet Logic
 * @see WebArticleHelperLocalService
 * @see com.hrportal.service.base.WebArticleHelperLocalServiceBaseImpl
 * @see com.hrportal.service.impl.WebArticleHelperLocalServiceImpl
 * @generated
 */
public class WebArticleHelperLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.hrportal.service.impl.WebArticleHelperLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* The code here was moved from navigation.vm in our blue-gray
	* theme. This is a workaround to bypass bugs in Liferay Security
	* Manager that prevented proper execution of this code
	* in the navigation.vm
	*/
	public static java.util.List<com.liferay.portal.theme.NavItem> getNavItemsFromLayouts(
		java.util.List<com.liferay.portal.model.Layout> newMainLayouts,
		javax.servlet.http.HttpServletRequest request,
		com.liferay.portal.theme.ThemeDisplay themeDisplay, long ancestorPlid,
		long ancestorLayoutId) {
		return getService()
				   .getNavItemsFromLayouts(newMainLayouts, request,
			themeDisplay, ancestorPlid, ancestorLayoutId);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalArticle> getJournalArticlesByCategoryIds(
		java.util.List<java.lang.Long> cIds) {
		return getService().getJournalArticlesByCategoryIds(cIds);
	}

	public static java.util.List<com.liferay.portlet.journal.model.JournalArticle> getJournalArticlesByCategoryXPaths(
		java.util.List<java.lang.String> catXPathList) {
		return getService().getJournalArticlesByCategoryXPaths(catXPathList);
	}

	public static java.util.ArrayList<java.lang.Long> convertCategoryHierarchyToIds(
		java.util.List<java.lang.String> catXPathArr) {
		return getService().convertCategoryHierarchyToIds(catXPathArr);
	}

	public static void clearCachedCategoryHierarchy() {
		getService().clearCachedCategoryHierarchy();
	}

	public static com.liferay.portal.kernel.xml.Document getAssetCategoryHierarchyDocument() {
		return getService().getAssetCategoryHierarchyDocument();
	}

	public static com.liferay.portal.kernel.xml.Document generateAssetCategoryHierarchyDocument() {
		return getService().generateAssetCategoryHierarchyDocument();
	}

	public static java.lang.String test(java.lang.String str) {
		return getService().test(str);
	}

	public static void clearService() {
		_service = null;
	}

	public static WebArticleHelperLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					WebArticleHelperLocalService.class.getName());

			if (invokableLocalService instanceof WebArticleHelperLocalService) {
				_service = (WebArticleHelperLocalService)invokableLocalService;
			}
			else {
				_service = new WebArticleHelperLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(WebArticleHelperLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(WebArticleHelperLocalService service) {
	}

	private static WebArticleHelperLocalService _service;
}