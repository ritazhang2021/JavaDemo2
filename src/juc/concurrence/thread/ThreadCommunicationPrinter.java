package juc.concurrence.thread;

/**
 * @Author: Rita
 */
/**
 * 使用两个线程线程1, 线程2 交替打印100页
 *
 * 涉及到的三个方法：
 * wait():The current thread blocks and releases the synchronization monitor once executed.
 * notify():Wake up a thread that was waited.If more than one thread is waited, the one with the higher priority is awakened.
 * notifyAll():Wake up all threads that have been waited on。
 *
 * 说明：
 * 1.wait()，notify()，notifyAll()
 * All three methods must be used in synchronized code.
 * The caller must be a synchronization monitor.
 * otherwise, there will be a IllegalMonitorStateException anomalies
 * The three methods are defined in the java.lang.Object class.
 *
 difference between sleep() and wait()
 * 1.they both can cause the current thread a blocking state
 * 2.1）sleep() in Thread, wait() in Object
 *   2）sleep() can be use in anywhere of code。 wait() must in synchronized code.
 *    3）sleep() can not release the locker，wait() can。
 * */
public class ThreadCommunicationPrinter{
    public static void main(String[] args) {
        PrintPageNumber number = new PrintPageNumber();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("Thread1");
        t2.setName("Thread2");

        t1.start();
        t2.start();
    }
}
class PrintPageNumber implements Runnable{
    private int number = 1;
    private Object obj = new Object();
    @Override
    public void run() {
        while(true){
            synchronized (obj) {//需要一个实例，不论地址
                obj.notify();
                if(number <= 100){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;
                    try {
                        //进入阻塞状态
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }
}

