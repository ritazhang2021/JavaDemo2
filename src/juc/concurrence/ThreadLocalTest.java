package juc.concurrence;

import org.junit.Test;

/**
 * @Author: Rita
 * 线程局部变量分隔每个线程的变量的值。
 *
 * java.lang包中的ThreadLocal类提供了一个线程局部变量的实现。
 *
 * 它有四个方法:get()，set()，remove()和initialValue()。
 *
 * get()和set()方法分别用于获取和设置线程局部变量的值。
 *
 * 您可以使用remove()方法删除该值。
 *
 * initialValue()方法设置变量的初始值，它具有受保护的访问。要使用它，子类ThreadLocal类并重写此方法。
 *
 * * ThreadLocal 类的常用方法 *
 *  *      ThreadLocal() : 创建一个线程本地变量
 *  *     get() : 返回此线程局部变量的当前线程副本中的值
 *  *     initialValue() : 返回此线程局部变量的当前线程的"初始值"
 *  *     set(T value) : 将此线程局部变量的当前线程副本中的值设置为value
 * * ThreadLocal与同步机制
 *   a.ThreadLocal与synchronization都是为了解决多线程中相同变量的访问冲突问题。
 *   b.前者采用以"空间换时间"的方法，后者采用以"时间换空间"的方式
 */
//项目中有一个用Account类做的实例
public class ThreadLocalTest {
    //以下代码显示如何使用ThreadLocal类。
    @Test
    public void test(){
        new Thread(ThreadLocalTest::run).start();
        new Thread(ThreadLocalTest::run).start();
    }
    public static void run() {
        int counter = 3;
        System.out.println(Thread.currentThread().getName()+ "  generated counter:  " + counter);
        for (int i = 0; i < counter; i++) {
            CallTracker.call();
        }
    }
    static class CallTracker {
        private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

        public static void call() {
            int counter = 0;
            Integer counterObject = threadLocal.get();

            if (counterObject == null) {
                counter = 1;
            } else {
                counter = counterObject.intValue();
                counter++;
            }
            threadLocal.set(counter);
            String threadName = Thread.currentThread().getName();
            System.out.println("Call  counter for " + threadName + "  = " + counter);
        }
    }
}
