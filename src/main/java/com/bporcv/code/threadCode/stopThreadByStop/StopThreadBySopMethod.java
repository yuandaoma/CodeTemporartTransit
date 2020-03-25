package com.bporcv.code.threadCode.stopThreadByStop;

/**
 * 使用stop()方法暴力停止线程
 */
public class StopThreadBySopMethod {

    public static void main(String[] args) {
        try {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(5_000);
            myThread.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static class MyThread extends Thread {

        private int i = 0;


        @Override
        public void run() {
            try {
                while (true){
                    i++;
                    System.out.println("i = " + i);
                    Thread.sleep(1_000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
