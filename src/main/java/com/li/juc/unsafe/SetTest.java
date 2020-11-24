package com.li.juc.unsafe;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : set
 * @date : 2020-11-24 20:52:19
 */
public class SetTest {
    // java.util.ConcurrentModificationException 并发修改异常
    /*
     * new HashSet<>() 底层就是HashMap set 就是 map的put
     * 1.Set<String> set = Collections.synchronizedSet(new HashSet<>());
     * 2.Set<String> set = new CopyOnWriteArraySet<>();
     * */
    public static void main(String[] args) {
//        Set<String> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
