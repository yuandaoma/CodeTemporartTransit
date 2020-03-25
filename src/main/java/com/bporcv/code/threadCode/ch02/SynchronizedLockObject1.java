package com.bporcv.code.threadCode.ch02;

/**
 * Synchronized锁的是对象
 * 代码解释：
 * 这里创建了一个MyObject对象，分别用两个线程去访问这个对象的【methodA】方法，该方法使用synchronized修饰，属于同步方法
 * 可以预见输出结果为同步输出，即线程A和线程B中的一个执行完了【methodA】方法后，另一个线程在执行
 * 输出结果：
 * 线程A: 访问MyObject#methodA方法开始
 * 线程A: 访问MyObject#methodA方法结束
 * 线程B: 访问MyObject#methodA方法开始
 * 线程B: 访问MyObject#methodA方法结束
 */
public class SynchronizedLockObject1 {

    public static void main(String[] args) {
        MyObject object = new MyObject();
        ThreadA threadA = new ThreadA(object);
        threadA.setName("A");

        ThreadB threadB = new ThreadB(object);
        threadB.setName("B");

        threadA.start();
        threadB.start();

    }

    public static class MyObject {
        synchronized public void methodA() {
            try {
                System.out.printf("线程%s: 访问MyObject#methodA方法开始\n", Thread.currentThread().getName());
                Thread.sleep(5_000);
                System.out.printf("线程%s: 访问MyObject#methodA方法结束\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadA extends Thread {

        private MyObject object;

        public ThreadA(MyObject object) {
            this.object = object;
        }

        @Override
        public void run() {
            object.methodA();
        }
    }

    public static class ThreadB extends Thread {

        private MyObject object;

        public ThreadB(MyObject object) {
            this.object = object;
        }

        @Override
        public void run() {
            object.methodA();
        }
    }
}
