package com.rivers.user.api.client;

import com.alibaba.fastjson.JSONObject;
import com.rivers.userservice.proto.GetUserByUserNameReq;
import com.rivers.userservice.proto.GetUserByUserNameRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author riversking
 */
@FeignClient(name = "USER-SERVER")
public interface UserClientFeign {


    /**
     * 用户查询
     *
     * @param req req
     * @return GetUserByUserNameRes
     */
    @PostMapping(value = "/user/info")
    GetUserByUserNameRes userInfo(@RequestBody GetUserByUserNameReq req);
}
