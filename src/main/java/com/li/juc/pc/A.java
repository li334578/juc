package com.li.juc.pc;

/**
 * @author : LiWenBo
 * @program : juc
 * @description :
 * @date : 2020-11-23 21:13:10
 */
/*
 * 线程之间的通讯问题 生产者和消费者问题
 * AB线程交替操作num num = 0
 * A： num+1
 * B： num-1
 * 等待唤醒 业务 通知唤醒
 * */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}

class Data {
    private int num = 0;

    // +1
    public synchronized void increment() throws InterruptedException {
        while (num != 0) {
            // 判断等待
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "=>" + num);
        // 通知其他线程我加一完毕了
        this.notifyAll();
    }

    // -1
    public synchronized void decrement() throws InterruptedException {
        while (num == 0) {
            // 判断等待
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "=>" + num);
        // 通知其他线程我减一完毕了
        this.notifyAll();
    }
}
/*
* 四个线程操作的时候会出现虚假唤醒
* if -> while
* */