package com.mmall.concurrency.tread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SupersedeOUJI {
        private int start = 1;
        private volatile boolean flag = false;
//        private boolean flag = false;
        private final static Lock lock = new ReentrantLock();

        public static class OuNum implements Runnable{
            private SupersedeOUJI supersedeOUJI;
            public OuNum(SupersedeOUJI supersedeOUJI){
                this.supersedeOUJI = supersedeOUJI;
            }
            public void run(){
                while (supersedeOUJI.start <= 1000){
//                    System.out.println("偶数抢到了！");
                    if(supersedeOUJI.flag){
                        try{
                            lock.lock();
                            System.out.println(Thread.currentThread().getName() + " +-+ " + supersedeOUJI.start);
                            supersedeOUJI.start ++;
                            supersedeOUJI.flag = false;
                        }catch (Exception e){

                        }finally {
                            lock.unlock();
                        }
                    }
                }
            }
        }

        public static class jiNum implements Runnable{
            private SupersedeOUJI supersedeOUJI;
            public jiNum(SupersedeOUJI supersedeOUJI){
                this.supersedeOUJI = supersedeOUJI;
            }
            public void run() {
                while(supersedeOUJI.start <1000){
//                    System.out.println("奇数抢到了！");
                    if(!supersedeOUJI.flag){
                        try{
                            lock.lock();
                            System.out.println(Thread.currentThread().getName() +  " +-+ " + supersedeOUJI.start);
                            supersedeOUJI.start++;
                            supersedeOUJI.flag = true;
                        }catch (Exception e){

                        }finally {
                            lock.unlock();
                        }
                    }
                }
            }
        }

        public static void main(String[] args) {
            SupersedeOUJI supersedeOUJI = new SupersedeOUJI();
            Thread t2 = new Thread(new OuNum(supersedeOUJI));
            Thread t1 = new Thread(new jiNum(supersedeOUJI));
            t2.setName("t2");
            t1.setName("t1");
            t1.start();
            t2.start();
        }
}
