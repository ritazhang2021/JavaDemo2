package project.project3_bank;

/**
 * @Author: Rita
 */
public class Account_Synchronized {
    protected double balance =0;
    /*它的原理是每次要线程要访问volatile修饰的变量时都是从内存中读取，而不是存缓存当中读取,开销比较大*/
    private static volatile Account_Synchronized instanceAccount;
    public Account_Synchronized(double init_balance){
        this.balance = init_balance;
    }

    private Account_Synchronized() {
    }

    public void deposit(int money){
        synchronized(this){
            balance +=money;
            System.out.println(Thread.currentThread().getName()+" deposit ："+money);
        }
    }
    public void withdraw(double money){
        synchronized(this){
            if(balance-money < 0){
                System.out.println("not sufficient funds");
                return;
            }
            balance -=money;
            System.out.println(Thread.currentThread().getName()+" withdraw："+money);
        }

    }
    public double getBalance(){
        /*DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(balance));*/

        /*System.out.print("账户余额：");
        System.out.println(String.format("%.2f", balance));*/
        return balance;

    }
    public static synchronized Account_Synchronized getAccountInstance(){
        if(instanceAccount == null){
            synchronized (Bank.class){
                if(instanceAccount == null){
                    instanceAccount = new Account_Synchronized();
                }
            }
        }
        return instanceAccount;
    }

}
