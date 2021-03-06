package com.rivers.user.api.dto;

import com.rivers.user.api.entity.SysMenuModel;
import com.rivers.utils.dto.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author riversking
 * @date 2019年02月14日
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuTree extends TreeNode {
    private String icon;
    private String name;
    private boolean spread = false;
    private String path;
    private String component;
    private String authority;
    private String redirect;
    private String code;
    private Integer type;
    private String title;
    private String label;
    private Integer sort;
    private boolean checked = false;
    private boolean expand = false;
    private Map<String, String> props;
    private boolean show = true;
    private String createUser;
    private String updateUser;

    public MenuTree() {
    }


    public MenuTree(int id, String name, int parentId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.label = name;
        this.title = name;
    }

    public MenuTree(int id, String name, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.name = name;
        this.label = name;
        this.title = name;
    }

    public MenuTree(SysMenuModel menuVo) {
        this.id = menuVo.getId();
        this.parentId = menuVo.getParentId();
        this.icon = menuVo.getIcon();
        this.name = menuVo.getName();
        this.path = menuVo.getPath();
        this.component = menuVo.getComponent();
        this.type = menuVo.getType();
        this.label = menuVo.getName();
        this.sort = menuVo.getSort();
        this.title = menuVo.getName();
    }
}
