package com.lzw.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* @author lijie
* @date 2020/5/13 16:35
*
**/
@Api("测试")
@RestController
@RequestMapping("/api")
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation("test")
    public String testSwagger(){
        return "OK";
    }
}
