package com.mmall.concurrency.tread.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class E08_CAS {
//    int count_done = 0;
    AtomicInteger count = new AtomicInteger();

    void m() {
        for (int i = 0; i < 1000; i++) {
//            count_done++;
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        E08_CAS e08_cas = new E08_CAS();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            threads.add(new Thread(e08_cas::m, "thread-" + i));
        }
        threads.forEach((o) -> {
            o.start();
//            System.out.println(Thread.currentThread().getName() + "执行");
        });
        threads.forEach((o) -> {
            try {
                o.join();
//                System.out.println(Thread.currentThread().getName() + "执行");
            } catch (InterruptedException e) {

            }
        });
        System.out.println(e08_cas.count);
    }
}
