package com.rivers.user.api.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


import java.io.Serializable;
import java.util.Date;

/**
 * SysRole
 *
 * @author riversking
 * @Date 2018-12-19 10:46
 */
@TableName("sys_role")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleModel extends Model<SysRoleModel>  {
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

	@TableField(value = "create_user")
	private String createUser;

	/**
	 * CreateTime
	 *
	 */
	@TableField(value="create_time")
	private Date createTime;

	@TableField(value = "update_user")
	private String updateUser;

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
	@TableLogic
	@TableField(value="is_delete")
	private Integer isDelete;


	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}