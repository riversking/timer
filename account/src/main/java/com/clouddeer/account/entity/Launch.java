package com.clouddeer.account.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "cd_launch")
public class Launch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(name = "launch_status")
    private Integer launchStatus;

    @Column(name = "plan_id")
    private Integer planId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "time_launch")
    private Boolean timeLaunch;

    private Date timing;

    @Column(name = "is_launch")
    private Integer isLaunch;

    @Column(name = "launch_time")
    private Date launchTime;

    @Column(name = "success_num")
    private Integer successNum;

    @Column(name = "sending_num")
    private Integer sendingNum;

    @Column(name = "failed_num")
    private Integer failedNum;

    @Column(name = "confirmed_num")
    private Integer confirmedNum;

    @Column(name = "denied_num")
    private Integer deniedNum;

    @Column(name = "edit_num")
    private Integer editNum;

    @Column(name = "tobe_confirmed_num")
    private Integer tobeConfirmedNum;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "create_user_id")
    private Integer createUserId;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_user_id")
    private Integer updateUserId;

    @Column(name = "record_status")
    private Integer recordStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLaunchStatus() {
        return launchStatus;
    }

    public void setLaunchStatus(Integer launchStatus) {
        this.launchStatus = launchStatus;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getTimeLaunch() {
        return timeLaunch;
    }

    public void setTimeLaunch(Boolean timeLaunch) {
        this.timeLaunch = timeLaunch;
    }

    public Date getTiming() {
        return timing;
    }

    public void setTiming(Date timing) {
        this.timing = timing;
    }

    public Integer getIsLaunch() {
        return isLaunch;
    }

    public void setIsLaunch(Integer isLaunch) {
        this.isLaunch = isLaunch;
    }

    public Date getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(Date launchTime) {
        this.launchTime = launchTime;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }

    public Integer getSendingNum() {
        return sendingNum;
    }

    public void setSendingNum(Integer sendingNum) {
        this.sendingNum = sendingNum;
    }

    public Integer getFailedNum() {
        return failedNum;
    }

    public void setFailedNum(Integer failedNum) {
        this.failedNum = failedNum;
    }

    public Integer getConfirmedNum() {
        return confirmedNum;
    }

    public void setConfirmedNum(Integer confirmedNum) {
        this.confirmedNum = confirmedNum;
    }

    public Integer getDeniedNum() {
        return deniedNum;
    }

    public void setDeniedNum(Integer deniedNum) {
        this.deniedNum = deniedNum;
    }

    public Integer getEditNum() {
        return editNum;
    }

    public void setEditNum(Integer editNum) {
        this.editNum = editNum;
    }

    public Integer getTobeConfirmedNum() {
        return tobeConfirmedNum;
    }

    public void setTobeConfirmedNum(Integer tobeConfirmedNum) {
        this.tobeConfirmedNum = tobeConfirmedNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }
}