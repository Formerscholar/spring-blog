package com.chad.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chad.api.dao.mapper.SysUserMapper;
import com.chad.api.dao.pojo.SysUser;
import com.chad.api.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

  @Autowired
  private SysUserMapper sysUserMapper;

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
    queryWrapper.eq(SysUser::getAccount,account);
    queryWrapper.eq(SysUser::getPassword,pwd);
    queryWrapper.select(SysUser::getId,SysUser::getAccount,SysUser::getAvatar,SysUser::getNickname);
    queryWrapper.last("limit 1");
    SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
    return sysUser;
  }
}
