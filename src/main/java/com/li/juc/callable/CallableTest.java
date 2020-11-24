package com.li.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : Callable
 * @date : 2020-11-24 21:10:29
 */
public class CallableTest {
    public static void main(String[] args) {
        FutureTask futureTask = new FutureTask(new MyThread());
        new Thread(futureTask, "A").start();
        try {
            String string = (String) futureTask.get();
            // 结果会被缓存 效率高
            // 可能会导致阻塞 1.放到最后 2.异步通信
            System.out.println("返回值" + string);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + ":call()");
        return "1024";
    }
}
