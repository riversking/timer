package com.clouddeer.account.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.io.Serializable;
@Data
@Table(name = "cd_account_wangyi")
public class CdAccountWangYi implements Serializable{

    private static final long serialVersionUID = 1L;

    //主键
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    //授权账户id
    @Column(name = "account_id")
    private Integer accountId;

    //总订阅
    @Column(name = "subscribe_count")
    private Integer subscribeCount;

    //总阅读
    @Column(name = "read_count")
    private Integer readCount;


    //创建时间
    @Column(name = "create_date")
    private Date createDate;

    //创建人id
    @Column(name = "create_user_id")
    private Integer createUserId;

    //更新时间
    @Column(name = "update_date")
    private Date updateDate;

    //更新人id
    @Column(name = "update_user_id")
    private Integer updateUserId;

    //是否有效：0 无效，1 有效
    @Column(name = "record_status")
    private Integer recordStatus;
}
