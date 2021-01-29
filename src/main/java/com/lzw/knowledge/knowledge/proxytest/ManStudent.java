package com.lzw.knowledge.knowledge.proxytest;

/**
* @author lijie
*  实现被动态代理接口的类
**/
public class ManStudent implements Student {
    @Override
    public void insertStudent() {
        System.out.println("添加男同学");
    }

    @Override
    public void deleteStudent() {
        System.out.println("删除男同学");
    }
}
