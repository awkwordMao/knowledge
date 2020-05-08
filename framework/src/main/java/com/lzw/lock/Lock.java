package com.lzw.lock;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 15
 * @Description:
 */
public interface Lock {
    //获取到锁的资源
    public void getLock();
    // 释放锁
    public void unLock();
}
