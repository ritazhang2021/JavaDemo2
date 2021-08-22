package juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @Author: Rita
 */
public class ForkJoinLambdaDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(2);
        Long ret = pool.submit(() -> {
            return LongStream.range(1, 50 * 1024 * 1024).boxed().collect(Collectors.toList())
                    .stream()
                    .parallel()
                    .map(x -> x * 2)
                    .filter(x -> x < 1500)
                    .reduce((x, y) -> x + y)
                    .get();
        }).get();
        System.out.println(ret);
    }
}
