package dj.com;

import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 廖雪峰：
 *      https://www.liaoxuefeng.com/wiki/1252599548343744/1501390373388322
 * 由于虚拟线程属于非常轻量级的资源，因此，用时创建，用完就扔，不要池化虚拟线程。
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("main start");
        // 传入Runnable实例并立刻运行:
        Thread vt = Thread.startVirtualThread(() -> {
            System.out.println("Start virtual thread...");
            ThreadUtil.sleep(1000);
            System.out.println("End virtual thread.");
        });
        ThreadUtil.sleep(2000);
        System.out.println("main end");
    }

    /**
     * 方法一：直接创建虚拟线程并运行
     */
    @SneakyThrows
    @Test
    public void m1(){
        // 传入Runnable实例并立刻运行:
        Thread vt = Thread.startVirtualThread(() -> {
            System.out.println("Start virtual thread...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("End virtual thread.");
        });
        ThreadUtil.getMainThread().join();
        System.out.println("main thread over");
    }

    /**
     * 方法二：创建虚拟线程但不自动运行，而是手动调用start()开始运行：
     */
    @Test
    public void m2() throws InterruptedException {
        System.out.println("method start");
        // 创建VirtualThread:
        Thread vt = Thread.ofVirtual().name("dj-").unstarted(() -> {
            System.out.println("Start virtual thread...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("End virtual thread.");
        });
        // 运行:
        vt.start();
        ThreadUtil.getMainThread().join();
        System.out.println("main thread over");
    }

    /**
     * 方法三：通过虚拟线程的ThreadFactory创建虚拟线程，然后手动调用start()开始运行：
     */
    @Test
    public void m3(){
        System.out.println("method start");
        // 创建ThreadFactory:
        ThreadFactory tf = Thread.ofVirtual().factory();
        // 创建VirtualThread:
        Thread vt = tf.newThread(() -> {
            System.out.println("Start virtual thread...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("End virtual thread.");
        });
        // 运行:
        vt.start();
        System.out.println("method end");
    }

    /**
     * 直接调用start()实际上是由ForkJoinPool的线程来调度的。我们也可以自己创建调度线程，然后运行虚拟线程：
     */
    @Test
    public void m4(){
        System.out.println("method start");

        // 创建调度器:
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        // 创建大量虚拟线程并调度:
        ThreadFactory tf = Thread.ofVirtual().factory();
        for (int i=0; i<100000; i++) {
            Thread vt = tf.newThread(() -> {});
            executor.submit(vt);
            // 也可以直接传入Runnable或Callable:
            executor.submit(() -> {
                System.out.println("Start virtual thread...");
                Thread.sleep(1000);
                System.out.println("End virtual thread.");
                return true;
            });
        }

        System.out.println("method end");
    }

}