package com.wkang.MultiThread.ch2;

/*====================2.2.5 suspend&resume ====================
* Ϊʲô�������н������ˣ�
* suspend���̹߳��𣬶������̹߳���֮�󣬲����ͷ��κ� �� ��Դ��
* ����ִ�й��̣�
* t1 t2���ٽ���ʵ�ַ��ʣ�������Ҳ���������̶߳�������ٽ������з��ʡ�
* ���Ǵ�ʱ�������̲�δ������˵�����̹߳��𡣵��ǲο�jstack��t2�����ǹ����״̬��
* ˵�� �� �����⣡����
* ��Ȼ�������Ѿ�����resume ������Ϊʱ���Ⱥ�˳���ԭ��resume��û����Ч
* */

public class BadSuspend {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized(u){
                System.out.println("in " + getName());
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
        //Thread.sleep(100); �����д����ʹ���������˳�
        t2.resume();
        t1.join();
        t2.join();
    }
}
