package com.rivers.file.controller;

import com.alibaba.fastjson.JSONObject;
import com.rivers.core.view.ResponseVo;
import com.rivers.file.client.UserClientFeign;
import com.rivers.file.service.UploadService;
import com.rivers.userservice.proto.GetUserListReq;
import com.rivers.userservice.proto.GetUserListRes;
import com.rivers.userservice.proto.Page;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author riversking
 */
@RestController
@RequestMapping("file")
@Log4j2
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private UserClientFeign userClientFeign;


    @PostMapping("/upload")
    @ResponseBody
    public ResponseVo upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        return ResponseVo.ok(uploadService.uploadFile(file));
    }

    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetUserListRes test() {
        GetUserListRes jsonObject = userClientFeign.userPage(GetUserListReq.newBuilder()
                .setPage(Page.newBuilder()
                        .setPageNum(1)
                        .setPageSize(10)
                        .build())
                .build());
        return jsonObject;
    }


}
