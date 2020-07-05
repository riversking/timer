package com.rivers.user.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rivers.core.annotation.SysLog;
import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.client.OauthClientFeign;
import com.rivers.user.api.dto.UserDto;
import com.rivers.user.api.dto.UserInfo;
import com.rivers.user.api.entity.SysUserModel;
import com.rivers.user.api.vo.TokenVo;
import com.rivers.user.service.UserService;
import com.rivers.userservice.proto.AddUserReq;
import com.rivers.userservice.proto.GetUserListReq;
import com.rivers.userservice.proto.GetUserListRes;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
        if (userModel.getId() == null) {
            return ResponseVo.fail("-101003", "用户名或密码错误");
        }
        JSONObject jsonObject = oauthClientFeign.getAccessToken("Basic YWRtaW46c2VjcmV0",
                userDto.getUsername(), userDto.getPassword(), "password");
        TokenVo token = JSONObject.toJavaObject(jsonObject, TokenVo.class);
        token.setUid(userModel.getId());
        token.setAvatar(userModel.getAvatar());
        token.setNickname(userModel.getNickname());
        responseVo.setData(token);
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
        ResponseVo vo = ResponseVo.ok();
        UserDto user = requestVo.getParam();
        List<SysUserModel> userModels = userService.getUser(user);
        if (StrUtil.isBlank(user.getPhone())) {
            return ResponseVo.fail("-101003", "手机号为空");
        }
        if (userModels.isEmpty()) {
            user.setCreateUser(requestVo.getUserId());
            user.setUpdateUser(requestVo.getUserId());
            userService.addUser(user);
            vo.setMessage("操作成功");
        } else {
            return ResponseVo.fail("-101004", "用户名/手机号已存在");
        }
        return vo;
    }

    @PostMapping(value = "addUser1")
    public ResponseVo addUser1(@RequestBody RequestVo<AddUserReq> user) {
        ResponseVo vo = ResponseVo.ok();
//        List<SysUserModel> userModels = userService.getUser(user);
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
        vo.setData(pageInfo);
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
        vo.setData(user);
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
    public ResponseVo userInfo(@RequestBody RequestVo<String> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        String username = requestVo.getParam();
        SysUserModel user = userService.getUserInfo(username);
        vo.setData(user);
        vo.setMessage("查询成功");
        return vo;
    }

    @PostMapping("info")
    public ResponseVo info(@RequestBody String username) {
        ResponseVo vo = ResponseVo.ok();
        UserInfo userInfo = userService.queryUserInfo(username);
        vo.setData(userInfo);
        vo.setMessage("查询成功");
        return vo;
    }

    @PostMapping("isDisable")
    public ResponseVo isDisableByUserId(@RequestBody RequestVo<UserDto> user) {
        ResponseVo vo = ResponseVo.ok();
        userService.isDisableByUserId(user.getParam());
        return vo;
    }

    @PostMapping("updateUserById")
    public ResponseVo updateUserById(@RequestBody RequestVo<UserDto> userReq) {
        UserDto param = userReq.getParam();
        param.setUpdateUser(userReq.getUserId());
        userService.updateUserById(param);
        return ResponseVo.ok();
    }

    @PostMapping("importUserExcel")
    public ResponseVo importUserExcel(@RequestBody RequestVo<String> filepath) {
        userService.parseUserExcel(filepath.getParam());
        return ResponseVo.ok();
    }

    @PostMapping("exportUserExcel")
    public void exportUserExcel() {
        userService.exportUserExcel();
    }

    @PostMapping(value = "getUserPage")
    public GetUserListRes getUserPage(@RequestBody GetUserListReq req) {
//        ResponseVo vo = ResponseVo.ok();
//        vo.setData(userService.getUserList(req));
        System.out.println(userService.getUserList(req));
        return GetUserListRes.newBuilder().addAllUsers(userService.getUserList(req)).build();
    }


}
