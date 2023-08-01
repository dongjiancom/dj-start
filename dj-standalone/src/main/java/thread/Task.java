package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Mr.DJ
 * @createTime: 2022-09-15 15:43
 **/
class Task implements Callable<String> {

    @Override
    public String call() throws Exception {

        System.out.println("Task.call start");
        int timeout = 1;
        System.out.println("Task.call sleep " + timeout +"s");
        TimeUnit.SECONDS.sleep(timeout);
        return String.valueOf(System.currentTimeMillis());
    }
}
