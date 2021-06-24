package com.chad.api.controller;

import com.chad.api.helper.Result;
import com.chad.api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注销控制器
 *
 * @author Chad
 * @date 2021/06/24
 */
@RestController
@RequestMapping("logout")
public class LogoutController {

	/** 登录服务 */
	@Autowired
	private LoginService loginService;


	/**
	 * 注销
	 *
	 * @param token 令牌
	 * @return {@link Result}
	 */
	@GetMapping
	public Result logout(@RequestHeader("token") String token){
		return loginService.logout(token);
	}
}
