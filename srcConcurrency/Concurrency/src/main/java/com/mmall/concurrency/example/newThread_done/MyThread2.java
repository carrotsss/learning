package com.mmall.concurrency.example.newThread_done;

/**
 * Created by carrots on 2019/1/10.
 */
public class MyThread2 implements Runnable {
    public void run(){
        int count=0;
        for (int i= 0 ;i <10 ;i++){
            count++;
            System.out.println(count);
        }


    }

    public static void main(String[] args) {
        MyThread2 myThread = new MyThread2();
        MyThread2 myThread2 = new MyThread2();

        Thread thread = new Thread(myThread);
        Thread thread2 = new Thread(myThread2);
        thread2.start();
        thread.start();
    }
}
