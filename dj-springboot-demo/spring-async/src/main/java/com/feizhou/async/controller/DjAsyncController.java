package com.feizhou.async.controller;

import com.feizhou.async.service.DjAsyncService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author jiandong 2024-05-21 create
 */
@RestController
@Slf4j
public class DjAsyncController {

    @Resource
    DjAsyncService djAsyncService;


    /**
     * async
     */
    @SneakyThrows
    @PostMapping("/async")
    @ResponseBody
    public String async() {
//        CompletableFuture<String> threadResult = djAsyncService.doAsync(1000);
        // 等待所有执行完成
//        CompletableFuture.allOf(threadResult).join();
//        log.info(threadResult.get());
        for (int i = 0; i < 10000; i++) {
            String s = djAsyncService.doAsync(1000);
            log.info(s);
        }
        return "success";
    }

    /**
     * async1
     */
    @SneakyThrows
    @PostMapping("/async1")
    @ResponseBody
    public String async1() {
        List<CompletableFuture<String>> allThreadResultList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            CompletableFuture<String> threadResult = djAsyncService.doAsync1(1000);
//            dealThreadResult(threadResult.get());
            allThreadResultList.add(threadResult);
        }

        // Combine all futures
        CompletableFuture<Void> allOf = CompletableFuture.allOf(allThreadResultList.toArray(new CompletableFuture[0]));
        // Wait for all futures to complete
        allOf.join();

        // 等待所有任务都执行完
        for(CompletableFuture<String> threadResult : allThreadResultList){
            // 获取每个任务的返回结果
            String result = null;
            try {
                result = threadResult.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            log.info("for deal" + result);
        }
        return "success";
    }
}
