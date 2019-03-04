package com.rivers.file.component;

import cn.hutool.core.util.StrUtil;
import com.rivers.file.annotation.Inner;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

/**
 * @author lengleng
 * @date 2019/02/14
 * <p>
 * 服务间接口不鉴权处理逻辑
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class PigSecurityInnerAspect implements Ordered {
	private final HttpServletRequest request;

	@SneakyThrows
	@Around("@annotation(inner)")
	public Object around(ProceedingJoinPoint point, Inner inner) {
		String header = request.getHeader("From");
		log.info("访问接口 header {}", header);
		if (inner.value() && !StrUtil.equals("Y", header)) {
			log.warn("访问接口 {} 没有权限", point.getSignature().getName());
			throw new AccessDeniedException("Access is denied");
		}
		log.info("访问接口 {}", point.getSignature().getName());
		return point.proceed();
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE + 1;
	}
}
