package com.li.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 关于锁的八个问题
 * @date : 2020-11-23 21:58:55
 */
public class Test4 {
    /*
     * 5.两个静态方法 先执行发短信还是打电话？
     *
     * */
    public static void main(String[] args) {
        Phone3 phone = new Phone3();
        new Thread(() -> phone.sendSms(), "A").start();
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone.call(), "B").start();
    }
}


class Phone3 {

    // synchronized 锁的是调用的对象
    // 两个方法使用的是同一把锁 谁先拿到谁执行
    // 类一加载static就有了 class模板 锁的是class
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }


    public static synchronized void call() {
        System.out.println("call");
    }
}