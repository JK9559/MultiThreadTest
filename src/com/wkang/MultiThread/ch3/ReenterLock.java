package com.wkang.MultiThread.ch3;

import java.util.concurrent.locks.ReentrantLock;

/*================= java并发重入锁 =================
* lock()方法:加锁
* unlock()方法:解锁
* 注意点:加几次锁,就得解几次锁
* 且可以多次加锁,相应的就得多次解锁*/

public class ReenterLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    public static int i = 0;
    @Override
    public void run() {
        for(int j = 0; j < 100000; j++){
            lock.lock();
            try{
                i++;
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock rLock = new ReenterLock();
        Thread t1 = new Thread(rLock);
        Thread t2 = new Thread(rLock);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
