package com.chad.api.service;

import com.chad.api.dao.pojo.SysUser;
import com.chad.api.helper.Result;

/**
 * 系统用户服务
 *
 * @author Chad
 * @date 2021/06/24
 */
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


	/**
	 * 得到用户信息
	 *
	 * @param token 令牌
	 * @return {@link Result}
	 */
	Result getUserInfoByToken(String token);
}
