package com.lzw.knowledge.knowledge.controller;

import com.lzw.knowledge.knowledge.MyAnnotation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author lijie
* @date 2020/4/27 15:52
*
**/
@RestController
public class TestController {

    @MyAnnotation
    @GetMapping("hello")
    @Transactional
    public String test(){
        return "hello";
    }
}
