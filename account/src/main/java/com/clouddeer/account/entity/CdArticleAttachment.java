package com.clouddeer.account.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 文章图片表
 *
 * @author clouddeer
 * @email cd@clouddeer.com
 * @date 2018-06-25 10:06:26
 */
@Table(name = "cd_article_attachment")
public class CdArticleAttachment implements Serializable {
	private static final long serialVersionUID = 1L;

	    //主键id
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

	    //文章id
    @Column(name = "article_id")
    private Integer articleId;

	    //文章图片
    @Column(name = "source_src")
    private String sourceSrc;

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
	 * 设置：文章id
	 */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	/**
	 * 获取：文章id
	 */
	public Integer getArticleId() {
		return articleId;
	}
	/**
	 * 设置：文章图片
	 */
	public void setSourceSrc(String sourceSrc) {
		this.sourceSrc = sourceSrc;
	}
	/**
	 * 获取：文章图片
	 */
	public String getSourceSrc() {
		return sourceSrc;
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

}
