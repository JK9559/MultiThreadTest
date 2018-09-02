package com.wkang.MultiThread.ch2;

/*
* ���ȸ���һ����Ǳ�����������ʾ��ǰ���߳��Ƿ񱻹���
* suspendMe()��resumeMe()�ֱ����ڹ����̺߳ͼ���ִ���߳�
* ��Change����᲻�ϼ���ǣ����ж��Լ��Ƿ񱻹��𣬲��Ҳ���ִ������(��ӡ)
* main������
* t1�߳����������ϼ���ǲ�ִ������
* ���𣬱����Ϊ�� t1��wait��main�߳�sleep 2sec
* ���ѣ������Ϊ�� ������waiting�е�t1��t1��������ǣ���ִ������*/

public class GoodSuspend {
    public static Object u = new Object();

    public static class ChangeObjectThread extends Thread{
        volatile boolean suspendme = false;
        public void suspendMe(){
            suspendme = true;
        }

        public void resumeMe(){
            suspendme = false;
            synchronized (this){
                notify();
            }
        }

        @Override
        public void run() {
            while (true){
                synchronized (this){
                    while (suspendme){
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                synchronized (u){
                    System.out.println("in ChangeObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    System.out.println("in ReadObjectThread");
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ChangeObjectThread t1 = new ChangeObjectThread();
        ReadObjectThread t2 = new ReadObjectThread();
        t1.start();
        //t2.start();
        Thread.sleep(10);
        t1.suspendMe();
        System.out.println("suspend t1 2sec");
        Thread.sleep(2000);
        System.out.println("resume t1");
        t1.resumeMe();
    }
}
