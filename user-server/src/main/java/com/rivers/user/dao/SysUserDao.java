package com.rivers.user.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rivers.user.api.entity.SysUserModel;
import org.apache.ibatis.annotations.Param;

/**
 * SysUserDao
 *
 * @author riversking
 * @Date 2018-12-19 14:51
 */
public interface SysUserDao extends BaseMapper<SysUserModel> {

    SysUserModel selectUserById(Integer id);

    Integer selectUserByUserName(String username);

    Integer selectUserByPhone(String phone);

    String selectUserId();

}
