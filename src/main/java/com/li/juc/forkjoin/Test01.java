package com.li.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author : LiWenBo
 * @program : juc
 * @description :
 * @date : 2020-11-25 21:25:22
 */
public class Test01 {
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    public static void test1() {
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (Long i = 1L; i <= 10_0000_0000; i++) {
            sum += i;
        }
        long end = System.currentTimeMillis();
        // sum=500000000500000000时间使用了7392
        System.out.println("sum=" + sum + "时间使用了" + (end - start));
    }

    public static void test2() {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> forkJoinTask = new Demo01(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinTask);// 提交任务
//        forkJoinPool.execute(forkJoinTask); // 执行任务
        Long result = 0L;
        try {
            result = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        // sum=500000000500000000时间使用了4527
        System.out.println("sum=" + result + "时间使用了" + (end - start));
    }

    public static void test3() {
        long start = System.currentTimeMillis();
        // stream 并行流
        Long sum = LongStream
                .rangeClosed(0L, 10_0000_0000L)
                .parallel()
                .reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        // sum=500000000500000000时间使用了297
        System.out.println("sum=" + sum + "时间使用了" + (end - start));
    }
}
