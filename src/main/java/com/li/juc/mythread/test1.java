package com.li.juc.mythread;

/**
 * @author : LiWenBo
 * @program : juc
 * @description :
 * @date : 2020-11-22 21:20:03
 */
public class test1 {
    public static void main(String[] args) {
        // 获取cpu核心数
        // CPU密集型 IO密集型
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
