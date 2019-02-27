
package com.rivers.log;


import com.rivers.log.aspect.SysLogAspect;
import com.rivers.log.event.SysLogListener;
import com.rivers.user.api.feign.RemoteLogService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author lengleng
 * 日志自动配置
 */
@EnableAsync
@Configuration
@AllArgsConstructor
@EnableFeignClients
@ConditionalOnWebApplication
public class LogAutoConfiguration {


	private final RemoteLogService remoteLogService;

	@Bean
	public SysLogListener sysLogListener() {
		return new SysLogListener(remoteLogService);
	}

	@Bean
	public SysLogAspect sysLogAspect() {
		return new SysLogAspect();
	}
}
