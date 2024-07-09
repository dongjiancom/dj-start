package thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

/**
 * @author jiandong 2024-07-09 create
 * get();   join();
 */
public class B4CompletableFuture {

    /**
     * 返回结果：如果计算完成，join() 方法返回计算结果。
     * 异常处理：如果计算过程中抛出了异常，join() 会将该异常包装在 CompletionException 中抛出，
     *          而 get() 方法则会抛出 ExecutionException。
     *          这意味着调用 join() 不需要显式捕获和处理 InterruptedException 和 ExecutionException。
     */
    public static void main(String[] args) {
        // 创建一个 CompletableFuture
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000); // 模拟耗时任务
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 42;
        });

        // 使用 join() 方法
        try {
            Integer resultJoin = future.join(); // 不需要处理 InterruptedException
            System.out.println("Result from join: " + resultJoin);
        } catch (CompletionException e) {
            System.err.println("Exception from join: " + e.getCause());
        }

        // 使用 get() 方法
        try {
            Integer resultGet = future.get(); // 需要处理 InterruptedException 和 ExecutionException
            System.out.println("Result from get: " + resultGet);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Exception from get: " + e.getCause());
        }

        System.out.println("Main thread ends");
    }
}
