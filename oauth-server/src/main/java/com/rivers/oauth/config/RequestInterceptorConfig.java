package com.rivers.oauth.config;

import cn.hutool.core.collection.CollUtil;
import com.rivers.user.api.constant.SecurityConstants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * @author v-wangyichuan
 */
@Configuration
public class RequestInterceptorConfig implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {
        Collection<String> fromHeader = template.headers().get(SecurityConstants.FROM);
        if (CollUtil.isNotEmpty(fromHeader) && fromHeader.contains(SecurityConstants.FROM_IN)) {
            return;
        }

    }
}
