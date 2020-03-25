package com.bporcv.code.threadCode.ch02;

public class EveryObjectHasALock {


    public static void main(String[] args) {
        ObjectLock objectLock1 = new ObjectLock();
        ObjectLock objectLock2 = new ObjectLock();
        ThreadA threadA = new ThreadA(objectLock1);
        threadA.start();
        ThreadB threadB = new ThreadB(objectLock2);
        threadB.start();
    }


    public static class ObjectLock {
        private int num = 0;

        /**
         * 使用synchronized方法保证临界区同步访问
         *
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

        private ObjectLock numRef;

        public ThreadA(ObjectLock numRef) {
            this.numRef = numRef;
        }

        @Override
        public void run() {
            numRef.addI("a");
        }
    }

    public static class ThreadB extends Thread {

        private ObjectLock numRef;

        public ThreadB(ObjectLock numRef) {
            this.numRef = numRef;
        }

        @Override
        public void run() {
            numRef.addI("b");
        }
    }
}
