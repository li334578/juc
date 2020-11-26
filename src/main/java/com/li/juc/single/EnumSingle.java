package com.li.juc.single;

import java.lang.reflect.Constructor;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 枚举
 * @date : 2020-11-26 21:31:46
 */
/*
 * 枚举是什么
 * 本身也是一个class类
 */


public enum EnumSingle {

    INSTANCE;

    public EnumSingle getInstance() {
        return INSTANCE;
    }
}


class Test {
    public static void main(String[] args) throws Exception {
        EnumSingle enumSingle1 = EnumSingle.INSTANCE;
        EnumSingle enumSingle2 = EnumSingle.INSTANCE;
        System.out.println(enumSingle1);
        System.out.println(enumSingle2);
        // java.lang.NoSuchMethodException: com.li.juc.single.EnumSingle.<init>()
//        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(null);
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle enumSingle3 = declaredConstructor.newInstance();
        // Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects
        System.out.println(enumSingle1 == enumSingle3);
        // 反射不能破坏枚举
    }
}