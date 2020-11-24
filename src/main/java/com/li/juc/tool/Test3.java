package com.li.juc.tool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : Semaphore
 * @date : 2020-11-24 21:31:01
 */
public class Test3 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    // 抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢占成功");
                    TimeUnit.SECONDS.sleep(3);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "释放成功");
                }
            }, String.valueOf(i)).start();
            // 多个线程共享资源互斥 并发限流控制最大的线程数
        }
    }
}
