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
        appEventPublisher.publishEvent(new UserChangePasswordEvent("1111111"));
        return "success";
    }
}
