package com.li.juc.tool;

import java.util.concurrent.CountDownLatch;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 减法计数器
 * @date : 2020-11-24 21:21:41
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        // 计数器
        CountDownLatch countDownLatch = new CountDownLatch(6);
        // 总数是6
        // 数量减一 countDownLatch.countDown();
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "leave...");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        // 等待计数器归零 向下执行
        countDownLatch.await();
        System.out.println("Close door");
    }
}
