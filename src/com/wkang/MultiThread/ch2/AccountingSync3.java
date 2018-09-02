package com.wkang.MultiThread.ch2;

public class AccountingSync3 implements Runnable {
    static int i = 0;

    // 错误的自增函数
    public synchronized void increase(){
        i++;
    }

    // 正确的自增函数
    public static synchronized void increasebb(){
        i++;
    }

    @Override
    public void run() {
        for(int j = 0; j < 10000000; j++){
            increasebb();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AccountingSync3());
        Thread t2 = new Thread(new AccountingSync3());
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
