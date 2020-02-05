package dsf.test.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 董少飞
 * @date 2020/2/4
 */
public class ReentrantReadWriteLockTest {

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args) {

        new Thread(ReentrantReadWriteLockTest::fun1, "read-0-Thread").start();
        new Thread(ReentrantReadWriteLockTest::fun1, "read-1-Thread").start();
        new Thread(ReentrantReadWriteLockTest::fun2, "write-thread").start();
    }

    public static void fun1() {

        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
        } finally {
            readLock.unlock();
        }
    }

    public static void fun2() {

        writeLock.lock();
        try {
            System.out.println("write");
        } finally {
            writeLock.unlock();
        }
    }
}
