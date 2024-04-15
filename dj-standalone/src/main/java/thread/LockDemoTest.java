package thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jiandong 2024-03-05 create
 */
public class LockDemoTest {

    private static int counter = 0;
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
//        ReentrantLock 测试代码。第一个线程加锁，等待，先不解锁，第二个线程再加锁
        new Thread(() -> {
            System.out.println("线程 1 开始");
            if(lock.tryLock()){
                try {
                    System.out.println("线程1加锁");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("线程1解锁");
                }
            }else {
                System.out.println("线程1加锁失败");
            }
        }).start();

        new Thread(() -> {
            System.out.println("线程 2 开始");
            try {
                if(lock.tryLock(1,TimeUnit.SECONDS)){
                    try {
                        System.out.println("线程2加锁");
                    } finally {
                        lock.unlock();
                        System.out.println("线程2解锁");
                    }
                }else {
                    System.out.println("线程2加锁失败");
                }
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
                System.out.println("线程2加异常"+e.getMessage());
            }
        }).start();
    }




}
