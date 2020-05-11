package com.lzw.framework.annotation;

import com.lzw.framework.config.cloud.OAuth2FeignAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: Rick
 * @Date: 2020/5/11 15
 * @Description:
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OAuth2FeignAutoConfiguration.class)
public @interface EnableOauth2FeignAccess {
}
