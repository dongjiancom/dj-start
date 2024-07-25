package dj.com;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * https://www.cnblogs.com/calvinit/p/17730501.html
 *
 * @author jiandong 2024-07-16 create
 */
public class Main2 {

    public static final long SLEEP_DEFAULT_MILLIS = 1000l;

    static TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

    /**
     * 1. Thread.ofVirtual() 创建和运行虚拟线程
     *    Thread.startVirtualThread(task) 可以快捷地创建并启动虚拟线程
     *    它与 Thread.ofVirtual().start(task) 是等价的
     */
    @SneakyThrows
    @Test
    public void t1(){
        Console.log("method start");

        context.set("main-dj-value");
        Thread thread = Thread.ofVirtual().start(() -> taskDoSomething());
        thread.join();			// 等待虚拟线程终止

        Console.log("主线程打印, context.get() = " + context.get());

        Console.log("method end");
    }

    /**
     * 2.   Thread.Builder 创建和运行虚拟线程
     *      Thread.Builder 接口允许我们创建具有通用的线程属性（例如线程名称）的线程，
     *          Thread.Builder.OfPlatform 子接口创建平台线程，
     *          Thread.Builder.OfVirtual 子接口则创建虚拟线程。
     */
    @SneakyThrows
    @Test
    public void t2(){
        Console.log("method start");

        Thread.Builder builder = Thread.ofVirtual().name("DJ's Thread");		// 虚拟线程的名称是 MyThread
        Runnable task = () -> taskDoSomething();
        Thread t = builder.start(task);
        Console.log("Thread t name: " + t.getName());                    // 控制台打印：Thread t name: MyThread
        t.join();

        Console.log("method end");
    }

    /**
     * 创建了 2 个虚拟线程，
     *      名称分别是 worker-0 和 worker-1（这个是由 name() 中的两个参数 prefix 和 start 指定的）：
     */
    @SneakyThrows
    @Test
    public void t2_2(){
        Console.log("method start");

        Thread.Builder builder = Thread.ofVirtual().name("worker-", 0);
        Runnable task = () -> Console.log("Thread ID: " + Thread.currentThread().threadId()+";Thread name: " + Thread.currentThread().getName());

        // 虚拟线程 1，名称为 worker-0
        Thread t1 = builder.start(task);
        Thread t2 = builder.start(task);

        t1.join();
        Console.log(t1.getName() + " terminated");

        // 虚拟线程 2，名称为 worker-1
        t2.join();
        Console.log(t2.getName() + " terminated");

        Console.log("method end");
    }

    /**
     * 3. Executors.newVirtualThreadPerTaskExecutor() 创建和运行虚拟线程
     *      Executor 允许我们将 线程 管理和创建 与 应用程序 的其余部分分开
     */
    @SneakyThrows
    @Test
    public void t3(){
        Console.log("method start");

        // Java 21 中 ExecutorService 接口继承了 AutoCloseable 接口，
        // 所以可以使用 try-with-resources 语法使 Executor 在最后被自动地 close()
        try (ExecutorService myExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            // 每次 submit() 调用向 Executor 提交任务时都会创建和启动一个新的虚拟线程
            Future<?> future = myExecutor.submit(() -> taskDoSomething());
            future.get();		// 等待线程任务执行完成
            Console.log("Task completed");
        } catch (ExecutionException | InterruptedException ignore) {
            ignore.printStackTrace();
        }

        Console.log("method end");
    }

    /**
     * CompletableFuture 应当如何适应虚拟线程？
     *      在有虚拟线程后，其实改动非常少，只需要将平台线程池的 executor 替换为虚拟线程的 executor
     *      在有虚拟线程以前，它一个惯常的使用方法如下：
     */
    @SneakyThrows
    @Test
    public void t5_1(){
        Console.log("method start");

        long startMills = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(256);
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        IntStream.range(0, 10000).forEach(i -> {
            // 如果 runAsync 不指定 Executor，则会使用默认的线程池（除非系统不支持并行，否则会使用一个通用的 ForkJoinPool.commonPool 线程池）
            CompletableFuture<Void> f = CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException ignore) {
                    Thread.currentThread().interrupt();
                }
            }, executor);
            futures.add(f);
        });
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executor.shutdown();
        Console.log("【线程池】任务执行时间：" + (System.currentTimeMillis() - startMills) / 1000 + " 秒！");

        Console.log("method end");
    }

    /**
     * CompletableFuture 应当如何适应虚拟线程？
     *      在有虚拟线程后，其实改动非常少，只需要将平台线程池的 executor 替换为虚拟线程的 executor
     */
    @SneakyThrows
    @Test
    public void t5_2(){
        Console.log("method start");

        long startMills = System.currentTimeMillis();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10000).forEach(i -> {
                // 如果 runAsync 不指定 Executor，则会使用默认的线程池（除非系统不支持并行，否则会使用一个通用的 ForkJoinPool.commonPool 线程池）
                CompletableFuture<Void> f = CompletableFuture.runAsync(() -> {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException ignore) {
                        Thread.currentThread().interrupt();
                    }
                }, executor);
                futures.add(f);
            });
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        }
        Console.log("【虚拟线程】任务执行时间：" + (System.currentTimeMillis() - startMills) / 1000 + " 秒！");


        Console.log("method end");
    }

    /**
     * 线程sleep一下，打印一下
     */
    private static void taskDoSomething() {
        Console.log("Running 子线程 start");
        Console.log("sleep" + SLEEP_DEFAULT_MILLIS + " ms");

        Console.log("子线程，打印：context.get() = " + context.get());
        Console.log("子线程  context.set(\"sub-dj-value\")");
        context.set("sub-dj-value");

        ThreadUtil.sleep(SLEEP_DEFAULT_MILLIS);
        Console.log("Running 子线程 end");
    }
}
