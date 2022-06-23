package com.mmall.concurrency.tread.interview;

public class E05_Singleton {
    private static volatile E05_Singleton INSTANCE;

    private E05_Singleton() {

    }

    public static E05_Singleton getInstance() {
        //省略业务代码
        if (INSTANCE == null) {
            synchronized (E05_Singleton.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new E05_Singleton();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(E05_Singleton.getInstance().hashCode());
            }).start();
        }
    }
}
