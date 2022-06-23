package com.mmall.concurrency.tread.interview;

import java.util.concurrent.TimeUnit;

public class E03_SynchronizedSameObject {
   /*final*/ Object o = new Object();

    void m() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        E03_SynchronizedSameObject e03_synchronizedSameObject = new E03_SynchronizedSameObject();
        new Thread(e03_synchronizedSameObject::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(e03_synchronizedSameObject::m, "t2");
        //改变加锁对象
//        e03_synchronizedSameObject.o = new Object();
        t2.start();
    }
}
