package com.mmall.concurrency.tread.interview;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport是一个线程阻塞工具类，所有方法都是静态方法，可以让线程在任意位置阻塞，当然阻塞之后肯定得有唤醒的方法
 * park(Object blocker) 暂停当前线程
 * parkNanos(Object blocker, long nanos) 暂停当前线程，不过有超时时间限制
 * parkUntil(Object blocker, long deadline) 暂停单签线程，知道某个时间
 * park() 无限期暂停当前线程
 * parkNanos(long nanos) 暂停当前线程，不过有超时间的限制
 * parkUntil(long deadline) 暂停当前线程，知道某个时间
 * unpark(Thread thread) 恢复当前线程
 * getBlocker(Thread t)
 * 1、不需要获取某个对象的锁
 * 2、park和unpark 可以实现类似wait和notify的功能，但是并不和wait和notify交叉，也就是说unpark不会对wait起作用，notify也不会对park起作用
 * 3、park和unpark的使用不会出现死锁的情况
 * 4、相对于现成的stop和resume，park和unpark的先后顺序并不是那么严格。stop和resume如果顺序反了，会出现死锁现象。而park和unpark不会
 * park和unpark会对每个线程维持一个许可（boolean值）
 *      i.unpark调用时，如果当前线程还未进入park，则许可为true
 *      ii.park调用时，判断许可是否为true，则继续往下执行；如果是false，则等待，直到许可为true
 * 5、blocker的作用是在dump线程的时候看到阻塞对象的信息
 */
public class E19_LockSupport {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
//        LockSupport.unpark(t);
    }
}
