package com.mmall.concurrency.tread.interview;

import java.util.concurrent.CountDownLatch;

//CountDownLatch还可以用于同时启动多个线程
public class E13_CountDownLatch02 {
    static class TaskThread extends Thread{
        CountDownLatch latch;

        public TaskThread(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
                System.out.println(Thread.currentThread().getName() + " start " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNum = 10;
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < threadNum; ++i) {
            TaskThread taskThread = new TaskThread(latch);
            taskThread.start();
        }
        latch.countDown();
    }
}
