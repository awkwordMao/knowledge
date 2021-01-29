package com.lzw.knowledge.knowledge.proxytest;

import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {
        Student student = new ManStudent();
        MyHandler handler = new MyHandler(student);
        Student proxy = (Student) Proxy.newProxyInstance(
                handler.getClass().getClassLoader(), student.getClass().getInterfaces(), handler);
        proxy.insertStudent();
        proxy.deleteStudent();
    }
}
