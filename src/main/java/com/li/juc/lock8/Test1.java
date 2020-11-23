package com.li.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 关于锁的八个问题
 * @date : 2020-11-23 21:58:55
 */
public class Test1 {
    /*
     * 1.标准情况下先发短信还是先打电话？
     * 2.发短信方法sleep4秒后 先发短信还是先打电话？
     * */
    public static void main(String[] args) {
        Phone1 phone = new Phone1();

        new Thread(() -> phone.sendSms(), "A").start();
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone.call(), "B").start();
    }
}

class Phone1 {

    // synchronized 锁的是调用的对象
    // 两个方法使用的是同一把锁 谁先拿到谁执行
    public synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }


    public synchronized void call() {
        System.out.println("call");
    }
}