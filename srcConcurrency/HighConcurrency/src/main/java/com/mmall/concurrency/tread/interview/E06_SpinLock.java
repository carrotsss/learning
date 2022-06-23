package com.mmall.concurrency.tread.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自己实现一个spinLock
 */
public class E06_SpinLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t lock_done it");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t unlock it");
    }

    public static void main(String[] args) {
        E06_SpinLock e06_spinLock = new E06_SpinLock();
        new Thread(() -> {
            e06_spinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            e06_spinLock.lock();
            e06_spinLock.unlock();
        }).start();
    }
}
