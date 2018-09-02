package com.wkang.MultiThread.ch2;

/*
* �߳��жϲ���ʹ�߳������˳����Ǹ��߳�һ��֪ͨ������Ŀ���߳�
* ����ϣ�����˳���
* �߳��ж���������صķ���
* 1. public void Thread.interrupt()
* 2. public boolean Thread.isInterrupted()
* 3. public static boolean Thread.interrupted()
* Ҫ�㣺
* 1. ʹ���жϴ�������ʱ����������Ӧ���жϴ�����������ʹ�ն���Ч
* 2. Thread.sleep()������Ϊ�ж϶��׳��쳣����ʱ������##����жϱ��##
* �����������ô����һ�ε�ѭ����ʼʱ����û����������жϣ�����
* Ҫ���쳣�����У��ٴε������жϱ��λ*/


/*������⣺
* ����һ���̣߳���ӡ ֮��sleep������ѭ����ֱ�����ж�
* ���ȣ��߳̿��������߳�sleep 2s��t1�߳�����2s
* t1��ӡ��sleep
* t1.interrupt
* ���ܵ������
* 1. ��t1�ڲ���sleep���жϣ����׳��쳣 ��ӡ When Sleep
* �ٽ�����һ��ѭ��������жϱ�־λ�󣬴�ӡInterrupted��break�˳��̡߳�
* 2. ��t1�ڲ���sleep�жϣ����쳣�׳������ж��жϱ�־λ�󣬴�ӡInterrupted��
* �˳��̡߳�*/

public class Interrupt {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    System.out.println("Yep");
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interrupted");
                        break;
                    }

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted When Sleep");
                        Thread.currentThread().interrupt();
                    }

                    Thread.yield();
                }
            }
        };

        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }
}
