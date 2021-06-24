package com.chad.api.config;

import com.chad.api.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private LoginInterceptor loginInterceptor;


	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//跨域配置，不可设置为*，不安全, 前后端分离项目，可能域名不一致
		//本地测试 端口不一致 也算跨域 http://localhost:8080
		registry.addMapping("/**").allowedOrigins("*");
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns(
				"/login",  //登录
				"/register" //注册
			);
	}
}
