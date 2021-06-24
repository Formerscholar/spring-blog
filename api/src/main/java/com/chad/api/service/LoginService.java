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
}
