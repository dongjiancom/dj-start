package com.dj.springredisson.controller;

import com.dj.springredisson.service.DLockService;
import com.dj.springredisson.tool2.RedissonSpringTool;
import dj.com.entity.DjBean;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jiandong 2024-06-05 create
 * 参考：https://blog.csdn.net/Ascend1977/article/details/131126047
 * 配置参考：https://springdoc.cn/spring-boot-redisson/
 * github：https://github.com/redisson/redisson/blob/master/redisson-spring-boot-starter/README.md
 */
@RestController
@Slf4j
public class DocRedissonController {

    @Resource
    DLockService dLockService;

    @Resource
    RedissonSpringTool redissonSpringTool;

    /**
     * 加锁，不解锁，看门狗续期
     */
    @SneakyThrows
    @GetMapping("/lockForever")
    @ResponseBody
    public String lockForever(@RequestBody String lockKey) {
        return String.valueOf(redissonSpringTool.lockForever(lockKey));
    }


    /**
     * 手动加锁；finally解锁
     */
    @SneakyThrows
    @GetMapping("/lockByHand")
    @ResponseBody
    public String lockByHand(@RequestBody DjBean djBean, String prefix) {
        log.info("djBean={}", djBean);
        log.info("prefix={}", prefix);
        return dLockService.lockByHand(djBean, prefix);
    }

    /**
     * 手动加锁；try-with-resources语法糖自动释放锁
     */
    @SneakyThrows
    @GetMapping("/lockByHand1")
    @ResponseBody
    public String lockByHand1(@RequestBody DjBean djBean, String prefix) {
        log.info("djBean={}", djBean);
        log.info("prefix={}", prefix);
        return dLockService.lockByHand1(djBean, prefix);
    }

    /**
     * 注解 @DistributedLock
     */
    @SneakyThrows
    @GetMapping("/lockByAnnotation")
    @ResponseBody
    public String lockByAnnotation(@RequestBody DjBean djBean, String prefix) {
        log.info("djBean={}", djBean);
        log.info("prefix={}", prefix);
        return dLockService.lockByAnnotation(djBean, prefix);
    }

    /**
     * 注解 @DLock
     */
    @SneakyThrows
    @GetMapping("/lockByAnnotation1")
    @ResponseBody
    public String lockByAnnotation1(@RequestBody DjBean djBean, String prefix) {
        log.info("djBean={}", djBean);
        log.info("prefix={}", prefix);
        return dLockService.lockByAnnotation1(djBean, prefix);
    }

}
