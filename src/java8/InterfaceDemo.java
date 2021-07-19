package java8;

/**
 * @Author: Rita
 */
public class InterfaceDemo {
    public static void main(String[] args) {
        InterfaceDemo interfaceAPI = new InterfaceDemo();
        Man man = new InterfaceDemo().new Man();
        man.help();
    }
    interface Filial {// 孝顺的
        default void help() {
            System.out.println("老妈，我来救你了");
        }
    }

    interface Spoony {
        default void help() {
            System.out.println("亲爱的，别怕，我来求你了");
        }
    }
    interface VariableAndMethodTest{
        int i = 0;
        //int j; //必须初始化
        //public static final int x = 0;//默认类型public static final
        default int m1() {
            int x = 0;
            System.out.println(x);
            return 1;
        }
        //private可以有方法体
        private int m2() {
            int x = 0;
            System.out.println(x);
            return 1;
        }
        static int m3(){
            int x = 0;
            System.out.println("静态方法");
            return 1;
        }
        //必须被implemented,默认类型是public abstract
        public abstract int m4();

    }
    abstract class saveAll{
        //抽像方法
        //abstract void help();
        //普通方法//注意这里是内部类，不加访问修饰符的方法不能被外调用，需要加public
        public void help(){
            System.out.println("优先用类的方法");
        }
    }

    class Man extends saveAll implements Filial, Spoony {
        //两个接口中的default方法冲突了，必须要重写一个，否则编译错误
        @Override
        public void help() {
            System.out.println("我该怎么办呢？");
            //这是调用interface default方法的写法,只有deafault方法这样调，普通方法必须要implement，普通方法没有方法体
            //因为接口允许多重继承,这可能导致相同方法的模糊性
            Filial.super.help();
            Spoony.super.help();
        }
    }

}
