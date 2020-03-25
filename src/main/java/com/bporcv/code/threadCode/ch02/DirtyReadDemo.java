package com.bporcv.code.threadCode.ch02;

/**
 * 主题：
 * 脏读
 * <p>
 * 代码解释：
 *
 * <p>
 * 预期结果：
 */
public class DirtyReadDemo {

    public static void main(String[] args) {
        try {
            Value value = new Value();
            new ThreadA(value).start();
            Thread.sleep(200);
            value.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static class Value {
        private String userName = "A";
        private String password = "AA";

        synchronized public void setValue(String userName, String password) {
            try {
                this.userName = userName;
                Thread.sleep(5_000);
                this.password = password;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void getValue() {
            System.out.println(userName + "," + password);
        }
    }


    public static class ThreadA extends Thread {

        private Value value;

        public ThreadA(Value value) {
            this.value = value;
        }


        @Override
        public void run() {
            value.setValue("B","BB");
        }

    }



}
