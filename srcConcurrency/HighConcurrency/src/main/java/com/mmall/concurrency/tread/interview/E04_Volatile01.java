package com.mmall.concurrency.tread.interview;

import java.util.concurrent.TimeUnit;

public class E04_Volatile01 {
    class Volatile01{

    }
    /*volatile*/ boolean running = true;

    void XXX() {
        System.out.println("XXX start");
        while (running) {
        }
        System.out.println("XXX end ");
    }

    public static void main(String[] args) {
        E04_Volatile01 e04_volatile01 = new E04_Volatile01();
        new Thread(e04_volatile01:: XXX, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        e04_volatile01.running = false;
    }
}
