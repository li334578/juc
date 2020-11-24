package com.li.juc.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : ReadWriteLock
 * @date : 2020-11-24 21:43:09
 */
public class ReadWriteLockDemo {
    /*
     * ReadWriteLock
     * 可以同时读 不能同时写
     * 独占锁 写锁 一次只能被一个线程占有
     * 共享锁 读锁 可以被多个线程同时使用
     * */
    public static void main(String[] args) {
//        MyCache myCache = new MyCache();
        MyCacheLock myCache = new MyCacheLock();
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "0", temp + "1");
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp + "0");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    // 存
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "存入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "存入ok");
    }

    // 取
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "取出" + key);
        Object object = map.get(key);
        System.out.println(Thread.currentThread().getName() + "取出ok");
    }
}


class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    // 存
    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "存入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "存入ok");
        } catch (Exception e) {
        } finally {
            lock.writeLock().unlock();
        }
    }

    // 取
    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "取出" + key);
            Object object = map.get(key);
            System.out.println(Thread.currentThread().getName() + "取出ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }

    }
}