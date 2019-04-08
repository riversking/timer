package com.rivers.user.controller;

import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.dto.DeptTree;
import com.rivers.user.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
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
        vo.setCode("0");
        vo.setMessage("查询成功");
        vo.setDatas(deptTrees);
        return vo;
    }

}
