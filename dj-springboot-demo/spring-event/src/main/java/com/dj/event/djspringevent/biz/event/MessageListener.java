package com.dj.event.djspringevent.biz.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author jiandong 2024-01-27 create
 */
@Component
public class MessageListener implements ApplicationListener<UserChangePasswordEvent> {
    @Override
    public void onApplicationEvent(UserChangePasswordEvent event) {
        System.out.println("收到事件:" + event);
        System.out.println("开始执行业务操作给用户发送短信。用户userId为：" + event.getUserId());
    }
}