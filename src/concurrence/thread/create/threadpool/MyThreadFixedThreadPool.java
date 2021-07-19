package concurrence.thread.create.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @Author: Rita
 Used when the current number of threads needs to be limited in order to meet resource management requirements.
 Used on heavily loaded servers.
 */
public class MyThreadFixedThreadPool {
    public static void main(String[] args) {
        ExecutorService ex= Executors.newFixedThreadPool(5);
        for(int i=0;i<3;i++) {
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
