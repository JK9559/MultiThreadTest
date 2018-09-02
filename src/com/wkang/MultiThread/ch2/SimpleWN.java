package com.wkang.MultiThread.ch2;

/* =====================2.2.4 wait&notify=====================
* 如果一个线程调用了obj.wait()，则会进入obj对象的等待队列，队列中可能会有很多线程同时
* 等待一个对象。当obj.notify()被调用时，会从线程的队列里，随机的选择一个线程唤醒。
* 两个方法必须获取到目标对象的监视器，在synchronized语句中
* 代码讲解：t1获取锁，发起wait，释放锁。
* t2获取锁，发起notify，结束，sleep 2秒，释放锁
* t1获取锁，结束*/

public class SimpleWN {
    final static Object obj = new Object();
    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (obj){
                System.out.println(System.currentTimeMillis() + ":T1 start !");
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait !");
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end !");
            }
        }
    }

    public static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (obj){
                System.out.println(System.currentTimeMillis() + ":T2 notify one Thread");
                obj.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();

        t1.start();
        t2.start();
    }
}
