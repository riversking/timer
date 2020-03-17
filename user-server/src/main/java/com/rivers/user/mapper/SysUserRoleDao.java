package com.rivers.user.mapper;


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

    List<Integer> selectRoleId(Integer id);

    void deleteByUserId(Integer userId);
}