package com.cloudder.utils.tree;

import java.util.List;

public class CommonTree {

    private Long id;

    private Integer level;

    private Long parentId;

    private List<CommonTree> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<CommonTree> getChildren() {
        return children;
    }

    public void setChildren(List<CommonTree> children) {
        this.children = children;
    }


}
