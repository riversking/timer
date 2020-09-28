package com.rivers.nba.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Stadium
 *
 * @author riversking
 * @Date 2020-09-25 12:16
 */
@TableName("stadium")
@Data
public class StadiumModel extends Model<StadiumModel> {
    private static final long serialVersionUID = 1L;
    /**
     * 球馆地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 容量
     */
    @TableField(value = "capacity")
    private Integer capacity;

    /**
     * 所在城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 国家
     */
    @TableField(value = "country")
    private String country;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 维度
     */
    @TableField(value = "geo_lat")
    private BigDecimal geoLat;

    /**
     * 经度
     */
    @TableField(value = "geo_long")
    private BigDecimal geoLong;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 球馆名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 球馆id
     */
    @TableField(value = "stadium_id")
    private Integer stadiumId;

    /**
     * 所在州
     */
    @TableField(value = "state")
    private String state;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 修改人
     */
    @TableField(value = "update_user")
    private String updateUser;

    /**
     * 邮编
     */
    @TableField(value = "zip")
    private String zip;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}