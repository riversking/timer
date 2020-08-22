package com.rivers.user.api.dto;

import com.rivers.utils.dto.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author riversking
 * @date 2019年02月14日
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode {

    private String name;

    private String label;

    private Integer orderNum;

}
