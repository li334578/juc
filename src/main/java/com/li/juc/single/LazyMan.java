package com.li.juc.single;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : 懒汉式
 * @date : 2020-11-26 21:10:15
 */
public class LazyMan {

    private volatile static LazyMan LAZYMAN;
    private static Boolean flag = false;

    private LazyMan() {
        /**
         * 解决反射破坏单例
         */
        synchronized (LazyMan.class) {
            // 加flag标志位 防止两次反射创建对象
            if (!flag) {
                flag = true;
            }else {
                throw new RuntimeException("不要试图用反射破坏单例");
            }
//            if (LAZYMAN != null) {
//                throw new RuntimeException("不要试图用反射破坏单例");
//            }
        }
        System.out.println(Thread.currentThread().getName());
    }

    // 双重检测锁模式的懒汉式单例 简称DCL懒汉式
    public static LazyMan getInstance() {
        if (LAZYMAN == null) {
            synchronized (LazyMan.class) {
                if (LAZYMAN == null) {
                    LAZYMAN = new LazyMan(); // 不是原子行操作
                    /*
                     * 1.分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把对象指向内存空间
                     *
                     * 指令重排会出现问题
                     * 123
                     * 132 A
                     *
                     * B
                     * 此时LazyMan还没有完成构造
                     *
                     * volatile 避免指令重排解决问题
                     * */
                }
            }
        }

        return LAZYMAN;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazyMan.getInstance();
            }).start();
        }
    }
}
