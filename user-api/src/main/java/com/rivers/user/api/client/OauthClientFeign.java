package com.rivers.user.api.client;

import com.alibaba.fastjson.JSONObject;
import com.rivers.core.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author riverskingking
 */
@FeignClient(name = "OAUTH-SERVER", configuration = FeignConfiguration.class)
public interface OauthClientFeign {


    /**
     * 获取TOKEN
     *
     * @param authorization authorization
     * @param username      username
     * @param password      password
     * @param grantType     grantType
     * @return JSONObject
     */
    @PostMapping(value = "/oauth/token")
    JSONObject getAccessToken(@RequestHeader("Authorization") String authorization,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("grant_type") String grantType);

}
