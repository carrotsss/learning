package com.mmall.concurrency.tread;

public class SupersedeOUJI3 implements Runnable {

    private volatile static int flag = 1;
    private  int start;
    private int end;
    private String name;

    private SupersedeOUJI3(int start ,int end, String name){
        this.start = start;
        this.end = end ;
        this.name = name;
    }

    public void run(){
        while(start <= end){
            int f = flag;
            if((start & 0x01) == f){
                System.out.println(name + "+-+" + start);
                start+= 2;
                flag ^=0x1;
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new SupersedeOUJI3(1,100 ,"t1")).start();
        new Thread(new SupersedeOUJI3(2,100, "t2")).start();
    }
}
