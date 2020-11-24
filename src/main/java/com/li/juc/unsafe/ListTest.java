package com.li.juc.unsafe;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : list
 * @date : 2020-11-24 20:33:16
 */
public class ListTest {
    // java.util.ConcurrentModificationException 并发修改异常
    /*
     * 解决方案
     * 1. vector List<String> list = new Vector<>();
     * 2. List<String> list = Collections.synchronizedList(new ArrayList<>());
     * 3. List<String> list = new CopyOnWriteArrayList<>();
     *
     * */
    // CopyOnWriteArrayList 写入时复制 COW 计算机领域程序的优化策略
    // 多个线程调用list 读取的时候读取同一个 写入的时候存在覆盖操作
    // 在写入的时候避免覆盖造成的数据问题
    // CopyOnWriteArrayList (lock) 比 Vector (synchronized) 强在哪里
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
