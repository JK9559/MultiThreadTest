package com.wkang.MultiThread.ch2.point8;

import java.util.HashMap;
import java.util.Map;

/*==================2.8.3 HashMap线程不安全=================
* 情况有3：
* 1. 符合预期，程序正常 100000
* 2. 不符合预期，小于 10000
* 3. 死循环*/

public class HashMapMultiThread {
    static Map<String,String> map = new HashMap<>();
    public static class AddThread implements Runnable{
        int start = 0;
        public AddThread(int start){
            this.start = start;
        }
        @Override
        public void run() {
            for(int i = start; i < 100000; i+=2){
                map.put(Integer.toString(i),Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddThread(0));
        Thread t2 = new Thread(new AddThread(1));
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(map.size());
    }
}
