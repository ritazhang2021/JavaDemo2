package design_patterns;

/**
 * @Author: Rita
 */

//还可以用enum
final class Singleton{
    //private final static Singleton instance = null;
    private volatile static Singleton instance = null;
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

