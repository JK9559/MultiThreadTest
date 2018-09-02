package com.wkang.MultiThread.ch3;

import java.util.concurrent.locks.ReentrantLock;

/*================= java���������� =================
* lock()����:����
* unlock()����:����
* ע���:�Ӽ�����,�͵ý⼸����
* �ҿ��Զ�μ���,��Ӧ�ľ͵ö�ν���*/

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
