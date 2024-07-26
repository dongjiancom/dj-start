package dj.com.java21virtual.service;

import cn.hutool.core.lang.Console;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import java.util.concurrent.CountDownLatch;

/**
 * @author jiandong 2024-07-26 create
 */
@Service
@EnableAsync
public class AsyncService {
    /**
     *
     * @param countDownLatch 用于测试
     */
    @Async
    public void doSomething(CountDownLatch countDownLatch) throws InterruptedException {
        Console.log(Thread.currentThread().getName() + "doSomething start");
        Thread.sleep(50);
        countDownLatch.countDown();
        Console.log(Thread.currentThread().getName() + "doSomething end");
    }

}