package com.rivers.user.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.core.util.ExceptionUtil;
import com.rivers.user.api.dto.RoleDto;
import com.rivers.user.api.entity.SysRoleModel;
import com.rivers.user.mapper.SysRoleDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author riverskingking
 */
@Service
@Log4j2
public class RoleService extends ServiceImpl<SysRoleDao, SysRoleModel> {

    @Resource
    private SysRoleDao sysRoleDao;

    public void addRole(SysRoleModel sysRoleModel) {
        try {
            sysRoleModel.setCreateUser("tester");
            sysRoleModel.setUpdateUser("tester");
            sysRoleModel.insert();
        } catch (Exception e) {
            log.error("Add Role Exception {}", e);
            ExceptionUtil.throwBusinessException("101001", e);
        }
    }

    /**
     * 分页查询角色
     *
     * @param roleDto roleDto
     * @return IPage
     */
    public IPage<SysRoleModel> getRolePage(RoleDto roleDto) {
        QueryWrapper<SysRoleModel> wrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(roleDto.getRoleName())) {
            wrapper.eq("role_name", roleDto.getRoleName());
        }
        if (StrUtil.isNotBlank(roleDto.getRoleCode())) {
            wrapper.eq("role_code", roleDto.getRoleCode());
        }
        if (StrUtil.isNotBlank(roleDto.getCreateTime())) {
            wrapper.ge("create_time", roleDto.getCreateTime());
        }
        if (StrUtil.isNotBlank(roleDto.getUpdateTime())) {
            wrapper.le("update_time", roleDto.getUpdateTime());
        }
        wrapper.eq("is_delete", 0);
        Page<SysRoleModel> page = new Page<>(roleDto.getPage(), roleDto.getPageSize());
        return sysRoleDao.selectPage(page, wrapper);
    }

    /**
     * 通过id查询角色详情
     *
     * @param id id
     * @return SysRoleModel
     */
    public SysRoleModel getRoleDetailById(Integer id) {
        log.info("RoleId {}", id);
        QueryWrapper<SysRoleModel> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        wrapper.eq("id", id);
        return sysRoleDao.selectOne(wrapper);
    }


    /**
     * 通过id删除角色
     *
     * @param id
     */
    public void deleteRoleById(Integer id) {
        try {
            sysRoleDao.deleteById(id);
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException("101002", e);
        }
    }

    public void updateRoleById(SysRoleModel sysRoleModel) {
        try {
            sysRoleModel.setUpdateUser("tester");
            sysRoleDao.updateById(sysRoleModel);
        } catch (Exception e) {
            log.error("Update Role Exception {}", e);
            ExceptionUtil.throwBusinessException("101006", e);
        }

    }

    public List<SysRoleModel> getRoleList() {
        return sysRoleDao.selectRoleList();
    }

}
