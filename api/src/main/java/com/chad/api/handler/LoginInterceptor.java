package com.chad.api.handler;

import com.alibaba.fastjson.JSON;
import com.chad.api.dao.pojo.SysUser;
import com.chad.api.enums.ErrorCode;
import com.chad.api.helper.Result;
import com.chad.api.service.LoginService;
import com.chad.api.utils.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private LoginService loginService;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		String token = request.getHeader("token");
		log.info("=================request start===========================");
		String requestURI = request.getRequestURI();
		log.info("request uri:{}", requestURI);
		log.info("request method:{}", request.getMethod());
		log.info("token:{}", token);
		log.info("=================request end===========================");

		if (token == null) {
			Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(JSON.toJSONString(result));
			return false;
		}
		SysUser sysUser = loginService.checkToken(token);
		if (sysUser == null) {
			Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(JSON.toJSONString(result));
			return false;
		}

		UserThreadLocal.put(sysUser);
		return true;
	}


	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		UserThreadLocal.remove();
	}
}
