package com.lzw.framework.config.log;

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
public @interface Logging {
    // 参数描述
    String paramMemo();
    // 参数名
    String paramName();
    // 字段名
    String fieldName();
}
