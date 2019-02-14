package com.rivers.user.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

import java.util.List;


/**
 * @author riverskingking
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
    public ResponseVo login(@RequestBody RequestVo<UserDto> requestVo) {
        ResponseVo responseVo = ResponseVo.ok();
        UserDto userDto = requestVo.getParam();
        SysUserModel userModel = userService.getUserDetail(userDto);
        if (userModel == null) {
            return ResponseVo.fail("-101003", "用户名或密码错误");
        }
        JSONObject jsonObject = oauthClientFeign.getAccessToken("Basic YWRtaW46c2VjcmV0",
                userDto.getUsername(), userDto.getPassword(), "password");
        TokenVo token = JSONObject.toJavaObject(jsonObject, TokenVo.class);
        responseVo.setRsp(token);
        responseVo.setMsg("请求成功");
        return responseVo;
    }

    @PostMapping("addUser")
    public ResponseVo addUser(@RequestBody RequestVo<UserDto> requestVo) {
        log.info("getParam {}", requestVo.getParam());
        ResponseVo vo = ResponseVo.ok();
        UserDto user = requestVo.getParam();
        List<SysUserModel> userModels = userService.getUserByName(user);
        if (StrUtil.isBlank(user.getPhone())) {
            return ResponseVo.fail("-101003", "手机号为空");
        }
        if (userModels.isEmpty()) {
            userService.addUser(user);
            vo.setMsg("操作成功");
        } else {
            return ResponseVo.fail("-101004", "用户名/手机号已存在");
        }
        return vo;
    }

    @PostMapping("userPage")
    public ResponseVo userPage(@RequestBody RequestVo<UserDto> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        UserDto userDto = requestVo.getParam();
        IPage<SysUserModel> pageInfo = userService.getUserPage(userDto);
        vo.setRsp(pageInfo);
        return vo;
    }

    @PostMapping("getUserById")
    public ResponseVo getUserById(@RequestBody RequestVo<Integer> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        Integer id = requestVo.getParam();
        SysUserModel user = userService.getUserById(id);
        vo.setRsp(user);
        vo.setMsg("查询成功");
        return vo;
    }

    @PostMapping("deleteUser")
    public ResponseVo deleteUser(@RequestBody RequestVo<Integer> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        Integer id = requestVo.getParam();
        userService.deleteById(id);
        vo.setMsg("删除成功");
        return vo;
    }


}
