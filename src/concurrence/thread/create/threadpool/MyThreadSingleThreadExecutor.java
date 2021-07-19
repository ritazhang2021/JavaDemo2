package concurrence.thread.create.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @Author: Rita
 need to ensure that the tasks are executed sequentially
 */
public class MyThreadSingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService ex= Executors.newSingleThreadExecutor();

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
