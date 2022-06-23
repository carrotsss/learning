package com.mmall.concurrency.tread.interview;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class E07_ReadWriteLock {
    private static int value;
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over");
            System.out.println(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void write(Lock lock, int v) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("write over");
            value = v;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable readRunnable = () -> read(readLock);
        Runnable writeRunnable = () -> write(writeLock, new Random().nextInt());
        for (int i = 0; i < 2; i++) {
            new Thread(writeRunnable).start();
        }
        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }
    }
}
