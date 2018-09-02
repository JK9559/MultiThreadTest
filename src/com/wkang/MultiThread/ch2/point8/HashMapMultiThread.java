package com.wkang.MultiThread.ch2.point8;

import java.util.HashMap;
import java.util.Map;

/*==================2.8.3 HashMap�̲߳���ȫ=================
* �����3��
* 1. ����Ԥ�ڣ��������� 100000
* 2. ������Ԥ�ڣ�С�� 10000
* 3. ��ѭ��*/

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
