package com.rivers.log.aspect;


import com.rivers.log.annotation.SysLog;
import com.rivers.log.event.SysLogEvent;
import com.rivers.log.utils.SpringContextHolder;
import com.rivers.log.utils.SysLogUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author riversking
 */
@Aspect
public class SysLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) throws Throwable {
        String strClassName = point.getTarget().getClass().getName();
        String strMethodName = point.getSignature().getName();
        logger.debug("[类名]:{},[方法]:{}", strClassName, strMethodName);

        com.rivers.user.api.entity.SysLog logVo = SysLogUtils.getSysLog();
        logVo.setTitle(sysLog.value());
        // 发送异步日志事件
        Long startTime = System.currentTimeMillis();
        Object obj = point.proceed();
        Long endTime = System.currentTimeMillis();
        logVo.setTime(endTime - startTime);
        logger.debug("getParams:{}", logVo.getParams());
        SpringContextHolder.publishEvent(new SysLogEvent(logVo));
        return obj;
    }


}
