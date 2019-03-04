package com.rivers.user.api.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author riverskingking
 */
@FeignClient(name = "OAUTH-SERVER")
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
    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
    JSONObject getAccessToken(@RequestHeader("Authorization") String authorization,
                              @RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("grant_type") String grantType);

}
