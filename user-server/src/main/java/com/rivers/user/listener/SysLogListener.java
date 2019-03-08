package com.rivers.user.listener;

import com.rivers.core.log.entity.SysLog;
import com.rivers.core.log.event.SysLogEvent;
import com.rivers.user.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

@Slf4j
public class SysLogListener {

    @Autowired
    private LogService logService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        log.info("SysLog log {}", sysLog.getUserAgent());
        logService.saveLog(sysLog);
    }
}
