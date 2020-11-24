package com.li.juc.unsafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : map
 * @date : 2020-11-24 21:00:21
 */
public class MapTest {
    // java.util.ConcurrentModificationException 并发修改异常
    /*
    * 1.Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
    * 2.Map<String, String> map = new ConcurrentHashMap<>();
    *
     */
    public static void main(String[] args) {
        // map是这样用的吗 不是 工作中不用这个
        // 底层等价于什么 new HashMap<>(16,0.75);
//        Map<String, String> map = new HashMap<>();
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map = new ConcurrentHashMap<>();
        // 加载因子和初始容量

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
