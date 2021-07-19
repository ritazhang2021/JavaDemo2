package concurrence.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * @Author: Rita
 A. Implement the Runnable interface to avoid multiple inheritance limitations
 B. Implementing Runnable() can better reflect the concept of sharing
  Call () has a return value
 Runnable接口中的run()方法的返回值是void，它做的事情只是纯粹地去执行run()方法中的代码而已；
 Callable接口中的call()方法是有返回值的，支持泛型，和Future、FutureTask配合可以用来获取异步执行的结果。
 */
public class MyThreadCallable implements Callable<String> {
    private int count = 20;

    @Override
    public String call() throws Exception {
        for (int i = count; i > 0; i--) {
//			mThread3.yield();
            System.out.println(Thread.currentThread().getName()+"-Current Remaining ticket: " + i);
        }
        return "Sold out";
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> callable  =new MyThreadCallable();
        FutureTask<String> futureTask=new FutureTask<>(callable);
        Thread mThread1=new Thread(futureTask);
        Thread mThread2=new Thread(futureTask);
        Thread mThread3=new Thread(futureTask);
//		mThread.setName("xxx");
        mThread1.start();
        mThread2.start();
        mThread3.start();
        System.out.println(futureTask.get());

    }

}
