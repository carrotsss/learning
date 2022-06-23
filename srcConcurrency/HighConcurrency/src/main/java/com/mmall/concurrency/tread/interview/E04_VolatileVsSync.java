package com.mmall.concurrency.tread.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 并不能保证多个线程共同修改running变量所带来的不一致性问题，也就是说volatile不能替代synchronized
 */
public class E04_VolatileVsSync {
    volatile int count = 0;

    /*synchronized*/ void m() {
        for (int i = 0; i < 1000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        E04_VolatileVsSync e04_volatileVsSync = new E04_VolatileVsSync();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(e04_volatileVsSync :: m, "thread-" + i));
        }
        threads.forEach((o) -> o.start());
        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(e04_volatileVsSync.count);
    }
}
