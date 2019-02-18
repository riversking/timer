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
        vo.setDatas(menuService.getMenuTree());
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
        rvo.setDatas(sysMenuModel);
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

    @PostMapping("updateMenu")
    public  ResponseVo updateMenu(@RequestBody RequestVo<MenuDto> requestVo) {
        ResponseVo vo = ResponseVo.ok();
        MenuDto menuDto = requestVo.getParam();
        menuService.updateMenuById(menuDto);
        vo.setCode("0");
        vo.setMessage("更新成功");
        return vo;
    }

}
