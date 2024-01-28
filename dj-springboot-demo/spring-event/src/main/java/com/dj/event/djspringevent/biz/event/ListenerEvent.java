package com.dj.event.djspringevent.biz.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author jiandong 2024-01-28 create
 * 参考：https://juejin.cn/post/7214699255507959869
 *
 * 牛啊：https://www.yuque.com/hollis666/hgtuok/wleqh7dyg2c20uqq
 */
@Component
public class ListenerEvent {
    @EventListener({ UserChangePasswordEvent.class })
    public void LogListener(UserChangePasswordEvent event) {
        System.out.println("收到事件:" + event);
        System.out.println("开始执行业务操作生成关键日志。用户userId为：" + event.getUserId());
    }

//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT,value = { UserChangePasswordEvent.class })
    @EventListener({ UserChangePasswordEvent.class })
    public void messageListener(UserChangePasswordEvent event) {
        System.out.println("收到事件:" + event);
        System.out.println("开始执行业务操作给用户发送短信。用户userId为：" + event.getUserId());
    }

}