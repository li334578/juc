package com.li.juc.function;

import java.util.function.Function;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 函数式接口
 * @date : 2020-11-25 20:33:44
 */
public class Demo01 {
    /*
     * 一个输入参数 一个输出参数
     * 只要是函数式接口 就可以用lambda表达式简化
     * */
    public static void main(String[] args) {
        // 工具类
        Function<String, String> function = new Function<String, String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };
        Function<String, String> function2 = (str) -> str;
        System.out.println(function.apply("hhh"));
        System.out.println(function2.apply("iiii"));
    }
}
