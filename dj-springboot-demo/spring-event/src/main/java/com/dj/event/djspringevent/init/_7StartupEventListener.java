package com.dj.event.djspringevent.init;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author jiandong 2024-04-23 create
 * 适用场景：
 *
 * 1. 应用启动后执行一次性操作：如数据初始化、缓存预热、统计信息收集等。
 * 2. 等待所有Bean初始化后再执行：当需要确保所有Bean都已经初始化完毕再执行某些操作时。
 * 通过事件驱动的方式，将初始化逻辑与Bean的创建逻辑解耦开来，并且可以监听多种事件类型（例如：ContextRefreshedEvent），不仅仅是应用启动事件，还可用于其他业务场景。相比于@PostConstruct、CommandLineRunner或ApplicationRunner等机制，@EventListener监听的ApplicationReadyEvent在Spring Boot启动流程中的执行时机较晚，所有Bean都已经初始化并准备就绪后才会触发。
 */
@Component
public class _7StartupEventListener {

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        System.out.println("初始化操作" + "7. @EventListener");
        System.out.println("ApplicationReadyEvent: " + event);
    }
}
