package com.mmall.concurrency.tread;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程顺序执行
 */
@Slf4j
public class SerialRun {
    private static void joinRun() {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("产品经理规划新需求！");
            }
        });
        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();
                    System.out.println("开发人员开发新需求功能");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread2.join();
                    System.out.println("测试人员测试新功能");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("早上");
        System.out.println("测试人员来上班了");
        thread3.start();
        System.out.println("产品经理来上班了");
        thread1.start();
        System.out.println("开发人员来上班了");
        thread2.start();
    }

    public static void mainJoinRun() throws Exception{
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("产品经理正在规划需求");
            }
        });
        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("开发人员正在开发新需求");
            }
        });
        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("测试人员正在测试新需求");
            }
        });
        log.info("早上:{}");
        log.info("产品经理来上班了");
        log.info("测试人员来上班了");
        log.info("开发人员来上班了");
        thread1.start();
        log.info("开发人员和测试人员休息一会...");
        //父进程调用子进程的join方法后，父进程需要等待子进程运行完再继续运行
        thread1.join();
        log.info("产品经理新需求规划完成");
        thread2.start();
        log.info("测试人员休息会...");
        thread2.join();
        thread3.start();
    }

    private static Object myLock1 = new Object();
    private static Object myLock2 = new Object();
    private static Boolean t1Run = false;
    private static Boolean t2Run = false;
    public static void waitRun() {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myLock1) {
                    log.info("产品经理规划新需求");
                    t1Run = true;
                    myLock1.notify();
                }
            }
        });
        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myLock1) {
                    try {
                        if (!t1Run) {
                            log.info("开发人员先休息会");
                            myLock1.wait();
                        }
                        synchronized (myLock2) {
                            log.info("开发人员开发新需求");
//                            t2Run = true;
                            myLock2.notify();
                        }
                    } catch (Exception e) {

                    }
                }
            }
        });
        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (myLock2) {
                    try {
                        if (!t2Run) {
                            log.info("测试人员休息会");
                            myLock2.wait();
                        }
                        log.info("测试人员测试新功能");
                    } catch (Exception e) {

                    }
                }
            }
        });
        log.info("早上");
        log.info("测试人员来上班了");
        thread3.start();
        log.info("产品经理来上班了");
        thread1.start();
        log.info("开发人员来上班了");
        thread2.start();
    }

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static void threadPoolRun() {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("产品经理规划新需求");
            }
        });
        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("开发人员开发新需求");
            }
        });
        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("测试人员测试新需求");
            }
        });
        log.info("早上:");
        log.info("产品经理来上班了");
        log.info("测试人员来上班了");
        log.info("开发人员来上班了");
        log.info("领导吩咐");
        log.info("首先，产品经理规划新需求。。。");
        executorService.submit(thread1);
        log.info("然后，开发人员开发新功能。。。");
        executorService.submit(thread2);
        log.info("最后，测试人员测试洗功能。。。");
        executorService.submit(thread3);
        executorService.shutdown();
    }

    private static Lock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();
    /**
     * 为什么要加这两个标识状态
     * 如果没有状态标识，当t1已经执行完了t2才会运行，t2正在等待t1唤醒导致t2永远在等待状态
     */
    private static Boolean t1Run2 = false;
    private static Boolean t2Run2 = false;
    public static void conditionRun() {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                log.info("产品经理规划需求");
                t1Run2 = true;
                condition1.signal();
                lock.unlock();
            }
        });
        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    if (!t1Run2) {
                        log.info("开发人员先休息会");
                        condition1.await();
                    }
                    log.info("开发人员开发新功能");
                    t2Run2 = true;
                    condition2.signal();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        });
        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    if (!t2Run2) {
                        log.info("测试人员先休息会");
                        condition2.await();
                    }
                    log.info("测试人员测试新功能");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        });
        log.info("早上");
        log.info("测试人员来上班了");
        thread3.start();
        log.info("开发人员来上班了");
        thread2.start();
        log.info("产品经理来上班了");
        thread1.start();
    }

    /**
     * 用于判断线程一是否执行，倒计时设置成1，执行后减1
     */
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    /**
     * 用于判断线程二是否执行
     */
    private static CountDownLatch countDownLatch2 = new CountDownLatch(1);
    public static void countDownLatchRun() {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("产品经理规划需求");
                countDownLatch.countDown();
            }
        });
        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    log.info("开发人员开发新功能");
                    countDownLatch2.countDown();
                } catch (Exception e) {
                }
            }
        });
        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    countDownLatch2.await();
                    log.info("测试人员测试新功能");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        log.info("早上");
        log.info("测试人员来上班了");
        thread3.start();
        log.info("开发热暖来上班了");
        thread2.start();
        log.info("产品经理莱昂和了");
        thread1.start();
    }

    private static CyclicBarrier cyclicBarrier1 = new CyclicBarrier(1);
    private static CyclicBarrier cyclicBarrier2 = new CyclicBarrier(1);
    public static void barrierRun() {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("产品呢经理规划新需求");
                try {
                    /**
                     * 放开栅栏1
                     */
                    cyclicBarrier1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //放开栅栏1
                    cyclicBarrier1.await();
                    log.info("开发人员开发新需求");
                    //放开栅栏2
                    cyclicBarrier2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                log.info("测试人员测试新功能");
            }
        });
        log.info("早上");
        log.info("产品经理来上班了");
        thread.start();
        log.info("开发人员里上班了");
        thread2.start();
        log.info("测试经理来上班了");
        thread3.start();
    }

    private static Semaphore semaphore1 = new Semaphore(1);
    private static Semaphore semaphore2 = new Semaphore(1);
    public static void semaphoreRun() {
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("产品经理规划新需求");
                semaphore1.release();
            }
        });
        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("开发人员开发新需求");
                semaphore2.release();
            }
        });
        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore2.acquire();
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("测试人员测试新功能");
                semaphore2.release();
            }
        });
        log.info("上上");
        log.info("产品经理来上班了");
        thread1.start();
        log.info("测试人员来上班了");
        thread3.start();
        log.info("开发人员来上班了");
        thread2.start();
    }
    public static void main(String[] args) throws Exception{
//        joinRun();
//        mainJoinRun();
//        waitRun();
//        threadPoolRun();
//        conditionRun();
//        countDownLatchRun();
//        barrierRun();
        semaphoreRun();
    }
}
