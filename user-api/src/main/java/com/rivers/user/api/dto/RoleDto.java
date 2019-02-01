package com.rivers.user.api.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author riversking
 */
@Data
public class RoleDto extends Page {

    private String roleName;

    private String roleCode;

    private String createTime;

    private String updateTime;

    private String delFlag;

}
