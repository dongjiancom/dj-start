package com.dj.springredisson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class DjSpringRedissonApplication {

    public static void main(String[] args) {
        SpringApplication.run(DjSpringRedissonApplication.class, args);
    }

}
