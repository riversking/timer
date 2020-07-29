package com.rivers.user.controller;

import com.rivers.core.view.ResponseVo;
import com.rivers.user.api.dto.DeptTree;
import com.rivers.user.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static void main(String[] args) {
        String string = "73.25%";
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        System.out.println(hex);
    }

}
