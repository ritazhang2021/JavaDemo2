package project.project1_sell_ticket;

/**
 * @Author: Rita
 */
public class SellTicketSynchronizeWithMethodAndThread {
    public static void main(String[] args) {
    //注意线程的方法和变量都是static, 一段代码，放进三个线程里，同时三个线程访问
    TicketAgent4 t1 = new TicketAgent4();
    TicketAgent4 t2 = new TicketAgent4();
    TicketAgent4 t3 = new TicketAgent4();
    //这种方式比较慢，方法不用加static,thread类型也能放到Tread的构造器中
    /*Thread t1 = new Thread(t1);
    Thread t2 = new Thread(t2);
    Thread t3 = new Thread(t3);*/

    t1.setName("ticketAgent 1");
    t2.setName("ticketAgent 2");
    t3.setName("ticketAgent 3");
    //can't use run
    t1.start();
    t2.start();
    t3.start();
}
}

class TicketAgent4 extends Thread{
    private static int ticket = 100;
    @Override
    public void run() {
        while(ticket >0){
            synchronizeMethod();
        }

    }

    private static synchronized void synchronizeMethod(){//否则同步监视器是t1,t2,t3,同步监视器：Window4.class
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
