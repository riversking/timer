package com.rivers.user.api.client;

import com.alibaba.fastjson.JSONObject;
import com.rivers.core.view.RequestVo;
import com.rivers.user.api.constant.SecurityConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVER")
public interface UserClientFeign {

    /**
     * userInfo: 查询用户信息
     *
     * @param vo requestVo
     * @return JSONObject
     */
    @PostMapping("userInfo")
    JSONObject userInfo(@RequestHeader(SecurityConstants.FROM) String from, @RequestBody RequestVo<String> vo);

}
