package com.clouddeer.account.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashMap;

@Table(name = "cd_launch_account")
public class LaunchAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "plan_id")
    private Integer planId;

    @Column(name = "plan_account_id")
    private Integer planAccountId;

    @Column(name = "launch_id")
    private Integer launchId;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "cooperation_status")
    private Integer cooperationStatus;

    @Column(name = "account_fee")
    private Double accountFee;

    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "edit_article")
    private Boolean editArticle;

    @Column(name = "lanuch_status")
    private Integer lanuchStatus;

    @Column(name = "billing_type")
    private Integer billingType;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "success_url")
    private String successUrl;

    @Column(name = "send_volume")
    private Integer sendVolume;

    @Column(name = "read_volume")
    private Integer readVolume;

    @Column(name = "repost_volume")
    private Integer repostVolume;

    @Column(name = "fans_num")
    private Integer fansNum;

    @Column(name = "comment_volume")
    private Integer commentVolume;

    @Column(name = "likes_volume")
    private Integer likesVolume;

    @Column(name = "private_letter_volume")
    private Integer privateLetterVolume;

    @Column(name = "message_volume")
    private Integer messageVolume;

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
    private Integer userId;

    @Transient
    private Integer accountUserId;

    @Transient
    private String accountUserName;

    @Transient
    private CdArticle cdArticle;

    private LinkedHashMap<String,String> linkedHashMap;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getPlanAccountId() {
        return planAccountId;
    }

    public void setPlanAccountId(Integer planAccountId) {
        this.planAccountId = planAccountId;
    }

    public Integer getLaunchId() {
        return launchId;
    }

    public void setLaunchId(Integer launchId) {
        this.launchId = launchId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCooperationStatus() {
        return cooperationStatus;
    }

    public void setCooperationStatus(Integer cooperationStatus) {
        this.cooperationStatus = cooperationStatus;
    }

    public Double getAccountFee() {
        return accountFee;
    }

    public void setAccountFee(Double accountFee) {
        this.accountFee = accountFee;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Boolean getEditArticle() {
        return editArticle;
    }

    public void setEditArticle(Boolean editArticle) {
        this.editArticle = editArticle;
    }

    public Integer getLanuchStatus() {
        return lanuchStatus;
    }

    public void setLanuchStatus(Integer lanuchStatus) {
        this.lanuchStatus = lanuchStatus;
    }

    public Integer getBillingType() {
        return billingType;
    }

    public void setBillingType(Integer billingType) {
        this.billingType = billingType;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public Integer getSendVolume() {
        return sendVolume;
    }

    public void setSendVolume(Integer sendVolume) {
        this.sendVolume = sendVolume;
    }

    public Integer getReadVolume() {
        return readVolume;
    }

    public void setReadVolume(Integer readVolume) {
        this.readVolume = readVolume;
    }

    public Integer getRepostVolume() {
        return repostVolume;
    }

    public void setRepostVolume(Integer repostVolume) {
        this.repostVolume = repostVolume;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public Integer getCommentVolume() {
        return commentVolume;
    }

    public void setCommentVolume(Integer commentVolume) {
        this.commentVolume = commentVolume;
    }

    public Integer getLikesVolume() {
        return likesVolume;
    }

    public void setLikesVolume(Integer likesVolume) {
        this.likesVolume = likesVolume;
    }

    public Integer getPrivateLetterVolume() {
        return privateLetterVolume;
    }

    public void setPrivateLetterVolume(Integer privateLetterVolume) {
        this.privateLetterVolume = privateLetterVolume;
    }

    public Integer getMessageVolume() {
        return messageVolume;
    }

    public void setMessageVolume(Integer messageVolume) {
        this.messageVolume = messageVolume;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAccountUserId() {
        return accountUserId;
    }

    public void setAccountUserId(Integer accountUserId) {
        this.accountUserId = accountUserId;
    }

    public String getAccountUserName() {
        return accountUserName;
    }

    public void setAccountUserName(String accountUserName) {
        this.accountUserName = accountUserName;
    }

    public CdArticle getCdArticle() {
        return cdArticle;
    }

    public void setCdArticle(CdArticle cdArticle) {
        this.cdArticle = cdArticle;
    }


    public LinkedHashMap<String, String> getLinkedHashMap() {
        return linkedHashMap;
    }

    public void setLinkedHashMap(LinkedHashMap<String, String> linkedHashMap) {
        this.linkedHashMap = linkedHashMap;
    }
}