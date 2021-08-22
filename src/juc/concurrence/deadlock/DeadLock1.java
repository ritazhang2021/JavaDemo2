package juc.concurrence.deadlock;

/**
 @Author: Rita
 不同的线程分别占用对方需要的同步资源不放弃，都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
 两个线程同时执行init（）和run（）
 其中一个线程在A类中调用B实例b的方法methodInsideB(),同步监视器是a, 另一个线程同时在B类中用A的实例a的methodInsideA方法，同步监视器是b，
 在A的进程中，同步监视器是a，需要b实例作为锁去调用B类中的方法，
 而b实例正在作为锁在B类中执行另一个进程，并且需要a实例作为锁去调用A类中的方法
 就形成了死锁
 */
public class DeadLock1 implements Runnable{
    A a = new A();
    B b = new B();

    public void init() {
        Thread.currentThread().setName("main thread.....");
        System.out.println("entered main thread.....");
        // 调用a对象的methodA方法
        a.methodA(b);
    }

    @Override
    public void run() {
        Thread.currentThread().setName("second thread inside of main thread....");
        System.out.println("entered second thread inside of main thread");
        // 调用b对象的methodB方法
        b.methodB(a);
    }

    public static void main(String[] args) {
        DeadLock1 deadLock1 = new DeadLock1();
        new Thread(deadLock1).start();
        deadLock1.init();
    }
}
class A {
    public synchronized void methodA(B b) { //同步监视器：A类的对象：a
        System.out.println("current thread: " + Thread.currentThread().getName() + " Entered methodA of instance A"); // 1
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
        System.out.println("current thread: " + Thread.currentThread().getName() + " attempt to call the methodInsideB of instance B"); // 3
        b.methodInsideB();
    }

    public synchronized void methodInsideA() {//同步监视器：A类的对象：a
        System.out.println("Entered methodInsideA of class A");
    }
}

class B {
    public synchronized void methodB(A a) {//同步监视器：b
        System.out.println("current thread: " + Thread.currentThread().getName()
                + " Entered methodB of instance B"); // 2
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException ex) {
//			ex.printStackTrace();
//		}
        System.out.println("current thread: " + Thread.currentThread().getName()
                + " attempt to call the methodInsideA of instance A"); // 4
        a.methodInsideA();
    }

    public synchronized void methodInsideB() {//同步监视器：b
        System.out.println("Entered methodInsideB of class B");
    }
}
