package com.li.juc.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LiWenBo
 * @program : juc
 * @description :
 * @date : 2020-11-23 21:30:00
 */
public class B {
    public static void main(String[] args) {
        Data2 data2 = new Data2();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.increment();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.decrement();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.increment();

            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data2.decrement();
            }
        }, "D").start();
    }
}

class Data2 {
    private int num = 0;

    Lock lock = new ReentrantLock();
    // 对象监视器
    Condition condition = lock.newCondition();

    // +1
    public void increment() {
        lock.lock();
        try {
            while (num != 0) {
                // 判断等待
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            // 通知其他线程我加一完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    // -1
    public void decrement() {
        lock.lock();
        try {
            while (num == 0) {
                // 判断等待
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "=>" + num);
            // 通知其他线程我减一完毕了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
