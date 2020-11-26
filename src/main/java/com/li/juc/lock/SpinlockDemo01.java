package com.li.juc.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 自选锁
 * @date : 2020-11-26 22:31:57
 */
public class SpinlockDemo01 {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void Mylock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+":Mylock");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void MyUnlock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+":MyUnlock");
        atomicReference.compareAndSet(thread,null);
    }
}
