package com.rivers.user.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.rivers.core.util.ExceptionUtil;
import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.dto.UserDto;
import com.rivers.user.api.entity.SysUserModel;
import com.rivers.user.api.feign.OauthClientFeign;
import com.rivers.user.api.vo.TokenVo;
import com.rivers.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author riversking
 */
@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    @Autowired
    private OauthClientFeign oauthClientFeign;

    @Autowired
    private UserService userService;


    @PostMapping("login")
    public ResponseVo login(@RequestBody RequestVo<SysUserModel> requestVo) {
        ResponseVo responseVo = ResponseVo.ok();
        SysUserModel sysUserModel = requestVo.getParam();
        JSONObject jsonObject = oauthClientFeign.getAccessToken("Basic YWRtaW46c2VjcmV0",
                sysUserModel.getUsername(), sysUserModel.getPassword(), "password");
        TokenVo token = JSONObject.toJavaObject(jsonObject, TokenVo.class);
        responseVo.setRsp(token);
        responseVo.setMsg("请求成功");
        return responseVo;
    }

    @PostMapping("addUser")
    public ResponseVo addUser(@RequestBody RequestVo<UserDto> requestVo) {
        log.info("getParam {}", requestVo.getParam());
        ResponseVo vo = ResponseVo.ok();
        UserDto userDto = requestVo.getParam();
        if (StrUtil.isBlank(userDto.getUsername())) {
            return ResponseVo.fail("-101001", "用户名为空");
        }
        if (StrUtil.isBlank(userDto.getPassword())) {
            return ResponseVo.fail("-101001", "密码为空");
        }
        userService.addUser(userDto);
        vo.setMsg("操作成功");
        return vo;
    }


}
