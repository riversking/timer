package com.rivers.user.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.core.util.ExceptionUtil;
import com.rivers.user.dto.RoleDO;
import com.rivers.user.dto.RoleDTO;
import com.rivers.user.api.entity.SysRoleModel;
import com.rivers.user.api.entity.SysUserRoleModel;
import com.rivers.user.dao.SysRoleDao;
import com.rivers.user.dao.SysUserRoleDao;
import com.rivers.userservice.proto.UpdateRoleByIdReq;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
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

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    public void addRole(RoleDO role) {
        SysRoleModel sysRoleModel = new SysRoleModel();
        BeanUtils.copyProperties(role, sysRoleModel);
        String userId = role.getUser().getUserId();
        sysRoleModel.setCreateUser(userId);
        sysRoleModel.setUpdateUser(userId);
        sysRoleModel.insert();
    }

    /**
     * 分页查询角色
     *
     * @param roleDto roleDto
     * @return IPage
     */
    public IPage<SysRoleModel> getRolePage(RoleDTO roleDto) {
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
            sysUserRoleDao.delete(new QueryWrapper<SysUserRoleModel>().eq("role_id", id));
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException("101002", e);
        }
    }

    public void updateRoleById(UpdateRoleByIdReq req) {
        SysRoleModel role = new SysRoleModel();
        role.setId(req.getId());
        role.setRoleCode(req.getRoleCode());
        role.setRoleName(req.getRoleName());
        role.setRoleDesc(req.getRoleDesc());
        role.setUpdateUser(req.getUser().getUserId());
        sysRoleDao.updateById(role);
    }

    public List<SysRoleModel> getRoleList() {
        return sysRoleDao.selectRoleList();
    }

    public void addRoleByUserId(RoleDTO roleDto) {
        roleDto.getRoleIds().forEach(i -> {
            SysUserRoleModel userRole = new SysUserRoleModel();
            userRole.setRoleId(i);
            userRole.setUserId(roleDto.getUserId());
            sysUserRoleDao.insert(userRole);
        });
    }

    public List<SysRoleModel> getRoleByUserId(String userId) {
        return sysRoleDao.selectRoleByUserId(userId);
    }

}
