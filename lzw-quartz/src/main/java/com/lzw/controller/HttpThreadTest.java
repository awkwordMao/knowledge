package com.lzw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpThreadTest {

    @GetMapping("http/test1")
    public String test1(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int j = 0;
        for (int i = 0; i < 100000000; i++) {
            j++;
        }
        return "test1";
    }
}
