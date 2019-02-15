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
 * SysRoleMenu
 *
 * @author riversking
 * @Date 2019-01-09 15:09
 */
@TableName("sys_role_menu")
@Data
@EqualsAndHashCode(callSuper = true)
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


	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}