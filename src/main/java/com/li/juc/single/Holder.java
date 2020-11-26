package com.li.juc.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 静态内部类实现
 * @date : 2020-11-26 21:16:54
 */
public class Holder {

    private Holder() {

    }

    public static Holder getInstance() {
        return InnerClass.HOLDER;
    }

    public static class InnerClass {
        private static final Holder HOLDER = new Holder();

    }

    // 反射！
    public static void main(String[] args) throws Exception {
//        LazyMan instance = LazyMan.getInstance();

        // 再次通过反射获取标志位信息
        Field flag = LazyMan.class.getDeclaredField("flag");
        flag.setAccessible(true);

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyMan instance = declaredConstructor.newInstance();

        // 修改标志位信息 破坏单例模式
        flag.set(instance, false);

        LazyMan instance2 = declaredConstructor.newInstance();
        System.out.println(instance);
        System.out.println(instance2);
        // 反射破坏private
    }
}
