package project.project1_sell_ticket;

/**
 * @Author: Rita
 */
public class SellTicketAsynchronize {
    public static void main(String[] args) {
        TicketAgent ticketAgent = new TicketAgent();
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

class TicketAgent implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while(true){
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() + ":is selling ticket，ticket number is ：" + ticket);
                ticket--;
            }else{
                break;
            }
        }
    }

}
