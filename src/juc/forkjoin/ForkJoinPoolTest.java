package juc.forkjoin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @Author: Rita
 * fork/join框架通过利用机器上的多个处理器或多个内核来解决问题。
 *
 * 该框架有助于解决涉及并行性的问题。
 *
 * fork/join框架创建一个线程池来执行子任务。
 *
 * 当线程在子任务上等待完成时，框架使用该线程来执行其他线程的其他未决子任务。
 *
 * java.util.concurrent包中的以下四个类是学习fork/join框架的核心：
 *
 * ForkJoinPool
 * ForkJoinTask
 * RecursiveAction
 * RecursiveTask
 * ForkJoinPool类的一个实例表示一个线程池。 ForkJoinTask类的一个实例表示一个任务。
 *
 * ForkJoinTask类是一个抽象类。它有两个具体的子类：RecursiveAction和RecursiveTask。
 *
 * Java 8添加了一个称为CountedCompleter的ForkJoinTask类的抽象子类。
 *
 * 该框架支持两种类型的任务：不产生结果的任务和产生结果的任务。
 *
 * RecursiveAction类的实例表示不产生结果的任务。 RecursiveTask类的实例表示产生结果的任务。
 *
 * CountedCompleter任务可能产生结果，也可能不产生结果。
 *
 * 这两个类，RecursiveAction和RecursiveTask，提供了一个抽象的compute()方法。
 *
 * 我们应该继承这些类之一，并为compute()方法提供一个实现。
 */
public class ForkJoinPoolTest {
    //ForkJoinTask类的以下两个方法在任务执行期间提供了两个重要的功能：
    //fork()方法从异步执行的任务启动一个新的子任务。join()方法让任务等待另一个任务完成。
    @Test
    public void test1(){
        ForkJoinPool pool = new ForkJoinPool();
        IntSum task = new IntSum(3);
        long sum = pool.invoke(task);
        System.out.println("Sum is " + sum);
    }
    class IntSum extends RecursiveTask<Long> {
        private int count;

        public IntSum(int count) {
            this.count = count;
        }

        @Override
        protected Long compute() {
            long result = 0;

            if (this.count <= 0) {
                return 0L;
            } else if (this.count == 1) {
                return (long) this.getRandomInteger();
            }
            List<RecursiveTask<Long>> forks = new ArrayList<>();
            for (int i = 0; i < this.count; i++) {
                IntSum subTask = new IntSum(1);
                subTask.fork(); // Launch the subtask
                forks.add(subTask);
            }
            // all subtasks finish and combine the result
            for (RecursiveTask<Long> subTask : forks) {
                result = result + subTask.join();
            }
            return result;
        }

        public int getRandomInteger() {
            return 2;
        }
    }
}
