package com.rivers.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rivers.user.api.entity.SysUserRoleModel;

import java.util.List;

/**
 * SysUserRoleDao
 *
 * @author riversking
 * @Date 2019-01-09 14:43
 */
public interface SysUserRoleDao extends BaseMapper<SysUserRoleModel> {

    List<Integer> selectRoleId(String userId);

    void deleteByUserId(String userId);
}
