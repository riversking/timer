package com.rivers.user.api.feign;

import com.alibaba.fastjson.JSONObject;
import com.rivers.core.view.RequestVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("USER-SERVER")
public interface UserClientFeign {

    /**
     * 查询用户信息
     * @param requestVo requestVo
     * @return JSONObject
     */
    @PostMapping("userInfo")
    JSONObject userInfo(@RequestBody RequestVo<String> requestVo);

}
