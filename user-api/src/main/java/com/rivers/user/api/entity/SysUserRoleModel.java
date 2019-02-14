package com.rivers.user.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * SysUserRole
 *
 * @author riversking
 * @Date 2019-01-09 14:43
 */
@TableName("sys_user_role")
public class SysUserRoleModel extends Model<SysUserRoleModel> {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 用户ID
	 *
	 */
	@TableField(value="user_id")
	private Integer userId;

	/**
	 * 角色ID
	 *
	 */
	@TableField(value="role_id")
	private Integer roleId;


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
	 * 获取: 用户ID
	 *
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置: 用户ID
	 *
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}