package com.wkang.MultiThread.ch2;

public class PriorityDemo {
    public static class HighPriority extends Thread{
        static int i = 0;

        @Override
        public void run() {
            while (true){
                synchronized (PriorityDemo.class){
                    i++;
                    if(i > 10000000){
                        System.out.println("HighPriority is Complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread{
        static int i = 0;

        @Override
        public void run() {
            while (true){
                synchronized (PriorityDemo.class){
                    i++;
                    if(i > 10000000){
                        System.out.println("LowPriority is Complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread hi = new HighPriority();
        Thread lo = new LowPriority();

        hi.setPriority(Thread.MAX_PRIORITY);
        lo.setPriority(Thread.MIN_PRIORITY);

        hi.start();
        lo.start();
    }
}
