package com.rivers.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rivers.user.api.entity.SysMenuModel;

import java.util.List;

/**
 * SysMenuDao
 *
 * @author riversking
 * @Date 2019-01-09 15:06
 */
public interface SysMenuDao extends BaseMapper<SysMenuModel> {

    /**
     * 通过roleId 获取menu
     * @param id id
     * @return List
     */
    List<SysMenuModel> getMenuByRoleId(Integer id);


    List<SysMenuModel> getMenuByUserId(String userId);

}