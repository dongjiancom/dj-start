package thread;

import java.util.concurrent.*;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2021-01-12 20:54
 **/
public class TTest {

    public static void main(String[] args) {
        /**
         * 速度快
         *
         * 互联网，不用这个的原因：
         *  多的话 建立很多线程 —— CPU占用，100%
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        /**
         * 速度慢
         *
         * 互联网，不用这个的原因：
         *
         *  多的话 任务放在队列里 —— 内存溢出
         */
        ExecutorService executorService1 = Executors.newFixedThreadPool(10);
        //速度最慢 一个一个执行
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        /**
         * 以上三种模式都是用的ThreadPoolExecutor
         * SO互联网用 ThreadPoolExecutor，自定义参数
         * 预估；*2到3倍
         *
         * 老师说的重点：线程池 为什么demo在后面打印输出。
         * 提交优先级
         * 执行优先级
         *
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

    }
}
