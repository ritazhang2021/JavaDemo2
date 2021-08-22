package juc.concurrence.sync;
/**
 * @Author: Rita
 * 1、在构造函数中使用synchronized关键字是语法错误。同步构造函数没有意义，因为只有创建对象的线程在构建时才能访问它。 *
 * 2、如果一个对象对多个线程可见，则通过同步方法完成对该对象变量的所有读取或写入操作，不然还是会出现线程干扰和内存一致性错误。
 * */
public class SynchronizedUsedOnMethod {

    private int a = 0;

    public synchronized void increment() {
        a++;
    }

    public synchronized void decrement() {
        a--;
    }

    public synchronized int value() {
        return a;
    }
}
