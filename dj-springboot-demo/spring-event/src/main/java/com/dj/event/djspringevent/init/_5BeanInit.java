package com.dj.event.djspringevent.init;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiandong 2024-04-23 create
 * 适用场景：
 *
 * 1. 资源初始化：例如，初始化数据库连接、网络连接、线程池等资源。
 * 2. Bean状态设置：在Bean实例化后，对其进行额外的状态设定或配置。
 * 3. 缓存预热：在服务启动时预先加载部分数据至缓存中。
 * Bean实例上定义初始化方法，与Bean紧密关联，可以精确地控制Bean在何时执行初始化操作，与Spring容器的生命周期绑定，尤其适用于那些需要在Bean实例化后立即执行的操作。。但是如果多个Bean都有初始化方法，它们之间的执行顺序难以控制，除非依赖于Spring容器中Bean的依赖注入顺序。
 *
 */
@Configuration
public class _5BeanInit {
    /**
     * 指定初始化init
     * @return
     */
    @Bean(initMethod = "init")
    BeanWayService beanWayService(){
        return new BeanWayService();
    }

    public class BeanWayService {

        public void init() {
            System.out.println("初始化操作" + "5. @Bean(initMethod = \"init\")");
        }

        public BeanWayService(){
            super();
            System.out.println("初始化构造函数-BeanWayService");
        }
    }
}
