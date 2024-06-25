package com.feizhou.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author jiandong 2024-06-25 create
 */
@Service
@Slf4j
@EnableAsync
public class DjAsyncService {

    @Async("dj666_")
//    public CompletableFuture<String> doAsync(Integer delayTime) {
    public String doAsync(Integer delayTime) {
        try {
            if (delayTime > 0) {
                log.info("延迟时间:" + delayTime + "ms");
                Thread.sleep(delayTime);
            }
        } catch (Exception e) {
//            return CompletableFuture.completedFuture("exception");
            return "exception";
        }
//        return CompletableFuture.completedFuture("success");
        log.info("service over");
        return "success";
    }

    @Async("dj666_")
    public CompletableFuture<String> doAsync1(Integer delayTime) {
        try {
            if (delayTime > 0) {
                log.info("延迟时间:" + delayTime + "ms");
                Thread.sleep(delayTime);
            }
        } catch (Exception e) {
            return CompletableFuture.completedFuture("exception");
        }
        log.info("service over");
        return CompletableFuture.completedFuture("success");
    }
}
