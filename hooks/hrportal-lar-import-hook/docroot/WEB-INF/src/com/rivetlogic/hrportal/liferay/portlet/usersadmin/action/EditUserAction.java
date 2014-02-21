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

package com.rivetlogic.hrportal.liferay.portlet.usersadmin.action;

import com.liferay.portal.LARFileException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.lar.PortletDataHandlerKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

public class EditUserAction extends BaseStrutsPortletAction {
	@Override
	public void processAction(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		if (cmd.equals(IMPORT_CMD)) {
			importUserPrivatePageTemplate(actionRequest);
		}
		originalStrutsPortletAction.processAction(originalStrutsPortletAction,
				portletConfig, actionRequest, actionResponse);
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

	private final String IMPORT_CMD = "import";

	private void importUserPrivatePageTemplate(ActionRequest actionRequest)
			throws LARFileException, PortalException, SystemException {

		_log.info(">>>>>>>>>> Starting user private page template import <<<<<<<<<<");

		UploadPortletRequest uploadRequest = PortalUtil
				.getUploadPortletRequest(actionRequest);
		File larFilePublic = getUploadRequestFile(uploadRequest,
				"importPublicPagesFileName");
		File larFilePrivate = getUploadRequestFile(uploadRequest,
				"importPrivatePagesFileName");
		if (larFilePublic != null || larFilePrivate != null) {
			List<User> allUsers = null;
			try {
				allUsers = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS,
						QueryUtil.ALL_POS);
			} catch (SystemException e) {
				_log.error("Error while getting all the portal users", e);
			}

			if (allUsers != null) {
				Map<String, String[]> parameterMap = new HashMap<String, String[]>();
				parameterMap.put(PortletDataHandlerKeys.PERMISSIONS,
						new String[] { Boolean.TRUE.toString() });
				parameterMap.put(PortletDataHandlerKeys.PORTLET_DATA,
						new String[] { Boolean.TRUE.toString() });
				parameterMap.put(
						PortletDataHandlerKeys.PORTLET_DATA_CONTROL_DEFAULT,
						new String[] { Boolean.TRUE.toString() });
				parameterMap.put(PortletDataHandlerKeys.PORTLET_SETUP,
						new String[] { Boolean.TRUE.toString() });
				/*parameterMap.put(PortletDataHandlerKeys.USER_PERMISSIONS,
						new String[] { Boolean.FALSE.toString() });*/

				for (User user : allUsers) {
					try {
						//Adding condition: user is not default user to avoid "not group exists" exception 
						if (user.getUserId() > 0 && !user.isDefaultUser() && user.getGroup() != null) {
							importUserPages(parameterMap, user, larFilePrivate,
									true);
							importUserPages(parameterMap, user, larFilePublic,
									false);
						}
					} catch (Exception e) {
						logError(user, null, e);
					}
				}
			}
		}

		_log.info(">>>>>>>>>> Completed user private page template import <<<<<<<<<<");
	}

	private File getUploadRequestFile(UploadPortletRequest uploadRequest,
			String paramName) throws LARFileException {
		File file = null;
		if (Validator.isNotNull(uploadRequest.getFileName(paramName))) {
			file = uploadRequest.getFile(paramName);
			if (!file.exists()) {
				throw new LARFileException("Import file '" + paramName
						+ "' does not exist");
			}
		}
		return file;
	}

	private void importUserPages(Map<String, String[]> parameterMap, User user,
			File larFile, boolean privateLayout) {
			
		if (larFile != null) {
			try {
				LayoutLocalServiceUtil.importLayouts(user.getUserId(), user
						.getGroup().getGroupId(), privateLayout, parameterMap,
						larFile);
			} catch (PortalException e) {
				logError(user, privateLayout, e);
			} catch (SystemException e) {
				logError(user, privateLayout, e);
			}
		}
	}

	private void logError(User user, Boolean privateLayout, Exception exception) {
		_log.error(
				"Error while importing "
						+ ((privateLayout != null) ? (privateLayout ? "private"
								: "public") : "") + " page template for user "
						+ user.getScreenName(), exception);
	}

	private static Log _log = LogFactoryUtil.getLog(EditUserAction.class);
}
