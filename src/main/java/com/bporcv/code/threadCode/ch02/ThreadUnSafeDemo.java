package com.bporcv.code.threadCode.ch02;

/**
 * 实例变量非线程安全
 * 此例说明：
 * 如果多个线程共同访问1个对象中的实例变量，则有可能会出现线程安全问题
 * *************************************
 * 用户[a]设置方法内部变量[num]值完成
 * 用户[b]设置方法内部变量[num]值完成
 * b num = 200
 * a num = 200
 * *************************************
 */
public class ThreadUnSafeDemo {


    public static void main(String[] args) {
        InstanceVariableThreadUnSafe num = new InstanceVariableThreadUnSafe();
        ThreadA threadA = new ThreadA(num);
        threadA.start();
        ThreadB threadB = new ThreadB(num);
        threadB.start();
    }


    public static class InstanceVariableThreadUnSafe {

        private int num = 0;

        public void addI(String username) {
            try {
                if (username.equals("a")) {
                    num = 100;
                    System.out.println("用户[a]设置方法内部变量[num]值完成");
                    Thread.sleep(2_000);
                } else {
                    num = 200;
                    System.out.println("用户[b]设置方法内部变量[num]值完成");
                }
                System.out.println(username + " num = " + num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadA extends Thread {

        private InstanceVariableThreadUnSafe numRef;

        public ThreadA(InstanceVariableThreadUnSafe numRef) {
            this.numRef = numRef;
        }

        @Override
        public void run() {
            numRef.addI("a");
        }
    }

    public static class ThreadB extends Thread {

        private InstanceVariableThreadUnSafe numRef;

        public ThreadB(InstanceVariableThreadUnSafe numRef) {
            this.numRef = numRef;
        }

        @Override
        public void run() {
            numRef.addI("b");
        }
    }
}
