package interfaceAbstractClass;

/**
 * @Author: Rita

 */
public class Class1 extends Abstract1 implements Interface2, Interface1,Interface3{
    //interface中也有这个值，不影响
    public static final int file1 = 5;

    //deafualt方法没有让实现

    //必须实现Interface1的方法

    @Override
    public void method1() {
        //interface可以被实例化，只要实现它的方法
        Interface1 interface1 = new Interface1() {
            @Override
            public void method1() {

            }
        };
        //Abstract class无论如何不能被实例化
        //Abstract1 abstract1 = new Abstract1();

    }


    @Override
    void abstractMethod1() {

    }

    @Override
    void abstractMethod2() {

    }
    void m6(double s){

    }
    void m6(String s){

    }

    void m6(){

    }
}
