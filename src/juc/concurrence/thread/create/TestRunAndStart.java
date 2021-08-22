package juc.concurrence.thread.create;

/**
 * @Author: Rita
 */

public class TestRunAndStart {
    public static void main(String[] args) {
        //one thread can only be started once
       // testThreadStart();
        //one thread can be run many times
        //testThreadRun();
        concurrency();
    }

    public static void testThreadStart(){
        MyThreadThread myThread1 = new MyThreadThread();
        myThread1.start();
        myThread1.start();//wrong
    }
    public static void testThreadRun(){
        MyThreadThread myThread1 = new MyThreadThread();
        myThread1.run();
        myThread1.run();
    }
    public static void concurrency() {
        MyThreadThread mThread1=new MyThreadThread();
        MyThreadThread mThread2=new MyThreadThread();
        MyThreadThread myThread3=new MyThreadThread();
        //they have chance to get the same i
        //告诉CPU线程ready了，CPU有时间就可以执行它，所以会有并发的情况，执行时调用run方法
        mThread1.start();
        mThread2.start();
        myThread3.start();
        //they have no chance to get the same i
        //不会有并发的情况发生，会按代码从上到下的执行顺序执行
        mThread1.run();
        mThread1.run();
        mThread1.run();
    }
}
