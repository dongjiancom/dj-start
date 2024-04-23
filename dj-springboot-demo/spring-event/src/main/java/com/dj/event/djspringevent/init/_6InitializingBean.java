package com.dj.event.djspringevent.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author jiandong 2024-04-23 create
 * 适用场景：
 *
 * 1. 资源初始化：如初始化数据库连接、网络连接、线程池等资源。
 * 2. Bean状态设置：在依赖注入完成后，设置Bean的初始状态或执行特定的配置操作。
 * afterPropertiesSet()方法会在所有属性注入完成后执行，确保Bean在使用前完成初始化。不需要额外的注解，只需实现接口就可以定义初始化逻辑。但是其要求Bean实现特定接口，增加了类的耦合度，同时也不符合Spring倡导的基于注解的编程风格。并且需要显式抛出异常。
 * 相比较于@PostConstruct，@PostConstruct注解更具语义化且不强制类实现接口，降低了耦合度。推荐优先考虑使用@PostConstruct注解进行初始化逻辑的编写。
 */
@Component
public class _6InitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        // 在所有依赖注入完成后执行的初始化逻辑
        System.out.println("初始化操作" + "6. InitializingBean");
    }

}
