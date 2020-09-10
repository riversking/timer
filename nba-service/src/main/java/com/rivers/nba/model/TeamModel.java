package com.rivers.nba.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Team
 *
 * @author riversking
 * @Date 2020-09-09 17:48
 */
@TableName("team")
@Data
public class TeamModel extends Model<TeamModel> {
	private static final long serialVersionUID = 1L;
	/**
	 * 是否活跃 0.不活跃 1.活跃
	 *
	 */
	@TableField(value="active")
	private Integer active;

	/**
	 * 球队所在城市
	 *
	 */
	@TableField(value="city")
	private String city;

	/**
	 * 所在联盟 东部 或 西部
	 *
	 */
	@TableField(value="conference")
	private String conference;

	/**
	 * 所在赛区
	 *
	 */
	@TableField(value="division")
	private String division;

	/**
	 * 全球球队id
	 *
	 */
	@TableField(value="global_team_id")
	private Integer globalTeamId;

	/**
	 * 主键
	 *
	 */
	@TableId(value = "id",type = IdType.AUTO)
	private Long id;

	/**
	 * 球队名称
	 *
	 */
	@TableField(value="name")
	private String name;

	/**
	 * nba官方球队id
	 *
	 */
	@TableField(value="nba_dot_com_team_id")
	private Integer nbaDotComTeamId;

	/**
	 * 球队原色
	 *
	 */
	@TableField(value="primary_color")
	private String primaryColor;

	/**
	 * 四季色
	 *
	 */
	@TableField(value="quaternary_color")
	private String quaternaryColor;

	/**
	 * 二次色
	 *
	 */
	@TableField(value="secondary_color")
	private String secondaryColor;

	/**
	 * 球馆id
	 *
	 */
	@TableField(value="stadium_id")
	private Integer stadiumId;

	/**
	 * 球队ID
	 *
	 */
	@TableField(value="team_id")
	private Integer teamId;

	/**
	 * 三原色
	 *
	 */
	@TableField(value="tertiary_color")
	private String tertiaryColor;

	/**
	 * 球队logo
	 *
	 */
	@TableField(value="wikipedia_logo_url")
	private String wikipediaLogoUrl;

	/**
	 * 创建时间
	 *
	 */
	@TableField(value="create_time")
	private Date createTime;

	/**
	 * 创建人
	 *
	 */
	@TableField(value="create_user")
	private String createUser;

	/**
	 * 修改时间
	 *
	 */
	@TableField(value="update_time")
	private Date updateTime;

	/**
	 * 修改人
	 *
	 */
	@TableField(value="update_user")
	private String updateUser;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
