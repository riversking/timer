package com.rivers.user.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Data
@EqualsAndHashCode(callSuper = true)
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
	@TableField(value="is_delete")
	private Integer isDelete;

	@TableField(exist = false)
	private List<SysRoleModel> sysRoleModels;

	@TableField(exist = false)
	private List<Integer> roleIds;

//	@TableField(exist = false)
//	private List<Integer> access;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}