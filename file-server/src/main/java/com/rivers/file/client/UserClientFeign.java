package com.rivers.file.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author riversking
 */
@FeignClient(name = "USER-SERVER")
public interface UserClientFeign {


    @PostMapping(value = "/user/userPage")
    JSONObject userPage(@RequestBody String username);
}
