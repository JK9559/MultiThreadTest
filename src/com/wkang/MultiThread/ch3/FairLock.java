package com.wkang.MultiThread.ch3;

import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable {
    public static ReentrantLock lock = new ReentrantLock(false);

    @Override
    public void run() {
        while (true){
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " »ñÈ¡Ëø");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock f1 = new FairLock();
        Thread t1 = new Thread(f1,"Thread_n1");
        Thread t2 = new Thread(f1,"Thread_n2");
        t1.start();
        t2.start();
    }
}
