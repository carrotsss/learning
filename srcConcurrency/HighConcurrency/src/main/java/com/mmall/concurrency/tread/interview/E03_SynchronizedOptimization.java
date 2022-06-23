package com.mmall.concurrency.tread.interview;

import java.util.concurrent.TimeUnit;

/**
 * Synchronized优化一般指的是同步代码块中代码语句越少越好，就是锁细化，还有一种锁粗化
 */
public class E03_SynchronizedOptimization {

    int count = 0;

    synchronized void m1() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //整个方法只有这个地方需要加锁
        synchronized (this) {
            count++;
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
