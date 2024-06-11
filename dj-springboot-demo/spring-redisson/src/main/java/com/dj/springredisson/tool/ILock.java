package com.dj.springredisson.tool;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * @author jiandong 2024-06-11 create
 */
@AllArgsConstructor
public class ILock implements AutoCloseable {
    /**
     * 持有的锁对象
     */
    @Getter
    private Object lock;
    /**
     * 分布式锁接口
     */
    @Getter
    private IDistributedLock distributedLock;

    @Override
    public void close() throws Exception {
        if(Objects.nonNull(lock)){
            distributedLock.unLock(lock);
        }
    }
}