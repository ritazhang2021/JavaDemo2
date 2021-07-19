package project.project3_bank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Rita
 */
public class Account_Lock {
    protected double balance =0;
    /*它的原理是每次要线程要访问volatile修饰的变量时都是从内存中读取，而不是存缓存当中读取,开销比较大*/
    private static volatile Account_Lock instanceAccount;
    public Account_Lock(double init_balance){
        this.balance = init_balance;
    }

    private Account_Lock() {
    }
    private Lock lock = new ReentrantLock();

    public void deposit(int money){
        lock.lock();
        try{
            balance +=money;
            System.out.println(Thread.currentThread().getName()+" deposit ："+money);
        }finally {
            lock.unlock();
        }
    }
    public void withdraw(double money){
        lock.lock();
        try{
            if(balance-money < 0){
                System.out.println("not sufficient funds");
                return;
            }
            balance -=money;
            System.out.println(Thread.currentThread().getName()+" withdraw："+money);
        }finally {
            lock.unlock();
        }

    }
    public double getBalance(){
        /*DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(balance));*/

        /*System.out.print("账户余额：");
        System.out.println(String.format("%.2f", balance));*/
        return balance;

    }
    public static synchronized Account_Lock getAccountInstance(){
        if(instanceAccount == null){
            synchronized (Bank.class){
                if(instanceAccount == null){
                    instanceAccount = new Account_Lock();
                }
            }
        }
        return instanceAccount;
    }

}
