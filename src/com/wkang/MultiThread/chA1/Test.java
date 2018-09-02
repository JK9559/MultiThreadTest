package com.wkang.MultiThread.chA1;

public class Test extends Thread {
    public Test(){
        System.out.println("init curr:" + Thread.currentThread().getName());
        System.out.println("init this:" + this.getName());
    }

    @Override
    public void run() {
        System.out.println("run curr:"+Thread.currentThread().getName());
        System.out.println("run this:"+this.getName());
    }

    public static void main(String[] args) {
        Test t=new Test();
    }
}
