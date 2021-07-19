package concurrence.thread.create;
/**
 * @Author: Rita
 */
/**
 * 如果我们采用的是继承Thread类的方式，那么这个target就是线程对象自身，如果我们采用的是实现Runnable接口的方式，
 * 那么这个target就是实现了Runnable接口的类的实例。
 run()为线程类的核心方法，相当于主线程的main方法，是每个线程的入口
 a.一个线程调用 两次start()方法将会抛出线程状态异常，也就是的start()只可以被调用一次
 b.native生明的方法只有方法名，没有方法体。是本地方法，不是抽象方法，而是调用c语言方法
 registerNative()方法包含了所有与线程相关的操作系统方法
 c. run()方法是由jvm创建完本地操作系统级线程后回调的方法，不可以手动调用（否则就是普通方法）
 run()可以被线程调用很多次，是按代码的执行顺序执行，不会发生并发的情况
 * */
public class MyThreadThread extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread inheritance Thread");
        for(int i=0;i<10;i++) {
            System.out.println(Thread.currentThread()+":"+i);
        }
    }


}
