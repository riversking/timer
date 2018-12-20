package com.rivers.user.api.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("OAUTH-SERVER")
public interface OauthClientFeign {

    /**
     * 获取token
     * @param Authorization
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/oauth/token")
    JSONObject getAccessToken(@RequestHeader("Authorization") String Authorization,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("grant_type") String grant_type);

}
