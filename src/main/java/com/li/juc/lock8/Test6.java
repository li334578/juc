package com.li.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 关于锁的八个问题
 * @date : 2020-11-23 21:58:55
 */
public class Test6 {
    /*
     * 7.一个对象 一个静态同步方法一个普通同步方法 先执行发短信还是打电话？
     *
     * */
    public static void main(String[] args) {
        // 静态方法的时候锁的class
        // 两把锁 sendSms锁的class
        // call 锁的是对象
        Phone4 phone = new Phone4();
        new Thread(() -> phone.sendSms(), "A").start();
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone.call(), "B").start();
    }
}


class Phone4 {

    // 静态同步方法
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendSms");
    }


    // 普通同步方法
    public synchronized void call() {
        System.out.println("call");
    }
}