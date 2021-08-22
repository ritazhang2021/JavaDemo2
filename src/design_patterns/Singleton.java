package design_patterns;

/**
 * @Author: Rita
 */

//还可以用enum
final class Singleton{
    //private static Singleton instance;
    private volatile static Singleton instance;
    private Singleton() { }

    public static Singleton getInstance() {
        if(instance==null) {
            synchronized (Singleton.class) {
                if(instance==null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
//不能通过 reflection attack 来调用私有构造方法。
enum SingletonEnum {
    INSTANCE;
}

