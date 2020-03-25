package com.bporcv.code.threadCode.stopThreadByException;

/**
 * 通过interrupt()放来来停止线程，这种表面上看上去线程已经停止了，
 * 也停止打印了，但是实际上如果在for循环代码块后面继续编写代码，还是会继续执行
 * 想要停止线程的运行，需要通过异常机制来控制
 * @see StopThreadByException
 */
public class StopThreadByInterrupt {


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
            for (int i = 0; i < 50_0000; i++) {
                if (this.interrupted()) {
                    System.out.println("MyThread已经是停止状态了");
                    break;
                }
                System.out.println("i = " + (i + 1));
            }
            System.out.println("MyThread退出for循环，继续执行下面的代码");
        }
    }
}