package com.li.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 关于锁的八个问题
 * @date : 2020-11-23 21:58:55
 */
public class Test2 {
    /*
     * 3.增加了一个hello方法，先执行发短信还是hello
     *
     * */
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(() -> phone.sendSms(), "A").start();
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> phone.hello(), "B").start();
    }
}

class Phone2 {

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

    // 没有锁 不是同步方法 不受锁的影响
    public void hello() {
        System.out.println("hello");
    }
}