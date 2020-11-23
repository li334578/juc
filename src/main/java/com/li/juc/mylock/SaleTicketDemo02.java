package com.li.juc.mylock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : LiWenBo
 * @program : juc
 * @description :
 * @date : 2020-11-23 20:34:39
 */
public class SaleTicketDemo02 {
    // 线程就是一个单独的资源类没有任何的附属操作
    // 1.属性、方法
    public static void main(String[] args) {
        // 多个线程操作同一个资源类
        Ticket2 ticket = new Ticket2();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) ticket.sale();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) ticket.sale();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) ticket.sale();
        }, "C").start();

    }


}

// 资源类OOP

/**
 * lock三部曲
 * 1. 创建锁 Lock lock = new ReentrantLock();
 * 2. 加锁 lock.lock();
 * 3. 释放锁 finally lock.unlock();
 */
class Ticket2 {
    // 初始票数
    private int numbers = 20;
    // 创建锁 // 可重入锁 默认非公平锁
    Lock lock = new ReentrantLock();

    // FairSync 公平锁 按照顺序来
    // NonfairSync 非公平锁 可插队
    public void sale() {
        // 加锁
        lock.lock();
        try {
            if (numbers > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + (numbers--) + "张票,剩余票数" + numbers);
            } else {
                System.out.println(Thread.currentThread().getName() + "票卖完了");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放锁

            lock.unlock();
        }

    }
    /*
    * 1、Synchronized 内置的Java关键字， Lock 是一个Java类
    * 2、Synchronized 无法判断获取锁的状态，Lock 可以判断是否获取到了锁
    * 3、Synchronized 会自动释放锁，lock 必须要手动释放锁！如果不释放锁，死锁
    * 4、Synchronized 线程 1（获得锁，阻塞）、线程2（等待，傻傻的等）；Lock锁就不一定会等待下去；
    * 5、Synchronized 可重入锁，不可以中断的，非公平；Lock ，可重入锁，可以 判断锁，非公平（可以自己设置）；
    * 6、Synchronized 适合锁少量的代码同步问题，Lock 适合锁大量的同步代码！
    * lock.tryLock() 尝试获取锁
    * 锁是什么，如何判断锁的是谁！
    *
    * */
}