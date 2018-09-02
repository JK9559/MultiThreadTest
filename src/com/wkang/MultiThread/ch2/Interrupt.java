package com.wkang.MultiThread.ch2;

/*
* 线程中断不会使线程立即退出，是给线程一个通知，告诉目标线程
* 有人希望你退出。
* 线程中断有三个相关的方法
* 1. public void Thread.interrupt()
* 2. public boolean Thread.isInterrupted()
* 3. public static boolean Thread.interrupted()
* 要点：
* 1. 使用中断触发函数时，必须有相应的中断处理函数，才能使终端生效
* 2. Thread.sleep()方法因为中断而抛出异常，这时，它会##清除中断标记##
* 如果不处理，那么在下一次的循环开始时，就没法捕获这个中断，所以
* 要在异常处理中，再次的设置中断标记位*/


/*代码详解：
* 定义一个线程：打印 之后sleep，不断循环，直到被中断
* 首先，线程开启，主线程sleep 2s，t1线程运行2s
* t1打印后sleep
* t1.interrupt
* 可能的情况：
* 1. 在t1内部的sleep内中断：先抛出异常 打印 When Sleep
* 再进入下一次循环，获得中断标志位后，打印Interrupted后break退出线程。
* 2. 在t1内部非sleep中断，无异常抛出，在判断中断标志位后，打印Interrupted后
* 退出线程。*/

public class Interrupt {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.println("Yep");
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interrupted");
                        break;
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted When Sleep");
                        Thread.currentThread().interrupt();
                    }

                    Thread.yield();
                }
            }
        };

        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }
}
