package com.dj.event.djspringevent.biz.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author jiandong 2024-01-27 create
 * 修改密码事件
 */
@Getter
@Setter
public class UserChangePasswordEvent extends ApplicationEvent {
    private String userId;

    public UserChangePasswordEvent(String userId) {
        super(new Object());
        this.userId = userId;
    }

}