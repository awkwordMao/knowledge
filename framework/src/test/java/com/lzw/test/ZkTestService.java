package com.lzw.test;

import com.lzw.lock.Lock;
import com.lzw.lock.ZookeeperLockEphemeral;
import com.lzw.lock.ZookeeperLockEphemeralSequential;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 16
 * @Description:
 */
public class ZkTestService implements Runnable {
    private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();

//    private Lock lock = new ZookeeperLockEphemeral();

    private Lock lock = new ZookeeperLockEphemeralSequential();

    public void run() {
        getNumber();
    }
    public void getNumber() {
        try {
            lock.getLock();
            String number = orderNumGenerator.getNumber();
            System.out.println(Thread.currentThread().getName() + ",生成订单ID:" + number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unLock();
        }
    }
    public static void main(String[] args) {
        System.out.println("####生成唯一订单号###");
        for (int i = 0; i < 50; i++) {
            new Thread( new ZkTestService()).start();
        }
    }
}
