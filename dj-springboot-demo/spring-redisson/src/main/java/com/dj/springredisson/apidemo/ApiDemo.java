package com.dj.springredisson.apidemo;

import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jiandong 2024-07-11 create
 */
@Component
public class ApiDemo {

    @Resource
    RedissonClient redisson;

}
