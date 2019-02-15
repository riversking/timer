package com.rivers.user.api.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * SysMenu
 *
 * @author riversking
 * @date 2019-02-14 14:17
 */
@TableName("sys_menu")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysMenuModel extends Model<SysMenuModel> {

    private static final long serialVersionUID = 1L;
    /**
     * 菜单ID
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 菜单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 菜单权限标识
     */
    @TableField(value = "permission")
    private String permission;

    /**
     * 前端URL
     */
    @TableField(value = "path")
    private String path;

    /**
     * 父菜单ID
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * VUE页面
     */
    @TableField(value = "component")
    private String component;

    /**
     * 排序值
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 菜单类型 （0菜单 1按钮）
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改人
     */
    @TableField(value = "update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * iframe路径
     */
    @TableField(value = "iframe")
    private String iframe;

    /**
     * 0表示有 1表示没有
     */
    @TableField(value = "is_content")
    private String isContent;

    /**
     * 0--正常 1--删除
     */
    @TableField(value = "is_delete")
    @TableLogic
    private String isDelete;


}