package com.clouddeer.account.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "cd_plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "u_id")
    private String uId;

    private String cover;

    @Column(name = "plan_name")
    private String planName;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "account_sum")
    private Integer accountSum;

    @Column(name = "account_own")
    private Integer accountOwn;

    @Column(name = "account_other")
    private Integer accountOther;

    @Column(name = "account_stop")
    private Integer accountStop;

    @Column(name = "total_send_volume")
    private Integer totalSendVolume;

    @Column(name = "total_read_volume")
    private Integer totalReadVolume;

    @Column(name = "total_repost_volume")
    private Integer totalRepostVolume;

    @Column(name = "total_fans_num")
    private Integer totalFansNum;

    @Column(name = "total_comment_volume")
    private Integer totalCommentVolume;

    @Column(name = "total_likes_volume")
    private Integer totalLikesVolume;

    @Column(name = "total_private_letter_volume")
    private Integer totalPrivateLetterVolume;

    @Column(name = "total_message_volume")
    private Integer totalMessageVolume;

    private Date deadline;

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

    @Transient
    private List<Integer> planAccountList;

    @Transient
    private List<Integer> planTagList;

    @Transient
    private List<CdAccount> planAccounts;



    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAccountSum() {
        return accountSum;
    }

    public void setAccountSum(Integer accountSum) {
        this.accountSum = accountSum;
    }

    public Integer getAccountOwn() {
        return accountOwn;
    }

    public void setAccountOwn(Integer accountOwn) {
        this.accountOwn = accountOwn;
    }

    public Integer getAccountOther() {
        return accountOther;
    }

    public void setAccountOther(Integer accountOther) {
        this.accountOther = accountOther;
    }

    public Integer getAccountStop() {
        return accountStop;
    }

    public void setAccountStop(Integer accountStop) {
        this.accountStop = accountStop;
    }

    public Integer getTotalSendVolume() {
        return totalSendVolume;
    }

    public void setTotalSendVolume(Integer totalSendVolume) {
        this.totalSendVolume = totalSendVolume;
    }

    public Integer getTotalReadVolume() {
        return totalReadVolume;
    }

    public void setTotalReadVolume(Integer totalReadVolume) {
        this.totalReadVolume = totalReadVolume;
    }

    public Integer getTotalRepostVolume() {
        return totalRepostVolume;
    }

    public void setTotalRepostVolume(Integer totalRepostVolume) {
        this.totalRepostVolume = totalRepostVolume;
    }

    public Integer getTotalFansNum() {
        return totalFansNum;
    }

    public void setTotalFansNum(Integer totalFansNum) {
        this.totalFansNum = totalFansNum;
    }

    public Integer getTotalCommentVolume() {
        return totalCommentVolume;
    }

    public void setTotalCommentVolume(Integer totalCommentVolume) {
        this.totalCommentVolume = totalCommentVolume;
    }

    public Integer getTotalLikesVolume() {
        return totalLikesVolume;
    }

    public void setTotalLikesVolume(Integer totalLikesVolume) {
        this.totalLikesVolume = totalLikesVolume;
    }

    public Integer getTotalPrivateLetterVolume() {
        return totalPrivateLetterVolume;
    }

    public void setTotalPrivateLetterVolume(Integer totalPrivateLetterVolume) {
        this.totalPrivateLetterVolume = totalPrivateLetterVolume;
    }

    public Integer getTotalMessageVolume() {
        return totalMessageVolume;
    }

    public void setTotalMessageVolume(Integer totalMessageVolume) {
        this.totalMessageVolume = totalMessageVolume;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getPlanAccountList() {
        return planAccountList;
    }

    public void setPlanAccountList(List<Integer> planAccountList) {
        this.planAccountList = planAccountList;
    }

    public List<Integer> getPlanTagList() {
        return planTagList;
    }

    public void setPlanTagList(List<Integer> planTagList) {
        this.planTagList = planTagList;
    }

    public List<CdAccount> getPlanAccounts() {
        return planAccounts;
    }

    public void setPlanAccounts(List<CdAccount> planAccounts) {
        this.planAccounts = planAccounts;
    }

}