package com.rivers.oauth.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rivers.user.api.entity.SysOauthClientModel;
import com.rivers.oauth.mapper.SysOauthClientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author rivers
 * 重写了ClientDetailsService
 */
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Resource
    private SysOauthClientDao sysOauthClientDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 注意secret需要BCrypt加密，否则会报Encoded password does not look like BCrypt
     *
     * @param s
     * @return
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        /**
         * client模式，没有用户的概念，直接与认证服务器交互，用配置中的客户端信息去申请accessToken，客户端有自己的client_id,client_secret对应于用户的username,password，而客户端也拥有自己的authorities，当采取client模式认证时，对应的权限也就是客户端自己的authorities。
         * password模式，自己本身有一套用户体系，在认证时需要带上自己的用户名和密码，以及客户端的client_id,client_secret。此时，accessToken所包含的权限是用户本身的权限，而不是客户端的权限
         */
        //scope表示此客户端可以授予的权限范围，是authorities的一个集合,且客户端必须得有一个scope
        //第二个参数resourceIds如果设置了，则在资源配置ResourceServerConfigurer中必须配置一致的resourceId
        QueryWrapper<SysOauthClientModel> wrapper = new QueryWrapper<>();
        wrapper.eq("client_id", s);
        SysOauthClientModel sysOauthClientModel = sysOauthClientDao.selectOne(wrapper);
        if (sysOauthClientModel.getClientId().equals(s)) {
            BaseClientDetails  bcd = new BaseClientDetails(s, "", sysOauthClientModel.getScope(), sysOauthClientModel.getAuthorizedGrantTypes(), "");
            bcd.setClientSecret(passwordEncoder.encode("secret"));
            return bcd;
        }
        return new BaseClientDetails();
    }
}
