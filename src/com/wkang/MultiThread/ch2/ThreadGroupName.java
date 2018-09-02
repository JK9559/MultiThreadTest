package com.wkang.MultiThread.ch2;

/*=====================2.4 线程组 =========================
* activeCount() 获得活动线程的个数，但因为线程是动态的，因此这个值只是一个估计值
* list() 方法可以打印线程组中的全部线程信息，对调试有一定帮助
* */

public class ThreadGroupName implements Runnable {

    @Override
    public void run() {
        String groupAndName = Thread.currentThread().getThreadGroup().getName() +
                "-" + Thread.currentThread().getName();
        while (true){
            System.out.println("I am " + groupAndName);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(tg,new ThreadGroupName(),"T1");
        Thread t2 = new Thread(tg,new ThreadGroupName(),"T2");

        t1.start();
        t2.start();
        System.out.println(tg.activeCount());
        tg.list();
    }
}
