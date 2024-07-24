package com.dj.event.djspringevent.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.dj.event.djspringevent.biz.event.UserChangePasswordEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author jiandong 2024-07-22 create
 */
@RestController
@Slf4j
public class EventController {

    @Resource
    ApplicationEventPublisher appEventPublisher;

    @SneakyThrows
    @PostMapping("/userChangePasswordEvent")
    @ResponseBody
    public String userChangePasswordEvent() {
        // 打印执行时间
        long startL = System.currentTimeMillis();
        // 发布事件
        appEventPublisher.publishEvent(new UserChangePasswordEvent("1111111"));

        log.info("发布事件耗时：{}", System.currentTimeMillis() - startL);
        return "success";
    }
}
