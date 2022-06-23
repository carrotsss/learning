package com.mmall.concurrency.tread.interview;

import java.util.concurrent.locks.ReentrantLock;

public class E12_FairLock extends Thread{
    //参数为true表示为公平锁，请对比输出结果
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        E12_FairLock e12_fairLock = new E12_FairLock();
        Thread thread1 = new Thread(e12_fairLock);
        Thread thread2 = new Thread(e12_fairLock);
        thread1.start();
        thread2.start();
    }
}
