package OOP;

/**
 * @Author: Rita
 */

/**
 父类静态属性（成员变量） > 父类静态代码块 > 子类静态属性 > 子类静态代码块 > 父类非静态属性 >
 父类非静态代码块 > 父类构造器 > 子类非静态属性 > 子类非静态代码块 > 子类构造器

 */
public class LoadingOrder {

    public static void main(String[] args) {
        System.out.println("new Test2 in the first time ：");
        Test2 t2 = new Test2();
        System.out.println();
        System.out.println("new Test2 in the second time ：");
        t2 = new Test2();
    }

    static class Test1 {
        static LoadingType type1 = new LoadingType("static in Test1");
        LoadingType type2 = new LoadingType("non-static in Test1");
        static {
            System.out.println("static block in Test1");
        }
        {
            System.out.println("non-static block in Test1");
        }
        public Test1() {
            System.out.println("Test1 init");
        }
    }

    static class Test2 extends Test1 {
        static LoadingType type1 = new LoadingType("static in Test2");
        LoadingType type2 = new LoadingType("non-static in Test2");

        static {
            System.out.println("static block in Test2");
        }
        {
            System.out.println("non-static block in Test2");
        }
        public Test2() {
            System.out.println("Test2 init");
        }
    }

    static class LoadingType {
        public LoadingType(String str) {
            System.out.println("LoadingType = " + str);
        }
    }
}
