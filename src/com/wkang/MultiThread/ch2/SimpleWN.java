package com.wkang.MultiThread.ch2;

/* =====================2.2.4 wait&notify=====================
* ���һ���̵߳�����obj.wait()��������obj����ĵȴ����У������п��ܻ��кܶ��߳�ͬʱ
* �ȴ�һ�����󡣵�obj.notify()������ʱ������̵߳Ķ���������ѡ��һ���̻߳��ѡ�
* �������������ȡ��Ŀ�����ļ���������synchronized�����
* ���뽲�⣺t1��ȡ��������wait���ͷ�����
* t2��ȡ��������notify��������sleep 2�룬�ͷ���
* t1��ȡ��������*/

public class SimpleWN {
    final static Object obj = new Object();
    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (obj){
                System.out.println(System.currentTimeMillis() + ":T1 start !");
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait !");
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end !");
            }
        }
    }

    public static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (obj){
                System.out.println(System.currentTimeMillis() + ":T2 notify one Thread");
                obj.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();

        t1.start();
        t2.start();
    }
}
