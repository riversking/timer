package com.rivers.user.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * SysRoleDept
 *
 * @author riversking
 * @Date 2019-01-09 15:07
 */
@TableName("sys_role_dept")
@Data
@EqualsAndHashCode(callSuper = true)
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


	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}