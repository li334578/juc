package com.li.juc.function;

import java.util.function.Predicate;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 断定型接口 又一个输入参数返回值只能是boolean值
 * @date : 2020-11-25 20:39:00
 */
public class Demo02 {
    public static void main(String[] args) {
        // 判断字符串是否为空
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };
        Predicate<String> predicate2 = (s) -> s.isEmpty();
        System.out.println(predicate.test("s"));
        System.out.println(predicate2.test(""));
    }
}
