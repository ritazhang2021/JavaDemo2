package concurrence.thread;

import com.sun.tools.javac.Main;
import org.junit.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * @Author: Rita
 */
public class ThreadAPI {
    //以下实例演示了如何通过继承Thread类并使用currentThread()方法来检测一个线程是否存活
    @Test
    public void test1(){
        Thread t = new Thread();
        t.setName("Current Thread");
        System.out.println("before start(), t.isAlive()=" + t.isAlive());
        t.start();
        System.out.println("just after start(), t.isAlive()=" + t.isAlive());
        System.out.println("The end of test1(), t.isAlive()=" + t.isAlive());
    }

    //以下实例演示了使用 getName() 方法来获取当前线程名称：
    @Test
    public void test2(){
        Thread t = new Thread();
        String name = t.getName();
        System.out.println(name);
    }

    //以下实例演示了使用 currentThread.getName() 方法来监测线程的状态
    @Test
    public void test3(){
        boolean alive = Thread.currentThread().isAlive();
        Thread.State state = Thread.currentThread().getState();

    }

    //以下实例演示了如何通过setPriority() 方法来设置线程的优先级
    @Test
    public void test4(){
        //only 1 to 10
        Thread t = new Thread();
        t.setPriority(Thread.MAX_PRIORITY);
        t.setPriority(5);
        t.setPriority(Thread.MIN_PRIORITY);
        t.setPriority(1);
        //t.setPriority(0);

    }

    //以下实例演示了如何将线程挂起：
    @Test
    public void test5(){


    }
    /**
     Java中原来在Thread中提供了stop()方法来终止线程，但这个方法是不安全的，所以一般不建议使用。
     本文向大家介绍使用interrupt方法中断线程。
     使用interrupt方法来终端线程可分为两种情况：
     （1）线程处于阻塞状态，如使用了sleep方法。
     （2）使用while（！isInterrupted（））{……}来判断线程是否被中断。
     在第一种情况下使用interrupt方法，sleep方法将抛出一个InterruptedException例外，
     而在第二种情况下线程将直接退出。下面的代码演示了在第一种情况下使用interrupt方法。
     * */
    @Test
    public void test6() throws IOException, InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    //Causes the currently executing thread to sleep
                    sleep(50000);  // 延迟50秒
                }
                catch (InterruptedException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        });

        thread.start();
        System.out.println("在50秒之内按任意键中断线程!");
        System.in.read();
        thread.interrupt();
        thread.join();
        System.out.println("线程已经退出!");
    }

    //以下实例演示了如何将线程挂起：
    @Test
    public void test7(){
        Thread thread = new Thread();
        long id = thread.getId();
        System.out.println(id);


    }

    //以下实例演示了如何使用interrupt()方法来中断线程并使用 isInterrupted() 方法来判断线程是否已中断：
    @Test
    public void test8(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("in run() - 将运行 work2() 方法");
                    work2();
                    System.out.println("in run() - 从 work2() 方法回来");
                }
                catch (InterruptedException x) {
                    System.out.println("in run() - 中断 work2() 方法");
                    return;
                }
                System.out.println("in run() - 休眠后执行");
                System.out.println("in run() - 正常离开");
            }
        });
        t.start();
        try {
            Thread.sleep(2000);
        }
        //你可以中断一个被阻塞的线程。
        //如果在这三种方法上阻塞的线程被中断，则抛出一个InterruptedException，并清除线程的中断状态。
        //以下代码启动一个休眠一秒的线程，并打印一条消息，直到它被中断。
        catch (InterruptedException x) {
        }
        System.out.println("in main() - 中断其他线程");
        t.interrupt();
        System.out.println("in main() - 离开");
    }

    public void work2() throws InterruptedException {
        while (true) {
            //Thread类有一个非静态的isInterrupted()方法，可以用来测试一个线程是否被中断。
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("C isInterrupted()=" + Thread.currentThread().isInterrupted());
                Thread.sleep(2000);
                System.out.println("D isInterrupted()=" + Thread.currentThread().isInterrupted());
            }
        }
    }
    public void work() throws InterruptedException {
        while (true) {
            for (int i = 0; i < 100000; i++) {
                int j = i * 2;
            }
            System.out.println("A isInterrupted()=" + Thread.currentThread().isInterrupted());
            if (Thread.interrupted()) {
                System.out.println("B isInterrupted()=" + Thread.currentThread().isInterrupted());
                throw new InterruptedException();
            }
        }
    }
    //以下代码创建一个线程并将线程设置为守护线程。
    @Test
    public void test9(){
        Thread t = new Thread(ThreadAPI::print);
        t.setDaemon(true);
        t.start();
        System.out.println("Exiting main  method");
    }
    public static void print() {
        int counter = 1;
        while (true) {
            try {
                System.out.println("Counter:" + counter++);
                Thread.sleep(2000); // sleep for 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //以下代码将线程设置为非守护线程。由于这个程序有一个非守护线程，JVM将继续运行应用程序，即使在main()方法完成后。
    //您必须强制停止此应用程序，因为线程在无限循环中运行。
    @Test
    public void test10(){
        Thread t = new Thread(ThreadAPI::print);
        t.setDaemon(false);
        t.start();
        System.out.println("Exiting main  method");
    }

    //下面的代码演示了如何模拟Thread类中的stop()，suspend()和resume()方法。
    @Test
    public void test11() throws InterruptedException {
        MyThread t = new MyThread();
        t.start();
        Thread.sleep(2000);
        t.suspendThread();
        Thread.sleep(2000);
        t.resumeThread();
        Thread.sleep(2000);
        t.stopThread();
    }
    public static class MyThread extends Thread {
        private volatile boolean keepRunning = true;
        private boolean suspended = false;

        public synchronized void stopThread() {
            this.keepRunning = false;
            this.notify();
        }

        public synchronized void suspendThread() {
            this.suspended = true;
        }

        public synchronized void resumeThread() {
            this.suspended = false;
            this.notify();
        }

        @Override
        public void run() {
            System.out.println("Thread started...");
            while (keepRunning) {
                try {
                    System.out.println("Going to sleep...");
                    Thread.sleep(1000);
                    synchronized (this) {
                        while (suspended) {
                            System.out.println("Suspended...");
                            this.wait();
                            System.out.println("Resumed...");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



