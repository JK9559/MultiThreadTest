package com.wkang.MultiThread.ch3;

import java.util.concurrent.locks.ReentrantLock;

/*
* 类中声明两个重入锁，main中实现了两个线程，同时开始运行
* 线程1 先获取了锁lk1，等待500，想去获取lk2
* 线程2 先获取了锁lk2，等待500，想去获取lk1
* 形成了死锁
* 然后t2，被中断了。
* t2的lockInterruptibly()响应了中断，运行到finally释放了lk2锁，也就不会去获取lk1锁
* t1锁继续执行，获取了t2锁。直到运行结束，运行到finally释放了lk1和lk2锁
* */

public class IntLock implements Runnable {
    public static ReentrantLock lk1 = new ReentrantLock();
    public static ReentrantLock lk2 = new ReentrantLock();
    int lock;

    public IntLock(int lk){
        this.lock = lk;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lk1.lockInterruptibly();
                Thread.sleep(500); // 给缓冲时间 以形成死锁
                lk2.lockInterruptibly();
            }else{
                lk2.lockInterruptibly();
                Thread.sleep(500);
                lk1.lockInterruptibly();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(lk1.isHeldByCurrentThread())
                lk1.unlock();
            if(lk2.isHeldByCurrentThread())
                lk2.unlock();
            System.out.println(Thread.currentThread().getId() + ":线程退出");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock l1 = new IntLock(1);
        IntLock l2 = new IntLock(2);
        Thread t1 = new Thread(l1);
        Thread t2 = new Thread(l2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t2.interrupt();
    }
}
