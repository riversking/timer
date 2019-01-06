package com.rivers.user.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;


import java.io.Serializable;
import java.util.Date;

/**
 * SysRole
 *
 * @author rivers
 * @Date 2018-12-19 10:46
 */
@TableName("sys_role")
public class SysRoleModel extends Model<SysRoleModel> {
	private static final long serialVersionUID = 1L;
	/**
	 * Id
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * RoleName
	 *
	 */
	@TableField(value="role_name")
	private String roleName;

	/**
	 * RoleCode
	 *
	 */
	@TableField(value="role_code")
	private String roleCode;

	/**
	 * RoleDesc
	 *
	 */
	@TableField(value="role_desc")
	private String roleDesc;

	/**
	 * CreateTime
	 *
	 */
	@TableField(value="create_time")
	private Date createTime;

	/**
	 * UpdateTime
	 *
	 */
	@TableField(value="update_time")
	private Date updateTime;

	/**
	 * 删除标识（0-正常
	 * 删除标识（0-正常,1-删除）
	 */
	@TableField(value="del_flag")
	private String delFlag;


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
	 * 获取: RoleName
	 *
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * 设置: RoleName
	 *
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 获取: RoleCode
	 *
	 */
	public String getRoleCode() {
		return roleCode;
	}
	/**
	 * 设置: RoleCode
	 *
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	/**
	 * 获取: RoleDesc
	 *
	 */
	public String getRoleDesc() {
		return roleDesc;
	}
	/**
	 * 设置: RoleDesc
	 *
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	/**
	 * 获取: CreateTime
	 *
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置: CreateTime
	 *
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取: UpdateTime
	 *
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置: UpdateTime
	 *
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取: 删除标识（0-正常
	 * 删除标识（0-正常,1-删除）
	 */
	public String getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置: 删除标识（0-正常
	 * 删除标识（0-正常,1-删除）
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}