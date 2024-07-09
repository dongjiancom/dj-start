package thread;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @description:
 *
 * CompletableFuture可以指定异步处理流程：
 * thenAccept()处理正常结果；
 * exceptional()处理异常结果；
 * thenApplyAsync()用于串行化另一个CompletableFuture；
 * anyOf()和allOf()用于并行化多个CompletableFuture。
 *
 * @author: Mr.DJ
 * @createTime: 2020-12-23 14:54
 **/
public class A3CompletableFuture {

    /**
     * 自添加，获取 allOf 里的值
     *
     * @param completableFutures
     * @param <T>
     * @return
     */
    @SneakyThrows
    public static <T> List<T> sequence(CompletableFuture<T>... completableFutures) {
        List<T> result = new ArrayList<>();
        for (CompletableFuture<T> completableFuture : completableFutures) {
            result.add(completableFuture.get());
        }
        return result;
    }

    /**
     * 自定义线程池
     */
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    public static void main(String[] args) throws Exception {
        // 两个CompletableFuture执行异步查询:
        CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://finance.sina.com.cn/code/");
        }, threadPoolExecutor);
        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油", "https://money.163.com/code/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);


        // 两个CompletableFuture执行异步查询:
        CompletableFuture<Double> cfFetchFromSina = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://finance.sina.com.cn/price/");
        });
        CompletableFuture<Double> cfFetchFrom163 = cfQuery.thenApplyAsync((code) -> {
            return fetchPrice((String) code, "https://money.163.com/price/");
        });

        // 用anyOf合并为一个新的CompletableFuture:
        // 试试 allOf 2022-09-15
        CompletableFuture<Void> cfFetch = CompletableFuture.allOf(cfFetchFromSina, cfFetchFrom163);

        List<Double> doubles = sequence(cfFetchFromSina, cfFetchFrom163);
        System.out.println(doubles);

        // 最终结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price 二选一: " + doubles);
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
//        TimeUnit.SECONDS.sleep(10);

    }

    static String queryCode(String name, String url) {
        System.out.println(Thread.currentThread().getName());
        System.out.println("query code from " + url + "...");

        System.out.println("queryCode - start");
        int timeout = 1;
        System.out.println("queryCode - sleep" + timeout + "s");

        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
        }
        return "601857";
    }

    static Double fetchPrice(String code, String url) {
        System.out.println(Thread.currentThread().getName());
        System.out.println("query price from " + url + "...");

        System.out.println("fetchPrice - start");
        long timeout = (long) (Math.random() * 5);
        System.out.println("fetchPrice - sleep" + timeout + "s");

        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}
