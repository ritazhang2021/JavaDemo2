package project.project1_sell_ticket;

/**
 * @Author: Rita
 */
public class SellTicketSynchronizeWithStatementAndThread{
    public static void main(String[] args) {

        //三个线程，共享一份数据
        TicketAgent2 t1 = new TicketAgent2();
        TicketAgent2 t2 = new TicketAgent2();
        TicketAgent2 t3 = new TicketAgent2();

        t1.setName("ticketAgent 1");
        t2.setName("ticketAgent 2");
        t3.setName("ticketAgent 3");
        t1.start();
        t2.start();
        t3.start();

        /*TicketAgent3 ticketAgent3 = new TicketAgent3();
        //同样一段代码，放进三个线程里，同时三个线程访问
        Thread t1 = new Thread(ticketAgent3);
        Thread t2 = new Thread(ticketAgent3);
        Thread t3 = new Thread(ticketAgent3);*/

        }
    }

class TicketAgent2 extends Thread{
    //如果不是static的数据，每个线程就会有自己的一套数据
    private static int ticket = 100;
    private static Object obj = new Object();

    @Override
    public void run() {
        while(true){
            //正确的，在继承Thread类创建多线程的方式中，慎用this充当同步监视器，考虑使用当前类充当同步监视器。或唯一的key
            //错误的方式：this代表着t1,t2,t3三个对象
            synchronized (obj){//SellTicketSynchronizeWithStatement.class只会加载一次, obj是唯一的
                if(ticket > 0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":is selling ticket，ticket number is ：" + ticket);
                    ticket--;
                }else{
                    break;
                }
            }
        }
    }
}
