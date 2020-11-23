package com.li.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 关于锁的八个问题
 * @date : 2020-11-23 21:58:55
 */
public class Test7 {
    /*
     * 8.两个对象 一个静态同步方法一个普通同步方法 先执行发短信还是打电话？
     *
     * */
    public static void main(String[] args) {
        // 静态方法的时候锁的class
        // 两把锁 sendSms锁的class
        // call 锁的是对象
        Phone4 phone41 = new Phone4();
        Phone4 phone42 = new Phone4();
        new Thread(() -> phone41.sendSms(), "A").start();
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone42.call(), "B").start();
    }
}