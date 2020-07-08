package com.rivers.file.controller;

import cn.hutool.core.date.DateUtil;
import com.rivers.file.client.UserClientFeign;
import com.rivers.file.service.ExportService;
import com.rivers.userservice.proto.GetUserListReq;
import com.rivers.userservice.proto.GetUserListRes;
import com.rivers.userservice.proto.Page;
import com.rivers.userservice.proto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/file")
public class ExportController {

    @Autowired
    private UserClientFeign userClientFeign;

    @Autowired
    private ExportService exportService;

    @GetMapping(value = "/exportUser")
    public ResponseEntity<InputStreamResource> exportUser() {
        GetUserListRes getUserListRes = userClientFeign.userPage(GetUserListReq.newBuilder()
                .setPage(Page.newBuilder()
                        .setPageNum(1)
                        .setPageSize(10)
                        .build())
                .build());
        List<User> usersList = getUserListRes.getUsersList();
        ByteArrayInputStream byteArrayInputStream = exportService.exportToUserExcel(usersList);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename="
                + DateUtil.date().toDateStr() + ".xlsx");
        headers.add("Content-Type", "application/x-download");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(byteArrayInputStream));
    }



}
