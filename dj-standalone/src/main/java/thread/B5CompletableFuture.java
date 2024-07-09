package thread;

import java.util.concurrent.CompletableFuture;
/**
 * @author jiandong 2024-07-09 create
 */
public class B5CompletableFuture {
    /**
     * CompletableFuture.allOf()：
     *      返回一个新的 CompletableFuture<Void>，
     *      它将在所有给定的 CompletableFuture 完成后完成。可以用 join() 等方法阻塞，直到所有任务完成。
     * CompletableFuture.anyOf()：
     *      返回一个新的 CompletableFuture<Object>，
     *      它将在任意一个给定的 CompletableFuture 完成后完成。可以用 join() 等方法阻塞，直到任意一个任务完成，并返回该任务的结果。
     */
    public static void main(String[] args) {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Future 1 done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Future 2 done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 2;
        });

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("Future 3 done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        });

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future1, future2, future3);

        // 阻塞主线程，直到任意一个 CompletableFuture 完成
        Object result = anyOf.join();
        System.out.println("First future done with result: " + result);
    }
}
