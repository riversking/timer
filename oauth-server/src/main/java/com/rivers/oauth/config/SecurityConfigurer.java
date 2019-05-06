package com.rivers.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 只有开启了这个注解，@PreAuthorize才会起效，否则需要到ResourceServerConfigurerAdapter里配置才行
 *
 * @author wangyichuan
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 如果完全基于token，可以不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .addFilterAt(getOpenIdAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        http.authorizeRequests()
                .antMatchers("/oauth/**", "/actuator/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
