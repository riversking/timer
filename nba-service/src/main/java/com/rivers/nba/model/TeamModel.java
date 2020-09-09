package com.rivers.nba.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Team
 *
 * @author riversking
 * @Date 2020-09-09 17:48
 */
@TableName("team")
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
	 * 获取: 是否活跃 0.不活跃 1.活跃
	 * 
	 */
	public Integer getActive() {
		return active;
	}
	/**
	 * 设置: 是否活跃 0.不活跃 1.活跃
	 * 
	 */
	public void setActive(Integer active) {
		this.active = active;
	}
	/**
	 * 获取: 球队所在城市
	 * 
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置: 球队所在城市
	 * 
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取: 所在联盟 东部 或 西部
	 * 
	 */
	public String getConference() {
		return conference;
	}
	/**
	 * 设置: 所在联盟 东部 或 西部
	 * 
	 */
	public void setConference(String conference) {
		this.conference = conference;
	}
	/**
	 * 获取: 所在赛区
	 * 
	 */
	public String getDivision() {
		return division;
	}
	/**
	 * 设置: 所在赛区
	 * 
	 */
	public void setDivision(String division) {
		this.division = division;
	}
	/**
	 * 获取: 全球球队id
	 * 
	 */
	public Integer getGlobalTeamId() {
		return globalTeamId;
	}
	/**
	 * 设置: 全球球队id
	 * 
	 */
	public void setGlobalTeamId(Integer globalTeamId) {
		this.globalTeamId = globalTeamId;
	}
	/**
	 * 获取: 主键
	 * 
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置: 主键
	 * 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取: 球队名称
	 * 
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置: 球队名称
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取: nba官方球队id
	 * 
	 */
	public Integer getNbaDotComTeamId() {
		return nbaDotComTeamId;
	}
	/**
	 * 设置: nba官方球队id
	 * 
	 */
	public void setNbaDotComTeamId(Integer nbaDotComTeamId) {
		this.nbaDotComTeamId = nbaDotComTeamId;
	}
	/**
	 * 获取: 球队原色
	 * 
	 */
	public String getPrimaryColor() {
		return primaryColor;
	}
	/**
	 * 设置: 球队原色
	 * 
	 */
	public void setPrimaryColor(String primaryColor) {
		this.primaryColor = primaryColor;
	}
	/**
	 * 获取: 四季色
	 * 
	 */
	public String getQuaternaryColor() {
		return quaternaryColor;
	}
	/**
	 * 设置: 四季色
	 * 
	 */
	public void setQuaternaryColor(String quaternaryColor) {
		this.quaternaryColor = quaternaryColor;
	}
	/**
	 * 获取: 二次色
	 * 
	 */
	public String getSecondaryColor() {
		return secondaryColor;
	}
	/**
	 * 设置: 二次色
	 * 
	 */
	public void setSecondaryColor(String secondaryColor) {
		this.secondaryColor = secondaryColor;
	}
	/**
	 * 获取: 球馆id
	 * 
	 */
	public Integer getStadiumId() {
		return stadiumId;
	}
	/**
	 * 设置: 球馆id
	 * 
	 */
	public void setStadiumId(Integer stadiumId) {
		this.stadiumId = stadiumId;
	}
	/**
	 * 获取: 球队ID
	 * 
	 */
	public Integer getTeamId() {
		return teamId;
	}
	/**
	 * 设置: 球队ID
	 * 
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	/**
	 * 获取: 三原色
	 * 
	 */
	public String getTertiaryColor() {
		return tertiaryColor;
	}
	/**
	 * 设置: 三原色
	 * 
	 */
	public void setTertiaryColor(String tertiaryColor) {
		this.tertiaryColor = tertiaryColor;
	}
	/**
	 * 获取: 球队logo
	 * 
	 */
	public String getWikipediaLogoUrl() {
		return wikipediaLogoUrl;
	}
	/**
	 * 设置: 球队logo
	 * 
	 */
	public void setWikipediaLogoUrl(String wikipediaLogoUrl) {
		this.wikipediaLogoUrl = wikipediaLogoUrl;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}