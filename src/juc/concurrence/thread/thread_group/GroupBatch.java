package juc.concurrence.thread.thread_group;

/**
 * @Author: Rita
 */
public class GroupBatch {
    public static void main(String[] args) throws InterruptedException{
        GroupBatch groupBatch = new GroupBatch();
        groupBatch.batchTest();
    }
    /**
     * 线程组下的线程任务执行异常，需要全部终止
     * */
    public void batchTest() throws InterruptedException{
        ThreadGroup group = new ThreadGroup("批处理线程组");
        for (int i = 0; i <5 ; i++) {
            new Thread(group, new SubTask(),"subtask-0"+i).start();
        }
        Thread.sleep(5000);
        System.out.println("--all SubTask 阻塞，需终止--");
        group.interrupt();//中断线程组下的所有线程任务
        Thread.sleep(2000);
        System.out.println("--stop the world--");
    }
    static class SubTask implements Runnable{
        @Override
        public void run() {
            System.out.println("--subTask thread name："+ Thread.currentThread().getName() + "，begin run");
            while(!Thread.currentThread().isInterrupted()){ };
            System.out.println("--subTask thread name："+ Thread.currentThread().getName() + "，end run");
        }
    }
}
