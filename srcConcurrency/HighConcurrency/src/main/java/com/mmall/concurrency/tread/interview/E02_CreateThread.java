package com.mmall.concurrency.tread.interview;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class E02_CreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("create Thread1");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("create Thread2 runnable");
        }
    }

    public static void main(String[] args) throws Exception{
        new MyThread().start();
        new Thread(new MyRun()).start();
        //下面两个一样的
       /*
        new Thread(e04_volatile02::m, "thread1");
        同
        new Thread(new Runnable() {
            @Override
            public void run() {
                e04_volatile02.m();
            }
        }).start();
        */
        new Thread(() -> {
            System.out.println("create Thread3 lamda");
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("creat Thread3 ^lamda");
            }
        }).start();

        MyCallable<String> mc=new MyCallable<String>();
        FutureTask<String> ft=new FutureTask<String>(mc);
        new Thread(ft).start();
        String result=ft.get();
        System.out.println(result);
    }


    static class MyCallable<String> implements Callable<String> {
        private int tickt = 10;
        @Override
        public String call() throws Exception {
            // TODO Auto-generated method stub
            String result;
            while (tickt > 0) {
                System.out.println("票还剩余：" + tickt);
                tickt--;
            }
            result = (String) "票已卖光";
            return result;
        }

    }
}