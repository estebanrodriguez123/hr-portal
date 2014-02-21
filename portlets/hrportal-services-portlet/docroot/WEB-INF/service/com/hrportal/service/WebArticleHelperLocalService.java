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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service interface for WebArticleHelper. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Rivet Logic
 * @see WebArticleHelperLocalServiceUtil
 * @see com.hrportal.service.base.WebArticleHelperLocalServiceBaseImpl
 * @see com.hrportal.service.impl.WebArticleHelperLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface WebArticleHelperLocalService extends BaseLocalService,
	InvokableLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WebArticleHelperLocalServiceUtil} to access the Web Article helper local service. Add custom service methods to {@link com.hrportal.service.impl.WebArticleHelperLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* The code here was moved from navigation.vm in our blue-gray
	* theme. This is a workaround to bypass bugs in Liferay Security
	* Manager that prevented proper execution of this code
	* in the navigation.vm
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portal.theme.NavItem> getNavItemsFromLayouts(
		java.util.List<com.liferay.portal.model.Layout> newMainLayouts,
		javax.servlet.http.HttpServletRequest request,
		com.liferay.portal.theme.ThemeDisplay themeDisplay, long ancestorPlid,
		long ancestorLayoutId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.journal.model.JournalArticle> getJournalArticlesByCategoryIds(
		java.util.List<java.lang.Long> cIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.portlet.journal.model.JournalArticle> getJournalArticlesByCategoryXPaths(
		java.util.List<java.lang.String> catXPathList);

	public java.util.ArrayList<java.lang.Long> convertCategoryHierarchyToIds(
		java.util.List<java.lang.String> catXPathArr);

	public void clearCachedCategoryHierarchy();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.kernel.xml.Document getAssetCategoryHierarchyDocument();

	public com.liferay.portal.kernel.xml.Document generateAssetCategoryHierarchyDocument();

	public java.lang.String test(java.lang.String str);
}