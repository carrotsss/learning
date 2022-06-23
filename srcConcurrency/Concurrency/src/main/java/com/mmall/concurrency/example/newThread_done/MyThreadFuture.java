package com.mmall.concurrency.example.newThread_done;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by carrots on 2019/1/10.
 */
@Slf4j
public class MyThreadFuture {

    public static void main(String[] args) throws Exception{
        FutureTask futureTask = new FutureTask(new MyThread3());
        new Thread(futureTask).start();
        Thread.sleep(1000);
    }
}

class MyThread3 implements Callable{

    @Override
    public String call() throws Exception {
        String i = "222";
        System.out.println(i);
        return i;
    }

}
