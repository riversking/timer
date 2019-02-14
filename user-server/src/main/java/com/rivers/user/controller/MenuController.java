package com.rivers.user.controller;

import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.entity.SysMenuModel;
import com.rivers.user.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author riverskingking
 */
@RestController
@RequestMapping("/user/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("getMenuTree")
    public ResponseVo getMenuTree() {
        ResponseVo vo = ResponseVo.ok();
        vo.setCode("0");
        vo.setMsg("查询成功");
        vo.setRsp(menuService.getMenuTree());
        return vo;
    }

    @PostMapping("getMenuById")
    public ResponseVo getMenuById(@RequestBody RequestVo<Integer> vo) {
        ResponseVo rvo = ResponseVo.ok();
        if (Objects.isNull(vo.getParam())) {
            return ResponseVo.fail("105001", "菜单id为空");
        }
        SysMenuModel sysMenuModel = menuService.selectMenuById(vo.getParam());
        rvo.setMsg("查询成功");
        rvo.setRsp(sysMenuModel);
        return rvo;
    }


}
