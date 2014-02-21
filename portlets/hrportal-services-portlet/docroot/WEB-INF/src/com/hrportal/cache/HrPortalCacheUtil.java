/*
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

package com.hrportal.cache;

import com.liferay.portal.kernel.cache.MultiVMPoolUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.Serializable;

public class HrPortalCacheUtil {
	public static final String CACHE_NAME = HrPortalCacheUtil.class.getName();
	
	public static void clearCache() {
		_cache.removeAll();
	}

	public static Serializable getResult(String key) {
		return (Serializable)_cache.get(key);
	}

	public static void putResult(String key, Serializable value) {
		_cache.put(key, value);
	}

	public static void removeResult(String key) {
		_cache.remove(key);
	}

	private static PortalCache _cache = MultiVMPoolUtil.getCache(CACHE_NAME);

	private static Log _log = LogFactoryUtil.getLog(HrPortalCacheUtil.class);
}
