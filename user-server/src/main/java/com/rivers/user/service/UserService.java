package com.rivers.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.user.api.entity.SysUserModel;
import com.rivers.user.mapper.SysUserDao;
import org.springframework.stereotype.Service;

/**
 * @author riversking
 */
@Service
public class UserService extends ServiceImpl<SysUserDao, SysUserModel> {

    public void addUser(SysUserModel sysUserModel) {

    }
}
