package thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 加锁： https://www.liaoxuefeng.com/wiki/1252599548343744/1306580960149538
 * @author: Mr.DJ
 * @createTime: 2022-05-29 19:19
 **/
public class LockDemo {

    private final Lock lock = new ReentrantLock();
    private int count;

    /**
     *  synchronized
     * @param n
     */
    public void add_synchronized(int n) {
        synchronized(this) {
            count += n;
        }
    }

    /**
     * lock
     * 因为synchronized是Java语言层面提供的语法，所以我们不需要考虑异常，
     * 而ReentrantLock是Java代码实现的锁，我们就必须先获取锁，然后在finally中正确释放锁。
     * 顾名思义，ReentrantLock是可重入锁，它和synchronized一样，一个线程可以多次获取同一个锁。
     * @param n
     */
    public void add_lock(int n) {
        lock.lock();
        try {
            count += n;
        } finally {
            lock.unlock();
        }
    }

    /**
     * lock 和 synchronized不同的是，ReentrantLock可以尝试获取锁：
     *  在尝试获取锁的时候，最多等待1秒。如果1秒后仍未获取到锁，tryLock()返回false，程序就可以做一些额外处理，而不是无限等待下去。
     *  所以，使用ReentrantLock比直接使用synchronized更安全，线程在tryLock()失败的时候不会导致死锁。
     * @param n
     */
    @SneakyThrows
    public void add_lock_different(int n) {
        if (lock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                count += n;
            } finally {
                lock.unlock();
            }
        }
    }




    public static void main(String[] args) {

    }
}
