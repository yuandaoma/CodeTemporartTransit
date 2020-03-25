package com.bporcv.code.threadCode;

/**
 * 线程的停止：
 * 停止一个线程意味着线程在处理完任务之前停掉正在做的操作，也就是放弃当前的操作。
 * 线程的停止。三种方法
 * 1: 使用退出标志，使线程正常退出，也就是当run方法完成后线程终止
 * 2：使用stop方法强行终止线程，但是不推荐这个方法，因为stop和suspend及resume一样，都是过期作废的方法，使用可能会产生不可预期的后果
 * 3：使用interrupt()方法中断线程
 */
public class InterruptMethodDemo {
    

    /**
     * 停止main线程
     */

    public static class InterruptedThread {
        // 验证interrupt只会中断当前线程
        public static void main(String[] args) {
            Thread.currentThread().interrupt();
            System.out.println("是否停止1? = " + Thread.currentThread().interrupted());
            System.out.println("是否停止2? = " + Thread.currentThread().interrupted());

        }
    }

    /**
     * 判断线程是否是停止状态
     * 1.this.interrupted()：测试<strong>当前</strong>线程是否已经中断,当前线程是指运行this.interrupted()方法的线程
     * 2.this.isInterrupted():测试线程是否已经中断
     */
    public static class JudgeThreadStopStatus {


        public static void main(String[] args) throws InterruptedException {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(10);
            myThread.interrupt();
            System.out.println("是否停止1? = " + myThread.interrupted());
            System.out.println("是否停止2? = " + myThread.interrupted());
            System.out.println("结束！");

        }

        public static class MyThread extends Thread {

            @Override
            public void run() {
                for (int i = 0; i < 50000; i++) {
                    System.out.println("i=" + (i + 1));
                }
            }
        }

    }

    /**
     * 不能停止的线程
     */
    public static class CantStopThread {


        public static void main(String[] args) {
            MyThread myThread = new MyThread();
            myThread.start();
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 打断当前线程的运行，但是此处并不如人意，并没有打断当前线程的运行，程序依旧输出了50万条记录
            // 调用这个方法，只是给当前线程设置一个标记位，并不会真正的停止线程
            myThread.interrupt();

        }

        static class MyThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < 500_000; i++) {
                    System.out.println("i = " + (i + 1));
                }
            }
        }
    }

}
