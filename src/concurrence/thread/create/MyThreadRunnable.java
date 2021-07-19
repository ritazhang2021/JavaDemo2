package concurrence.thread.create;
/**
 * @Author: Rita
 * a.Overwriting the Runnable interface to avoids the single-inheritance limitation
 * b.When a subclass implements the Runnable interface, the subclass and Thread's proxy mode
 * (subclass is responsible for real business operations, Thread is responsible for resource
 * scheduling and Thread creation to assist real business operations).
 * 当子类实现Runnable接口，此时子类和Thread的代理模式（子类负责真是业务的操作，thread负责资源调度与线程创建辅助真实业务。
 * 如果我们采用的是继承Thread类的方式，那么这个target就是线程对象自身，如果我们采用的是实现Runnable接口的方式，
 * 那么这个target就是实现了Runnable接口的类的实例。
 */
public class MyThreadRunnable implements Runnable{
    public static int count=50;
    @Override
    public void run() {
        while(count>0) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-Current Remaining ticket:"+count--);
        }
    }
    public static void main(String[] args) {
        MyThreadRunnable Thread1 = new MyThreadRunnable();
        Thread mThread1 = new Thread(Thread1,"Thread1");
        Thread mThread2 = new Thread(Thread1,"Thread2");
        Thread mThread3 = new Thread(Thread1,"Thread3");
        //会有重票现象
        mThread1.start();
        mThread2.start();
        mThread3.start();
    }
}
