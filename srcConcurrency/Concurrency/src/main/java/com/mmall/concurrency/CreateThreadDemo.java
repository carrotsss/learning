package com.mmall.concurrency; /**
 * Created by carrots on 2018/12/27.
 */
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的三种方式： 1.继承Thread类 2.实现Runnable接口 3.实现Callable接口
 *
 * @author wxb
 *
 */
public class CreateThreadDemo {

    public static void main(String[] args) throws Exception {

        // 线程1
        Thread1 t1 = new Thread1();
        t1.start();

        // 线程2:必须创建Thread类的实例，通过Thread类的构造方法函数将Runnable对象转为Thread对象，
        Thread2 t2 = new Thread2();
        Thread t = new Thread(t2);
        t.start();

        // 线程3：创建Future对象，再使用Thread类的构造方法去完成
        Thread3 t3 = new Thread3();
        FutureTask<Integer> task = new FutureTask<>(t3);
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("sum---" + task.get());// 通过get()方法获取结果
        // 主线程
        Thread.currentThread().setName("主线程");
        System.out.println("主线程的名字：" + Thread.currentThread().getName());
        System.out.println("主线程编号：" + Thread.currentThread().getId());
        System.out.println("主线程优先级：" + Thread.currentThread().getPriority());

    }

}

// 继承Thread类
class Thread1 extends Thread {

    @Override
    public void run() {
        Thread.currentThread().setName("第一个线程");// 设置线程的名字
        System.out.println("线程1的名字：" + Thread.currentThread().getName());
        System.out.println("线程1的优先级：" + Thread.currentThread().getPriority());
        System.out.println("线程1的编号：" + Thread.currentThread().getId());
    }
}

// 实现Runnable接口，没有返回类型，重写run()方法
class Thread2 implements Runnable {

    @Override
    public void run() {
        System.out.println("线程2的名字：" + Thread.currentThread().getName());
        System.out.println("线程2的优先级：" + Thread.currentThread().getPriority());
        System.out.println("线程2的编号：" + Thread.currentThread().getId());
    }
}

/**
 * 实现Callable接口,有返回类型 ，重写call()方法
 *
 * @author wxb
 *
 */
class Thread3 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 100;
        return Integer.valueOf(sum);
    }

}

