package concurrence.synchronize;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author: Rita
 * 信号量用于控制可以访问资源的线程数。
 *
 * java.util.concurrent包中的Semaphore类表示信号量同步器。
 *
 * 如果你创建一个公平的信号量，在多线程请求许可的情况下，信号量将保证先进先出（FIFO）。也就是说，首先请求许可的线程将首先获得许可。
 *
 * 要获取许可证，请使用acquire()方法。
 *
 * 如果许可证可用，它立即返回。
 *
 * 它阻止如果许可证不可用。线程在等待许可证可用时可能中断。
 *
 * Semaphore类的其他方法允许您一次性获取一个或多个许可证。要释放许可证，请使用release()方法。
 */
public class SynchronizeTest {
    //四种类型的同步器：
    //Semaphores
    //Barriers
    //Latches
    //Exchangers
    //以下代码显示了一个Restaurant类，它使用信号量来控制对表的访问。
    @Test
    public void test3() {
        Restaurant restaurant = new Restaurant(2);
        for (int i = 1; i <= 5; i++) {
            RestaurantCustomer c = new RestaurantCustomer(restaurant, i);
            c.start();
        }
    }
    class Restaurant {
        private Semaphore tables;

        public Restaurant(int tablesCount) {
            this.tables = new Semaphore(tablesCount);
        }

