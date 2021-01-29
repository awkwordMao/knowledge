package com.lzw.knowledge.knowledge.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
* @author lijie
*  动态代理handler，使用时将被动态代理类作为object传入，
 *  通过Proxy.newProxyInstance()方法，将该handler作为参数传入，
 *  获取一个project的代理对象
**/
public class MyHandler implements InvocationHandler {

    private Object object = new Object();

    public MyHandler(Object object) {
        this.object = object;
    }
    /**
    * @params [proxy, method, args]
    * @return java.lang.Object
    * invoke方法中对object对象的方法做增强
    **/
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("添加学生前记录");
        method.invoke(object, args);
        System.out.println("添加学生后记录");
        return null;
    }
}
