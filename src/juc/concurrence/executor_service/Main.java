package juc.concurrence.executor_service;

import java.util.stream.LongStream;

/**
 * @Author: Rita

 */
public class Main {
    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 1000).toArray();
        Calculator calculator = new ForLoopCalculator();
        System.out.println(calculator.sumUp(numbers)); // 打印结果500500
    }
}
