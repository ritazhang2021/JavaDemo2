package concurrence.synchronize;


import project.project3_bank.Bank;
import project.project3_bank.CheckingAccount;
import project.project3_bank.Customer;

/**
 * @Author: Rita
 */
public class BankTestWithoutSynchronized implements Runnable{

    @Override
    public void run() {
        Bank bank = Bank.getBank();
        Customer customer;
        bank.addCustomer("Owen", "Bryant");
        customer = bank.getCustomer(0);
        //有可能两个线程同时做这个，就会有NullPointerException
        customer.addAccount(new CheckingAccount(0));
        System.out.println(customer.getAccount(0).getBalance());
        customer.getAccount(0).deposit(200);
        System.out.println(customer.getAccount(0).getBalance());
        customer.getAccount(0).withdraw(200);
        System.out.println(customer.getAccount(0).getBalance());

    }

    public static void main(String[] args) {
        BankTestWithoutSynchronized bankTest1 = new BankTestWithoutSynchronized();
        BankTestWithoutSynchronized bankTest2 = new BankTestWithoutSynchronized();
        Thread t1 = new Thread(bankTest1);
        Thread t2 = new Thread(bankTest2);
        //两个线程执行一个程序，t1执行到一个地方，t2会插入进来，所以有时候需要控制只能一个线程访问
        t1.start();
        t2.start();
    }
}
