package dj.com.java21virtual.service;

import cn.hutool.core.lang.Console;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CountDownLatch;

/**
 * https://juejin.cn/post/7266745788536799247
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AsyncServiceTest {

    /**
     * webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT 使用本地的一个随机端口启动服务；
     * @LocalServerPort 相当于 @Value("${local.server.port}")；
     */
    @LocalServerPort
    private int port;

    @Resource
    private AsyncService asyncService;

    @SneakyThrows
    @Test
    void doSomething() {
//        Console.log("port: {}", port);
        Console.log("开始测试");

        long start = System.currentTimeMillis();
        int n = 100000;
        CountDownLatch countDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            asyncService.doSomething(countDownLatch);
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        Console.log("耗时：" + (end - start) + "ms");

        Console.log("结束测试");
    }




    @SneakyThrows
    @Test
    void doSomethingVirtualTest() {

    }




}