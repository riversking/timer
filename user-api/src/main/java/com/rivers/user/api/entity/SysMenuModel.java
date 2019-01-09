package com.rivers.user.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * SysMenu
 *
 * @author rivers
 * @Date 2019-01-09 15:06
 */
@TableName("sys_menu")
public class SysMenuModel extends Model<SysMenuModel> {
	private static final long serialVersionUID = 1L;
	/**
	 * 菜单ID
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 菜单名称
	 *
	 */
	@TableField(value="name")
	private String name;

	/**
	 * 菜单权限标识
	 *
	 */
	@TableField(value="permission")
	private String permission;

	/**
	 * 前端URL
	 *
	 */
	@TableField(value="path")
	private String path;

	/**
	 * 父菜单ID
	 *
	 */
	@TableField(value="parent_id")
	private Integer parentId;

	/**
	 * 图标
	 *
	 */
	@TableField(value="icon")
	private String icon;

	/**
	 * VUE页面
	 *
	 */
	@TableField(value="component")
	private String component;

	/**
	 * 排序值
	 *
	 */
	@TableField(value="sort")
	private Integer sort;

	/**
	 * 菜单类型 （0菜单 1按钮）
	 *
	 */
	@TableField(value="type")
	private String type;

	/**
	 * 创建时间
	 *
	 */
	@TableField(value="create_time")
	private Date createTime;

	/**
	 * 更新时间
	 *
	 */
	@TableField(value="update_time")
	private Date updateTime;

	/**
	 * 0--正常 1--删除
	 *
	 */
	@TableField(value="del_flag")
	private String delFlag;

	/**
	 * iframe路径
	 *
	 */
	@TableField(value="iframe")
	private String iframe;

	/**
	 * 0表示有 1表示没有
	 *
	 */
	@TableField(value="is_content")
	private String isContent;


	/**
	 * 获取: 菜单ID
	 *
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置: 菜单ID
	 *
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取: 菜单名称
	 *
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置: 菜单名称
	 *
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取: 菜单权限标识
	 *
	 */
	public String getPermission() {
		return permission;
	}
	/**
	 * 设置: 菜单权限标识
	 *
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}
	/**
	 * 获取: 前端URL
	 *
	 */
	public String getPath() {
		return path;
	}
	/**
	 * 设置: 前端URL
	 *
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * 获取: 父菜单ID
	 *
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置: 父菜单ID
	 *
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取: 图标
	 *
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * 设置: 图标
	 *
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 获取: VUE页面
	 *
	 */
	public String getComponent() {
		return component;
	}
	/**
	 * 设置: VUE页面
	 *
	 */
	public void setComponent(String component) {
		this.component = component;
	}
	/**
	 * 获取: 排序值
	 *
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置: 排序值
	 *
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取: 菜单类型 （0菜单 1按钮）
	 *
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置: 菜单类型 （0菜单 1按钮）
	 *
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取: 创建时间
	 *
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置: 创建时间
	 *
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取: 更新时间
	 *
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置: 更新时间
	 *
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取: 0--正常 1--删除
	 *
	 */
	public String getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置: 0--正常 1--删除
	 *
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取: iframe路径
	 *
	 */
	public String getIframe() {
		return iframe;
	}
	/**
	 * 设置: iframe路径
	 *
	 */
	public void setIframe(String iframe) {
		this.iframe = iframe;
	}
	/**
	 * 获取: 0表示有 1表示没有
	 *
	 */
	public String getIsContent() {
		return isContent;
	}
	/**
	 * 设置: 0表示有 1表示没有
	 *
	 */
	public void setIsContent(String isContent) {
		this.isContent = isContent;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}