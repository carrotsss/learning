package com.mmall.concurrency.tread;

public class SupersedeOUJI2 {
    private int start = 1;
    private boolean flag = false;

    public static class OuNum implements Runnable{
        private SupersedeOUJI2 supersedeOUJI2 = new SupersedeOUJI2();
        public OuNum(SupersedeOUJI2 supersedeOUJI2){
            this.supersedeOUJI2 = supersedeOUJI2;
        }
        public void run(){
            while (supersedeOUJI2.start<1000){

                synchronized (SupersedeOUJI2.class){
                    System.out.println("偶数抢到了！");
                    if(supersedeOUJI2.flag){
                        System.out.println(Thread.currentThread().getName() + " +-+ " + supersedeOUJI2.start);
                        supersedeOUJI2.start ++;
                        supersedeOUJI2.flag = false;

                        SupersedeOUJI2.class.notify();

                    }else{
                        try{

                            SupersedeOUJI2.class.wait();

                        }catch (Exception e ){}
                    }
                }
            }
        }
    }

    public static class JiNum implements Runnable{
        private SupersedeOUJI2 supersedeOUJI2 = new SupersedeOUJI2();
        public JiNum(SupersedeOUJI2 supersedeOUJI2){
            this.supersedeOUJI2 = supersedeOUJI2;
        }
        public void run(){
            while (supersedeOUJI2.start<1000) {

                synchronized (SupersedeOUJI2.class){
                    System.out.println("奇数抢到了！");
                    if(!supersedeOUJI2.flag){
                        System.out.println(Thread.currentThread().getName() + " +-+ " + supersedeOUJI2.start);
                        supersedeOUJI2.start ++;
                        supersedeOUJI2.flag = true;

                        SupersedeOUJI2.class.notify();

                    }else{
                        try{

                            SupersedeOUJI2.class.wait();

                        }catch (Exception e ){}
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        SupersedeOUJI2 supersedeOUJI2 = new SupersedeOUJI2();
        Thread t2 = new Thread(new OuNum(supersedeOUJI2));
        t2.setName("t2");
        Thread t1 = new Thread(new JiNum(supersedeOUJI2));
        t1.setName("t1");
        t1.start();
        t2.start();
    }
}
