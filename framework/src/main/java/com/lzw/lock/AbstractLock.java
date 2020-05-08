package com.lzw.lock;

/**
 * @Auther: Rick
 * @Date: 2020/5/8 15
 * @Description:
 */
public abstract class AbstractLock implements Lock {
    public void getLock() {

        //任务通过竞争获取锁才能对该资源进行操作(1竞争锁)；
        // 当有一个任务在对资源进行更新时（2占有锁），
        // 其他任务都不可以对这个资源进行操作（3任务阻塞），
        // 直到该任务完成更新(4释放锁)
        //尝试获得锁资源
        // 1竞争锁
        if (tryLock()) {
            System.out.println("##获取lock锁的资源####");
        } else {
            // 3任务阻塞
            waitLock();
            // 重新获取锁资源
            getLock();
        }
    }

    // 2占有锁
    public abstract boolean tryLock();

    // 等待
    public abstract void waitLock();
}
