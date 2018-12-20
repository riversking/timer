package com.rivers.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.entity.SysUserModel;
import com.rivers.user.api.feign.OauthClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author riversking
 */
@RestController("/user")
public class UserController {

    @Autowired
    private OauthClientFeign oauthClientFeign;


    @PostMapping("login")
    public ResponseVo login(@RequestBody RequestVo<SysUserModel> requestVo) {
        JSONObject jsonObject = oauthClientFeign.getAccessToken("YWRtaW46c2VjcmV0","admin","123456","password");
        System.out.println(jsonObject);
        return ResponseVo.ok();
    }


}
