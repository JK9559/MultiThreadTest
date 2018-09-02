package com.wkang.MultiThread.ch2;

/*
* ===================2.2.2 ��ֹ�߳�===================
* ================��ʹ��stop����ֹ�߳�=================
* ����Thread ����һ��Ϊд���࣬һ��Ϊ��ȡ��
* д���� ��д��ͬһ�������������ʱ �����0.1�룬����һֱ��ΪΨһһ��u������ֵ
* ��ȡ�� һֱ�ڲ���ϵ�ɨ��User��ı���
* main��������ȡ�಻��϶�ȡ
* д����ÿ�ζ����µĶ������µĶ�����������0.15��
* ֮��stop()����ʱ��дu�����������ͷţ��������������
* 1. ��д���̸߳ոռ���ʱ��stop�ˣ����ʱ������������id��name��δ����ֵ����ʱid��name����ƥ���
* 2. ��д���߳�д��id������ʱ��stop�ˣ���ʱ����������id������ֵ(��)stop��name������ֵ(С)����ʱ���߳����
* 3. ��д���߳�д����id��nameʱ��stop�ˣ���ʱ����������id��name������ȵ�
* ���ϣ������ڵ��쳣 ֻ��id>name*/

public class StopThreadUnsafe {
    public static User u = new User();
    public static class User{
        private int id;
        private String name;
        public User(){
            id = 0;
            name = "0";
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "User [id="+id+", name="+name+"]";
        }
    }
    public static class WriteObjectThread extends Thread{
        volatile boolean stopme = false;
        public void stopMe(){
            stopme = true;
        }
        @Override
        public void run() {
            while (true){
                if(stopme){
                    System.out.println("Thread exit");
                    break;
                }
                synchronized (u){
                    int v = (int)(System.currentTimeMillis()/1000);
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    if(u.getId() != Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String args[]){
        new ReadObjectThread().start();
        while (true){
            Thread t = new WriteObjectThread();
            t.start();
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //t.stop();
            ((WriteObjectThread) t).stopMe();
        }
    }
}
