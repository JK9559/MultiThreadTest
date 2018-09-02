package com.wkang.MultiThread.ch2;

public class AccountingSync2 implements Runnable {

    static AccountingSync2 aSync2 = new AccountingSync2();
    static int i = 0;

    public synchronized void increase(){
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 100000000; j++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(aSync2);
        Thread t2 = new Thread(aSync2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}


