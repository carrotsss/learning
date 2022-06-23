package com.mmall.concurrency.tread.interview;

import java.util.concurrent.CyclicBarrier;

public class E15_CyclicBarrier02 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(() -> {
            System.out.println("t1 running");
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("t1 end");
        }).start();

        new Thread(() -> {
            System.out.println("t2 running");
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
            }
            System.out.println("t2 end");
        }).start();

    }
}
