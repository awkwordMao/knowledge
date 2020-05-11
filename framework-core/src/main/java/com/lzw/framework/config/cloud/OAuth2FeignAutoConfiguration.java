package com.lzw.framework.config.cloud;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @Auther: Rick
 * @Date: 2020/5/11 15
 * @Description:
 */
public class OAuth2FeignAutoConfiguration {
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor();
    }
}
