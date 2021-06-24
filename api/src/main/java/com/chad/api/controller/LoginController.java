package com.chad.api.controller;


import com.chad.api.helper.Result;
import com.chad.api.service.LoginService;
import com.chad.api.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author Chad
 * @date 2021/06/24
 */
@RestController
@RequestMapping("login")
public class LoginController {

	/** 登录服务 */
	@Autowired
	private LoginService loginService;

	/**
	 * 登录
	 *
	 * @param loginParam 登录参数
	 * @return {@link Result}
	 */
	@RequestMapping
	public Result login(@RequestBody LoginParam loginParam) {
		return loginService.login(loginParam);
	}
}
