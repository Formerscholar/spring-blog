package com.chad.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.chad.api.dao.pojo.SysUser;
import com.chad.api.helper.Result;
import com.chad.api.service.LoginService;
import com.chad.api.service.SysUserService;
import com.chad.api.utils.JWTUtils;
import com.chad.api.vo.ErrorCode;
import com.chad.api.vo.params.LoginParam;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * 登录服务impl
 *
 * @author Chad
 * @date 2021/06/24
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	private static final String SLAT = "mszlu!@#";

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;


	@Override
	public Result login(LoginParam loginParam) {
		String account = loginParam.getAccount();
		String password = loginParam.getPassword();

		if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
			return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
		}
		String pwd = DigestUtils.md5Hex(password + SLAT);
		SysUser sysUser = sysUserService.findUser(account, pwd);
		if (sysUser == null) {
			return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
		}
		String token = JWTUtils.createToken(sysUser.getId());
		redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
		return Result.success(token);
	}

	@Override
	public Result logout(String token) {
		redisTemplate.delete("TOKEN_" + token);
		return Result.success(null);
	}

	@Override
	public Result register(LoginParam loginParam) {

		String account = loginParam.getAccount();
		String password = loginParam.getPassword();
		String nickname = loginParam.getNickname();
		if (StringUtils.isBlank(account)
			|| StringUtils.isBlank(password)
			|| StringUtils.isBlank(nickname)
		) {
			return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
		}
		SysUser sysUser = this.sysUserService.findUserByAccount(account);
		if (sysUser != null) {
			return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
		}
		sysUser = new SysUser();
		sysUser.setNickname(nickname);
		sysUser.setAccount(account);
		sysUser.setPassword(DigestUtils.md5Hex(password + SLAT));
		sysUser.setCreateDate(System.currentTimeMillis());
		sysUser.setLastLogin(System.currentTimeMillis());
		sysUser.setAvatar("/static/img/logo.b3a48c0.png");
		sysUser.setAdmin(1); //1 为true
		sysUser.setDeleted(0); // 0 为false
		sysUser.setSalt("");
		sysUser.setStatus("");
		sysUser.setEmail("");
		this.sysUserService.save(sysUser);
		//token
		String token = JWTUtils.createToken(sysUser.getId());
		redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
		return Result.success(token);
	}
}
