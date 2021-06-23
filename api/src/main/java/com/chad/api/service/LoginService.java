package com.chad.api.service;

import com.chad.api.helper.Result;
import com.chad.api.vo.params.LoginParam;

public interface LoginService {
	Result login(LoginParam loginParam);
}
