package com.rivers.user.api.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * SysOauthClient
 *
 * @author riverskingking
 * @Date 2018-11-03 01:03
 */
@TableName("sys_oauth_client")
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


	/**
	 * 获取: ClientId
	 *
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * 设置: ClientId
	 *
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * 获取: ResourceIds
	 *
	 */
	public String getResourceIds() {
		return resourceIds;
	}
	/**
	 * 设置: ResourceIds
	 *
	 */
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	/**
	 * 获取: ClientSecret
	 *
	 */
	public String getClientSecret() {
		return clientSecret;
	}
	/**
	 * 设置: ClientSecret
	 *
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	/**
	 * 获取: Scope
	 *
	 */
	public String getScope() {
		return scope;
	}
	/**
	 * 设置: Scope
	 *
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	/**
	 * 获取: AuthorizedGrantTypes
	 *
	 */
	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}
	/**
	 * 设置: AuthorizedGrantTypes
	 *
	 */
	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}
	/**
	 * 获取: WebServerRedirectUri
	 *
	 */
	public String getWebServerRedirectUri() {
		return webServerRedirectUri;
	}
	/**
	 * 设置: WebServerRedirectUri
	 *
	 */
	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}
	/**
	 * 获取: Authorities
	 *
	 */
	public String getAuthorities() {
		return authorities;
	}
	/**
	 * 设置: Authorities
	 *
	 */
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	/**
	 * 获取: AccessTokenValidity
	 *
	 */
	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}
	/**
	 * 设置: AccessTokenValidity
	 *
	 */
	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}
	/**
	 * 获取: RefreshTokenValidity
	 *
	 */
	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}
	/**
	 * 设置: RefreshTokenValidity
	 *
	 */
	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}
	/**
	 * 获取: AdditionalInformation
	 *
	 */
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	/**
	 * 设置: AdditionalInformation
	 *
	 */
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	/**
	 * 获取: Autoapprove
	 *
	 */
	public String getAutoapprove() {
		return autoapprove;
	}
	/**
	 * 设置: Autoapprove
	 *
	 */
	public void setAutoapprove(String autoapprove) {
		this.autoapprove = autoapprove;
	}

	@Override
	protected Serializable pkVal() {
		return this.clientId;
	}
}