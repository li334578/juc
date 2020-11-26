package com.li.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author : LiWenBo
 * @program : juc
 * @description :
 * @date : 2020-11-26 22:36:27
 */
public class SpinlockTest {
    public static void main(String[] args) {
        // 自旋锁
        SpinlockDemo01 spinlockDemo01 = new SpinlockDemo01();

        new Thread(()->{
            spinlockDemo01.Mylock();
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){

            }finally {
                spinlockDemo01.MyUnlock();
            }
        },"A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){

        }finally {
        }

        new Thread(()->{
            spinlockDemo01.Mylock();
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){

            }finally {
                spinlockDemo01.MyUnlock();
            }
        },"B").start();

//        spinlockDemo01.Mylock();
//        spinlockDemo01.MyUnlock();
    }
}
