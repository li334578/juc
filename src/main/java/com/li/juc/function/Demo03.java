package com.li.juc.function;

import java.util.function.Consumer;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 消费型接口 只有输入没有返回值
 * @date : 2020-11-25 20:42:52
 */
public class Demo03 {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Consumer<String> consumer2 = System.out::println;
        consumer.accept("1213");
        consumer2.accept("33333");
    }
}
