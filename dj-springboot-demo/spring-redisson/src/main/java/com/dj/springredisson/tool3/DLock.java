package com.dj.springredisson.tool3;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author jiandong 2024-06-07 create
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DLock {

    /**
     * 锁的key
     */
    String value();

    String prefix() default "";

    long waitTime() default 5L;

    long lockTime() default 30L;

    boolean useMethod() default true;

    @Slf4j
    @Component
    @Aspect
    class DistributedLockAspect {

        @Resource
        RedissonClient redissonClient;

        // 创建SPEL解析器，用于解析SPEL表达式
        private final ExpressionParser parser = new SpelExpressionParser();
        // 创建参数名发现器，用于获取方法参数名称
        private final DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

        @Pointcut("@annotation(com.dj.springredisson.tool3.DLock)")
        public void distributorLock() {

        }

        /**
         * 切面方法，用于拦截带有@DLock注解的方法
         * @param joinPoint 连接点，表示被拦截的方法
         * @return 方法的执行结果
         * @throws Throwable 如果方法执行过程中抛出异常，则抛出该异常
         */
        @Around("distributorLock()")
        public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("@DLock.DistributedLockAspect.doAround in");
            DLock dLock = getDLockObj(joinPoint);

            // 获取方法签名
            Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            // 获取方法的参数值
            Object[] args = joinPoint.getArgs();

            // 创建方法参数上下文，用于获取方法参数值
            StandardEvaluationContext context = new StandardEvaluationContext();
            // 获取方法签名并强制转换为MethodSignature，以便获取方法的详细信息
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            // 获取方法的参数名称
            String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
            // 将方法参数名称和对应的值添加到SPEL上下文中
            for (int i = 0; i < args.length; i++) {
                context.setVariable(paramNames[i], args[i]);
            }
            // 获取注解中的SPEL表达式
            String valueSpel = dLock.value();
            String prefixSpel = dLock.prefix();
            String value = parser.parseExpression(valueSpel).getValue(context, String.class);
            value = this.parse(dLock.useMethod(), value, method, args);
            String prefix = parser.parseExpression(prefixSpel).getValue(context, String.class);
            // 组合锁的Key
            String lockKey = prefix + (value == null ? "" : ":" + value);
            RLock rLock = this.redissonClient.getLock(lockKey);
            if (rLock.tryLock(dLock.waitTime(), dLock.lockTime(), TimeUnit.SECONDS)) {
                try {
                    return joinPoint.proceed();
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

        private String parse(boolean useMethod, String value, Method method, Object[] args) {
            if(useMethod){
                // TODO
            }
            return value;
        }

        private DLock getDLockObj(ProceedingJoinPoint pjp) throws NoSuchMethodException {
            String methodName = pjp.getSignature().getName();
            Class clazz = pjp.getTarget().getClass();
            Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
            Method lockMethod = clazz.getMethod(methodName, par);
            DLock distributedLock = lockMethod.getAnnotation(DLock.class);
            return distributedLock;
        }
    }

}
