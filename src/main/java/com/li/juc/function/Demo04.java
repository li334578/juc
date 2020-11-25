package com.li.juc.function;

import java.util.function.Supplier;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 供给型接口 只有返回值没有输入
 * @date : 2020-11-25 20:46:34
 */
public class Demo04 {
    public static void main(String[] args) {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "hhhhh";
            }
        };
        Supplier<String> supplier2 = () -> {
            return "12346";
        };
        System.out.println(supplier.get());
        System.out.println(supplier2.get());
    }
}
