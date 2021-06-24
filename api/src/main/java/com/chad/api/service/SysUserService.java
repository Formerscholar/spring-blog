package com.chad.api.service;

import com.chad.api.dao.pojo.SysUser;

public interface SysUserService {

	/**
	 * 得到用户的id
	 *
	 * @param id id
	 * @return {@link SysUser}
	 */
	SysUser getUserById(long id);

	/**
	 * 找到用户
	 *
	 * @param account 账户
	 * @param pwd     密码
	 * @return {@link SysUser}
	 */
	SysUser findUser(String account, String pwd);
}
