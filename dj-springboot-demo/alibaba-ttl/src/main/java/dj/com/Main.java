package dj.com;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.SneakyThrows;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * hollis:
 *  https://thoughts.aliyun.com/workspaces/6655879cf459b7001ba42f1b/docs/6673eca62d397600011bc880
 * github:
 *  https://github.com/alibaba/transmittable-thread-local/blob/master/ttl-core/src/test/java/com/alibaba/demo/ttl3/TtlWrapperDemo.kt
 * api:
 *  https://alibaba.github.io/transmittable-thread-local/apidocs/2.14.5/index.html
 */
public class Main implements Runnable{

    static TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

    /**
     * 1. 简单使用
     *      父线程给子线程传递值。
     */
    @SneakyThrows
    @Test
    public void t1() {
        // 在父线程中设置
        context.set("value-set-in-parent");

        ExecutorService executorService = ThreadUtil.newExecutor(2);
        executorService.submit(this);

        // 在子线程中可以读取，值是"value-set-in-parent"
        ThreadUtil.getMainThread().join();
    }

    /**
     * 2.保证线程池中传递值
     *  2.1 修饰Runnable和Callable；使用TtlRunnable和TtlCallable来修饰传入线程池的Runnable和Callable。
     */
    @Test
    public void t2_1() {

    }

    @Override
    public void run() {
        Console.log("run - start");
        System.out.println("run" + ThreadUtil.getMainThread().getId());
        System.out.println(context.get());
        ThreadUtil.sleep(1000l);
        Console.log("run - stop");
    }
}