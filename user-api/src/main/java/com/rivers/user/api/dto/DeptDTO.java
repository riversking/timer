package com.rivers.user.api.dto;

import com.rivers.core.bean.LoginUser;
import lombok.Data;

@Data
public class DeptDTO {

    private Integer id;

    private String name;

    private Integer orderNum;

    private Integer parentId;

    private LoginUser user;

}
