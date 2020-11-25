package com.li.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : Executors
 * @date : 2020-11-25 19:56:19
 */
public class Demo01 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 单个线程
        ExecutorService threadPool2 = Executors.newFixedThreadPool(5); // 指定数量的
        ExecutorService threadPool3 = Executors.newCachedThreadPool(); // 可伸缩的
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }
            for (int i = 0; i < 10; i++) {
                threadPool2.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }
            for (int i = 0; i < 10; i++) {
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
            threadPool2.shutdown();
            threadPool3.shutdown();
        }

    }
}
