package OOP;

/**
 * @Author: Rita
 */
public class FinalTest {
    // final static必须声明时就赋值
    //public final static int vale;

    /**
     编译时Javac将会为value生成ConstantValue属性，在准备阶段虚拟机就会根据 ConstantValue的设置将value赋值为3。
     我们可以理解为static final常量在编译期就将其结果放入了调用它的类的常量池中
     * */
    public final static int value = 0;


    //public final int value2;


    void m(){
        final int value2;
        //System.out.println(value2);//final使用前要被赋值
    }
}
