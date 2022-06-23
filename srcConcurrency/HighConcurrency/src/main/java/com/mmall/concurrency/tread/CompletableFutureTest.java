package com.mmall.concurrency.tread;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName CompletableFutureTest
 * @Description
 * @Author carrots
 * @Date 2022/6/23 10:25
 * @Version 1.0
 */
public class CompletableFutureTest {

    static ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
            4,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(5),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws Exception{
        List<CompletableFuture<Void>> list = new ArrayList<>();
        Map<Integer, String> map = new ConcurrentHashMap<>();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            AtomicInteger integer = new AtomicInteger();
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                String task = task(integer.get());
                map.put(integer.get(), task);
            }, executor);
            list.add(future);
        }
        CompletableFuture[] arr = list.toArray(new CompletableFuture[0]);
        CompletableFuture.allOf(arr).get();
        Long end = System.currentTimeMillis();
        System.out.println("全部执行完毕后输出==" + (end - start));
        System.out.println(map);
        executor.shutdown();
    }

    @SneakyThrows
    public static String task(int i) {
        TimeUnit.SECONDS.sleep(1);
        String str = Thread.currentThread().getName() + "==" + i + "==" + System.currentTimeMillis();
        System.out.println(str);
        return str;
    }
}
