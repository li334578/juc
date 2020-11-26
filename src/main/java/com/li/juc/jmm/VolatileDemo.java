package com.li.juc.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : volatile 可见性
 * @date : 2020-11-26 20:33:34
 */
public class VolatileDemo {

    private volatile static int num = 0;
    // 不加volatile程序不会停止 保证可见性
    public static void main(String[] args) { // main线程
        // 需要线程A知道主线程中的值的变化
        new Thread(()->{
            while (num==0){
            }
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1L);
        }catch (Exception e){

        }

        num = 1;
        System.out.println(num);
    }
}
