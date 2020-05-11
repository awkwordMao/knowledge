package com.lzw.framework.annotation;

import com.lzw.framework.config.ThymeleafConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: Rick
 * @Date: 2020/5/11 15
 * @Description: 模板引擎
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ThymeleafConfiguration.class)
public @interface EnableThymeleafConfiguration {

    String value() default "";

}