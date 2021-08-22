package juc.concurrence.thread;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: Rita
 * 新线程并发包
 * java.util.concurrent和java.util.concurrent.atomic和java.util.concurrent.locks包括非常有用的并发构造。
 *
 * 线程并发包以四种方式支持并发。
 *
 * 原子变量
 * 锁
 * 同步器
 * 并发集合
 * 原子变量
 * 原子变量类的命名类似于AtomicXxx，例如，AtomicInteger类用于表示一个int变量。
 *
 * 原子变量可用于在不使用任何锁的情况下以原子方式对单个变量执行多个指令。
 *
 * 标量原子变量类
 * AtomicInteger，AtomicLong和AtomicBoolean类分别支持对原始数据类型int，long和boolean的操作。
 *
 * 当引用变量需要以原子方式更新时，AtomicReference类用于处理引用数据类型。
 *
 * 原子数组类
 * 有三个类称为AtomicIntegerArray，AtomicLongArray和AtomicReferenceArray，它们表示一个int，long和引用类型的数组，其元素可以进行原子性更新。
 *
 * 原子字段更新程序类
 * 有三个类称为AtomicLongFieldUpdater，AtomicIntegerFieldUpdater和AtomicReferenceFieldUpdater，可用于使用反射以原子方式更新类的易失性字段。
 *
 * 要获得对这些类的对象的引用，您需要使用他们的工厂方法newUpdater()。
 *
 * 原子复合变量类
 */

public class AtomicLongTest {
    //以下代码显示如何使用AtomicLong类来创建计数器。
    @Test
    public void test(){
         AtomicLong value = new AtomicLong(0L);


    }
    public long next(AtomicLong value) {
        return value.incrementAndGet();
    }
}
