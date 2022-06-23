package com.mmall.concurrency.example.newThread_done;

/**
 * Created by carrots on 2019/1/10.
 */
public class MyThread extends Thread {
    public void run (){
        int count = 0;
        count++;
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

