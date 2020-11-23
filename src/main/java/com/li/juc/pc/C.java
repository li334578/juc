package com.li.juc.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LiWenBo
 * @program : juc
 * @description :
 * @date : 2020-11-23 21:43:41
 */
public class C {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.PrintA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.PrintB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data3.PrintC();
            }
        }, "C").start();
    }
}

class Data3 {
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void PrintA() {
        lock.lock();
        try {
            while (num != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "AAAAAAAA");
            num = 2;
            // 唤醒B
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void PrintB() {
        lock.lock();
        try {
            while (num != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "BBBBBBBB");
            num = 3;
            // 唤醒C
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void PrintC() {
        lock.lock();
        try {
            while (num != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "CCCCCCCCC");
            num = 1;
            // 唤醒B
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
