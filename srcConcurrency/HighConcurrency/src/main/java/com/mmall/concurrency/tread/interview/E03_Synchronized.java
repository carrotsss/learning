package com.mmall.concurrency.tread.interview;

import java.util.concurrent.TimeUnit;

public class E03_Synchronized {
    //锁o这个对象
    static class SynchronizedObject {
        private int count = 10;
        private Object o = new Object();

        public void XXX() {
            synchronized (o) {
                count--;
            }
        }
    }

    //所得是SynchronizedMethod实例化的对象
    static class SynchronizedMethod {
        private int count = 10;
        private Object o = new Object();

        public synchronized void XXX() {
            count--;
        }
    }

    //对class对象进行上锁
    static class SynchronizedClass {
        private int count = 10;
        private Object o = new Object();

        public void XXX() {
            synchronized (SynchronizedClass.class) {
                count--;
                System.out.println("hello");
            }
        }
    }

    //对class进行上锁
    static class SynchronizedMain {
        private int count = 10;
        private Object o = new Object();

        public synchronized static void main(String[] args) {
            System.out.println("hello");
        }
    }

    static class SynchronizedThis {
        private int count = 10;
        private Object o = new Object();

        public void XXX() {
            synchronized (this) {
                count++;
            }
        }
    }

    //synchronized是可重入锁
    static class SychronizedReentrant {
        public synchronized void m1() {
            System.out.println("m1 start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            m2();
        }

        public synchronized void m2() {
            System.out.println("m2 start");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public synchronized static void main(String[] args) {
            new SychronizedReentrant().m1();
        }
    }

}
