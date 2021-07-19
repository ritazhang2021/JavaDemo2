package project.project1_sell_ticket;

/**
 * @Author: Rita
 */
public class SellTicketSynchronizeWithMethodAndRunnable {
    public static void main(String[] args) {
    TicketAgent3 ticketAgent3 = new TicketAgent3();
    //注意线程的方法和变量都是static, 一段代码，放进三个线程里，同时三个线程访问
    Thread t1 = new Thread(ticketAgent3);
    Thread t2 = new Thread(ticketAgent3);
    Thread t3 = new Thread(ticketAgent3);

    t1.setName("ticketAgent 1");
    t2.setName("ticketAgent 2");
    t3.setName("ticketAgent 3");
    //can't use run
    t1.start();
    t2.start();
    t3.start();
}
}

class TicketAgent3 implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while(true){
            synchronizeMethod();
        }

    }
    /**
     *  1. 同步方法仍然涉及到同步监视器，只是不需要我们显式的声明。
     *  2. 非静态的同步方法，同步监视器是：this
     *     静态的同步方法，同步监视器是：当前类本身
     */
    private synchronized void synchronizeMethod(){//同步监视器：this
        if(ticket > 0){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":is selling ticket，ticket number is ：" + ticket);
            ticket--;
        }
    }

}
