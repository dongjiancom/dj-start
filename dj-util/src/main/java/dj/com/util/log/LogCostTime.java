
package dj.com.util.log;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiandong 2024-06-27 create
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogCostTime {

    String logOrderNo() default "";

    @Aspect
    @Component
    @Slf4j
    class LogExecutionTimeAspect {
        @Around("@annotation(logCostTime)")
        public Object logCostTime(ProceedingJoinPoint joinPoint, LogCostTime logCostTime) throws Throwable {
            log.info("Around logCostTime in");
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Object[] args = joinPoint.getArgs();

            String logOrderNo = null;
            try {
                logOrderNo = getStrBySpel(logCostTime.logOrderNo(), signature.getParameterNames(), args);
            } catch (Exception e) {
                log.error(StrUtil.join(StrUtil.COMMA, signature, signature.getParameterNames()), e);
            }

            long start = System.currentTimeMillis();

            Object proceed = joinPoint.proceed();

            long costTime = System.currentTimeMillis() - start;
            // 小于1s
            if (costTime < 1000) {
                log.info(StrUtil.join(StrUtil.COMMA, signature, logOrderNo, "cost time:" + costTime + "ms", "cost time:" + costTime/1000 + "s"));
            }
            // 1-5s
            if(costTime >= 1000 && costTime <= 5000){
                log.error(StrUtil.join(StrUtil.COMMA, signature, logOrderNo, "内部方法耗时 较长，cost time:" + costTime + "ms", "cost time:" + costTime/1000 + "s"));
            }
            // >5s
            if(costTime > 5000){
                log.error(StrUtil.join(StrUtil.COMMA, signature, logOrderNo, "内部方法耗时 超长，cost time:" + costTime + "ms", "cost time:" + costTime/1000 + "s"));
            }
            return proceed;
        }

        public static String getStrBySpel(String expression, String[] parameterNames, Object[] args) {
            String result = StrUtil.EMPTY;
            if (!expression.isEmpty()) {
                ExpressionParser parser = new SpelExpressionParser();
                StandardEvaluationContext context = new StandardEvaluationContext();
                for (int i = 0; i < parameterNames.length; i++) {
                    context.setVariable(parameterNames[i], args[i]);
                }
                result = parser.parseExpression(expression).getValue(context, String.class);
            }
            return result;
        }
    }
}
