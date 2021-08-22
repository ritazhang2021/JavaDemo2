package juc.forkjoin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: Rita
 * @Date:8/19/2021 5:29 PM
 */
public class ForkJoinStreamDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //使用parallerStream必须确保线程安全问题
        //那些耗时很长的任务，请不要使用parallerStream。
        // 假设原本一个任务执行需要1分钟时间，有10个任务并行执行，
        // 如果你偷懒，只是使用parallerStream来将这10个任务并行执行，
        // 那你这个jvm进程中，其它同样使用parallerStream的地方也会因此被阻塞住，严重的将会导致整个服务瘫痪。
        numbers.parallelStream()
                .forEach((n)->{
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(n);
                });
        System.out.println("******************************");
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //这样可以确保
        ForkJoinPool pool = new ForkJoinPool(1);
        Object o = pool.submit(() -> {
            System.out.println(Thread.currentThread().getName());

            numbers2.parallelStream().forEach((n)->{
                System.out.println(Thread.currentThread().getName());
                System.out.println(n);
            });
        }).get();
        //e ForkJoinPool(int, ForkJoinWorkerThreadFactory, UncaughtExceptionHandler, boolean, int, int, int, Predicate, long, TimeUnit)).
        ForkJoinPool forkJoinPool = new ForkJoinPool(


        );

    }
}