        public void getTable(int customerID) {
            try {
                System.out.println("Customer  #" + customerID
                        + "  is trying  to  get  a  table.");
                tables.acquire();
                System.out.println("Customer #" + customerID + "  got  a  table.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void returnTable(int customerID) {
            System.out.println("Customer #" + customerID + "  returned a  table.");
            tables.release();
        }
    }
    static class RestaurantCustomer extends Thread {
        private Restaurant r;
        private int customerID;
        private static final Random random = new Random();

        public RestaurantCustomer(Restaurant r, int customerID) {
            this.r = r;
            this.customerID = customerID;
        }

        @Override
        public void run() {
            r.getTable(this.customerID); // Get a table
            try {
                int eatingTime = random.nextInt(30) + 1;
                System.out.println("Customer #" + this.customerID
                        + "  will eat for " + eatingTime + "  seconds.");
                Thread.sleep(eatingTime * 1000);
                System.out.println("Customer #" + this.customerID
                        + "  is done  eating.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                r.returnTable(this.customerID);
            }
        }
    }
    //障碍器
    /**
     屏障使一组线在屏障点汇合。

     来自到达屏障的组的线程等待，直到该组中的所有线程到达。

     一旦组中的最后一个线程到达屏障，组中的所有线程都将被释放。

     当你有一个可以分成子任务的任务时，你可以使用一个屏障;每个子任务可以在单独的线程中执行，并且每个线程必须在共同点处相遇以组合它们的结果。

     java.util.concurrent包中的CyclicBarrier类提供了屏障同步器的实现。

     CyclicBarrier类可以通过调用其reset()方法来重用。
     * */
    //以下代码显示了如何在程序中使用循环障碍。
    @Test
    public void test4() {
        Runnable barrierAction = () -> System.out.println("We are ready.");
        CyclicBarrier barrier = new CyclicBarrier(3, barrierAction);
        for (int i = 1; i <= 3; i++) {
            Worker t = new Worker(i, barrier);
            t.start();
        }
    }

    class Worker extends Thread {
        private CyclicBarrier barrier;
        private int ID;
        private Random random = new Random();

        public Worker(int ID, CyclicBarrier barrier) {
            this.ID = ID;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                int workTime = random.nextInt(30) + 1;
                System.out.println("Thread #" + ID + " is going to work for " + workTime + "  seconds");
                Thread.sleep(workTime * 1000);
                System.out.println("Thread #" + ID + " is waiting at the barrier.");
                this.barrier.await();
                System.out.println("Thread #" + ID + " passed the barrier.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                System.out.println("Barrier is broken.");
            }
        }

    }
    /**
     Phasers
     Phaser提供类似于CyclicBarrier和CountDownLatch同步器的功能。它提供以下功能：

     Phaser是可重复使用的。

     在Phaser上同步的参与方数量可以动态更改。在循环障碍中，当创建障碍时，方的数量是固定的。

     移相器具有相关的相位编号，从零开始。当所有注册方都到达移相器时，移相器进入下一个阶段，阶段编号加1。相位编号的最大值为Integer.MAX_VALUE。在其最大值之后，相位编号重新从零开始。

     Phaser有终止状态。在终止状态的Phaser上调用的所有同步方法立即返回，而不等待提前。

     移相器有三种类型的参与者计数：注册参与者计数，到达参与者计数和未参与方计数。

     注册方数量是注册同步的方的数量。到达的当事方数目是已经到达移相器的当前阶段的各方的数目。

     未携带者数量是尚未到达移动器的当前阶段的各方的数量。

     当最后一方到达时，移相器前进到下一阶段。

     或者，当所有注册方都到达移动器时，Phaser可以执行移相器操作。

     CyclicBarrier允许您执行屏障操作，这是一个Runnable任务。

     我们通过在Phaser类的onAdvance()方法中编写代码来指定移相器操作。

     我们需要继承Phaser类，并覆盖onAdvance()方法以提供Phaser动作。
     * */
    //以下代码显示了如何表示通过在Phaser上同步启动的任务
    @Test
    public void test5() {
        Phaser phaser = new Phaser(1);
        for (int i = 1; i <= 3; i++) {
            phaser.register();
            String taskName = "Task  #" + i;
            StartTogetherTask task = new StartTogetherTask(taskName, phaser);
            task.start();
        }
        phaser.arriveAndDeregister();
    }
    static class StartTogetherTask extends Thread {
        private Phaser phaser;
        private String taskName;
        private static Random rand = new Random();

        public StartTogetherTask(String taskName, Phaser phaser) {
            this.taskName = taskName;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            System.out.println(taskName + ":Initializing...");
            int sleepTime = rand.nextInt(5) + 1;
            try {
                Thread.sleep(sleepTime * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(taskName + ":Initialized...");
            phaser.arriveAndAwaitAdvance();
            System.out.println(taskName + ":Started...");
        }
    }
    //以下代码显示了如何向Phaser添加Phaser Action。
    @Test
    public void test6() {
        Phaser phaser = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int parties) {
                System.out.println("Inside onAdvance(): phase  = " + phase
                        + ",  Registered Parties = " + parties);
                // Do not terminate the phaser by returning false
                return false;
            }
        };
        // Register the self (the "main" thread) as a party
        phaser.register();
        System.out.println("#1: isTerminated():" + phaser.isTerminated());
        phaser.arriveAndDeregister();

        // Trigger another phase advance
        phaser.register();
        phaser.arriveAndDeregister();

        System.out.println("#2: isTerminated():" + phaser.isTerminated());
        phaser.forceTermination();
        System.out.println("#3: isTerminated():" + phaser.isTerminated());
    }
    //以下代码显示如何使用移相器生成一些整数。
    @Test
    public void test7() {
        final int PHASE_COUNT = 2;
        Phaser phaser = new Phaser() {
            public boolean onAdvance(int phase, int parties) {
                System.out.println("Phase:" + phase + ", Parties:" + parties
                        + ",  Arrived:" + this.getArrivedParties());
                boolean terminatePhaser = false;
                if (phase >= PHASE_COUNT - 1 || parties == 0) {
                    terminatePhaser = true;
                }

                return terminatePhaser;
            }
        };
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        int ADDER_COUNT = 3;
        phaser.bulkRegister(ADDER_COUNT + 1);
        for (int i = 1; i <= ADDER_COUNT; i++) {
            String taskName = "Task  #" + i;
            AdderTask task = new AdderTask(taskName, phaser, list);
            task.start();
        }
        while (!phaser.isTerminated()) {
            phaser.arriveAndAwaitAdvance();
        }
        int sum = 0;
        for (Integer num : list) {
            sum = sum + num;
        }
        System.out.println("Sum = " + sum);
    }
    class AdderTask extends Thread {
        private Phaser phaser;
        private String taskName;
        private List<Integer> list;

        public AdderTask(String taskName, Phaser phaser, List<Integer> list) {
            this.taskName = taskName;
            this.phaser = phaser;
            this.list = list;
        }

        @Override
        public void run() {
            do {
                System.out.println(taskName + "  added  " + 3);
                list.add(3);
                phaser.arriveAndAwaitAdvance();
            } while (!phaser.isTerminated());
        }
    }
    //锁存器
    /**
     锁存器使一组线程等待，直到它到达其终端状态。

     一旦锁存器达到其终端状态，它允许所有线程通过。

     与障碍不同，它是一个一次性的对象。它不能被重置和重用。

     使用锁存器，其中在一定数量的一次性活动完成之前，多个活动不能进行。

     例如，一个服务不应该启动，直到它依赖的所有服务都已启动。

     java.util.concurrent包中的CountDownLatch类提供了一个锁存器的实现。
     * */
    @Test
    public void test8() {
        // Create a countdown latch with 2 as its counter
        CountDownLatch latch = new CountDownLatch(2);
        LatchMainService ms = new LatchMainService(latch);
        ms.start();
        for (int i = 1; i <= 2; i++) {
            LatchHelperService lhs = new LatchHelperService(i, latch);
            lhs.start();
        }
    }
    class LatchHelperService extends Thread {
        private int ID;
        private CountDownLatch latch;
        public LatchHelperService(int ID, CountDownLatch latch) {
            this.ID = ID;
            this.latch = latch;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println("Service #" + ID + "  has  started...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.latch.countDown();
            }
        }
    }
    class LatchMainService extends Thread {
        private CountDownLatch latch;

        public LatchMainService(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run() {
            try {
                System.out.println("waiting for services to start.");
                latch.await();
                System.out.println("started.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //交换器
    /**
     交换器允许两个线程在同步点处等待彼此。
     当两个线程到达时，它们交换一个对象并继续他们的活动
     Exchanger类提供了交换器同步器的实现。
     以下代码显示将使用交换器与客户交换数据的生产者线程。
    * */
    @Test
    public void test9() {
        Exchanger<ArrayList<Integer>> exchanger = new Exchanger<>();
        ExchangerProducer producer = new ExchangerProducer(exchanger);
        ExchangerConsumer consumer = new ExchangerConsumer(exchanger);
        producer.start();
        consumer.start();
    }
    class ExchangerProducer extends Thread {
        private Exchanger<ArrayList<Integer>> exchanger;
        private ArrayList<Integer> buffer = new ArrayList<Integer>();
        public ExchangerProducer(Exchanger<ArrayList<Integer>> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Producer.");
                    Thread.sleep(1000);
                    fillBuffer();
                    System.out.println("Producer has produced and waiting:" + buffer);
                    buffer = exchanger.exchange(buffer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void fillBuffer() {
            for (int i = 0; i <= 3; i++) {
                buffer.add(i);
            }
        }
    }

    class ExchangerConsumer extends Thread {
        private Exchanger<ArrayList<Integer>> exchanger;
        private ArrayList<Integer> buffer = new ArrayList<Integer>();
        public ExchangerConsumer(Exchanger<ArrayList<Integer>> exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Consumer.");
                    buffer = exchanger.exchange(buffer);
                    System.out.println("Consumer  has received:" + buffer);
                    Thread.sleep(1000);
                    System.out.println("eating:"+buffer);
                    buffer.clear();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //Java执行器
    //框架提供了一种将任务提交与任务执行分离的方法。
    //java.util.concurrent包中的Executor接口是执行器框架的基础。
    //它是一个只有一个方法的接口，如图所示：
    @Test
    public void test10() {
        //必须改成main方法执行

        final int THREAD_COUNT = 3;
        final int LOOP_COUNT = 3;
        final int TASK_COUNT = 5;

        // Get an executor with three threads in its thread pool
        ExecutorService exec = Executors.newFixedThreadPool(THREAD_COUNT);

        // Create five tasks and submit them to the executor
        for (int i = 1; i <= TASK_COUNT; i++) {
            RunnableTask task = new RunnableTask(i, LOOP_COUNT);
            exec.submit(task);
        }
        exec.shutdown();
    }
     class RunnableTask implements Runnable {
        private int taskId;
        private int loopCounter;

        public RunnableTask(int taskId, int loopCounter) {
            this.taskId = taskId;
            this.loopCounter = loopCounter;
        }

        @Override
        public void run() {
            for (int i = 1; i <= loopCounter; i++) {
                try {
                    System.out.println("Task #" + this.taskId + "  - Iteration #" + i);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("Task #" + this.taskId
                            + "  has  been  interrupted.");
                    break;
                }
            }
        }
    }
    //结果承载任务
    //要在任务完成时获取任务的结果，请使用Callable接口的实例。
    //类型参数V是任务的结果的类型。
    //Callable接口有一个call()方法。它可以返回任何类型的值。
    //它允许你抛出异常。它声明如下：
    @Test
    public void test11() throws ExecutionException, InterruptedException {
        // Get an executor with three threads in its thread pool
        ExecutorService exec = Executors.newFixedThreadPool(3);
        CallableTask task = new CallableTask(1);
        // Submit the callable task to executor
        Future<Integer> submittedTask = exec.submit(task);

        Integer result = submittedTask.get();
        System.out.println("Task's total  sleep time: " + result + "  seconds");
        exec.shutdown();
    }
    class CallableTask implements Callable<Integer> {
        private int taskId;

        public CallableTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public Integer call() throws InterruptedException {
            int total = taskId;
            try {
                System.out.println("Task #" + this.taskId);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Task #" + this.taskId
                        + "  has  been  interupted.");
                throw e;
            }
            total+=taskId;
            return total;
        }
    }
    //调度任务
    //执行器框架允许您计划将来运行的任务。
    @Test
    public void test12(){
        // Get an executor with 3 threads
        ScheduledExecutorService sexec = Executors.newScheduledThreadPool(3);

        ScheduledTask task1 = new ScheduledTask(1);
        ScheduledTask task2 = new ScheduledTask(2);

        // Task #1 will run after 2 seconds
        sexec.schedule(task1, 2, TimeUnit.SECONDS);

        // Task #2 runs after 5 seconds delay and keep running every 10 seconds
        sexec.scheduleAtFixedRate(task2, 5, 10, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sexec.shutdown();
    }
    class ScheduledTask implements Runnable {
        private int taskId;

        public ScheduledTask(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            LocalDateTime currentDateTime = LocalDateTime.now();
            System.out.println("Task #" + this.taskId + "  ran  at "
                    + currentDateTime);
        }
    }
    //在任务执行中处理未捕获的异常
    //执行器框架在任务执行期间处理任何未捕获异常的事件。
    //如果使用Executor对象的execute()方法执行Runnable任务，任何未捕获的运行时异常将停止任务执行，并且异常堆栈跟踪将打印在控制台上。
    @Test
    public void test13(){
        Runnable badTask = () -> {
            throw new RuntimeException(
                    "Throwing exception  from  task execution...");
        };

        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.execute(badTask);
        exec.shutdown();
    }
    //在Callable任务中处理异常
    //以下代码显示了如何在Callable任务中处理异常。
    @Test
    public void test14(){
        Callable<Object> badTask = () -> {
            throw new RuntimeException(
                    "Throwing exception from task execution...");
        };
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Future submittedTask = exec.submit(badTask);
        try {
            Object result = submittedTask.get();
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        exec.shutdown();
    }
    //执行完成服务
    //要将提交的任务的结果提供给执行程序，请使用执行程序的完成服务。
    //它由CompletionService接口的一个实例表示。
    @Test
    public void test15() throws ExecutionException, InterruptedException {
        // Get an executor with three threads in its thread pool
        ExecutorService exec = Executors.newFixedThreadPool(3);

        // Completed task returns an object of the TaskResult class
        ExecutorCompletionService<MyResult> completionService = new ExecutorCompletionService<>(
                exec);
        for (int i = 1; i <= 5; i++) {
            SleepingTask task = new SleepingTask(i, 3);
            completionService.submit(task);
        }
        for (int i = 1; i <= 5; i++) {
            Future<MyResult> completedTask = completionService.take();
            MyResult result = completedTask.get();
            System.out.println("Completed a  task - " + result);
        }
        exec.shutdown();
    }
    class MyResult {
        private int taskId;
        private int result;

        public MyResult(int taskId, int result) {
            this.taskId = taskId;
            this.result = result;
        }

        public int getTaskId() {
            return taskId;
        }

        public int getResult() {
            return result;
        }

        @Override
        public String toString() {
            return "Task  Name: Task  #" + taskId + ", Task  Result:" + result
                    + "  seconds";
        }
    }

    class SleepingTask implements Callable<MyResult> {
        private int taskId;
        private int loopCounter;
        public SleepingTask(int taskId, int loopCounter) {
            this.taskId = taskId;
            this.loopCounter = loopCounter;
        }

        @Override
        public MyResult call() throws InterruptedException {
            int totalSleepTime = 0;
            for (int i = 1; i <= loopCounter; i++) {
                try {
                    System.out.println("Task #" + this.taskId + "  - Iteration #"
                            + i);
                    Thread.sleep(1000);
                    totalSleepTime = totalSleepTime + 1000;
                } catch (InterruptedException e) {
                    System.out.println("Task #" + this.taskId
                            + "  has  been  interupted.");
                    throw e;
                }
            }
            return new MyResult(taskId, totalSleepTime);
        }
    }

}
