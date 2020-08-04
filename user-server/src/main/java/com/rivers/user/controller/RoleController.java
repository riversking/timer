package com.rivers.user.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rivers.core.bean.LoginUser;
import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.dto.RoleDO;
import com.rivers.user.api.dto.RoleDTO;
import com.rivers.user.api.entity.SysRoleModel;
import com.rivers.user.service.RoleService;
import com.rivers.userservice.proto.UpdateRoleByIdReq;
import com.rivers.userservice.proto.UpdateRoleByIdRes;
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
@RequestMapping(value = "/user/role", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色
     *
     * @param role role
     * @return ResponseVo
     */
    @PostMapping("addRole")
    public ResponseVo addRole(@RequestBody RoleDO role) {
        ResponseVo responseVo = ResponseVo.ok();
        LoginUser user = role.getUser();
        if (Objects.isNull(user)) {
            return ResponseVo.fail("100001", "用户不存在");
        }
        if (StrUtil.isBlank(role.getRoleName())) {
            return ResponseVo.fail("102001", "角色名称为空");
        }
        roleService.addRole(role);
        responseVo.setMessage("添加成功");
        return responseVo;
    }

    /**
     * 分页查询角色
     *
     * @param requestVo requestVo
     * @return ResponseVo
     */
    @PostMapping("rolePage")
    public ResponseVo rolePage(@RequestBody RequestVo<RoleDTO> requestVo) {
        RoleDTO roleDto = requestVo.getParam();
        ResponseVo responseVo = ResponseVo.ok();
        IPage<SysRoleModel> pageInfo = roleService.getRolePage(roleDto);
        responseVo.setData(pageInfo);
        return responseVo;
    }

    /**
     * 角色详情
     *
     * @param requestVo requestVo
     * @return ResponseVo
     */
    @PostMapping("roleDetail")
    public ResponseVo roleDetail(@RequestBody RequestVo<Integer> requestVo) {
        Integer id = requestVo.getParam();
        ResponseVo responseVo = ResponseVo.ok();
        SysRoleModel sysRoleModel = roleService.getRoleDetailById(id);
        responseVo.setData(sysRoleModel);
        return responseVo;
    }

    /**
     * 删除角色
     *
     * @param requestVo requestVo
     * @return ResponseVo
     */
    @PostMapping("deleteRole")
    public ResponseVo deleteRole(@RequestBody RequestVo<Integer> requestVo) {
        Integer id = requestVo.getParam();
        ResponseVo responseVo = ResponseVo.ok();
        roleService.deleteRoleById(id);
        return responseVo;
    }

    /**
     * 角色列表
     *
     * @return ResponseVo
     */
    @PostMapping("roleList")
    public ResponseVo roleList() {
        ResponseVo responseVo = ResponseVo.ok();
        List<SysRoleModel> list = roleService.getRoleList();
        responseVo.setCode("0");
        responseVo.setMessage("查询成功");
        responseVo.setData(list);
        return responseVo;
    }

    @PostMapping("updateRoleById")
    public UpdateRoleByIdRes updateRoleById(@RequestBody UpdateRoleByIdReq req) {
        com.rivers.userservice.proto.LoginUser user = req.getUser();
        if (Objects.isNull(user.getUserId())) {
            return UpdateRoleByIdRes.failed(-10201, "用户id为空");
        }
        roleService.updateRoleById(req);
        return UpdateRoleByIdRes.ok();
    }


    @PostMapping("addRoleByUserId")
    public ResponseVo addRoleByUserId(@RequestBody RequestVo<RoleDTO> req) {
        RoleDTO roleVo = req.getParam();
        if (roleVo.getRoleIds().isEmpty()) {
            return ResponseVo.fail("-100001", "角色id为空");
        }
        if (StrUtil.isBlank(roleVo.getUserId())) {
            return ResponseVo.fail("-100002", "用户id为空");
        }
        roleService.addRoleByUserId(roleVo);
        return ResponseVo.ok();
    }

    @PostMapping("getRoleByUserId")
    public ResponseVo getRoleByUserId(@RequestBody RequestVo<String> req) {
        String userId = req.getParam();
        List<SysRoleModel> list = roleService.getRoleByUserId(userId);
        return ResponseVo.ok(list);
    }


}
