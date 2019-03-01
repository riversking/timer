package com.rivers.user.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rivers.core.log.annotation.SysLog;
import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.annotation.Inner;
import com.rivers.user.api.constant.SecurityConstants;
import com.rivers.user.api.dto.UserDto;
import com.rivers.user.api.entity.SysUserModel;
import com.rivers.user.api.client.OauthClientFeign;
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

    @Autowired
    private TestController testController;


    /**
     * 登录
     *
     * @param requestVo requestVo
     * @return ResponseVo
     */
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
        responseVo.setDatas(token);
        responseVo.setMessage("请求成功");
        return responseVo;
    }

    /**
     * 添加用户
     *
     * @param requestVo requestVo
     * @return ResponseVo
     */
    @PostMapping("addUser")
    public ResponseVo addUser(@RequestBody RequestVo<UserDto> requestVo) {
        log.info("getParam {}", requestVo.getParam());
        ResponseVo vo = ResponseVo.ok();
        UserDto user = requestVo.getParam();
        List<SysUserModel> userModels = userService.getUser(user);
        if (StrUtil.isBlank(user.getPhone())) {
            return ResponseVo.fail("-101003", "手机号为空");
        }
        if (userModels.isEmpty()) {
            userService.addUser(user);
            vo.setMessage("操作成功");
        } else {
            return ResponseVo.fail("-101004", "用户名/手机号已存在");
        }
        return vo;
    }

    /**
     * 用户分页查询
     *
     * @param requestVo requestVo
     * @return ResponseVo
     */
    @PostMapping("userPage")
    @SysLog("用户分页查询")
    public ResponseVo userPage(@RequestBody RequestVo<UserDto> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        UserDto userDto = requestVo.getParam();
        IPage<SysUserModel> pageInfo = userService.getUserPage(userDto);
        vo.setMessage("查询成功");
        vo.setDatas(pageInfo);
        return vo;
    }

    /**
     * 通过id查询用户
     *
     * @param requestVo requestVo
     * @return ResponseVo
     */
    @PostMapping("getUserById")
    public ResponseVo getUserById(@RequestBody RequestVo<Integer> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        Integer id = requestVo.getParam();
        SysUserModel user = userService.getUserById(id);
        vo.setDatas(user);
        vo.setMessage("查询成功");
        return vo;
    }

    /**
     * 删除用户
     *
     * @param requestVo requestVo
     * @return ResponseVo
     */
    @PostMapping("deleteUser")
    public ResponseVo deleteUser(@RequestBody RequestVo<Integer> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        Integer id = requestVo.getParam();
        userService.deleteById(id);
        vo.setMessage("删除成功");
        return vo;
    }

    /**
     * 用户信息
     *
     * @param requestVo requestVo
     * @return ResponseVo
     */
    @PostMapping("userInfo")
    @Inner
    public ResponseVo userInfo(@RequestBody RequestVo<String> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        String username = requestVo.getParam();
        SysUserModel user = userService.getUserInfo(username);
        vo.setDatas(user);
        vo.setMessage("查询成功");
        return vo;
    }


}
