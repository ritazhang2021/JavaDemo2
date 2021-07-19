package concurrence.thread.thread_group;

/**
 * @Author: Rita
 */

public class GroupBasic {
    public static void main(String[] args) {
        GroupBasic groupBasic = new GroupBasic();
        //auto
        groupBasic.auto();
        //one level
        groupBasic.oneLevel();
        //multi level
        groupBasic.multiLevel();
    }
    /**
     * 自动归属
     * */
    public void auto(){
        Thread current = Thread.currentThread();
        Thread thread = new Thread(()->{
            System.out.println("---run---");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("--current thread name："+current.getName() + "，线程组："+current.getThreadGroup().getName());
        System.out.println("--new thread name："+thread.getName() + "，线程组："+thread.getThreadGroup().getName());
    }
    /**
     * 一级关联
     * */
    public void oneLevel(){
        ThreadGroup group = new ThreadGroup("一级线程组");
        Thread t1 = new Thread(()->{
            System.out.println("---t1 run---");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            System.out.println("---t2 run---");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread one = new Thread(group, t1);
        Thread two = new Thread(group, t2);
        one.start();
        two.start();
        System.out.println("--ThreadGroup name："+group.getName() + "，存活线程个数："+group.activeCount());
    }
    /**
     * 多级关联
     * */
    public void multiLevel(){
        ThreadGroup group = new ThreadGroup("一级线程组");
        ThreadGroup group1 = new ThreadGroup(group,"二级线程组A");
        ThreadGroup group2 = new ThreadGroup(group,"二级线程组B");
        Thread t1 = new Thread(()->{
            System.out.println("---t1 run---");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            System.out.println("---t2 run---");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(()->{
            System.out.println("---t3 run---");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread one = new Thread(group, t1);
        Thread two = new Thread(group1, t2);
        Thread three = new Thread(group2, t3);
        one.start();
        two.start();
        three.start();
        System.out.println("--一级线程组 name："+group.getName() + "，存活线程个数："+group.activeCount()
                +"，子线程组个数："+group.activeGroupCount());
        System.out.println("--二级线程组A name："+group1.getName() + "，存活线程个数："+group1.activeCount()
                +"，子线程组个数："+group1.activeGroupCount());
        System.out.println("--二级线程组B name："+group2.getName() + "，存活线程个数："+group2.activeCount()
                +"，子线程组个数："+group2.activeGroupCount());
    }
}