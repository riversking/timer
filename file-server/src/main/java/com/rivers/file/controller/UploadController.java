package com.rivers.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.rivers.core.view.ResponseVo;
import com.rivers.file.client.UserClientFeign;
import com.rivers.file.service.UploadService;
import com.rivers.userservice.proto.GetUserListReq;
import com.rivers.userservice.proto.GetUserListRes;
import com.rivers.userservice.proto.Page;
import com.rivers.userservice.proto.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author riversking
 */
@RestController
@RequestMapping("file")
@Log4j2
public class UploadController {

    @Autowired
    private UploadService uploadService;


    @PostMapping("/upload")
    @ResponseBody
    public ResponseVo upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        return ResponseVo.ok(uploadService.uploadFile(file));
    }

}
