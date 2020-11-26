package com.li.juc.single;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 饿汉式单例
 * @date : 2020-11-26 21:07:14
 */
public class Hungry {

    private final static Hungry HUNGRY = new Hungry();

    // 可能会浪费空间
    private byte[] data1 = new byte[1024 * 1024];
    private byte[] data2 = new byte[1024 * 1024];
    private byte[] data3 = new byte[1024 * 1024];
    private byte[] data4 = new byte[1024 * 1024];


    private Hungry() {

    }

    public static Hungry getInstance() {
        return HUNGRY;
    }

    public static void main(String[] args) {

    }
}
