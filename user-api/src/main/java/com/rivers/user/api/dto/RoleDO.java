package com.rivers.user.api.dto;

import com.rivers.core.bean.LoginUser;
import lombok.Data;

@Data
public class RoleDO {

    private String roleName;

    private String roleCode;

    private String roleDesc;

    private LoginUser user;
}
