package com.rivers.user.controller;

import com.rivers.core.log.entity.SysLog;
import com.rivers.core.view.ResponseVo;
import com.rivers.user.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author v-wangyichuan
 */
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("log")
    public ResponseVo saveLog(@RequestBody SysLog sysLog) {
        ResponseVo vo = ResponseVo.ok();
        logService.saveLog(sysLog);
        return vo;
    }
}
