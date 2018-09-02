package com.wkang.MultiThread.ch2;

/*
* 首先给出一个标记变量，用来表示当前的线程是否被挂起
* suspendMe()和resumeMe()分别用于挂起线程和继续执行线程
* 在Change类里，会不断检查标记，来判断自己是否被挂起，并且不断执行任务(打印)
* main函数：
* t1线程启动，不断检查标记并执行任务
* 挂起，标记置为真 t1，wait。main线程sleep 2sec
* 唤醒，标记置为假 并唤起waiting中的t1，t1继续检查标记，并执行任务*/

public class GoodSuspend {
    public static Object u = new Object();

    public static class ChangeObjectThread extends Thread{
        volatile boolean suspendme = false;
        public void suspendMe(){
            suspendme = true;
        }

        public void resumeMe(){
            suspendme = false;
            synchronized (this){
                notify();
            }
        }

        @Override
        public void run() {
            while (true){
                synchronized (this){
                    while (suspendme){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                synchronized (u){
                    System.out.println("in ChangeObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    System.out.println("in ReadObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread t1 = new ChangeObjectThread();
        ReadObjectThread t2 = new ReadObjectThread();
        t1.start();
        //t2.start();
        Thread.sleep(10);
        t1.suspendMe();
        System.out.println("suspend t1 2sec");
        Thread.sleep(2000);
        System.out.println("resume t1");
        t1.resumeMe();
    }
}
