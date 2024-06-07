package com.dj.springredisson.tool;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author jiandong 2024-06-07 create
 */
@Slf4j
@Component
public class DistributedLockAspect {

    @Resource
    RedissonClient redissonClient;

    @Around("@annotation(com.dj.springredisson.tool.DLock)")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        Object[] args = proceedingJoinPoint.getArgs();
        DLock dLock = method.getAnnotation(DLock.class);
        String value = this.parse(dLock.value(), method, args);
        String lockKey = dLock.prefix() + (value == null ? "" : ":" + value);
        RLock rLock = this.redissonClient.getLock(lockKey);

        if (rLock.tryLock(dLock.waitTime(), dLock.leaseTime(), TimeUnit.SECONDS)) {
            try {
                return proceedingJoinPoint.proceed();
            } catch (Exception e) {
                log.error("execute dlock break exception======= lockKey is {}", lockKey);
                log.error("[执行异常]", e);
                throw e;
            } finally {
                rLock.unlock();
            }
        } else {
            log.error("获取锁超时======= lockKey is {}", lockKey);
            throw new RuntimeException("请稍后重新重试");
        }
    }

    private String parse(String value, Method method, Object[] args) {
        // TODO
        return null;
    }
}
