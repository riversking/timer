package com.rivers.oauth.service;

import com.alibaba.fastjson.JSONObject;
import com.rivers.core.view.RequestVo;
import com.rivers.user.api.client.UserClientFeign;
import com.rivers.user.api.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;


/**
 * @author riversking
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserClientFeign userClientFeign;

    /**
     * 注意password需要BCrypt加密，否则会报Encoded password does not look like BCrypt
     * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
     * 因此这里授予的是用户的资源权限而非角色（角色是变化的，而系统的资源是固定的）
     *
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //这里实际的做法应该是从数据库获取用户的权限信息
        //这里为了方便演示，创建了两个用户，一个admin，拥有res1,res2和res3
        //一个user,只拥有res1和res2
        //一个guest,只拥有res1
        RequestVo<String> vo = new RequestVo<>();
        vo.setParam(s);
//        JSONObject result = userClientFeign.userInfo(SecurityConstants.FROM_IN, vo);
//        UserDetails userDetails = getUserDetails(result);
        User user = null;
        if ("admin".equalsIgnoreCase(s)) {
            user = mockAdmin();
        }
        if ("user".equalsIgnoreCase(s)) {
            user = mockUser();
        }
        if ("guest".equalsIgnoreCase(s)) {
            user = mockGuest();
        }
        return user;
    }


    private UserDetails getUserDetails(JSONObject result) {
        System.out.println("resultresultresultresultresultresultresultresult" + result);
        return null;
    }


    private User mockAdmin() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("res1"));//用户所拥有权限,注意不是角色
        authorities.add(new SimpleGrantedAuthority("res2"));//用户所拥有权限,注意不是角色
        authorities.add(new SimpleGrantedAuthority("res3"));//用户所拥有权限,注意不是角色
        User user = new User("admin", passwordEncoder.encode("123456"), authorities);

        return user;
    }

    private User mockUser() {
        User user = new User("user", passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("res1,res2"));
        return user;
    }

    private User mockGuest() {
        User user = new User("guest", passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("res2"));
        return user;
    }
}
