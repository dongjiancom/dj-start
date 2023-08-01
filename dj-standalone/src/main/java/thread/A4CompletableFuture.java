package thread;

import java.util.concurrent.CompletableFuture;

/**
 * @description:
 *
 *  我看到supplyAsync在thenAccept的前面，而且任务是在supplyAsync中启动的。
 *  那有没有可能在通过cf.thenAccept设置回调前，异步执行的任务已经完成了（假如fetchPrice是个空函数），
 *  这样在thenAccept中设置的回调就不会被执行了，有这种可能吗？
 *
 * 回调一定会执行
 *
 * @author: Mr.DJ
 * @createTime: 2022-09-16 09:57
 **/
public class A4CompletableFuture {
        public static void main(String[] args) throws Exception {
            // 创建异步执行任务:
            CompletableFuture<Double> cf = CompletableFuture.supplyAsync(A4CompletableFuture::fetchPrice);
            // 暂停让Future完成:
            Thread.sleep(5000);
            // 如果执行成功:
            cf.thenAccept((result) -> {
                System.out.println("thenAccept: " + result);
            });
            // 如果执行异常:
            cf.exceptionally((e) -> {
                e.printStackTrace();
                return null;
            });
            // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
            Thread.sleep(2000);
        }

        static double fetchPrice() {
            System.out.println("fetched!");
            return 123.456;
        }

}
