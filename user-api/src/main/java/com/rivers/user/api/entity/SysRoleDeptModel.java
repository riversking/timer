package com.rivers.user.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * SysRoleDept
 *
 * @author riversking
 * @Date 2019-01-09 15:07
 */
@TableName("sys_role_dept")
public class SysRoleDeptModel extends Model<SysRoleDeptModel> {
	private static final long serialVersionUID = 1L;
	/**
	 * Id
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
	 * 部门ID
	 *
	 */
	@TableField(value="dept_id")
	private Integer deptId;


	/**
	 * 获取: Id
	 *
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置: Id
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
	 * 获取: 部门ID
	 *
	 */
	public Integer getDeptId() {
		return deptId;
	}
	/**
	 * 设置: 部门ID
	 *
	 */
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}