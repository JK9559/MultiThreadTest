package com.wkang.MultiThread.ch2;

public class AccountingSync1 implements Runnable {

    static AccountingSync1 aSync = new AccountingSync1();
    static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 100000000; j++){
            synchronized (aSync){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(aSync);
        Thread t2 = new Thread(aSync);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}


