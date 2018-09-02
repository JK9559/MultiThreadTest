package com.wkang.MultiThread.ch2;

/*======================2.2.6 join()&yield()======================
* join()��ʾ���޵ȴ�������һֱ������ǰ�̣߳�ֱ��Ŀ���߳�ִ����ϡ�
* yield()��ʹ��ǰ�߳��ó�CPU�����Ǹ��߳�Ҳ��������뾺����ֻ���Ƿ���Ա����䵽�Ͳ�һ����*/

public class JoinMain {
    public volatile static int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for(i = 0; i < 100000000; i++);
        }

        public void joinIt(){
            synchronized (this) {
                while (isAlive()) {
                    try {
                        wait(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread t1 = new AddThread();
        t1.start();
        t1.joinIt();
        //t1.join();
        System.out.println(i);
    }
}
