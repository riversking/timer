package com.rivers.user.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.PageInfo;
import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.dto.RoleDto;
import com.rivers.user.api.entity.SysRoleModel;
import com.rivers.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            return ResponseVo.fail("102001", "角色名称为空");
        }
        roleService.addRole(sysRoleModel);
        responseVo.setMsg("成功");
        return responseVo;
    }

    @PostMapping("rolePage")
    public ResponseVo rolePage(@RequestBody RequestVo<RoleDto> requestVo) {
        RoleDto roleDto = requestVo.getParam();
        ResponseVo responseVo = ResponseVo.ok();
        IPage<SysRoleModel> pageInfo = roleService.getRolePage(roleDto);
        responseVo.setRsp(pageInfo);
        return responseVo;
    }

    @PostMapping("roleDetail")
    public ResponseVo roleDetail(@RequestBody RequestVo<Integer> requestVo) {
        Integer id = requestVo.getParam();
        ResponseVo responseVo = ResponseVo.ok();
        SysRoleModel sysRoleModel = roleService.getRoleDetailById(id);
        responseVo.setRsp(sysRoleModel);
        return responseVo;
    }


    @PostMapping("deleteRole")
    public ResponseVo deleteRole(@RequestBody RequestVo<Integer> requestVo) {
        Integer id = requestVo.getParam();
        ResponseVo responseVo = ResponseVo.ok();
        roleService.deleteRoleById(id);
        return responseVo;
    }

    @PostMapping("roleList")
    public ResponseVo roleList() {
        ResponseVo responseVo = ResponseVo.ok();
        List<SysRoleModel> list = roleService.getRoleList();
        responseVo.setCode("0");
        responseVo.setMsg("查询成功");
        responseVo.setRsp(list);
        return responseVo;
    }


}
