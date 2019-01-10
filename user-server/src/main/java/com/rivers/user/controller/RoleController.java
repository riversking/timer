package com.rivers.user.controller;

import cn.hutool.core.util.StrUtil;
import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.entity.SysRoleModel;
import com.rivers.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author riversking
 */
@RestController
@RequestMapping("/user/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("addRole")
    public ResponseVo addRole(@RequestBody RequestVo<SysRoleModel> requestVo) {
        SysRoleModel sysRoleModel = requestVo.getParam();
        ResponseVo responseVo = ResponseVo.ok();
        if (StrUtil.isBlank(sysRoleModel.getRoleName())) {
            return ResponseVo.fail("102001","角色名称为空");
        }
        roleService.addRole(sysRoleModel);
        responseVo.setMsg("成功");
        return responseVo;
    }
}
