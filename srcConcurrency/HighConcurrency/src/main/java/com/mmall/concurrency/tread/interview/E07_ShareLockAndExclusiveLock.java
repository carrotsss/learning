package com.mmall.concurrency.tread.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantLock和Synchronized都是独占锁，ReentrantReadWriteLock其读锁是共享锁，写锁是排他锁
 */
public class E07_ShareLockAndExclusiveLock {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        //加写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            try {
                TimeUnit.MICROSECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 已写入");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        //加读锁
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取");
            try {
                TimeUnit.MICROSECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 已读取：" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
        return null;
    }
}

class ReadWriteDemo {
    public static void main(String[] args) {
        E07_ShareLockAndExclusiveLock e07_shareLockAndExclusiveLock = new E07_ShareLockAndExclusiveLock();
        for (int i = 0; i < 5; i++) {
            final int keyValue = i;
            new Thread(() -> {
                e07_shareLockAndExclusiveLock.put(keyValue + "", keyValue + "");
            }, i + "").start();
        }

        for (int i = 0; i < 5; i++) {
            final int keyValue = i;
            new Thread(() -> {
                e07_shareLockAndExclusiveLock.get(keyValue + "");
            }, i + "").start();
        }
    }
}
