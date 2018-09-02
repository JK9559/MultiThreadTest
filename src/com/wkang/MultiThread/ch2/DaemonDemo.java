package com.wkang.MultiThread.ch2;

/*==================2.5 �ػ��߳�==================
* �ػ��߳����Ӧ�ľ����û��߳�
* ���û��߳�ȫ������֮��Ҳ��ζ���������ʵ����Ҳ���¿�����
* �ػ��߳��ػ��Ķ����Ѿ��������ˣ���ô����Ӧ�ó���Ҳ�������
* ��һ��JavaӦ���ڣ�ֻ���ػ��߳�ʱ��Java������ͻ��˳�
*
* �����У���Ϊt1�����ó�Ϊ�ػ��̣߳�ϵͳ��ֻ�����߳�mainΪ�û��߳�
* ������main����2����˳�ʱ����������Ҳ��֮�˳���
* �������߳�t1���ó��ػ��̣߳���main����֮��t1���᲻ͣ�Ĵ�ӡ���������*/

public class DaemonDemo {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while(true){
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new DaemonT();
        t1.setDaemon(true);
        t1.start();

        Thread.sleep(2000);
    }
}
