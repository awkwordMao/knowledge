package com.lzw.knowledge.knowledge.iptest;

public class ClassLoadTest extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("-----------I load myself---------");
        return super.loadClass(name);
    }

    public static void main(String[] args) {
        ClassLoadTest classLoadTest = new ClassLoadTest();


    }

}
