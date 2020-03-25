package com.bporcv.code.threadCode.stopThreadBySleep;

/**
 * 打断在沉睡中的线程
 */
public class StopThreadBySleep2 {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.interrupt();
        System.out.println("main方法执行结束");
    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("MyThread开始运行");
            try {
                for (int i = 0; i < 10_0000; i++) {
                    System.out.println("i = " + (i + 1));
                }
                System.out.println("MyThread沉睡开始");
                Thread.sleep(200_000);
                System.out.println("MyThread沉睡结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("先停止，再遇到了sleep！进入cache");
            }
            System.out.println("MyThread运行结束");
        }
    }
}
