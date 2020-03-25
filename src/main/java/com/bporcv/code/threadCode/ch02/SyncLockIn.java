package com.bporcv.code.threadCode.ch02;

/**
 * 主题：
 * synchronized锁重入
 * <p>
 * 代码解释：
 * 定义了一个对象，其拥有三个同步方法 {@link Service#service1()},{@link Service#service2()},{@link Service#service3()}
 * 定义了一个线程对象，其组合了这个对象，然后访问其{@link Service#service1()}方法，可以看到，{@link Service#service2()}和
 * {@link Service#service3()}同步方法也能够被访问，则在线程拥有这个对象的锁之后，可以继续访问其他同步方法
 * <p>
 * 预期结果：
 * Service.service1
 * Service.service2
 * Service.service3
 */
public class SyncLockIn {

    public static void main(String[] args) {
        Service service = new Service();
        MyThread myThread = new MyThread(service);
        myThread.start();
    }


    public static class Service {
        synchronized public void service1(){
            System.out.println("Service.service1");
            service2();
        }

        synchronized public void service2(){
            System.out.println("Service.service2");
            service3();
        }

        synchronized public void service3(){
            System.out.println("Service.service3");
        }

    }


    public static class MyThread extends Thread {

        private Service service;

        public MyThread(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.service1();
        }
    }



}
