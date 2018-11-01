package com.riversking.oauth.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableAuthorizationServer
@Order(Integer.MIN_VALUE)
@Configuration
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("timer_oauth:");
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthSecurity) {
        oauthSecurity.allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()");

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endPoints) {
        endPoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancer())
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        String selectSql = "select client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
                + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
                + "refresh_token_validity, additional_information, autoapprove from sys_oauth_client where client_id = ?";
        clientDetailsService.setSelectClientDetailsSql(selectSql);

        String findSql = "select client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
                + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
                + "refresh_token_validity, additional_information, autoapprove from sys_oauth_client order by client_id";
        clientDetailsService.setFindClientDetailsSql(findSql);

        clientDetailsServiceConfigurer.withClientDetails(clientDetailsService);
    }


    /**
     * token 增强器
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(1);
            additionalInfo.put("license", "timer");
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

}
