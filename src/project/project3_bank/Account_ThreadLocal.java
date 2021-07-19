package project.project3_bank;


/**
 * @Author: Rita
 */
/**
 * ThreadLocal 类的常用方法 *
 *      ThreadLocal() : 创建一个线程本地变量
 *     get() : 返回此线程局部变量的当前线程副本中的值
 *     initialValue() : 返回此线程局部变量的当前线程的"初始值"
 *     set(T value) : 将此线程局部变量的当前线程副本中的值设置为value
* ThreadLocal与同步机制
  a.ThreadLocal与synchronization都是为了解决多线程中相同变量的访问冲突问题。
  b.前者采用以"空间换时间"的方法，后者采用以"时间换空间"的方式 */

public class Account_ThreadLocal {
    /*它的原理是每次要线程要访问volatile修饰的变量时都是从内存中读取，而不是存缓存当中读取,开销比较大*/
    private static volatile Account_ThreadLocal instanceAccount;

    private Account_ThreadLocal() {
    }
    private static ThreadLocal<Double> balance = new ThreadLocal<Double>(){
        @Override
        protected Double initialValue() {
            return 0D;
        }
    };

    public void deposit(double money){
        balance.set(balance.get()+money);
        System.out.println(Thread.currentThread().getName()+" deposit ："+money);
    }
    public void withdraw(double money){

        if(balance.get()-money < 0){
            System.out.println("not sufficient funds");
            return;
        }
        balance.set(balance.get()- money);
        System.out.println(Thread.currentThread().getName()+" withdraw："+money);

    }
    public double getBalance(){
        /*DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(balance));*/

        /*System.out.print("balance：");
        System.out.println(String.format("%.2f", balance));*/
        return balance.get();

    }
    public static synchronized Account_ThreadLocal getAccountInstance(){
        if(instanceAccount == null){
            synchronized (Bank.class){
                if(instanceAccount == null){
                    instanceAccount = new Account_ThreadLocal();
                }
            }
        }
        return instanceAccount;
    }

}
