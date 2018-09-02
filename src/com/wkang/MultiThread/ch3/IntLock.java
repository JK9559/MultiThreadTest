package com.wkang.MultiThread.ch3;

import java.util.concurrent.locks.ReentrantLock;

/*
* ��������������������main��ʵ���������̣߳�ͬʱ��ʼ����
* �߳�1 �Ȼ�ȡ����lk1���ȴ�500����ȥ��ȡlk2
* �߳�2 �Ȼ�ȡ����lk2���ȴ�500����ȥ��ȡlk1
* �γ�������
* Ȼ��t2�����ж��ˡ�
* t2��lockInterruptibly()��Ӧ���жϣ����е�finally�ͷ���lk2����Ҳ�Ͳ���ȥ��ȡlk1��
* t1������ִ�У���ȡ��t2����ֱ�����н��������е�finally�ͷ���lk1��lk2��
* */

public class IntLock implements Runnable {
    public static ReentrantLock lk1 = new ReentrantLock();
    public static ReentrantLock lk2 = new ReentrantLock();
    int lock;

    public IntLock(int lk){
        this.lock = lk;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                lk1.lockInterruptibly();
                Thread.sleep(500); // ������ʱ�� ���γ�����
                lk2.lockInterruptibly();
            }else{
                lk2.lockInterruptibly();
                Thread.sleep(500);
                lk1.lockInterruptibly();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(lk1.isHeldByCurrentThread())
                lk1.unlock();
            if(lk2.isHeldByCurrentThread())
                lk2.unlock();
            System.out.println(Thread.currentThread().getId() + ":�߳��˳�");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock l1 = new IntLock(1);
        IntLock l2 = new IntLock(2);
        Thread t1 = new Thread(l1);
        Thread t2 = new Thread(l2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        t2.interrupt();
    }
}
