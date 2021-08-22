package juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Rita

 */

//第一步  创建资源类，定义属性和和操作方法
class LTicket {
    //票数量
    private int number;
    LTicket(int number){
        this.number = number;
    }

    //创建可重入锁 当是true的时候，是公平锁，默认是非公平锁
    //公平锁，效率低，人人都有机会，非公平锁，效率高，靠抢
    private final ReentrantLock lock = new ReentrantLock(true);
    //卖票方法
    public void sale() {
        //上锁
        lock.lock();
        try {
            //判断是否有票
            if(number > 0) {
                System.out.println(Thread.currentThread().getName()+" ：卖出"+(number--)+" 剩余："+number);
            }
        }

        finally {
            //解锁放在finally,但是如果try的时候down机了，finally就不会执行
            lock.unlock();
        }
    }
}

public class LockSaleTicket {
    //第二步 创建多个线程，调用资源类的操作方法
    //创建三个线程
    public static void main(String[] args) {

        LTicket ticket = new LTicket(30);
        //thread 的start方法在执行后，线程可能会被创建，也可能不会，里面有个start0方法，是native的，
        // 说明start后就由系统CPU来定
        new Thread(()-> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"1").start();

        new Thread(()-> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"2").start();

        new Thread(()-> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"3").start();
    }
}