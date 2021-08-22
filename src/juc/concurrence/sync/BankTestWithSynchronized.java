package juc.concurrence.sync;


import project.project3_bank.Bank;
import project.project3_bank.CheckingAccount;
import project.project3_bank.Customer;

/**
 * @Author: Rita
 */
public class BankTestWithSynchronized implements Runnable{

    @Override
    public void run() {
        Bank bank = Bank.getBank();
        Customer customer;
        bank.addCustomer("Owen", "Bryant");
        customer = bank.getCustomer(0);

        customer.addAccount(new CheckingAccount(0));

        synchronized (this){
            customer.getAccount(0).deposit(200);
            System.out.println(customer.getAccount(0).getBalance());

            customer.getAccount(0).withdraw(200);
            System.out.println(customer.getAccount(0).getBalance());
        }

    }

    public static void main(String[] args) {
        BankTestWithoutSynchronized bankTest1 = new BankTestWithoutSynchronized();
        BankTestWithoutSynchronized bankTest2 = new BankTestWithoutSynchronized();
        Thread t1 = new Thread(bankTest1);
        Thread t2 = new Thread(bankTest2);
        t1.start();
        t2.start();
    }
}

