package com.mmall.concurrency.tread.interview;

/**
 * Created by carrots on 2022/2/27.
 */
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class E14_ForkJoinPool {
    static class ForkJoinSumCalculate extends RecursiveTask<Long> {
        //计算开始值
        private long start;
        //计算结束值
        private long end;
        //拆分临界值
        private static final long THURHOLD = 10L;

        //构造函数
        public ForkJoinSumCalculate(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            //获取菜单拆分的极限值
            long length = end - start;
            //判断拆分的极限值是否小于阈值，小于的话就可以合并了，不然就继续拆分
            if (length <= THURHOLD) {
                long sum = 0L;
                for (long i = start; i <= end; i++) {
                    sum += i;
                }
                return sum;
            } else {
                long middle = (start + end) / 2;
                ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
                //进行拆分，然后压入线程队列
                left.fork();
                ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
                //进行拆分，然后压入线程队列
                right.fork();
                return left.join() + right.join();
            }
        }
    }
    //测试方法
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long sum = forkJoinPool.invoke(new ForkJoinSumCalculate(0, 100L));
        System.out.println(sum);
    }
}

