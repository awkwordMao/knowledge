package com.lzw.knowledge.knowledge;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Scope
public class AspectTest {

    @Before("@annotation(com.lzw.knowledge.knowledge.MyAnnotation)")
    public Object aVoid(){
        System.out.println("我打印了");
        return null;
    }
}
