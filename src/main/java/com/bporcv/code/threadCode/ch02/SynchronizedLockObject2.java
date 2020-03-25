package com.bporcv.code.threadCode.ch02;

/**
 * 主题
 * Synchronized锁的是对象
 * <p>
 * 代码解释：
 * 这里创建了一个MyObject对象，创建两个线程对象
 * ThreadA访问 {@link MyObject#methodA()}方法，该方法为synchronized方法
 * ThreadB访问 {@link MyObject#methodB()}方法，该方法为普通方法
 * 可以预见输出结果为异步输出，即ThreadB访问 {@link MyObject#methodB()}和ThreadA访{@link MyObject#methodA()}是异步的
 * A线程先持有object对象的Lock锁，B线程可以以异步的形式调用object对象中的非synchronized方法
 * A线程先持有object对象的Lock锁，B线程如果在这时调用object对象中的synchronized类型的方法则需要等待，也就是同步
 * <p>
 * 输出结果：
 * 线程A: 访问MyObject#methodA方法开始
 * 线程B: 访问MyObject#methodB方法开始
 * 线程A: 访问MyObject#methodA方法结束
 * 线程B: 访问MyObject#methodB方法结束
 */
public class SynchronizedLockObject2 {

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

        public void methodB() {
            try {
                System.out.printf("线程%s: 访问MyObject#methodB方法开始\n", Thread.currentThread().getName());
                Thread.sleep(5_000);
                System.out.printf("线程%s: 访问MyObject#methodB方法结束\n", Thread.currentThread().getName());
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
            object.methodB();
        }
    }
}
