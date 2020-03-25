package com.bporcv.code.threadCode;

/**
 * 线程的休眠方法
 * 方法sleep()的作用是在指定的毫秒数内让当前"正在执行的线程"休眠（暂停执行）
 * 正在执行的线程 == this.currentThread
 */
public class SleepMethodDemo {


    /**
     * 这个案例告诉我们，如果直接调用线程的run()方法，就是普通的方法调用，还是以前的顺序流.
     * main线程会阻塞两秒钟
     */
    public static class TestRun {
        public static void main(String[] args) {
            MyThread myThread = new MyThread();
            System.out.println("main开始时间：" + System.currentTimeMillis());
            myThread.run();
            System.out.println("main结束时间：" + System.currentTimeMillis());
        }
    }


    /**
     * 这个测试案例告诉我们：如果通过调用线程的start()方法，则是异步调用
     * main()线程不会阻塞，是执行线程休眠两秒钟
     */
    public static class TestStart {
        public static void main(String[] args) {
            MyThread myThread = new MyThread();
            System.out.println("main开始时间：" + System.currentTimeMillis());
            myThread.start();
            System.out.println("main结束时间：" + System.currentTimeMillis());
        }
    }


    public static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                System.out.println("运行[MyThread#run]线程名称 = " + this.currentThread().getName() + ",开始时间：" + System.currentTimeMillis());
                // 这里会使执行进这个方法的线程休眠2000毫秒，即2秒钟
                Thread.sleep(2_000);
                System.out.println("运行[MyThread#run]线程名称 = " + this.currentThread().getName() + ",结束时间：" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
