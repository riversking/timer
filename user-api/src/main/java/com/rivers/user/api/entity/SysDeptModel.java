package com.rivers.user.api.entity;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * SysDept
 *
 * @author riversking
 * @Date 2019-01-09 14:45
 */
@TableName("sys_dept")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDeptModel extends Model<SysDeptModel> {
	private static final long serialVersionUID = 1L;
	/**
	 * Id
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 部门名称
	 *
	 */
	@TableField(value="name")
	private String name;

	/**
	 * 排序
	 *
	 */
	@TableField(value="order_num")
	private Integer orderNum;

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
	 * 是否删除  -1：已删除  0：正常
	 *
	 */
	@TableLogic
	@TableField(value="is_delete")
	private Integer isDelete;



	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}