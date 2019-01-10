package com.rivers.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.user.api.entity.SysRoleModel;
import com.rivers.user.mapper.SysRoleDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author riversking
 */
@Service
@Log4j2
public class RoleService extends ServiceImpl<SysRoleDao, SysRoleModel> {

    public void addRole(SysRoleModel sysRoleModel) {
        try {
            sysRoleModel.insert();
        } catch (Exception e) {
            log.error("Add Role Exception {}", e);
        }
    }

}
