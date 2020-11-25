package com.li.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 异步调用
 * @date : 2020-11-25 21:52:14
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 异步执行
        // 成功回调
        // 失败回调
        // 没有返回值的异步回调
        /*
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println("11111111");
        completableFuture.get(); // 获取阻塞执行结果
        */
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            int i = 10/0;
            return "1024";
        });
        System.out.println(completableFuture.whenComplete((t,u)->{
            System.out.println(t);
            System.out.println(u);
        }).exceptionally((e)->{
            System.out.println(e.getMessage());
            return "2333";
        }).get());
    }
}
