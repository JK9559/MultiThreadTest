package com.wkang.MultiThread.ch2;

public class VolatileTest {
    static volatile int s = 0;
    public static Object obj = new Object();
    public static class PlusTask implements Runnable{
        @Override
        public void run() {
            synchronized (obj) {
                for(int k = 0; k < 10000; k++){
                    s++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        for(int i = 0; i < 10; i++){
            threads[i] = new Thread(new PlusTask());
            threads[i].start();
        }
        for(int i = 0; i < 10; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(s);
    }
}
