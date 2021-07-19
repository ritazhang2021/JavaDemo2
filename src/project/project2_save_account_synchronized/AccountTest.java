package project.project2_save_account_synchronized;

/**
 * @Author: Rita
 */
/**
two customer save money in one account and check the balance in the same time.
 * */
class Account {
    private double balance;
    public Account(double balance) {
        this.balance = balance;
    }
    //存钱
    public synchronized void deposit(double amt){
        if(amt > 0){
            balance += amt;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":存钱成功。余额为：" + balance);
        }
    }
}

class Customer extends  Thread{
    private Account acct;
    public Customer(Account acct) {
        this.acct = acct;
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }
    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account acct = new Account(0);
        //因为两个customer对应的是同一个帐号的地址，所以不用static
        //自操作的需要变成static,因为自增操作是不具备原子性的，它包括读取变量的原始值、进行加1操作、写入工作内存。
        //那么就是说自增操作的三个子操作可能会分割开执行，就有可能导致每次线程拿到的不是最新的数据。
        Customer c1 = new Customer(acct);
        Customer c2 = new Customer(acct);
        c1.setName("Customer1");
        c2.setName("Customer2");
        c1.start();
        c2.start();
    }
}
