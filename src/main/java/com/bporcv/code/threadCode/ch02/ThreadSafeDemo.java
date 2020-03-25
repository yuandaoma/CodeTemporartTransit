package com.bporcv.code.threadCode.ch02;

/**
 * 方法内的变量为线程安全
 * 此案例说明：
 * 方法内部定义的变量是不存在线程安全问题的。
 */
public class ThreadSafeDemo {


    public static void main(String[] args) {
        HasSelfPrivateNum num = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(num);
        threadA.start();
        ThreadB threadB = new ThreadB(num);
        threadB.start();


    }


    public static class HasSelfPrivateNum {

        public void addI(String username){
            try {
                int num = 0;
                if (username.equals("a")){
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

        private HasSelfPrivateNum numRef;

        public ThreadA(HasSelfPrivateNum numRef) {
            this.numRef = numRef;
        }

        @Override
        public void run() {
            numRef.addI("a");
        }
    }

    public static class ThreadB extends Thread {

        private HasSelfPrivateNum numRef;

        public ThreadB(HasSelfPrivateNum numRef) {
            this.numRef = numRef;
        }

        @Override
        public void run() {
            numRef.addI("b");
        }
    }
}
