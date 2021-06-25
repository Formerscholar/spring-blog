package com.chad.api.service;

import com.chad.api.dao.pojo.SysUser;
import com.chad.api.helper.Result;
import com.chad.api.vo.params.LoginParam;

/**
 * 登录服务
 *
 * @author Chad
 * @date 2021/06/24
 */
public interface LoginService {
	/**
	 * 登录
	 *
	 * @param loginParam 登录参数
	 * @return {@link Result}
	 */
	Result login(LoginParam loginParam);

	/**
	 * 注销
	 *
	 * @param token 令牌
	 * @return {@link Result}
	 */
	Result logout(String token);

	/**
	 * 注册
	 *
	 * @param loginParam 登录参数
	 * @return {@link Result}
	 */
	Result register(LoginParam loginParam);

	/**
	 * 检查令牌
	 *
	 * @param token 令牌
	 * @return {@link SysUser}
	 */
	SysUser checkToken(String token);
}
