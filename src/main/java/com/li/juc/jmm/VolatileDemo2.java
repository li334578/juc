package com.li.juc.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : volatile 不保证原子性
 * @date : 2020-11-26 20:40:31
 */
public class VolatileDemo2 {

//    private volatile static int num = 0;
    // 原子类的 Integer
    private volatile static AtomicInteger num = new AtomicInteger();
    // 不使用lock 和 synchronized怎么保证原子性 使用原子类



    public static void add() {
//        num++; // 不是原子性操作
        num.getAndIncrement(); // +1
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + num);
    }
}
