package com.bporcv.code.threadCode.stopThreadBySleep;

/**
 * 打断在沉睡中的线程
 */
public class StopThreadBySleep {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("main方法抓住异常：" + e);
        }
        myThread.interrupt();
        System.out.println("main方法执行结束");
    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("MyThread开始运行");
            try {
                System.out.println("MyThread沉睡开始");
                Thread.sleep(200_000);
                System.out.println("MyThread沉睡结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("MyThread在sleep中被打断：" + this.isInterrupted());
            }
            System.out.println("MyThread运行结束");
        }
    }
}
