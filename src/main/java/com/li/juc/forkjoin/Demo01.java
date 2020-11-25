package com.li.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author : LiWenBo
 * @program : juc
 * @description :
 * @date : 2020-11-25 21:09:46
 */
public class Demo01 extends RecursiveTask<Long> {
    /**
     * 求和计算
     */
    // 起始值
    private Long start;
    // 结束值
    private Long end;
    // 临界值
    private Long temp = 10000L;

    public Demo01(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    // 计算方法
    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            // 分支合并计算
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2; // 中间值
            Demo01 demo01 = new Demo01(start, middle);
            // 把任务压入线程队列
            demo01.fork();
            Demo01 demo02 = new Demo01(middle + 1, end);
            // 把任务压入线程队列
            demo02.fork();
            return demo01.join() + demo02.join();
        }
    }
}
