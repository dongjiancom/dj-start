package com.dj.event.djspringevent.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author jiandong 2024-04-23 create
 *
 * 参考：https://mp.weixin.qq.com/s/iuCrejn-3KIiZN2qBdXwlg
 *
 * 使用场景：
 * 1. 执行一次性初始化操作： 当应用程序启动时，可能需要执行一些只需在应用程序初始化阶段执行一次的操作，例如加载基础数据、建立连接等。通过监听 ContextRefreshedEvent 事件，可以确保这些初始化操作在应用程序启动后立即执行。
 * 2. 初始化缓存或缓存刷新： 如果应用程序使用了缓存，可能需要在应用程序启动时初始化缓存或定期刷新缓存。通过监听 ContextRefreshedEvent 事件，可以在应用程序启动后立即执行缓存初始化或刷新操作，确保缓存数据是最新的。
 * 3. 执行与外部系统的交互： 在应用程序启动时，可能需要与外部系统进行交互，例如检查外部系统的可用性、加载配置信息等。通过监听 ContextRefreshedEvent 事件，可以在应用程序启动后立即执行与外部系统的交互操作，确保应用程序在启动后处于正常工作状态。
 * 4. 执行与 Spring Bean 相关的初始化操作： 在应用程序启动时，可能需要执行一些与 Spring Bean 相关的初始化操作，例如在数据库连接池初始化后执行数据库迁移、在消息队列连接初始化后执行订阅操作等。通过监听 ContextRefreshedEvent 事件，可以确保这些初始化操作在 Spring Bean 初始化完成后立即执行
 * 这种方式能够确保在 ApplicationContext 被完全初始化或刷新后执行初始化操作，可以在这个时机执行一些需要ApplicationContext完全准备好的操作。但是需要注意的是，ContextRefreshedEvent 事件可能会在应用程序的刷新周期内多次触发，因此在处理这个事件时需要谨慎处理，避免重复执行初始化逻辑。
 */
@Component
public class _1ApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("初始化操作" + "1. 监听 ApplicationListener");
    }
}
