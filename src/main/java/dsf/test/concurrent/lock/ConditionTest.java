package dsf.test.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 董少飞
 * @date 2020/2/3
 */
public class ConditionTest {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition1 = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        new Thread(ConditionTest::fun1).start();
        new Thread(ConditionTest::fun2).start();
    }

    public static void fun1() {

        lock.lock();
        try {
            condition1.await();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void fun2() {

        lock.lock();
        try {
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }
}
