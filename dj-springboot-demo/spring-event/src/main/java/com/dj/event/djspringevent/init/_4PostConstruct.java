package com.dj.event.djspringevent.init;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author jiandong 2024-04-23 create
 *
 * 参考：https://mp.weixin.qq.com/s/iuCrejn-3KIiZN2qBdXwlg
 *
 * 使用场景：
 * 1. 单个Bean初始化：对于某个特定的Bean，在其所有依赖项注入完成后，需要执行一些特定的初始化操作，例如数据库连接初始化、缓存预热、初始化内部状态等。
 * 2. 资源初始化：对于一些公共资源，如线程池、数据库连接池等，可以在对应的配置类或服务类中使用@PostConstruct来完成初始化设置。
 * @PostConstruct注解只需要在需要执行初始化操作的方法上加上即可，无需额外实现接口或关注Spring容器的生命周期事件。并且针对性强，仅针对单个Bean进行初始化操作，有助于提高代码的模块化和复用性。
 * 但是如果有多个具有@PostConstruct注解的方法，它们之间没有明确的执行顺序，除非通过Bean间的依赖关系隐式确定顺序。并且针对单个Bean进行初始化操作，所以他并不适合做全局性初始化操作。
 */
@Component
public class _4PostConstruct {

    @PostConstruct
    public void init() {
        // 在依赖注入完成后，执行初始化操作
        System.out.println("初始化操作" + "4. @PostConstruct注解");
    }
}
