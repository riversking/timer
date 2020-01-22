package com.rivers.configjdbcclient.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * ConfigProperties
 *
 * @author rivers
 * @Date 2020-01-21 11:31
 */
@TableName("config_properties")
public class ConfigModel extends Model<ConfigModel> {
	private static final long serialVersionUID = 1L;
	/**
	 * 应用名称
	 *
	 */
	@TableField(value="application")
	private String application;

	/**
	 * Id
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * Key
	 *
	 */
	@TableField(value="key")
	private String key;

	/**
	 * 应用环境
	 *
	 */
	@TableField(value="label")
	private String label;

	/**
	 * 应用模块
	 *
	 */
	@TableField(value="profile")
	private String profile;

	/**
	 * Value
	 *
	 */
	@TableField(value="value")
	private String value;


	/**
	 * 获取: 应用名称
	 *
	 */
	public String getApplication() {
		return application;
	}
	/**
	 * 设置: 应用名称
	 *
	 */
	public void setApplication(String application) {
		this.application = application;
	}
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
	 * 获取: Key
	 *
	 */
	public String getKey() {
		return key;
	}
	/**
	 * 设置: Key
	 *
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 获取: 应用环境
	 *
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * 设置: 应用环境
	 *
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * 获取: 应用模块
	 *
	 */
	public String getProfile() {
		return profile;
	}
	/**
	 * 设置: 应用模块
	 *
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}
	/**
	 * 获取: Value
	 *
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置: Value
	 *
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}