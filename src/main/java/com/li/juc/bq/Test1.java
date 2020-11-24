package com.li.juc.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : ArrayBlockingQueue 四组API
 * @date : 2020-11-24 22:01:58
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        // Collection
        // list
        // set
//        test1();
//        test2();
//        test3();
//        test4();
    }

    public static void test1() {
        // 抛出异常
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.add("A"));
        System.out.println(arrayBlockingQueue.add("B"));
        System.out.println(arrayBlockingQueue.add("C"));
        // java.lang.IllegalStateException: Queue full 队列满 抛出异常
//        System.out.println(arrayBlockingQueue.add("D"));
        // 查看队首元素
        System.out.println(arrayBlockingQueue.element());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        // java.util.NoSuchElementException 队列为空
//        System.out.println(arrayBlockingQueue.remove());
    }

    public static void test2() {
        // 有返回值 不抛出异常
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("A"));
        System.out.println(arrayBlockingQueue.offer("B"));
        System.out.println(arrayBlockingQueue.offer("C"));
        // false
        System.out.println(arrayBlockingQueue.offer("D"));

        // 查看队首元素
        System.out.println(arrayBlockingQueue.peek());

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        // null
        System.out.println(arrayBlockingQueue.poll());
    }

    public static void test3() throws InterruptedException {
        // 等待阻塞
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        arrayBlockingQueue.put("A");
        arrayBlockingQueue.put("B");
        arrayBlockingQueue.put("C");
        arrayBlockingQueue.put("D"); // 队列满 阻塞等待 一直等
        arrayBlockingQueue.take();
        arrayBlockingQueue.take();
        arrayBlockingQueue.take();
        arrayBlockingQueue.take(); // 队列空 阻塞的等待 一直等
    }

    public static void test4() throws InterruptedException {
        // 等待阻塞
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        arrayBlockingQueue.offer("A", 2, TimeUnit.SECONDS);
        arrayBlockingQueue.offer("B", 2, TimeUnit.SECONDS);
        arrayBlockingQueue.offer("C", 2, TimeUnit.SECONDS);
        arrayBlockingQueue.offer("D", 2, TimeUnit.SECONDS); // 队列满 超时等待2s 退出

        arrayBlockingQueue.poll(2, TimeUnit.SECONDS);
        arrayBlockingQueue.poll(2, TimeUnit.SECONDS);
        arrayBlockingQueue.poll(2, TimeUnit.SECONDS);
        arrayBlockingQueue.poll(2, TimeUnit.SECONDS); // 队列空 超时等待2s 退出
    }
}
