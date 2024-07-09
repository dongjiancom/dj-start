package com.feizhou.async.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.feizhou.async.service.DjAsyncService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author jiandong 2024-07-09 create
 * 测试主线程结束后，传入自定义线程池，是否会影响CompletableFuture的异步执行
 */
@RestController
@Slf4j
public class DjCompletableFutureController {

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    @SneakyThrows
    @PostMapping("/CompletableFuture1")
    @ResponseBody
    public String completableFuture1() {
        log.info("测试主线程结束后，传入自定义线程池，是否会影响CompletableFuture的异步执行");
        log.info("默认线程池ForkJoinPool");
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            log.info("CompletableFuture异步执行--start");
            ThreadUtil.sleep(3000l);
            log.info("CompletableFuture异步执行--end");
            return "step2 result";
        });
        return "success";
    }
    @SneakyThrows
    @PostMapping("/CompletableFuture2")
    @ResponseBody
    public String CompletableFuture2() {
        log.info("测试主线程结束后，传入自定义线程池，是否会影响CompletableFuture的异步执行");
        log.info("自定义线程池");
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            log.info("CompletableFuture异步执行");
            log.info("CompletableFuture异步执行--start");
            ThreadUtil.sleep(3000l);
            log.info("CompletableFuture异步执行--end");
            return "step2 result";
        });
        return "success";
    }

}
