package com.rivers.user.controller;

import cn.hutool.core.util.StrUtil;
import com.rivers.core.view.RequestVo;
import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.dto.MenuDto;
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
        vo.setMessage("查询成功");
        vo.setDatas(menuService.getMenuTree());
        return vo;
    }

    @PostMapping("getMenuById")
    public ResponseVo getMenuById(@RequestBody RequestVo<Integer> vo) {
        ResponseVo rvo = ResponseVo.ok();
        if (Objects.isNull(vo.getParam())) {
            return ResponseVo.fail("105001", "菜单id为空");
        }
        SysMenuModel sysMenuModel = menuService.selectMenuById(vo.getParam());
        rvo.setMessage("查询成功");
        rvo.setDatas(sysMenuModel);
        return rvo;
    }

    @PostMapping("addMenu")
    public ResponseVo addMenu(@RequestBody RequestVo<MenuDto> requestVo) {
        ResponseVo rvo = ResponseVo.ok();
        MenuDto menuDto = requestVo.getParam();
        if (menuDto.getId() == 0) {
            return ResponseVo.fail("103001", "id为空");
        }
        if (StrUtil.isBlank(menuDto.getName())) {
            return ResponseVo.fail("103002", "菜单名称为空");
        }
        menuService.addMenu(menuDto);
        rvo.setMessage("添加成功");
        return rvo;
    }


}
