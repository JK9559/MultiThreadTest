package com.wkang.MultiThread.ch2;

/*====================2.2.5 suspend&resume ====================
* 为什么程序运行结果会如此？
* suspend：线程挂起，而且在线程挂起之后，不会释放任何 锁 资源。
* 代码执行过程：
* t1 t2在临界区实现访问，程序结果也表明两个线程都进入的临界区进行访问。
* 但是此时，主进程并未结束。说明有线程挂起。但是参考jstack，t2并不是挂起的状态。
* 说明 ： 有问题！！！
* 虽然主函数已经掉了resume 但是因为时间先后顺序的原因resume并没有生效
* */

public class BadSuspend {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized(u){
                System.out.println("in " + getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
        //Thread.sleep(100); 加这行代码可使程序正常退出
        t2.resume();
        t1.join();
        t2.join();
    }
}
