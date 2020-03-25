package com.bporcv.code.threadCode;

/**
 * 线程的isAlive()方法：
 * 什么是活动状态：线程已经启动并且尚未终止，线程正在运行或者开始运行的状态，就会认为线程是存活的。
 *
 */
public class IsAliveMethodDemo {


    public static void main(String[] args) {
        // 创建一个线程的时候，会给这个线程初始化一个名称：规则是[Thread-]+id，id从0开始，
        // 所以这里的myThread这个对象所持有的线程名称就是Thread-0，在构造函数中，当前对象是在main线程中创建的，所以会得到main
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.setName("A");
        // 注意，这里是启动的thread对象，而不是用的myThread，所以说是这个线程去访问的myThread的方法，而这个看似是个线程的myThread从始到终都没有运行过
        thread.start();
        /**
         * ----------------输出结果--------------------
         * MyThread-------begin
         * Thread.currentThread().getName() = main
         * Thread.currentThread().isAlive() = true
         * this.getName() = Thread-0
         * this.isAlive() = false
         * MyThread-------end
         * MyThread#run-------begin
         * Thread.currentThread().getName() = A
         * Thread.currentThread().isAlive() = true
         * this.getName() = Thread-0
         * this.isAlive() = false
         * MyThread#run-------end
         */
    }


    public static class MyThread extends Thread {

        public MyThread() {
            System.out.println("MyThread-------begin");
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
            System.out.println("this.getName() = " + this.getName());
            System.out.println("this.isAlive() = " + this.isAlive());
            System.out.println("MyThread-------end");
        }

        @Override
        public void run() {
            System.out.println("MyThread#run-------begin");
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
            System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
            System.out.println("this.getName() = " + this.getName());
            System.out.println("this.isAlive() = " + this.isAlive());
            System.out.println("MyThread#run-------end");
        }
    }
}
