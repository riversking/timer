package com.rivers.user.api.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * SysUserRole
 *
 * @author riversking
 * @Date 2019-01-09 14:43
 */
@TableName("sys_user_role")
@Data
@EqualsAndHashCode(callSuper = true)
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
	private String userId;

	/**
	 * 角色ID
	 *
	 */
	@TableField(value="role_id")
	private Integer roleId;

	@TableLogic
	@TableField(value = "is_delete")
	private Integer isDelete;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
