package com.li.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LiWenBo
 * @program : juc
 * @description :
 * @date : 2020-11-26 22:27:15
 */
public class Demo02 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(() -> {
            phone.sms();
            // 锁了两次
            // lock1 lock2 unlock2 unlock1
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();
    }
}

class Phone2 {
    Lock lock = new ReentrantLock();

    public void sms() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":sms");
            // 这里也有锁
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":call");
            // 这里也有锁
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
