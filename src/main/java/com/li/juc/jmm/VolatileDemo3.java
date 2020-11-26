package com.li.juc.jmm;

/**
 * @author : LiWenBo
 * @program : juc
 * @description : volatile指令重排
 * @date : 2020-11-26 20:54:46
 */
public class VolatileDemo3 {
    public static void main(String[] args) {
        // 源代码 -> 编译器的优化重排 -> 指令并行 也可能会重排 -> 内存系统也可能会重排 -> 执行
        // 指令重排的时候会考虑数据之间的依赖性
        // volatile可以避免指令重排
        // 内存屏障
        /*
        * 保证特定操作的执行顺序
        * 保证某些变量的内存可见性(volatile保证了可见性)
        * */
        // volatile可以保持可见性。不能保证原子性，由于内存屏障，可以保证避免指令重排现象的产生。

    }
}
