package com.mmall.concurrency.tread.interview;

import java.util.concurrent.CountDownLatch;

/**
 * countDownLatch这个累是一个主线程等待其他线程执行完毕后在执行
 * 它是通过一个计数器来实现的，计数器的初始只是线程的数量。每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有的县城都执行完毕，然后在门槛上等待的县城就可以恢复工作了。
 * 他其实是作用于线程当中的，他就像一个门栓，一开始是关闭的，所有希望通过该们的县城都需要等待，然后开始倒计时，当道技师或已到，等待的所有县城都可以通过
 * 要记住套是一次性的，他一旦打开就没办法关闭了
 */

//主线程可以等待其他线程执行完毕后再执行
public class E13_CountDownLatch01 {
    public static void main(String[] args) {
        usingCountDownLathch();
        usingJoin();
        System.out.println("main running");
    }

    private static void usingCountDownLathch() {
        Thread[] threads = new Thread[100];
        //指定latch长度
        CountDownLatch latch = new CountDownLatch(50/*100*//*threads.length*/);
        for (int i = 0; i < /*100*/threads.length; i++) {
            threads[i] = new Thread(() -> {
                System.out.println("countdown thread begin, count_done:" + latch.getCount());
//                int result = 0;
//                for (int j = 0; j < 10000; j++) {
//                    result += j;
//                }

//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                }
                latch.countDown();
                System.out.println("countdown thread end, count_done:" + latch.getCount());
            })/*.start()*/;
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        try {
            latch.await();//等100个执行完 usingCountDownLatch才会停止
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end latch");
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) {
                    result += j;
                }
                System.out.println("join running");
            });
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end join");
    }

}
