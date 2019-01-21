package com.rivers.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.rivers.user.api.dto.UserDto;
import com.rivers.user.api.entity.SysUserModel;
import com.rivers.user.api.entity.SysUserRoleModel;
import com.rivers.user.mapper.SysDeptDao;
import com.rivers.user.mapper.SysRoleDao;
import com.rivers.user.mapper.SysUserDao;
import com.rivers.user.mapper.SysUserRoleDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * @author riversking
 */
@Service
@Log4j2
public class UserService extends ServiceImpl<SysUserDao, SysUserModel> {

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private SysUserRoleDao sysUserRoleDao;


    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDto userDto) {
        SysUserModel sysUserModel = new SysUserModel();
        sysUserModel.setUsername(userDto.getUsername());
        sysUserModel.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        sysUserModel.setPhone(userDto.getPhone());
        sysUserModel.setAvatar(userDto.getAvatar());
        sysUserModel.setSalt(UUID.randomUUID().toString());
        sysUserModel.setCreateUser(userDto.getCreateUser());
        sysUserModel.setUpdateUser(userDto.getUpdateUser());
        sysUserDao.insert(sysUserModel);
        userDto.getRoleIds().forEach(i -> {
            SysUserRoleModel sysUserRoleModel = new SysUserRoleModel();
            sysUserRoleModel.setRoleId(i);
            log.info("userId {}", sysUserModel.getId());
            sysUserRoleModel.setUserId(sysUserModel.getId());
            sysUserRoleDao.insert(sysUserRoleModel);
        });
    }
}
