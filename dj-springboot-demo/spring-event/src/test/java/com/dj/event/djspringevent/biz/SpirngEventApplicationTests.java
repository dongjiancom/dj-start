package com.dj.event.djspringevent.biz;

import com.dj.event.djspringevent.biz.event.UserChangePasswordEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author jiandong 2024-01-28 create
 */
@SpringBootTest
public class SpirngEventApplicationTests {

    @Autowired
    ApplicationEventPublisher appEventPublisher;

    @Test
    void contextLoads() {
        appEventPublisher.publishEvent(new UserChangePasswordEvent("1111111"));
    }
}