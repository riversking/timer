package com.rivers.user.controller;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.dto.MenuDto;
import com.rivers.user.api.dto.MenuRoleDto;
import com.rivers.user.api.dto.MenuTree;
import com.rivers.user.api.dto.UserDto;
import com.rivers.user.api.entity.SysMenuModel;
import com.rivers.user.api.entity.SysRoleMenuModel;
import com.rivers.user.service.MenuService;
import com.rivers.userservice.proto.GetMenuByUIdReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author riverskingking
 */
@RestController
@RequestMapping("/user/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取menu树
     *
     * @return ResponseVo
     */
    @PostMapping("getMenuTree")
    public ResponseVo getMenuTree() {
        ResponseVo vo = ResponseVo.ok();
        vo.setCode("0");
        vo.setMessage("查询成功");
        vo.setData(menuService.getMenuTree());
        return vo;
    }

    /**
     * 通过id获取menu详情
     *
     * @return ResponseVo
     */
    @PostMapping("getMenuById")
    public ResponseVo getMenuById(@RequestBody RequestVo<Integer> vo) {
        ResponseVo rvo = ResponseVo.ok();
        if (Objects.isNull(vo.getParam())) {
            return ResponseVo.fail("105001", "菜单id为空");
        }
        SysMenuModel sysMenuModel = menuService.selectMenuById(vo.getParam());
        rvo.setMessage("查询成功");
        rvo.setData(sysMenuModel);
        return rvo;
    }

    /**
     * 添加menu & button
     *
     * @return ResponseVo
     */
    @PostMapping("addMenu")
    public ResponseVo addMenu(@RequestBody RequestVo<MenuDto> requestVo) {
        ResponseVo rvo = ResponseVo.ok();
        MenuDto menuDto = requestVo.getParam();
        if (StrUtil.isBlank(menuDto.getName())) {
            return ResponseVo.fail("103002", "菜单名称为空");
        }
        menuService.addMenu(menuDto);
        rvo.setMessage("添加成功");
        return rvo;
    }

    /**
     * 通过id删除 menu & button
     *
     * @return ResponseVo
     */
    @PostMapping("deleteMenu")
    public ResponseVo deleteMenu(@RequestBody RequestVo<Integer> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        Integer id = requestVo.getParam();
        if (id == 0) {
            return ResponseVo.fail("103001", "id为空");
        }
        menuService.deleteMenuById(id);
        vo.setMessage("删除成功");
        return vo;
    }

    /**
     * 通过id更新 menu & button
     *
     * @param requestVo requestVo
     * @return ResponseVo
     */
    @PostMapping("updateMenu")
    public ResponseVo updateMenu(@RequestBody RequestVo<MenuDto> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        MenuDto menuDto = requestVo.getParam();
        menuService.updateMenuById(menuDto);
        vo.setCode("0");
        vo.setMessage("更新成功");
        return vo;
    }

    @PostMapping("getMenuByRoleId")
    public ResponseVo getMenuByRoleId(@RequestBody RequestVo<Integer> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        Integer roleId = requestVo.getParam();
        if (roleId == 0) {
            return ResponseVo.fail("103002", "roleId为空");
        }
        vo.setCode("0");
        vo.setData(menuService.getMenuByRoleId(roleId));
        return vo;
    }

    @PostMapping("updateByRoleId")
    public ResponseVo updateByRoleId(@RequestBody RequestVo<MenuRoleDto> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        MenuRoleDto menuRoleDto = requestVo.getParam();
        menuService.updateMenuByRoleId(menuRoleDto);
        vo.setCode("0");
        vo.setMessage("更新成功");
        return vo;
    }

    @PostMapping("getMenuByUserId")
    public ResponseVo getMenuByUserId(@RequestBody GetMenuByUIdReq user) {
        ResponseVo vo = ResponseVo.ok();
        String userId = user.getUser().getUserId();
        if (StrUtil.isBlank(userId)) {
            return ResponseVo.fail("103005", "userId为空");
        }
        vo.setCode("0");
        vo.setMessage("查询成功");
        vo.setData(menuService.getMenuByUserId(userId));
        return vo;
    }

    @PostMapping("getMenu")
    public ResponseVo getMenu(@RequestBody RequestVo<Integer> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        vo.setCode("0");
        vo.setMessage("查询成功");
        vo.setData(menuService.getMenu(requestVo.getParam()));
        return vo;
    }

    @PostMapping("getParentMenu")
    public ResponseVo getParentMenu() {
        List<MenuTree> parentMenu = menuService.getParentMenu();
        return ResponseVo.ok(parentMenu);
    }
}
