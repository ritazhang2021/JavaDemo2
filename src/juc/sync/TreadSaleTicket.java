package juc.sync;

/**
 * @Author: Rita
 */
//用Thread卖票
//第一步  创建资源类，定义属性和和操作方法
class Ticket {
    //票数
    private int number;
    Ticket(int number){
        this.number = number;
    }
    //操作方法：卖票 加同步
    public synchronized void sale() {
        //判断：是否有票
        if(number > 0) {
            System.out.println(Thread.currentThread().getName()+" : 已卖出："+(number--)+" 剩余："+number);
        }
    }
}

public class TreadSaleTicket {
    //第二步 创建多个线程，调用资源类的操作方法
    public static void main(String[] args) {
        //创建Ticket对象
        Ticket ticket = new Ticket(30);
        //创建三个线程  有很多方法可以创建
        new Thread(() ->{
            //调用卖票方法
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"3").start();
    }
    //线程开启后，会不会被CPU执行就不知道了，CPU有它自己的算法，锁也有自己的竞争机制
}
