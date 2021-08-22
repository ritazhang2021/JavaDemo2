package interfaceAbstractClass;

/**
 * @Author: Rita

 */
public abstract class Abstract1 {
    //do not have to init
    public static final int file1 = 0;
    //跟interface中的一样，被类继承，优先interface在类中被调用
    //正常方法 加上static或是final，就不能被继承了
    public void method1(){

    }
    public void method2(){

    }
    //抽像方法，不能有方法体，必须被实现
    abstract void abstractMethod1();
    abstract void abstractMethod2();
    //抽像方法不能有方法体
//    abstract void abstractMethod2(){
//
//    };
}
