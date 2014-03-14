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

package com.rivetlogic.hrportal.liferay.assetpublisher.hook.action;

import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

public class HRPortalCustomConfigurationActionImpl extends
		BaseStrutsPortletAction {

	public void processAction(StrutsPortletAction originalStrutsPortletAction, PortletConfig portletConfig, ActionRequest actionRequest,ActionResponse actionResponse) throws Exception {
	
		originalStrutsPortletAction.processAction(originalStrutsPortletAction, portletConfig, actionRequest, actionResponse);
		
		Portlet portlet = getPortlet(actionRequest);

		if (portlet.getPortletName().equals(PortletKeys.ASSET_PUBLISHER)) {
			PortletPreferences preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portlet.getPortletId());
			preferences.setValue("moreInfoUrl", ParamUtil.getString(actionRequest, "moreInfoUrl"));
			preferences.setValue("moreInfoUrlLabel", ParamUtil.getString(actionRequest, "moreInfoUrlLabel"));
			preferences.store();
		}
	}

	public String render(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception {

		return originalStrutsPortletAction.render(null, portletConfig,
				renderRequest, renderResponse);
	}

	public void serveResource(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws Exception {
		originalStrutsPortletAction.serveResource(originalStrutsPortletAction,
				portletConfig, resourceRequest, resourceResponse);
	}

	protected Portlet getPortlet(PortletRequest portletRequest)
			throws Exception {

		long companyId = PortalUtil.getCompanyId(portletRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();

		String portletId = ParamUtil.getString(portletRequest,"portletResource");

		if (!PortletPermissionUtil.contains(permissionChecker,
				themeDisplay.getLayout(), portletId, ActionKeys.CONFIGURATION)) {

			throw new PrincipalException();
		}

		return PortletLocalServiceUtil.getPortletById(companyId, portletId);
	}
}
