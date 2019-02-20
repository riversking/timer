package com.rivers.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rivers.user.api.entity.SysRoleMenuModel;

import java.util.List;

/**
 * SysRoleMenuDao
 *
 * @author riversking
 * @Date 2019-01-09 15:09
 */
public interface SysRoleMenuDao extends BaseMapper<SysRoleMenuModel> {

    List<Integer> getMenuIdByRoleId(Integer roleId);

    int updateByRoleId(SysRoleMenuModel sysRoleMenuModel);

}