package com.rivers.user.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author riverskingking
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends Page {

    private String roleName;

    private String roleCode;

    private String createTime;

    private String updateTime;

    private Integer isDelete;

}
