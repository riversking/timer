package com.rivers.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rivers.user.api.entity.SysRoleModel;

import java.util.List;

/**
 * SysRoleDao
 *
 * @author riversking
 * @Date 2018-12-19 10:46
 */
public interface SysRoleDao extends BaseMapper<SysRoleModel> {

    /**
     * 角色列表
     *
     * @return List
     */
    List<SysRoleModel> selectRoleList();

    List<SysRoleModel> selectRoleByUserId(String userId);

}