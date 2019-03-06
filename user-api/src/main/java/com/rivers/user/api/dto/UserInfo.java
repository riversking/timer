package com.rivers.user.api.dto;

import com.rivers.user.api.entity.SysUserModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author riversking
 */
@Data
public class UserInfo implements Serializable {
    /**
     * 用户基本信息
     */
    private SysUserModel sysUser;
    /**
     * 权限标识集合
     */
    private List<String> permissions;

    /**
     * 角色集合
     */
    private List<Integer> roles;
}
