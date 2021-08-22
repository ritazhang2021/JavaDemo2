package juc.concurrence.thread.create.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @Author: Rita
 The cached thread pool will constantly create threads when the submitted task is faster than the task in the pool
 It is suitable for small asynchronous applets, light - load servers
 */

public class MyThreadCachedThreadPool {
    public static void main(String[] args) {
        ExecutorService ex= Executors.newCachedThreadPool();
        for(int i=0;i<5;i++) {
            ex.submit(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10;j++) {
                        System.out.println(Thread.currentThread().getName()+"->"+j);
                    }

                }
            });
        }
        ex.shutdown();
    }

}
