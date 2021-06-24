package com.chad.api.service;

import com.chad.api.helper.Result;
import com.chad.api.vo.params.LoginParam;

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
}
