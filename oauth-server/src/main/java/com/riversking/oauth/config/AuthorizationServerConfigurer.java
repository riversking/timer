package com.riversking.oauth.config;

import com.riversking.oauth.common.CustomWebResponseExceptionTranslator;
import com.riversking.oauth.service.ClientDetailsServiceImpl;
import com.riversking.oauth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 认证服务器
 *
 * @author hwj
 * @date 2018/9/10
 * 加上这个注解则会生成oauth2的几个endpoint
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigurer extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;

    @Autowired
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public MyRedisTokenStore tokenStore() {
        MyRedisTokenStore myRedisTokenStore = new MyRedisTokenStore(redisConnectionFactory);
        myRedisTokenStore.setPrefix("timer-oauth:");
        return myRedisTokenStore;
    }


    @Primary
    @Bean
    DefaultTokenServices tokenServices() {
        DefaultTokenServices d = new DefaultTokenServices();
        d.setAccessTokenValiditySeconds(600);//设置token有效期
        d.setRefreshTokenValiditySeconds(1000);
        d.setTokenStore(tokenStore());
        d.setReuseRefreshToken(false);//是否重复使用token
        d.setSupportRefreshToken(true);//是否支持refresh token
        return d;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")//对于CheckEndpoint控制器[框架自带的校验]的/oauth/token端点允许所有客户端发送器请求而不会被Spring-security拦截
                .checkTokenAccess("permitAll()")//要访问/oauth/check_token必须设置为permitAll()，但这样所有人都可以访问了，设为isAuthenticated()又导致访问不了，这个问题暂时没找到解决方案
                .allowFormAuthenticationForClients()//允许客户表单认证,不加的话/oauth/token无法访问
                .passwordEncoder(passwordEncoder);//设置oauth_client_details中的密码编码器

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenServices(tokenServices())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
        // endpoints.pathMapping("/oauth/token","/oauth/token3");//可以修改默认的endpoint路径
        endpoints.tokenEnhancer(tokenEnhancer());//增加token返回内容
//        endpoints.accessTokenConverter();
        endpoints.exceptionTranslator(customWebResponseExceptionTranslator);//修改异常时返回格式
    }


    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (OAuth2AccessToken accessToken, OAuth2Authentication authentication) -> {
            if (accessToken instanceof DefaultOAuth2AccessToken) {
                DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                Map<String, Object> additionalInformation = new LinkedHashMap<>();
                additionalInformation.put("username", authentication.getDetails());
                token.setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        };

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
