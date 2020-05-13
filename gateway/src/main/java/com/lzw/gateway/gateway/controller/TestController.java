package com.lzw.gateway.gateway.controller;

import com.lzw.framework.common.ResultInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
* @author lijie
* @date 2020/5/13 16:35
*
**/
@RestController
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiOperation("test")
    public ResultInfo<String> testSwagger(){
        return new ResultInfo<>(200, "成功", "success");
    }
}
