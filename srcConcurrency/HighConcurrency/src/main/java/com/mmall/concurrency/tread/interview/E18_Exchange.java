package com.mmall.concurrency.tread.interview;

import java.util.concurrent.Exchanger;

/**
 * java.util.concurrent包中的Exchanger类可用于两个县城之间交换信息。可简单的将Exchange对象理解为一个包含两个格子的容器，通过exchanger的方法可以向两个格子中填充信息。
 * 当两个格子中的均被填充时，该对象会自动将两个格子的信息交换，然后返回给线程，宠而实现两个线程的信息交换。
 */
public class E18_Exchange {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(() -> {
            String s = "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "thread1").start();

        new Thread(() -> {
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " " + s);
        }, "thread2").start();
    }
}
