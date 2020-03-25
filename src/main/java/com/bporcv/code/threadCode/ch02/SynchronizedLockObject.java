package com.bporcv.code.threadCode.ch02;

/**
 * Synchronized锁的是对象
 * 代码解释：
 * 这里创建了一个MyObject对象，分别用两个线程去访问这个对象的methodA方法
 * 可以看到输出结果中，是异步访问的，这样符合我们的逾期输出，因为我们并没有对其进行
 * 加锁操作，所以是对外【共享】的资源,如果对其【methodA】方法进行synchronized修饰
 * 则逾期结果为同步输出，详情 {@link SynchronizedLockObject1}
 * 输出结果：
 * 线程A: 访问MyObject#methodA方法开始
 * 线程B: 访问MyObject#methodA方法开始
 * 线程A: 访问MyObject#methodA方法结束
 * 线程B: 访问MyObject#methodA方法结束
 *
 */
public class SynchronizedLockObject {

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
        public void methodA() {
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
