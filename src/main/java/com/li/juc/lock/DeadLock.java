package com.li.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 死锁
 * @date : 2020-11-26 22:41:29
 */
public class DeadLock {
    public static void main(String[] args) {
        String A = "A";
        String B = "B";
        new Thread(new MyThread(A,B), "A").start();
        new Thread(new MyThread(B,A), "B").start();
    }
}

class MyThread implements Runnable {

    private String lockA;
    private String lockB;

    public MyThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + lockA + "lockA 想要获取" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + lockB + "lockB 想要获取" + lockA);
            }
        }
    }
}
