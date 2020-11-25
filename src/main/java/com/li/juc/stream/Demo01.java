package com.li.juc.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : stream流
 * @date : 2020-11-25 20:51:09
 */
public class Demo01 {
    public static void main(String[] args) {
        User user1 = new User(1, "a", 21);
        User user2 = new User(2, "b", 22);
        User user3 = new User(3, "c", 23);
        User user4 = new User(4, "d", 24);
        User user5 = new User(6, "e", 25);
        List<User> users = Arrays.asList(user1, user2, user3, user3, user4, user5);
        // 计算
        users.stream()
                .filter(user -> user.getId() % 2 == 0 && user.getAge() > 23)
                .map(user -> user.getName().toUpperCase())
                .sorted((u1, u2) -> u2.compareTo(u1))
                .limit(1)
                .forEach(System.out::println);
    }
}
