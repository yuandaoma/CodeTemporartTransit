package com.bporcv.code.threadCode;

/**
 * getId():取的线程的唯一标识
 */
public class GetIdMethodDemo {

    public static void main(String[] args) {
        System.out.println("Thread.currentThread().getName():" + Thread.currentThread().getName() +
                ",id = " + Thread.currentThread().getId());
    }
}
