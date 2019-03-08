package com.rivers.user.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author riversking
 */
@Data
public class MenuRoleDto {

    private Integer roleId;
    private List<Integer> menuIds;

}
