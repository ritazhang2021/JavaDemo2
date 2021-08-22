package juc;

/**
 * @Author: Rita
 */
public class MainTread {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            //isDaemon true是守护线程，false是用户线程，一个用户线程包括守护线程
            System.out.println(Thread.currentThread().getName() + "->" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "thread");
        //由用户线程设置成守护线程， 要在线程开始前设置
        thread.setDaemon(true);
        thread.start();
        //主线程结束，用户线程还是存活状态， JVM会保持用户线程alive状态
        //如果没有用户线程了，都是守护线程，在主线程结束后，JVM就关闭了
        System.out.println(Thread.currentThread().getName()+" thread over");
    }

}
