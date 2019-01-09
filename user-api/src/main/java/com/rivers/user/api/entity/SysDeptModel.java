package com.rivers.user.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * SysDept
 *
 * @author rivers
 * @Date 2019-01-09 14:45
 */
@TableName("sys_dept")
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

	/**
	 * 创建时间
	 *
	 */
	@TableField(value="create_time")
	private Date createTime;

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
	@TableField(value="del_flag")
	private String delFlag;

	/**
	 * ParentId
	 *
	 */
	@TableField(value="parent_id")
	private Integer parentId;


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
	 * 获取: 部门名称
	 *
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置: 部门名称
	 *
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取: 排序
	 *
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
	/**
	 * 设置: 排序
	 *
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
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
	 * 获取: 是否删除  -1：已删除  0：正常
	 *
	 */
	public String getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置: 是否删除  -1：已删除  0：正常
	 *
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取: ParentId
	 *
	 */
	public Integer getParentId() {
		return parentId;
	}
	/**
	 * 设置: ParentId
	 *
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}