package com.clouddeer.account.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 标签库
 *
 * @author clouddeer
 * @email cd@clouddeer.com
 * @date 2018-06-05 16:47:42
 */
@Table(name = "cd_tag_library")
public class CdTagLibrary implements Serializable {
	private static final long serialVersionUID = 1L;

	    //主键id
    @Id
	@GeneratedValue(generator = "JDBC")
    private Integer id;

	    //标签名称
    @Column(name = "tag_name")
    private String tagName;

	    //使用这个tag的数量
    @Column(name = "num")
    private Integer num;

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
	 * 设置：标签名称
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	/**
	 * 获取：标签名称
	 */
	public String getTagName() {
		return tagName;
	}
	/**
	 * 设置：使用这个tag的数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：使用这个tag的数量
	 */
	public Integer getNum() {
		return num;
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
