package com.mmall.concurrency.tread.interview;

import java.util.concurrent.Semaphore;

public class E17_Semaphore {
    public static void main(String[] args) {
        //允许一个线程同时执行
        Semaphore semaphore = new Semaphore(1);
        //允许两个线程同时执行，第二个参数为true，表示公平模式
//        Semaphore semaphore1 = new Semaphore(2, true);

        new Thread(() ->{
            try {
                semaphore.acquire();
                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();
    }
}
