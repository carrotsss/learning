package company.cmcc.chapter2;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BankAccount维护了一个一行账户，可以进行余额记录、收款和付款。</p>
 * 目前账户余额100.15元，客户并发操作账户，进行付款操作250次、每次0.15元，进行收款操作250次、每次0.25元，实际账户余额应该125.15元。</p>
 * 请尝试修复目前账户余额不对的问题。
 */
public class BankAccount {
    /**
     * 账户初始金额
     */
    private Double CURRENT = 100.15;
    private ReentrantLock lock = new ReentrantLock();
    /**
     * 付款操作
     *
     * @param amount
     */
    public void pay(Double amount) {
        lock.lock();
        CURRENT = CURRENT - amount;
        lock.unlock();
    }

    public synchronized void pay1(Double amount) {
        CURRENT = CURRENT - amount;
    }

    /**
     * 收款操作
     *
     * @param amount
     */
    public void earn(Double amount) {
        lock.lock();
        CURRENT = CURRENT + amount;
        lock.unlock();
    }

    public synchronized void earn1(Double amount) {
        CURRENT = CURRENT + amount;
    }

    public static void main(String[] args) throws InterruptedException {
        BankAccount test = new BankAccount();
        test.doSomeThing();
    }

    /**
     * 通过排他锁reentrantlock保证线程原子性执行，不会造成线程读内存脏数据导致结果出错，
     * 也可以用sychronized，
     * 锁在方法内一定程度上细化锁粒度，提升性能
     * @throws InterruptedException
     */
    private void doSomeThing() throws InterruptedException {
        ExecutorService service = new ThreadPoolExecutor(100, 100, 0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(500));

        for (int i = 0; i < 500; i++) {
            boolean isPay = i % 2 == 0;
            service.execute(() -> {
                if (isPay) {
                    pay(0.15);
                } else {
                    earn(0.25);
                }
            });
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
        // 正确结果：账户最终金额125.15
        System.out.println("账户最终金额" + CURRENT);
    }
}
