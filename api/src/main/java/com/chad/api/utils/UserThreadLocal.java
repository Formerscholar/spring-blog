package com.chad.api.utils;

import com.chad.api.dao.pojo.SysUser;

/**
 * 用户线程本地
 *
 * @author Chad
 * @date 2021/06/24
 */
public class UserThreadLocal {

	private UserThreadLocal() {
	}

	private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

	public static void put(SysUser sysUser) {
		LOCAL.set(sysUser);
	}

	public static SysUser get() {
		return LOCAL.get();
	}

	public static void remove() {
		LOCAL.remove();
	}
}
