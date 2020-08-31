package com.rivers.user.dto;

import lombok.Data;

import java.util.List;

/**
 * @author riversking
 */
@Data
public class MenuRoleDTO {

    private Integer roleId;
    private List<Integer> menuIds;

}
