package com.clouddeer.account.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


/**
 * 授权账户表
 *
 * @author clouddeer
 * @email cd@clouddeer.com
 * @date 2018-06-13 15:27:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "cd_account")
public class CdAccount extends Page {

    //主键
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    //授权账号
    @Column(name = "account_name")
    private String accountName;

    //授权账户密码
    @Column(name = "account_pwd")
    private String accountPwd;

    //关联用户id
    @Column(name = "user_id")
    private Integer userId;

    //是否共享0.否1.是
    @Column(name = "is_share")
    private Integer isShare;

    //授权账户头像
    @Column(name = "head_portrait")
    private String headPortrait;

    //昵称
    @Column(name = "nickname")
    private String nickname;

    //排序
    @Column(name = "is_sort")
    private Integer isSort;

    //账号价格
    @Column(name = "price")
    private BigDecimal price;

    //授权平台
    @Column(name = "platform_name")
    private String platformName;

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

    @Column(name = "uuid")
    private String uuid;

    @Transient
    private CdAccountWeibo cdAccountWeibo;

    @Transient
    private CdAccountToutiao cdAccountToutiao;

    @Transient
    private CdAccountWangYi cdAccountWangYi;

    @Transient
    private CdAccountZhihu cdAccountZhihu;

    @Transient
    private CdAccountBtime cdAccountBtime;

    @Transient
    private List<Plan> planList;

    @Transient
    private List<CdArticle> articleList;

    @Transient
    private  List<CdTagLibrary> tagList;

    /**
     * 设置：主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：授权账号
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * 获取：授权账号
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 设置：授权账户密码
     */
    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    /**
     * 获取：授权账户密码
     */
    public String getAccountPwd() {
        return accountPwd;
    }

    /**
     * 设置：关联用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：关联用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置：是否共享0.否1.是
     */
    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    /**
     * 获取：是否共享0.否1.是
     */
    public Integer getIsShare() {
        return isShare;
    }

    /**
     * 设置：授权账户头像
     */
    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    /**
     * 获取：授权账户头像
     */
    public String getHeadPortrait() {
        return headPortrait;
    }

    /**
     * 设置：昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取：昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置：排序
     */
    public void setIsSort(Integer isSort) {
        this.isSort = isSort;
    }

    /**
     * 获取：排序
     */
    public Integer getIsSort() {
        return isSort;
    }

    /**
     * 设置：账号价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：账号价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置：授权平台
     */
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    /**
     * 获取：授权平台
     */
    public String getPlatformName() {
        return platformName;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public CdAccountWeibo getCdAccountWeibo() {
        return cdAccountWeibo;
    }

    public void setCdAccountWeibo(CdAccountWeibo cdAccountWeibo) {
        this.cdAccountWeibo = cdAccountWeibo;
    }

    public CdAccountToutiao getCdAccountToutiao() {
        return cdAccountToutiao;
    }

    public void setCdAccountToutiao(CdAccountToutiao cdAccountToutiao) {
        this.cdAccountToutiao = cdAccountToutiao;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

    public List<CdArticle> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<CdArticle> articleList) {
        this.articleList = articleList;
    }

    public List<CdTagLibrary> getTagList() {
        return tagList;
    }

    public void setTagList(List<CdTagLibrary> tagList) {
        this.tagList = tagList;
    }

    public CdAccountWangYi getCdAccountWangYi() {
        return cdAccountWangYi;
    }

    public void setCdAccountWangYi(CdAccountWangYi cdAccountWangYi) {
        this.cdAccountWangYi = cdAccountWangYi;
    }

    public CdAccountZhihu getCdAccountZhihu() {
        return cdAccountZhihu;
    }

    public void setCdAccountZhihu(CdAccountZhihu cdAccountZhihu) {
        this.cdAccountZhihu = cdAccountZhihu;
    }

    public CdAccountBtime getCdAccountBtime() {
        return cdAccountBtime;
    }

    public void setCdAccountBtime(CdAccountBtime cdAccountBtime) {
        this.cdAccountBtime = cdAccountBtime;
    }
}
