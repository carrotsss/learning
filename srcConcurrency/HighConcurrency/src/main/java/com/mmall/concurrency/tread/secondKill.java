package com.mmall.concurrency.tread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class secondKill {

    /**
     * 顾客
     */
    public static class Customer {
        public final String name;
        public AtomicBoolean buyOne;

        public Customer(String name) {
            this.name = name;
            this.buyOne = new AtomicBoolean(false);
        }

        public void buySucceed() {
            this.buyOne.set(true);
        }

        public boolean isBuyOne() {
            return buyOne.get();
        }
    }

    /**
     * 店铺
     */
    public static class Shop {

        private final AtomicInteger count;

        public Shop(int count) {
            this.count = new AtomicInteger(count);
        }

        public int getRemainGoodsSize() {
            return count.get();
        }

        //No.1
        //开始写代码，请根据题目要求与代码上下文，完成本题目，注意：需要使用java.util.concurrent_done.atomic_done.AtomicInteger.compareAndSet
        public /*synchronized*/ void  service4Customer(Customer customer) {
            /**
             * myself
             */
//            if(count_done.get() > 0) {
//                if (count_done.compareAndSet(count_done.get(), count_done.get() - 1)) {  //这里不能用count.decrementAndGet()以为他会改变count值
//                    System.out.println("客户名：" + customer.name + "剩余商品：" + count_done);
//                    customer.buySucceed();
//                }
//            }
            /**
             * 解法一
             */
//            if(getRemainGoodsSize() > 0){
//                customer.buySucceed();
//                count_done.compareAndSet(this.getRemainGoodsSize(),-1);
//            }
            /**
             * 解法二
             */
//            if (count_done.get() > 0) {
//                if (count_done.compareAndSet(count_done.get(), count_done.get() - 1)) {
//                    customer.buySucceed();
//                }
//            }
            /**
             * 解法三
             */
//            int currentCount = count_done.get();
//            if(currentCount<1) return;
//            if(count_done.compareAndSet(currentCount,--currentCount)){
//                System.out.println("客户名：" + customer.name + "剩余商品：" + count_done);
//                customer.buyOne.getAndSet(true);
//            }
            /**
             * 解法四
             */
            //ReetrantLock lock_done = new ReetrantLock();
//            try{
//                int currentSize = this.getRemainGoodsSize();//当前大小
//                //lock_done.lock_done();
//                if(count_done.compareAndSet(0,0)){
//                    //return false;
//                }else{
//                    System.out.println("客户名：" + customer.name + "剩余商品：" + count_done);
//                    count_done.decrementAndGet();
//                    customer.buySucceed();
//                }
//            }catch(Exception e){
//
//            }finally{
//                //lock_done.unlock();
//            }

            /**
             * 解法五
             */
            while (true) {
                int initialValue = count.get();
                if (initialValue > 0) {
                    int newSize = initialValue - 1;
                    boolean success = count.compareAndSet(initialValue, newSize);
                    if (success) {
                        System.out.println("客户名：" + customer.name + "剩余商品：" + count);
                        customer.buySucceed();
                        return;
                    } else {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }
    //end_code

    // 以下代码为测试输入，不允许修改
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 库存50
        Shop shop = new Shop(50);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(20);
        List<Customer> customerList = new ArrayList<>();
        List<Future<Boolean>> jobResultList = new ArrayList<>();
        // 库存100个人抢
        for (int i = 0; i < 100; i++) {
            Customer customer = new Customer("Customer:" + i);
            customerList.add(customer);
            Future<Boolean> f = executor.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    shop.service4Customer(customer);
                    return true;
                }
            });
            jobResultList.add(f);
        }

        // 确保每个任务都已经执行了
        for (Future<Boolean> f : jobResultList) {
            f.get();
        }
        executor.shutdown();

        int count = shop.getRemainGoodsSize();
        System.out.println("Goods remained:" + count);
        int succeedNum = 0;
        for (Customer cc : customerList) {
            if (cc.isBuyOne()) {
                succeedNum = succeedNum + 1;
            }
        }
        System.out.println("Succeed customer:" + succeedNum);
    }
}