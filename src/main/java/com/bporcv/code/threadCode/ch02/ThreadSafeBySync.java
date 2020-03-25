package com.bporcv.code.threadCode.ch02;

/**
 * 使用synchronized保证线程安全
 */
public class ThreadSafeBySync {


    public static void main(String[] args) {
        SyncUnSafeMethod num = new SyncUnSafeMethod();
        ThreadA threadA = new ThreadA(num);
        threadA.start();
        ThreadB threadB = new ThreadB(num);
        threadB.start();
    }


    public static class SyncUnSafeMethod {

        private int num = 0;

        /**
         * 使用synchronized方法保证临界区同步访问
         * @param username
         */
        synchronized public void addI(String username) {
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

        private SyncUnSafeMethod numRef;

        public ThreadA(SyncUnSafeMethod numRef) {
            this.numRef = numRef;
        }

        @Override
        public void run() {
            numRef.addI("a");
        }
    }

    public static class ThreadB extends Thread {

        private SyncUnSafeMethod numRef;

        public ThreadB(SyncUnSafeMethod numRef) {
            this.numRef = numRef;
        }

        @Override
        public void run() {
            numRef.addI("b");
        }
    }
}
