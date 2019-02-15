package com.rivers.user.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * SysOauthClient
 *
 * @author riverskingking
 * @Date 2018-11-03 01:03
 */
@TableName("sys_oauth_client")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysOauthClientModel extends Model<SysOauthClientModel> {
	private static final long serialVersionUID = 1L;
	/**
	 * ClientId
	 *
	 */
	@TableField(value="client_id")
	private String clientId;

	/**
	 * ResourceIds
	 *
	 */
	@TableField(value="resource_ids")
	private String resourceIds;

	/**
	 * ClientSecret
	 *
	 */
	@TableField(value="client_secret")
	private String clientSecret;

	/**
	 * Scope
	 *
	 */
	@TableField(value="scope")
	private String scope;

	/**
	 * AuthorizedGrantTypes
	 *
	 */
	@TableField(value="authorized_grant_types")
	private String authorizedGrantTypes;

	/**
	 * WebServerRedirectUri
	 *
	 */
	@TableField(value="web_server_redirect_uri")
	private String webServerRedirectUri;

	/**
	 * Authorities
	 *
	 */
	@TableField(value="authorities")
	private String authorities;

	/**
	 * AccessTokenValidity
	 *
	 */
	@TableField(value="access_token_validity")
	private Integer accessTokenValidity;

	/**
	 * RefreshTokenValidity
	 *
	 */
	@TableField(value="refresh_token_validity")
	private Integer refreshTokenValidity;

	/**
	 * AdditionalInformation
	 *
	 */
	@TableField(value="additional_information")
	private String additionalInformation;

	/**
	 * Autoapprove
	 *
	 */
	@TableField(value="autoapprove")
	private String autoapprove;


	@Override
	protected Serializable pkVal() {
		return this.clientId;
	}
}