package com.chad.api.controller;

import com.chad.api.helper.Result;
import com.chad.api.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author Chad
 * @date 2021/06/24
 */
@RestController
@RequestMapping("users")
public class UserController {
	/** 系统用户服务 */
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 当前用户
	 *
	 * @param token 令牌
	 * @return {@link Result}
	 */
	@GetMapping("currentUser")
	private Result currentUser(@RequestHeader("token") String token) {
		return sysUserService.getUserInfoByToken(token);
	}
}
