package com.rivers.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rivers.core.log.entity.SysLog;
import com.rivers.user.mapper.SysLogDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author riversking
 */
@Service
public class LogService extends ServiceImpl<SysLogDao, SysLog> {

    @Resource
    private SysLogDao sysLogDao;

    public void saveLog(SysLog sysLog) {
        sysLogDao.insert(sysLog);
    }
}
