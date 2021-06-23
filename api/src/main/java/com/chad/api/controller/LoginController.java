package com.chad.api.controller;


import com.chad.api.helper.Result;
import com.chad.api.service.LoginService;
import com.chad.api.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping
	public Result login(@RequestBody LoginParam loginParam) {
		return loginService.login(loginParam);
	}
}
