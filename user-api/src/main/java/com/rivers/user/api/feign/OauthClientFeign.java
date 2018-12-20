package com.rivers.user.api.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("OAUTH-SERVER")
public interface OauthClientFeign {

    /**
     * 调用token接口
     * @return
     */
    @PostMapping("/oauth/token")
    JSONObject getAccessToken();

}
