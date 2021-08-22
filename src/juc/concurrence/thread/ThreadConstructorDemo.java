package juc.concurrence.thread;

/**
 * @Author: Rita
 */
public class ThreadConstructorDemo {
    Thread t1 = new Thread();
    //Thread (Runnable target)
    Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    });
    //Thread (Runnable target, String name)
    Thread t3 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }, "thread3");
    //Thread (String name)
    Thread t4 = new Thread("thread4");
    //Thread (ThreadGroup group, Runnable target)
    Thread t5 = new Thread(new ThreadGroup("thread group" ), "t5");
    //Thread (ThreadGroup  group,  Runnable  target,  String  name)
    Thread t6 = new Thread(new ThreadGroup("thread group"), new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    },"thread6");
    //Thread (ThreadGroup   group,   Runnable   target,   String   name, long   stackSize)
    Thread t7 = new Thread(new ThreadGroup("thread group"), new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    },"thread7", 10);
    //Thread (ThreadGroup    group,    Runnable    target,    String    name, long    stackSize, boolean    inheritThreadLocals)
    Thread t8 = new Thread(new ThreadGroup("thread group"), new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    },"thread8", 10, true);
    //Thread (ThreadGroup group, String name)
    Thread t9 = new Thread(new ThreadGroup("thread group"),"thread9");
}
