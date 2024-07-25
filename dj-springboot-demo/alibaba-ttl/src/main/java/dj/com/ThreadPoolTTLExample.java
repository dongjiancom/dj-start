package dj.com;

import cn.hutool.core.lang.Console;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @author jiandong 2024-07-24 create
 */

   public class ThreadPoolTTLExample {
    private static  final TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));

        threadLocal.set("Parent");

        executorService.submit(() -> {
            Console.log("Child Thread 1 initial value: " + threadLocal.get());
            threadLocal.set("Child Thread 1");
            Console.log("Child Thread 1 updated value: " + threadLocal.get());
        });

        executorService.submit(() -> {
            Console.log("Child Thread 2 initial value: " + threadLocal.get());
            threadLocal.set("Child Thread 2");
            Console.log("Child Thread 2 updated value: " + threadLocal.get());
        });

        executorService.shutdown();
    }
}

