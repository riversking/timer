package com.rivers.file.client;

import com.rivers.core.config.ProtobufHttpMessageConverter;
import com.rivers.core.config.RequestInterceptorConfig;
import com.rivers.userservice.proto.GetUserListReq;
import com.rivers.userservice.proto.GetUserListRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author riversking
 */
@FeignClient(name = "USER-SERVER", configuration = {RequestInterceptorConfig.class, ProtobufHttpMessageConverter.class})
public interface UserClientFeign {


    @PostMapping(value = "/user/getUserPage", produces = MediaType.APPLICATION_JSON_VALUE)
    GetUserListRes userPage(@RequestBody GetUserListReq req);
}
