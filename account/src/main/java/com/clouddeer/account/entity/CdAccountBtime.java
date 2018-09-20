package com.clouddeer.account.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 授权账户-北京时间表
 *
 * @author clouddeer
 * @email cd@clouddeer.com
 * @date 2018-06-13 15:27:02
 */
@Table(name = "cd_account_btime")
public class CdAccountBtime implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    //授权账户id
    @Column(name = "account_id")
    private Integer accountId;

    //粉丝数
    @Column(name = "followers_count")
    private Integer followersCount;

    //关注数
    @Column(name = "friends_count")
    private Integer friendsCount;

    //文章数
    @Column(name = "article_count")
    private Integer articleCount;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public Integer getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(Integer friendsCount) {
        this.friendsCount = friendsCount;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
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
