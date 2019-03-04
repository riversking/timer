package com.rivers.user.api.client;

import com.alibaba.fastjson.JSONObject;
import com.rivers.core.view.RequestVo;
import com.rivers.user.api.config.RequestInterceptorConfig;
import com.rivers.user.api.constant.SecurityConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author riversking
 */
@FeignClient(name = "USER-SERVER", configuration = RequestInterceptorConfig.class)
public interface UserClientFeign {


    /**
     * 用户查询
     *
     * @param from from
     * @param vo   vo
     * @return JSONObject
     */
    @PostMapping(value = "userInfo")
    JSONObject userInfo(@RequestHeader(SecurityConstants.FROM) String from, @RequestBody RequestVo<String> vo);

}
