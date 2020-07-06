package com.rivers.file.client;

import com.alibaba.fastjson.JSONObject;
import com.rivers.file.config.FeignConfig;
import com.rivers.file.config.ProtobufHttpMessageConverter;
import com.rivers.userservice.proto.GetUserListReq;
import com.rivers.userservice.proto.GetUserListRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author riversking
 */
@FeignClient(name = "USER-SERVER", configuration = {FeignConfig.class, ProtobufHttpMessageConverter.class})
public interface UserClientFeign {


    @PostMapping(value = "/user/getUserPage", produces = MediaType.APPLICATION_JSON_VALUE)
    GetUserListRes userPage(@RequestBody GetUserListReq req);
}
