package com.mmall.concurrency.tread.interview;

public class E01_DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new DeadLock(lockA, lockB)).start();
        new Thread(new DeadLock(lockB, lockA)).start();
    }

    static class DeadLock implements Runnable {
        String lockA, lockB;

        public DeadLock(String lockA, String lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + "\t 获得" + lockA + "\t 尝试获得" + lockB);
                synchronized (lockB) {
                    System.out.println("lockA -> lockB");
                }
            }
        }
    }
}


