package thread;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jiandong 2024-07-09 create
 *
 * https://tech.meituan.com/2022/05/12/principles-and-practices-of-completablefuture.html
 */
public class B1CompletableFuture {

    /**
     * 下面将举例来说明，我们通过ListenableFuture、CompletableFuture来实现异步的差异。
     * 假设有三个操作step1、step2、step3存在依赖关系，其中step3的执行依赖step1和step2的结果。
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 1");
            ThreadUtil.sleep(1000l);
            return "step1 result";
        }, executor);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行step 2");
            ThreadUtil.sleep(1000l);
            return "step2 result";
        });
        cf1.thenCombine(cf2, (result1, result2) -> {
            System.out.println(result1 + " , " + result2);
            System.out.println("执行step 3");
            ThreadUtil.sleep(1000l);
            return "step3 result";
        }).thenAccept(result3 -> System.out.println(result3));
        System.out.println("over");
    }


}
