package com.rivers.user.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * SysRoleMenu
 *
 * @author riversking
 * @Date 2019-01-09 15:09
 */
@TableName("sys_role_menu")
public class SysRoleMenuModel extends Model<SysRoleMenuModel> {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 角色ID
	 *
	 */
	@TableField(value="role_id")
	private Integer roleId;

	/**
	 * 菜单ID
	 *
	 */
	@TableField(value="menu_id")
	private Integer menuId;


	/**
	 * 获取: 主键
	 *
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置: 主键
	 *
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取: 角色ID
	 *
	 */
	public Integer getRoleId() {
		return roleId;
	}
	/**
	 * 设置: 角色ID
	 *
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取: 菜单ID
	 *
	 */
	public Integer getMenuId() {
		return menuId;
	}
	/**
	 * 设置: 菜单ID
	 *
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}