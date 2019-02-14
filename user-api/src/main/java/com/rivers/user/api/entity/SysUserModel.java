package com.rivers.user.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * SysUser
 *
 * @author riversking
 * @Date 2018-12-19 14:51
 */
@TableName("sys_user")
public class SysUserModel extends Model<SysUserModel> {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 用户名
	 *
	 */
	@TableField(value="username")
	private String username;

	/**
	 * Password
	 *
	 */
	@TableField(value="password")
	private String password;

	/**
	 * 随机盐
	 *
	 */
	@TableField(value="salt")
	private String salt;

	/**
	 * 简介
	 *
	 */
	@TableField(value="phone")
	private String phone;

	/**
	 * 头像
	 *
	 */
	@TableField(value="avatar")
	private String avatar;


	@TableField(value = "create_user")
	private String createUser;

	/**
	 * 创建时间
	 *
	 */
	@TableField(value="create_time")
	private Date createTime;


	@TableField(value = "update_user")
	private String updateUser;

	/**
	 * 修改时间
	 *
	 */
	@TableField(value="update_time")
	private Date updateTime;

	/**
	 * 0-正常，1-删除
	 *
	 */
	@TableLogic
	@TableField(value="del_flag")
	private Integer delFlag;

	@TableField(exist = false)
	private List<SysRoleModel> sysRoleModels;

	@TableField(exist = false)
	private List<Integer> roleIds;

	/**
	 * 获取: 主键ID
	 *
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置: 主键ID
	 *
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取: 用户名
	 *
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置: 用户名
	 *
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取: Password
	 *
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置: Password
	 *
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取: 随机盐
	 *
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * 设置: 随机盐
	 *
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * 获取: 简介
	 *
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置: 简介
	 *
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取: 头像
	 *
	 */
	public String getAvatar() {
		return avatar;
	}
	/**
	 * 设置: 头像
	 *
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * 获取: 修改时间
	 *
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置: 修改时间
	 *
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取: 0-正常，1-删除
	 *
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置: 0-正常，1-删除
	 *
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public List<SysRoleModel> getSysRoleModels() {
		return sysRoleModels;
	}

	public void setSysRoleModels(List<SysRoleModel> sysRoleModels) {
		this.sysRoleModels = sysRoleModels;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}