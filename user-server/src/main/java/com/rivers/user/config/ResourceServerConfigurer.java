package com.rivers.user.config;


import com.rivers.oauth2.oath.adaper.BaseResourceServerConfigurerAdapter;
import com.rivers.oauth2.oath.common.AuthExceptionEntryPoint;
import com.rivers.oauth2.oath.common.CustomAccessDeniedHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


/**
 * @author riversking
 */
@Configuration
@EnableResourceServer
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfigurer extends BaseResourceServerConfigurerAdapter {

    /**
     * why add  resourceId
     * <p>
     * https://stackoverflow.com/questions/28703847/how-do-you-set-a-resource-id-for-a-token
     *
     * @param resources resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }

}