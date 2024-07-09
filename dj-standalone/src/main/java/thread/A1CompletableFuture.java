package thread;



import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 *  可参考 廖雪峰： https://www.liaoxuefeng.com/wiki/1252599548343744/1306581182447650
 * @author: Mr.DJ
 * @createTime: 2020-12-23 14:49
 **/
public class A1CompletableFuture {
    /**
     * 异步、不阻塞的方法
     */
    public static void main(String[] args) throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(A1CompletableFuture::fetchPrice);
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });

        /**
         * 以上方法都为异步
         * 不会阻塞
         */
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
//        TimeUnit.SECONDS.sleep(2);
    }

    static Double fetchPrice() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }
}
