package concurrence;

import com.sun.tools.javac.Main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
@Author: Rita
i = i + 1;
当线程执行这个语句时，会先从主存当中读取i的值，然后复制一份到高速缓存当中，然后CPU执行指令对i进行加1操作，
 然后将数据写入高速缓存，最后将高速缓存中i最新的值刷新到主存当中。所以这种操作没有原子性。
 缓存一致性问题
 初始时，两个线程分别读取i的值存入各自所在的CPU的高速缓存当中，然后线程1进行加1操作，然后把i的最新值1写入到内存。
 此时线程2的高速缓存当中i的值还是0，进行加1操作之后，i的值为1，然后线程2把i的值写入内存。
 也就是说，如果一个变量在多个CPU中都存在缓存（一般在多线程编程时才会出现），那么就可能存在缓存不一致的问题。
 为了解决缓存不一致性问题，通常来说有以下2种解决方法：
 　　1）通过在总线加LOCK#锁的方式
 　　2）通过缓存一致性协议(MESI)
 */
public class VolatileDemo {
    /**
     一旦一个共享变量（类的成员变量、类的静态成员变量）被volatile修饰之后，那么就具备了两层语义：
     　　1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
     　　2）禁止进行指令重排序。
     */
    volatile static int volatileNum = 0;
    static ExecutorService ex= Executors.newFixedThreadPool(5);
    public static void main(String[] args) {

        //test whether to read from memory every time in the concurrency case
//        test1(ex);
//        test2(ex);

//       increase();
//        increaseSynchronized();
//        increaseLock();
//        increaseAtomicInteger();
//        Ordering();
    }
    public static void test1(ExecutorService ex){

        int m = 5;
        while (m > 0){
            for(int i=0;i<5;i++) {
                ex.submit(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName()+"->"+volatileNum);
                    }
                });
                //volatileNum++;
            }
            volatileNum++;
            m--;
        }
        ex.shutdown();
    }

    volatile static int inc = 0;
    public static void increase(){
        for(int i=0;i<10;i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        /**
                         自增操作是不具备原子性的，它包括读取变量的原始值、进行加1操作、写入工作内存。
                         那么就是说自增操作的三个子操作可能会分割开执行，就有可能导致每次线程拿到的不是最新的数据。
                         volatileNum能保持每次读取的是最新的值，不能保证原子性
                         一个变量在修改volatile变量时，会让缓存无效，然后其他线程去读就会读到新的值，
                         但是要注意，当线程1对变量进行读取操作之后，被阻塞了的话，就不会对inc值进行修改。
                         然后虽然volatile能保证线程2对变量inc的值读取是从内存中读取的，但是线程1没有进行修改，所以线程2根本就不会看到修改的值。
                         解决办法：synchronized， Lock，AtomicInteger
                         */
                        inc++;
                    }
                }
            }.start();
        }
        //保证前面的线程都执行完
        while(Thread.activeCount()>1){
            Thread.yield();
            //every time print a number less than 1000
            System.out.println(inc);
        }
    }
    public synchronized static void increaseSynchronized(){
        for(int i=0;i<10;i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        inc++;
                    }
                }
            }.start();
        }
        //保证前面的线程都执行完
        while(Thread.activeCount()>1){
            Thread.yield();
            //every time print a number 1000
            System.out.println(inc);
        }
    }

    public static void increaseLock(){
        Lock lock = new ReentrantLock();
        for(int i=0;i<10;i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        lock.lock();
                        try {
                            inc++;
                        } finally{
                            lock.unlock();
                        }
                    }
                }
            }.start();
        }
        //保证前面的线程都执行完
        while(Thread.activeCount()>1){
            Thread.yield();
            //every time print a number 1000
            System.out.println(inc);
        }
    }
    //java.util.concurrent.atomic包下提供了一些原子操作类
    public static void increaseAtomicInteger(){
        AtomicInteger inc = new AtomicInteger();
        for(int i=0;i<10;i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        inc.getAndIncrement();
                    }
                }
            }.start();
        }
        //保证前面的线程都执行完
        while(Thread.activeCount()>1){
            Thread.yield();
            //every time print a number 1000
            System.out.println(inc);
        }
    }
    public static void Ordering() {

    }
    volatile static boolean flag = false;
    public static void test2(ExecutorService ex){
        ex.submit(new Runnable() {
            @Override
            public void run() {
                while(!flag){
                    System.out.println("while loop is working");
                }
            }
        });
        ex.submit(new Runnable() {
            @Override
            public void run() {
                flag = true;
            }
        });
    }
    //下面的代码演示了volatile变量的使用。
    public static void test3(ExecutorService ex) throws InterruptedException {
        Mythread t1 = new Mythread();
        t1.start();
        Thread.sleep(3000);
        System.out.println("Going to set the stop flag to true");
        t1.stopThread();
    }
    public static class Mythread extends Thread {
        private volatile boolean keepRunning = true;

        @Override
        public void run() {
            System.out.println("Thread started");
            while (keepRunning) {
                try {
                    System.out.println("Going to sleep");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread stopped");
        }

        public void stopThread() {
            this.keepRunning = false;
        }
    }


}
