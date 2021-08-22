package juc.concurrence.thread;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: Rita
 */
public class LockTest {
    /**
     显式锁定机制可以用于协调对多线程环境中的共享资源的访问。

     在java.util.concurrent.locks包中声明的Lock接口定义了显式锁定操作。

     ReentrantLock类在同一个包中，是Lock接口的具体实现。

     Lock接口声明如下：
     * */
    //lock()方法获取一个锁的行为与使用synchronized关键字相同。
    //我们必须在完成锁定后通过调用Lock接口的unlock()方法释放锁定。
    @Test
    public void test1(){
        // Instantiate the lock object
         Lock myLock = new ReentrantLock();

    }
    public void updateResource( Lock myLock) {

        try {
            // Acquire the lock
            myLock.lock();
        } finally {
            // Release the lock
            myLock.unlock();
        }
    }
    //ReentrantReadWriteLock类是ReadWriteLock接口的实现。只有一个线程可以保持ReentrantReadWriteLock的写锁，而多个线程可以保持其读锁。
   //下面的代码演示了ReentrantReadWriteLock的用法。
    @Test
    public void test2(){
         int value;
         ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
         Lock rLock = rwLock.readLock();
         Lock wLock = rwLock.writeLock();
    }

}
