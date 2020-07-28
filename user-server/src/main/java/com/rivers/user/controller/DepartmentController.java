package com.rivers.user.controller;

import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.dto.DeptTree;
import com.rivers.user.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author riverskingking
 */
@RestController
@RequestMapping("/user/dept")
public class DepartmentController {

    @Autowired
    private DeptService deptService;

    @PostMapping("getDeptTree")
    public ResponseVo getDeptTree() {
        ResponseVo vo = ResponseVo.ok();
        List<DeptTree> deptTrees = deptService.getDeptTree();
        vo.setData(deptTrees);
        return vo;
    }

    @GetMapping("getDeptList")
    public ResponseVo getDeptList() {
        ResponseVo vo = ResponseVo.ok();
        vo.setData(deptService.getDeptList());
        return vo;
    }

}
