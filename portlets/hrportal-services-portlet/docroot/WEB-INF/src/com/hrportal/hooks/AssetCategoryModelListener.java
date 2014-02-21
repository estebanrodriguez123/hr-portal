/**
 * Copyright (C) 2012-2013 Rivet Logic Corporation.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GPLv3 General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GPLv3 General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.hrportal.hooks;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.model.ModelListener;
import com.liferay.portlet.asset.model.AssetCategory;
import com.hrportal.service.WebArticleHelperLocalServiceUtil;

public class AssetCategoryModelListener implements ModelListener<AssetCategory> {

	//@Override
	public void onAfterAddAssociation(Object arg0, String arg1, Object arg2)
			throws ModelListenerException {
	}

	//@Override
	public void onAfterCreate(AssetCategory arg0) throws ModelListenerException {
		clearCachedCategoryHierarchy();
	}

	//@Override
	public void onAfterRemove(AssetCategory arg0) throws ModelListenerException {
		clearCachedCategoryHierarchy();
	}

	//@Override
	public void onAfterRemoveAssociation(Object arg0, String arg1, Object arg2)
			throws ModelListenerException {
	}

	//@Override
	public void onAfterUpdate(AssetCategory arg0) throws ModelListenerException {
		clearCachedCategoryHierarchy();
	}

	//@Override
	public void onBeforeAddAssociation(Object arg0, String arg1, Object arg2)
			throws ModelListenerException {
		
	}

	//@Override
	public void onBeforeCreate(AssetCategory arg0)
			throws ModelListenerException {
		
	}

	//@Override
	public void onBeforeRemove(AssetCategory arg0)
			throws ModelListenerException {
		
	}

	//@Override
	public void onBeforeRemoveAssociation(Object arg0, String arg1, Object arg2)
			throws ModelListenerException {
		
	}

	//@Override
	public void onBeforeUpdate(AssetCategory arg0)
			throws ModelListenerException {
		
	}

	private void clearCachedCategoryHierarchy() {
		WebArticleHelperLocalServiceUtil.clearCachedCategoryHierarchy();
	}
	
}
