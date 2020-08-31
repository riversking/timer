package com.rivers.oauth.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.rivers.oauth.model.TimerUser;
import com.rivers.user.api.client.UserClientFeign;
import com.rivers.userservice.proto.GetUserByUserNameReq;
import com.rivers.userservice.proto.GetUserByUserNameRes;
import com.rivers.userservice.proto.UserInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * @author riversking
 */
@Service
@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserClientFeign userClientFeign;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 注意password需要BCrypt加密，否则会报Encoded password does not look like BCrypt
     * 授权的时候是对角色授权，而认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
     * 因此这里授予的是用户的资源权限而非角色（角色是变化的，而系统的资源是固定的）
     *
     * @param username username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里实际的做法应该是从数据库获取用户的权限信息
        //这里为了方便演示，创建了两个用户，一个admin，拥有res1,res2和res3
        //一个user,只拥有res1和res2
        //一个guest,只拥有res1
//        redisTemplate.delete("admin");
        UserDetails user = (UserDetails) redisTemplate.opsForValue().get(username);
        if (user != null) {
            log.info("USER {}", JSONObject.toJSONString(user));
            return user;
        }
        GetUserByUserNameRes getUserRes = userClientFeign
                .userInfo(GetUserByUserNameReq.newBuilder().setUsername(username).build());
        UserDetails userDetails = getUserDetails(getUserRes);
        redisTemplate.opsForValue().set(username, userDetails);
        log.info("userDetails {}", JSONObject.toJSONString(userDetails));
        return userDetails;
    }


    private UserDetails getUserDetails(GetUserByUserNameRes getUserRes) {
        UserInfo userInfo = getUserRes.getUserInfo();
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Set<String> dbAuthsSet = new HashSet<>(userInfo.getPermissionsList());
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        return new TimerUser(userInfo.getId(), userInfo.getUserId(), userInfo.getUsername(), userInfo.getPassword(),
                StrUtil.equals(String.valueOf(userInfo.getIsDisable()), "0"),
                true, true, true, authorities);
    }

}
