package com.rivers.user.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.client.OauthClientFeign;
import com.rivers.user.api.dto.UserDTO;
import com.rivers.user.api.dto.UserInfo;
import com.rivers.user.api.entity.SysUserModel;
import com.rivers.user.api.vo.TokenVo;
import com.rivers.user.service.UserService;
import com.rivers.userservice.proto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


/**
 * @author riverskingking
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseVo login(@RequestBody RequestVo<UserDTO> requestVo) {
        ResponseVo responseVo = ResponseVo.ok();
        UserDTO userDto = requestVo.getParam();
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
    public ResponseVo addUser(@RequestBody RequestVo<UserDTO> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        UserDTO user = requestVo.getParam();
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
    public ResponseVo userPage(@RequestBody RequestVo<UserDTO> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        UserDTO userDto = requestVo.getParam();
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
        vo.setData(userService.getUserById(id));
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
    public ResponseVo isDisableByUserId(@RequestBody RequestVo<UserDTO> user) {
        ResponseVo vo = ResponseVo.ok();
        userService.isDisableByUserId(user.getParam());
        return vo;
    }

    @PostMapping("updateUserById")
    public ResponseVo updateUserById(@RequestBody RequestVo<UserDTO> userReq) {
        UserDTO param = userReq.getParam();
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
        return userService.getUserList(req);
    }

    @PostMapping(value = "changePassword")
    public ChangePasswordRes changePassword(@RequestBody ChangePasswordReq req) {
        String firstPassword = req.getFirstPassword();
        String secPassword = req.getSecPassword();
        if (!Objects.equals(firstPassword, secPassword)) {
            return ChangePasswordRes.failed(-608105, "两次密码输入不一致");
        }
        return userService.changePwd(req);
    }


}
