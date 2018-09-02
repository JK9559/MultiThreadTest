package com.wkang.MultiThread.ch2;

/*==================2.5 守护线程==================
* 守护线程相对应的就是用户线程
* 当用户线程全部结束之后，也意味着这个程序实际上也无事可做，
* 守护线程守护的对象已经不存在了，那么整个应用程序也会结束。
* 当一个Java应用内，只有守护线程时，Java虚拟机就会退出
*
* 本例中，因为t1被设置成为守护线程，系统中只有主线程main为用户线程
* 所以在main休眠2秒后退出时，整个程序也随之退出。
* 若不把线程t1设置成守护线程，在main结束之后，t1还会不停的打印，不会结束*/

public class DaemonDemo {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new DaemonT();
        t1.setDaemon(true);
        t1.start();

        Thread.sleep(2000);
    }
}
