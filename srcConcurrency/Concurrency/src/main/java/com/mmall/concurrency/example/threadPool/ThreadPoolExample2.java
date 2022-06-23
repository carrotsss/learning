package com.mmall.concurrency.example.threadPool_done;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolExample2 {

    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {try {

                    log.info("task:{}", index);
                    Thread.sleep(1000);
                }catch(Exception e ){
                    e.printStackTrace();
                }
                }
            });

        }
        executorService.shutdown();
    }
}
