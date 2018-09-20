package com.clouddeer.account.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 渠道账号新建文章
 *
 * @author clouddeer
 * @email cd@clouddeer.com
 * @date 2018-07-02 15:18:25
 */
@Table(name = "cd_article")
public class CdArticle extends Page implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键id
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    //文章标题
    @Column(name = "article_title")
    private String articleTitle;

    //文章内容
    @Column(name = "content")
    private String content;

    //作者
    @Column(name = "author")
    private String author;

    //是否定时发布 0.不定时 1.定时
    @Column(name = "is_timing")
    private Integer isTiming;

    //定时发送时间
    @Column(name = "send_time")
    private Date sendTime;

    //是否打开留言功能 0：否  1 ：是
    @Column(name = "leave_message")
    private Integer leaveMessage;

    //摘要
    @Column(name = "summary")
    private String summary;

    //原文链接
    @Column(name = "original_link")
    private String originalLink;

    //是否原创  0：否  1 ：是
    @Column(name = "is_original")
    private Integer isOriginal;

    //文章封面图片地址
    @Column(name = "cover_src")
    private String coverSrc;

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
    @Transient
    private List<Integer> ids;

    @Transient
    private List<CdArticleAttachment> cdArticleAttachment;

    @Transient
    private Boolean _expanded = true;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public List<CdArticleAttachment> getCdArticleAttachment() {
        return cdArticleAttachment;
    }

    public void setCdArticleAttachment(List<CdArticleAttachment> cdArticleAttachment) {
        this.cdArticleAttachment = cdArticleAttachment;
    }

    /**
     * 设置：主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：文章标题
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    /**
     * 获取：文章标题
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * 设置：文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取：作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置：是否定时发布 0.不定时 1.定时
     */
    public void setIsTiming(Integer isTiming) {
        this.isTiming = isTiming;
    }

    /**
     * 获取：是否定时发布 0.不定时 1.定时
     */
    public Integer getIsTiming() {
        return isTiming;
    }

    /**
     * 设置：定时发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取：定时发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * 设置：是否打开留言功能 0：否  1 ：是
     */
    public void setLeaveMessage(Integer leaveMessage) {
        this.leaveMessage = leaveMessage;
    }

    /**
     * 获取：是否打开留言功能 0：否  1 ：是
     */
    public Integer getLeaveMessage() {
        return leaveMessage;
    }

    /**
     * 设置：摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取：摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置：原文链接
     */
    public void setOriginalLink(String originalLink) {
        this.originalLink = originalLink;
    }

    /**
     * 获取：原文链接
     */
    public String getOriginalLink() {
        return originalLink;
    }

    /**
     * 设置：是否原创  0：否  1 ：是
     */
    public void setIsOriginal(Integer isOriginal) {
        this.isOriginal = isOriginal;
    }

    /**
     * 获取：是否原创  0：否  1 ：是
     */
    public Integer getIsOriginal() {
        return isOriginal;
    }

    /**
     * 设置：文章封面图片地址
     */
    public void setCoverSrc(String coverSrc) {
        this.coverSrc = coverSrc;
    }

    /**
     * 获取：文章封面图片地址
     */
    public String getCoverSrc() {
        return coverSrc;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置：创建人id
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取：创建人id
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置：更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取：更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置：更新人id
     */
    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取：更新人id
     */
    public Integer getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 设置：是否有效：0 无效，1 有效
     */
    public void setRecordStatus(Integer recordStatus) {
        this.recordStatus = recordStatus;
    }

    /**
     * 获取：是否有效：0 无效，1 有效
     */
    public Integer getRecordStatus() {
        return recordStatus;
    }

    public Boolean get_expanded() {
        return _expanded;
    }

    public void set_expanded(Boolean _expanded) {
        this._expanded = _expanded;
    }
}
