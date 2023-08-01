package thread;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * @description:
 *     廖雪峰： https://www.liaoxuefeng.com/wiki/1252599548343744/1306581155184674
 *
 * @author: Mr.DJ
 * @createTime: 2022-09-15 15:41
 **/
public class Future_TEST {

    /**
     * 并且Callable接口是一个泛型接口，可以返回指定类型的结果。
     * 现在的问题是，如何获得异步执行的结果？
     * 如果仔细看ExecutorService.submit()方法，可以看到，它返回了一个Future类型，一个Future类型的实例代表一个未来能获取结果的对象
     *
     * future.
     * get()：获取结果（可能会等待）
     * get(long timeout, TimeUnit unit)：获取结果，但只等待指定的时间；
     * cancel(boolean mayInterruptIfRunning)：取消当前任务；
     * isDone()：判断任务是否已完成。
     *
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {
//        ExecutorService executor = Executors.newFixedThreadPool(4);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        // 定义任务:
        Callable<String> task = new Task();
        // 提交任务并获得Future:
        Future<String> future = threadPoolExecutor.submit(task);
        // 从Future获取异步执行返回的结果:
        String result = future.get(); // 可能阻塞

        System.out.println(result);

        threadPoolExecutor.shutdown();

    }

    /**
     * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors返回的线程池对象的弊端如下：
     * 1）FixedThreadPool和SingleThreadPool:
     *   允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
     * 2）CachedThreadPool:
     *   允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
     *
     * Positive example 1：
     *     //org.apache.commons.lang3.concurrent.BasicThreadFactory
     *     ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
     *         new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
     *
     *
     *
     * Positive example 2：
     *     ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
     *         .setNameFormat("demo-pool-%d").build();
     *
     *     //Common Thread Pool
     *     ExecutorService pool = new ThreadPoolExecutor(5, 200,
     *         0L, TimeUnit.MILLISECONDS,
     *         new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
     *
     *     pool.execute(()-> System.out.println(Thread.currentThread().getName()));
     *     pool.shutdown();//gracefully shutdown
     *
     *
     *
     * Positive example 3：
     *     <bean id="userThreadPool"
     *         class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
     *         <property name="corePoolSize" value="10" />
     *         <property name="maxPoolSize" value="100" />
     *         <property name="queueCapacity" value="2000" />
     *
     *     <property name="threadFactory" value= threadFactory />
     *         <property name="rejectedExecutionHandler">
     *             <ref local="rejectedExecutionHandler" />
     *         </property>
     *     </bean>
     *     //in code
     *     userThreadPool.execute(thread);
     *
     *
     *
     *
     */

}
