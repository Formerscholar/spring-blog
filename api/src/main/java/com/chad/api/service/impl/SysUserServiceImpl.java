package com.chad.api.service.impl;

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
}
