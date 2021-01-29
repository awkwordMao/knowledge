package com.lzw.knowledge.knowledge.synchronizedtest;

public class ClassLock {

    public static void main(String[] args) {
        Thread th1 = new Thread(()-> {
            System.out.println(Thread.currentThread().getName());
            ClassLock.test1();
        });
        th1.start();
        Thread th2 = new Thread(()-> {
            System.out.println(Thread.currentThread().getName());
            ClassLock.test2();
        });
        th2.start();

    }

    public static synchronized void test1(){
        try {
            System.out.println("test1 等待10秒");
            Thread.sleep(10000);
            System.out.println("test1 执行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void test2(){
        try {
            System.out.println("test2 等待10秒");
            Thread.sleep(10000);
            System.out.println("test2 执行结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
