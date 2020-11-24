package com.li.juc.sq;


import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 同步队列
 * @date : 2020-11-24 22:23:54
 */
public class Test1 {
    public static void main(String[] args) {
        // 同步队列
        BlockingQueue<String> blockingDeque = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                blockingDeque.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                blockingDeque.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                blockingDeque.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + " get 1");
                blockingDeque.take();
                System.out.println(Thread.currentThread().getName() + " get 2");
                TimeUnit.SECONDS.sleep(3);
                blockingDeque.take();
                System.out.println(Thread.currentThread().getName() + " get 3");
                TimeUnit.SECONDS.sleep(3);
                blockingDeque.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
    }
}
