package com.mmall.concurrency.tread.interview;

/**
 * Created by carrots on 2022/1/9.
 */
public class E21_Interrupt {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(){

            @Override
            public void run(){
                while(true)
                {
                    if(Thread.currentThread().isInterrupted())
                    {
                        System.out.println("Interrupted!");
                        break;
                    }

                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}

