package com.dj.springredisson.service;

import com.dj.springredisson.tool.DistributedLock;
import com.dj.springredisson.tool.IDistributedLock;
import com.dj.springredisson.tool.ILock;
import com.dj.springredisson.tool3.DLock;
import dj.com.pojo.DjBean;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author jiandong 2024-06-11 create
 * 业务时间：10s，加锁时间>10s，看是否正常解锁
 */
@Service
@Slf4j
public class DLockService {
    /**
     * 业务执行时间，单位秒
     */
    public static final Long BIZ_TIME = 10L;

    @Resource
    private IDistributedLock distributedLock;

    @SneakyThrows
//    @DLock(value = "#djbean.name", prefix = "#prefix")
    @DistributedLock(key = "#djBean.name", lockTime = 100L, keyPrefix = "sku-")
    public String lockByAnnotation(DjBean djBean, String prefix) {
        TimeUnit.SECONDS.sleep(BIZ_TIME);
        return "over";
    }

    @SneakyThrows
    @DLock(value = "#djBean.name", prefix = "#prefix", lockTime = 100L)
    public String lockByAnnotation1(DjBean djBean, String prefix) {
        // 业务代码
        TimeUnit.SECONDS.sleep(BIZ_TIME);
        return "over";
    }

    @SneakyThrows
    public String lockByHand(DjBean djBean, String prefix) {
        // try-with-resources 语法糖自动释放锁
        try (ILock lock = distributedLock.lock(djBean.getName(), 100L, TimeUnit.SECONDS, false)) {
            if (Objects.isNull(lock)) {
                throw new RuntimeException("Duplicate request for method still in process");
            }
            // 业务代码
            TimeUnit.SECONDS.sleep(BIZ_TIME);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            log.error("修改异常", e);
            throw new RuntimeException("修改异常");
        }
        return "over";
    }

    public String lockByHand1(DjBean djBean, String prefix) {
        // 手动释放锁
        ILock lock = null;
        try {
            lock = distributedLock.lock(djBean.getName(), 100L, TimeUnit.SECONDS, false);
            if (Objects.isNull(lock)) {
                throw new RuntimeException("Duplicate request for method still in process");
            }
            // 业务代码
            TimeUnit.SECONDS.sleep(BIZ_TIME);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            log.error("保存异常", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            if (Objects.nonNull(lock)) {
                distributedLock.unLock(lock);
            }
        }
        return "over";
    }

}
