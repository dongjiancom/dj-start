package com.dj.springredisson.demo;

import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author jiandong 2024-07-09 create
 */
@Component
public class RedissonDemo {
    @Resource
    RedissonClient redisson;

    void a1() {
        RMap<String, String> workerIdKey = redisson.getMap("WORKER_ID_KEY");
    }

    public void setMapObject(String token, Object object, String mapName, Long minutes) {
        RMapCache<String, Object> rMapCache = redisson.getMapCache(mapName);
        rMapCache.addAndGet("",1);
        rMapCache.put(token, object, minutes, TimeUnit.MINUTES);
    }

    public Object getMapObject(String key, String mapName) {
        RMapCache<String, Object> map = redisson.getMapCache(mapName);
        return map.get(key);
    }
}