package com.li.juc.mylock;

/**
 * @author : LiWenBo
 * @program : juc
 * @description :
 * @date : 2020-11-23 20:34:39
 */
public class SaleTicketDemo01 {
    // 线程就是一个单独的资源类没有任何的附属操作
    // 1.属性、方法
    public static void main(String[] args) {
        // 多个线程操作同一个资源类
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }
        }, "C").start();

    }


}

// 资源类OOP
class Ticket {
    // 初始票数
    private int numbers = 20;

    public synchronized void sale() {
        if (numbers > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了第" + (numbers--) + "张票,剩余票数" + numbers);
        } else {
            System.out.println(Thread.currentThread().getName() + "票卖完了");
        }
    }
}