package com.bporcv.code.threadCode.stopThreadByException;

/**
 * 通过interrupt()配合异常机制来控制
 */
public class StopThreadByException {


    public static void main(String[] args) {
        try {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(2000);
            myThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main方法结束");
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 50_0000; i++) {
                    if (this.interrupted()) {
                        System.out.println("MyThread已经是停止状态了");
                        throw new InterruptedException("MyThread抛出异常");
                    }
                    System.out.println("i = " + (i + 1));
                }
                System.out.println("MyThread退出for循环，继续执行下面的代码");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("MyThread#run方法中的catch语句");
            }
        }
    }
}