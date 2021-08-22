package interfaceAbstractClass;

/**
 * @Author: Rita

 */
//接口可以多继承
public interface Interface1 extends Interface3, Interface2 {

    //必须初始化
    public static final int file1 = 0;
    //必须被实现，默认是abstract
    //默认是public，不能被其它修饰
    public void method1();

    public default void methodDefault() {

    }
}
