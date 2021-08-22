package juc.concurrence.thread;

/**
 * @Author: Rita
 */
public class CustomThread {

    public static void main(String[] args) {
        MyThread h1 = new MyThread("MyThread");
        //toString Returns a string representation of this thread, including the thread's name, priority, and thread group.
        System.out.println(h1.toString());
        //execute MyThread
        myThread(h1);
        //execute Current Thread
        currentThread(h1);

    }
    public static void myThread(MyThread h1){

        h1.setName("我的线程");
        /**
         * 线程的优先级：
         * 1.
         * MAX_PRIORITY：10
         * MIN _PRIORITY：1
         * NORM_PRIORITY：5  -->默认优先级
         * 2.如何获取和设置当前线程的优先级：
         *   getPriority():获取线程的优先级
         *   setPriority(int p):设置线程的优先级         *
         *   说明：高优先级的线程要抢占低优先级线程cpu的执行权。但是只是从概率上讲，高优先级的线程高概率的情况下被执行。
         *   并不意味着只有当高优先级的线程执行完以后，低优先级的线程才执行。
         */
        h1.setPriority(Thread.MAX_PRIORITY);
        h1.start();
    }
    public static void currentThread(MyThread h1){
        //给主线程命名
        Thread.currentThread().setName("主线程-main");
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":Priority" +Thread.currentThread().getPriority() + ":i=" + i);
            }
            if(i == 20){
                try {
                    //在线程a中调用线程b的join(),此时线程a就进入阻塞状态，直到线程b完全执行完以后，线程a才结束阻塞状态。
                    h1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(h1.isAlive());
    }
}
//只能在一个public的类
class MyThread extends Thread{
    private boolean waiting= true;
    private boolean ready= false;

    public boolean getWaiting() {
        return waiting;
    }

    public boolean getReady() {
        return ready;
    }


    public MyThread(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                try {
                    //在指定的millitime毫秒时间内，当前线程是阻塞状态。
                    sleep(100);
                    System.out.println("excuted sleep......" );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //currentThread():静态方法，返回执行当前代码的线程
                System.out.println(Thread.currentThread().getName() + ":Priority" +Thread.currentThread().getPriority() + ":i=" + i);
            }
            if(i % 20 == 0){
                //yield():释放当前cpu的执行权(释放后cpu有可能还会切换到当前线程) (并不是结束线程)
                //看谁先抢到，有可能还是当前的线程抢到
                Thread.yield();
                System.out.println("excuted yield......" );
            }
        }
    }

}
