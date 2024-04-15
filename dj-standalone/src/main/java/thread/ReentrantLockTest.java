package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jiandong 2024-03-05 create
 */
public class ReentrantLockTest {

        public static void main(String[] args) {
            ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
            reentrantLockTest.test();
        }

        public void test() {
            ReentrantLock lock = new ReentrantLock();
            lock.lock();
            try {
                System.out.println("lock");
            } finally {
                lock.unlock();
            }
        }
}
