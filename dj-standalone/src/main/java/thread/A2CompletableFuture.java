package thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 *      异步执行：supplyAsync
 *      串行执行：thenApplyAsync
 *
 * @author: Mr.DJ
 * @createTime: 2020-12-23 14:49
 **/
public class A2CompletableFuture {

    public static void main(String[] args) throws Exception {
        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油");
        });
        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync((code) -> {
            System.out.println("cfFetch-start");
            return fetchPrice(code);
        });
        // cfFetch成功后打印结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });

        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        TimeUnit.SECONDS.sleep(3);
    }

    static String queryCode(String name) {
        System.out.println("queryCode - start");
        int timeout = 1;
        System.out.println("queryCode - sleep"+ timeout+"s");
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
        }
        System.out.println("queryCode-end");
        return "601857";
    }

    static Double fetchPrice(String code) {
        System.out.println("fetchPrice - start");
        int timeout = 1;
        System.out.println("fetchPrice - sleep"+ timeout+"s");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        System.out.println("fetchPrice - end");
        return 5 + Math.random() * 20;
    }
}
