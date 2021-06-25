package com.chad.api.controller;

import com.chad.api.dao.pojo.SysUser;
import com.chad.api.helper.Result;
import com.chad.api.utils.UserThreadLocal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author Chad
 * @date 2021/06/25
 */
@RestController
@RequestMapping("test")
public class TestController {

	/**
	 * 测试
	 *
	 * @return {@link Result}
	 */
	@GetMapping
	public Result test() {
		SysUser sysUser = UserThreadLocal.get();
		return Result.success(sysUser);
	}
}
