package com.mmall.concurrency.tread.interview;

import java.util.concurrent.CyclicBarrier;

/**
 * 循环栅栏，一个可循环利用的屏障
 * 它的作用就是会让所有线程都等待完成后才会继续下一步的行动
 * 举个例子，很多朋友到一个餐厅吃饭，有些朋友早到，有些朋友晚到，但是餐厅规定必须等到所有的到期之后才会让我们进去。
 */
public class E15_CyclicBarrier01 {
    static class TaskThread extends Thread {
        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "到达栅栏 A1");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + "到达栅栏 A2");

                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + "到达栅栏 B1");
                barrier.await();
                System.out.println(Thread.currentThread().getName() + "到达栅栏 B2");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNum = 5;
        CyclicBarrier barrier = new CyclicBarrier(threadNum, () -> {
            System.out.println(Thread.currentThread().getName() + "满足threadNum，冲");
        });
        for (int i = 0; i < threadNum; i++) {
            new TaskThread(barrier).start();
        }
    }
}
