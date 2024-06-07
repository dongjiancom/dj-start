package com.dj.springredisson.tool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiandong 2024-06-07 create
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DLock {
    String value() default "";

    String prefix() default "";

    int waitTime() default 5;

    int leaseTime() default 30;
}
