<%--
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
--%>

<%@ include file="/html/portlet/search/init.jsp" %>

<%
long groupId = ParamUtil.getLong(request, "groupId2");
		 
String format = ParamUtil.getString(request, "format");
String keywords = ParamUtil.getString(request, "keywords");

List<Group> sites =  user.getMySites();
Group group = themeDisplay.getScopeGroup();

%>

<liferay-portlet:renderURL varImpl="searchURL" windowState="<%= LiferayWindowState.MAXIMIZED.toString() %>">
	<portlet:param name="struts_action" value="/search/search" />
</liferay-portlet:renderURL>

<aui:form action="<%= searchURL %>" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<aui:input name="<%= SearchContainer.DEFAULT_CUR_PARAM %>" type="hidden" value="<%= ParamUtil.getInteger(request, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_CUR) %>" />
	<aui:input name="format" type="hidden" value="<%= format %>" />
	<aui:fieldset id="searchContainer">
		<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>" inlineField="<%= true %>" label="" name="keywords" size="30" />
		<c:set var="gId" value="<%= group.getGroupId() %>" scope="page"/>
		<aui:select inlineField="<%= true %>" label="" name="groupId">
			<aui:option label="everything" selected="<%= groupId == 0 %>" value="0" />
			<% if(searchInMySitesAndOrganizations) {%>
				<c:forEach var="site" items="<%= sites %>">
					<c:set var="sId" value="${site.groupId}" scope="page"/>
					<c:if test="${site.type > 0}">
						<c:choose>
							<c:when test="${sId == gId}">
								<aui:option label="This ${site.organization ? 'organization' : 'site'}" value="${site.groupId}" />
							</c:when>
							<c:otherwise>
								<aui:option label="${site.name}" value="${site.groupId}" />
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			<% } else {%>
				<aui:option label='<%= "This " + (group.isOrganization() ? "organization" : "site") %>' selected="<%= groupId != 0 %>" value="<%= group.getGroupId() %>" />
			<% } %>
		</aui:select>
		<aui:input inlineField="<%= true %>" label="" name="search" src='<%= themeDisplay.getPathThemeImages() + "/common/search.png" %>' title="search" type="image" />

	</aui:fieldset>
</aui:form>