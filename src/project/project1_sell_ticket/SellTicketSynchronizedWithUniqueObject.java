package project.project1_sell_ticket;

/**
 * @Author: Rita
 * @Date:4/28/2021 5:27 PM
 */
public class SellTicketSynchronizedWithUniqueObject {
    public static void main(String[] args) {
        TicketAgent1 ticketAgent = new TicketAgent1();
        Thread t1 = new Thread(ticketAgent);
        Thread t2 = new Thread(ticketAgent);
        Thread t3 = new Thread(ticketAgent);

        t1.setName("ticketAgent 1");
        t2.setName("ticketAgent 2");
        t3.setName("ticketAgent 3");
        //can't use run
        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketAgent1 implements Runnable{
    private int ticket = 100;
    SomethingUnique somethingUnique = new SomethingUnique();
    @Override
    public void run() {
        while(true){
            synchronized (somethingUnique){//方式一 this:唯一的TicketAgent2的对象   //方式二：somethingUnique {
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
class SomethingUnique{

}
