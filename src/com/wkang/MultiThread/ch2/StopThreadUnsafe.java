package com.wkang.MultiThread.ch2;

/*
* ===================2.2.2 终止线程===================
* ================不使用stop来终止线程=================
* 两个Thread 其中一个为写入类，一个为读取类
* 写入类 在写入同一个类的两个变量时 间隔了0.1秒，并且一直在为唯一一个u变量赋值
* 读取类 一直在不间断的扫描User类的变量
* main函数，读取类不间断读取
* 写入类每次定义新的对象，让新的对象正常运行0.15秒
* 之后stop()，这时，写u变量的锁被释放，存在以下情况：
* 1. 在写入线程刚刚加锁时，stop了，则此时读锁加锁，而id和name均未赋新值，这时id和name还是匹配的
* 2. 在写入线程写完id变量的时候，stop了，此时读锁加锁，id赋了新值(大)stop后name还是老值(小)，这时读线程输出
* 3. 在写入线程写完了id和name时，stop了，此时读锁加锁，id和name还是相等的
* 综上，所存在的异常 只有id>name*/

public class StopThreadUnsafe {
    public static User u = new User();
    public static class User{
        private int id;
        private String name;
        public User(){
            id = 0;
            name = "0";
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "User [id="+id+", name="+name+"]";
        }
    }
    public static class WriteObjectThread extends Thread{
        volatile boolean stopme = false;
        public void stopMe(){
            stopme = true;
        }
        @Override
        public void run() {
            while (true){
                if(stopme){
                    System.out.println("Thread exit");
                    break;
                }
                synchronized (u){
                    int v = (int)(System.currentTimeMillis()/1000);
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    if(u.getId() != Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String args[]){
        new ReadObjectThread().start();
        while (true){
            Thread t = new WriteObjectThread();
            t.start();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //t.stop();
            ((WriteObjectThread) t).stopMe();
        }
    }
}
