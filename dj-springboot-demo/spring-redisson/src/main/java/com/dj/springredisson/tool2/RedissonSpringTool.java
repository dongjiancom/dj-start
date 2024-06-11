package com.dj.springredisson.tool2;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author jiandong 2024-06-07 create
 */
@Component
@Slf4j
public class RedissonSpringTool {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 加锁-最多等待5秒，锁30秒
     * 不抛出错误
     */
    public boolean waitLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        boolean isLock = false;
        try {
            // 尝试加锁，最多等待“参数1”秒，上锁以后“参数2”秒自动解锁
            isLock = lock.tryLock(5, 30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("pp waitLock exception:", e);
        }
        log.info("pp waitLock lockKey:{} isLock:{}", lockKey, isLock);
        return isLock;
    }

    /**
     * 释放锁
     * 不抛出错误
     */
    public void releaseLock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            lock.unlock();
        } catch (Exception e) {
            log.error("pp releaseLock exception:", e);
        }
        log.info("pp releaseLock lockKey:{}", lockKey);
    }

    /**
     * 不解锁
     */
    public boolean lockForever(String lockKey){
        RLock lock = redissonClient.getLock(lockKey);
        boolean isLock = false;
        try {
            isLock = lock.tryLock(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("pp waitLock exception:", e);
        }
        return isLock;
    }

}
