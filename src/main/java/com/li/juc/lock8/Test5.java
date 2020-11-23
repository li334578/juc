package com.li.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 关于锁的八个问题
 * @date : 2020-11-23 21:58:55
 */
public class Test5 {
    /*
     * 6.两个静态方法 先执行发短信还是打电话？
     *
     * */
    public static void main(String[] args) {
        // 静态方法的时候锁的class
        Phone3 phone31 = new Phone3();
        Phone3 phone32 = new Phone3();
        new Thread(() -> phone31.sendSms(), "A").start();
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone32.call(), "B").start();
    }
}