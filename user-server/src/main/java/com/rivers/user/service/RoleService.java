package com.rivers.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.user.api.dto.RoleDto;
import com.rivers.user.api.entity.SysRoleModel;
import com.rivers.user.mapper.SysRoleDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author riversking
 */
@Service
@Log4j2
public class RoleService extends ServiceImpl<SysRoleDao, SysRoleModel> {

    @Resource
    private SysRoleDao sysRoleDao;

    public void addRole(SysRoleModel sysRoleModel) {
        try {
            sysRoleModel.insert();
        } catch (Exception e) {
            log.error("Add Role Exception {}", e);
        }
    }

    /**
     *
     * @param roleDto
     * @return
     */
    public IPage<SysRoleModel> getRoleList(RoleDto roleDto) {
        QueryWrapper<SysRoleModel> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag", 0);

        Page<SysRoleModel> page = new Page<>(roleDto.getPage(), roleDto.getPageSize());

        return sysRoleDao.selectPage(page, wrapper);
    }

}
