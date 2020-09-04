package com.rivers.nba.model;


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
 * Player
 *
 * @author riversking
 * @Date 2020-09-02 10:04
 */
@EqualsAndHashCode(callSuper = true)
@TableName("player")
@Data
public class PlayerModel extends Model<PlayerModel> {
    private static final long serialVersionUID = 1L;
    /**
     * 球员出生城市
     *
     */
    @TableField(value="birth_city")
    private String birthCity;

    /**
     * 球员出生国家
     *
     */
    @TableField(value="birth_country")
    private String birthCountry;

    /**
     * 球员出生日期
     *
     */
    @TableField(value="birth_date")
    private Date birthDate;

    /**
     * 球员出生州
     *
     */
    @TableField(value="birth_state")
    private String birthState;

    /**
     * 大学
     *
     */
    @TableField(value="college")
    private String college;

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
     * 职业年限
     *
     */
    @TableField(value="experience")
    private Integer experience;

    /**
     * 球员名字
     *
     */
    @TableField(value="first_name")
    private String firstName;

    /**
     * 球员身高 米
     *
     */
    @TableField(value="height")
    private Integer height;

    /**
     * 主键
     *
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 球衣号码
     *
     */
    @TableField(value="jersey")
    private Integer jersey;

    /**
     * 球员的姓
     *
     */
    @TableField(value="last_name")
    private String lastName;

    /**
     * 头像
     *
     */
    @TableField(value="photo_url")
    private String photoUrl;

    /**
     * 球员id
     *
     */
    @TableField(value="player_id")
    private Integer playerId;

    /**
     * 球员位置
     *
     */
    @TableField(value="position")
    private String position;

    /**
     * 位置分类
     *
     */
    @TableField(value="position_category")
    private String positionCategory;

    /**
     * 年薪
     *
     */
    @TableField(value="salary")
    private Integer salary;

    /**
     * 球员状态
     *
     */
    @TableField(value="status")
    private String status;

    /**
     * 球队名称
     *
     */
    @TableField(value="team")
    private String team;

    /**
     * 球队id
     *
     */
    @TableField(value="team_id")
    private Integer teamId;

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

    /**
     * 球员体重 磅
     *
     */
    @TableField(value="weight")
    private Integer weight;

    /**
     * 球员全称
     *
     */
    @TableField(value="draft_kings_name")
    private String draftKingsName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
