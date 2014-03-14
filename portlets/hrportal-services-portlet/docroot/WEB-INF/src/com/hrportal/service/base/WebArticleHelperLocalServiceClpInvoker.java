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

package com.hrportal.service.base;

import com.hrportal.service.WebArticleHelperLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Rivet Logic
 * @generated
 */
public class WebArticleHelperLocalServiceClpInvoker {
	public WebArticleHelperLocalServiceClpInvoker() {
		_methodName16 = "getBeanIdentifier";

		_methodParameterTypes16 = new String[] {  };

		_methodName17 = "setBeanIdentifier";

		_methodParameterTypes17 = new String[] { "java.lang.String" };

		_methodName20 = "getNavItemsFromLayouts";

		_methodParameterTypes20 = new String[] {
				"java.util.List", "javax.servlet.http.HttpServletRequest",
				"com.liferay.portal.theme.ThemeDisplay", "long", "long"
			};

		_methodName21 = "getJournalArticlesByCategoryIds";

		_methodParameterTypes21 = new String[] { "java.util.List" };

		_methodName22 = "getJournalArticlesByCategoryXPaths";

		_methodParameterTypes22 = new String[] { "java.util.List" };

		_methodName23 = "convertCategoryHierarchyToIds";

		_methodParameterTypes23 = new String[] { "java.util.List" };

		_methodName24 = "clearCachedCategoryHierarchy";

		_methodParameterTypes24 = new String[] {  };

		_methodName25 = "getAssetCategoryHierarchyDocument";

		_methodParameterTypes25 = new String[] {  };

		_methodName26 = "generateAssetCategoryHierarchyDocument";

		_methodParameterTypes26 = new String[] {  };

		_methodName29 = "test";

		_methodParameterTypes29 = new String[] { "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return WebArticleHelperLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			WebArticleHelperLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			return WebArticleHelperLocalServiceUtil.getNavItemsFromLayouts((java.util.List<com.liferay.portal.model.Layout>)arguments[0],
				(javax.servlet.http.HttpServletRequest)arguments[1],
				(com.liferay.portal.theme.ThemeDisplay)arguments[2],
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue());
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			return WebArticleHelperLocalServiceUtil.getJournalArticlesByCategoryIds((java.util.List<java.lang.Long>)arguments[0]);
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			return WebArticleHelperLocalServiceUtil.getJournalArticlesByCategoryXPaths((java.util.List<java.lang.String>)arguments[0]);
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			return WebArticleHelperLocalServiceUtil.convertCategoryHierarchyToIds((java.util.List<java.lang.String>)arguments[0]);
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			WebArticleHelperLocalServiceUtil.clearCachedCategoryHierarchy();

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return WebArticleHelperLocalServiceUtil.getAssetCategoryHierarchyDocument();
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return WebArticleHelperLocalServiceUtil.generateAssetCategoryHierarchyDocument();
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return WebArticleHelperLocalServiceUtil.test((java.lang.String)arguments[0]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName20;
	private String[] _methodParameterTypes20;
	private String _methodName21;
	private String[] _methodParameterTypes21;
	private String _methodName22;
	private String[] _methodParameterTypes22;
	private String _methodName23;
	private String[] _methodParameterTypes23;
	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName25;
	private String[] _methodParameterTypes25;
	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName29;
	private String[] _methodParameterTypes29;
}