package com.li.juc.pool;


import java.util.concurrent.*;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 七个参数 四种拒绝策略
 * @date : 2020-11-25 20:08:55
 */
public class Demo02 {
    public static void main(String[] args) {
        // 获取CPU核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 自定义线程池
        ExecutorService executorService = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        // 银行满了 还有人进来 不处理这个人的 抛出异常
        // java.util.concurrent.RejectedExecutionException

        ExecutorService executorService2 = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        // 哪来的去哪里 main执行

        ExecutorService executorService3 = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        // 不会报出异常 队列满的时候会丢掉任务

        ExecutorService executorService4 = new ThreadPoolExecutor(
                2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        // 队列满了尝试和最早的竞争 竞争失败依然会丢弃任务

        try {
            // 最大承载 deque + max
            for (int i = 0; i < 5; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
            for (int i = 0; i < 15; i++) {
                executorService2.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
            for (int i = 0; i < 20; i++) {
                executorService3.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
            for (int i = 0; i < 25; i++) {
                executorService4.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
            executorService2.shutdown();
            executorService3.shutdown();
            executorService4.shutdown();
        }
    }
}
