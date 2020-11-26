package com.li.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : CAS
 * @date : 2020-11-26 21:42:32
 */
public class CASDemo01 {
    /*
     * CAS compareAndSet : 比较并交换
     * 比较当前工作内存中的值，如果这个值是期望的，那么则执行操作。如果不是 do while 自旋锁 一直循环
     * 缺点 1. 循环会耗时 2. 一次性只能保证一个共享变量的原子性 3. ABA问题
     * CPU的并发原语
     * Java无法操作内存 Java可以调用C++来操作内存
     * native
     * unsafe 可以通过这个类操作内存
     * */
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1024);
        // 期望 更新
        // public final boolean compareAndSet(int expect, int update)
        // 如果我期望值达到了就更新 否则不更新
        System.out.println(atomicInteger.compareAndSet(1024, 2020));
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.compareAndSet(1024, 2020));
        System.out.println(atomicInteger.getAndIncrement());
        /*
         * ABA问题
         * */
        AtomicInteger atomicInteger2 = new AtomicInteger(100);
        // ==============捣乱的线程====================
        System.out.println(atomicInteger2.compareAndSet(100, 200));
        System.out.println(atomicInteger2.get());
        System.out.println(atomicInteger2.compareAndSet(200, 100));
        System.out.println(atomicInteger2.get());
        // =================期望的线程============================
        System.out.println(atomicInteger2.compareAndSet(100, 6666));
        System.out.println(atomicInteger2.get());
        /*
         * 对于平时写的SQL ： 乐观锁
         * */
        System.out.println("---------------------------------------------------------------");
        // Integer 如果泛型是包装类，注意对象的引用问题
        /*
         * 解决ABA问题
         * */
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

        new Thread(() -> {
            // 获得版本号
            System.out.println(Thread.currentThread().getName()
                    + "线程第一次拿到的stamp="
                    + atomicStampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(
                    100,
                    50,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName()
                    + "线程第二次拿到的stamp="
                    + atomicStampedReference.getStamp());

            System.out.println(atomicStampedReference.compareAndSet(
                    50,
                    100,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName()
                    + "线程第三次拿到的stamp="
                    + atomicStampedReference.getStamp());
        }, "A").start();


        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()
                    + "线程第一次拿到的stamp="
                    + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(
                    100,
                    50,
                    stamp,
                    atomicStampedReference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName()
                    + "线程第二次拿到的stamp="
                    + atomicStampedReference.getStamp());
        }, "B").start();

    }

}
