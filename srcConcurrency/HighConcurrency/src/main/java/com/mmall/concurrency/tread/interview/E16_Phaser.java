package com.mmall.concurrency.tread.interview;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class E16_Phaser {
    static Random r = new Random();
    static MarrigePhaser phaser = new MarrigePhaser();

    static void milliSleep(int milli) {
        try {
            TimeUnit.MICROSECONDS.sleep(milli);
        } catch (InterruptedException e) {

        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p" + i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static class MarrigePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了！" + registeredParties);
                    return false;
                case 1:
                    System.out.println("所有人吃完了！" + registeredParties);
                    return false;
                case 2:
                    System.out.println("所有人离开了！" + registeredParties);
                    return false;
                case 3:
                    System.out.println("婚礼结束！新郎新娘抱抱！" + registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.println(name + " 到达现场！");
            phaser.arriveAndAwaitAdvance();
        }

        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.println(name + "吃完！");
            phaser.arriveAndAwaitAdvance();
        }

        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.println(name + "离开！");
            phaser.arriveAndAwaitAdvance();
        }

        public void hug() {
            if (name.equals("新浪") || name.equals("新娘")) {
                milliSleep(r.nextInt(1000));
                System.out.println(name + "洞房！");
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
                //phaser.register()
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }
}
