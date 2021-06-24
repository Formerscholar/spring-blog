package com.chad.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chad.api.dao.mapper.SysUserMapper;
import com.chad.api.dao.pojo.SysUser;
import com.chad.api.helper.Result;
import com.chad.api.service.SysUserService;
import com.chad.api.utils.JWTUtils;
import com.chad.api.enums.ErrorCode;
import com.chad.api.vo.LoginUserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public SysUser getUserById(long id) {
		SysUser sysUser = sysUserMapper.selectById(id);
		if (sysUser == null) {
			sysUser = new SysUser();
			sysUser.setNickname("chad");
		}
		return sysUser;
	}

	@Override
	public SysUser findUser(String account, String pwd) {
		LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(SysUser::getAccount, account);
		queryWrapper.eq(SysUser::getPassword, pwd);
		queryWrapper.select(SysUser::getId, SysUser::getAccount, SysUser::getAvatar, SysUser::getNickname);
		queryWrapper.last("limit 1");
		SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
		return sysUser;
	}

	/**
	 * 得到用户信息
	 *
	 * @param token 令牌
	 * @return {@link Result}
	 */
	@Override
	public Result getUserInfoByToken(String token) {
		Map<String, Object> map = JWTUtils.checkToken(token);
		if (map == null) {
			return Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
		}
		String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
		if (StringUtils.isBlank(userJson)) {
			return Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
		}
		SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
		LoginUserVo loginUserVo = new LoginUserVo();
		loginUserVo.setAccount(sysUser.getAccount());
		loginUserVo.setAvatar(sysUser.getAvatar());
		loginUserVo.setId(sysUser.getId());
		loginUserVo.setNickname(sysUser.getNickname());
		return Result.success(loginUserVo);
	}

	@Override
	public void save(SysUser sysUser) {
		//注意 默认生成的id 是分布式id 采用了雪花算法
		this.sysUserMapper.insert(sysUser);
	}

	@Override
	public SysUser findUserByAccount(String account) {
		LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(SysUser::getAccount,account);
		queryWrapper.last("limit 1");
		return sysUserMapper.selectOne(queryWrapper);
	}


}
