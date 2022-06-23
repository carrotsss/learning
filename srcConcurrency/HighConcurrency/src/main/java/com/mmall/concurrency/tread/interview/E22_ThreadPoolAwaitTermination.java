package com.mmall.concurrency.tread.interview;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class E22_ThreadPoolAwaitTermination {
    private static void executorService() throws Exception {
        BlockingQueue queue = new LinkedBlockingQueue(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MILLISECONDS, queue);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("A running");
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                }
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("B running");
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                }
            }
        });

        executor.shutdown();
        while (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
            System.out.println("线程还在执行");
        }
        System.out.println("main over");
    }

    public static void main(String[] args) throws Exception{
        executorService();
    }
}
