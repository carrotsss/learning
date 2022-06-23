package com.mmall.concurrency.tread.interview;

/**
 * volatile 引用类型（包括数组）只能保证引用本身的可见性，不能保证内部字段的可见性
 */
public class E04_Volatile02 {
    boolean running = true;
    volatile static E04_Volatile02 e04_volatile02 = new E04_Volatile02();

    void m() {
        System.out.println("m1 start");
        while (running) {
        }
        System.out.println("m1 end");
    }

    public static void main(String[] args) {
        new Thread(e04_volatile02::m, "thread1");
        /*
        同
        new Thread(new Runnable() {
            @Override
            public void run() {
                e04_volatile02.m();
            }
        }).start();*/
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        e04_volatile02.running = false;
    }
}
