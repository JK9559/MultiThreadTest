package com.wkang.MultiThread.ch2;

/*======================2.2.6 join()&yield()======================
* join()表示无限等待，他会一直阻塞当前线程，直到目标线程执行完毕。
* yield()会使当前线程让出CPU，但是该线程也会继续参与竞争，只是是否可以被分配到就不一定了*/

public class JoinMain {
    public volatile static int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i = 0; i < 100000000; i++);
        }

        public void joinIt(){
            synchronized (this) {
                while (isAlive()) {
                    try {
                        wait(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread t1 = new AddThread();
        t1.start();
        t1.joinIt();
        //t1.join();
        System.out.println(i);
    }
}
