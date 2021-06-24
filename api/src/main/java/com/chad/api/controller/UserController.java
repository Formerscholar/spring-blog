package com.chad.api.controller;

import com.chad.api.helper.Result;
import com.chad.api.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	private  SysUserService sysUserService;

	@GetMapping("currentUser")
	private Result currentUser(@RequestHeader("token") String token){

		return sysUserService.getUserInfoByToken(token);
	}
}
