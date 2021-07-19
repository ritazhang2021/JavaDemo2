package OOP;

import org.junit.Test;

/**
 * @Author: Rita
 */
public class StaticTest {
    //提前加载，准备时是0，然后再把对应的值赋值当被执行时
    public static int value2;
    public int value3;//attribute the same with static method
    @Test
    public void testMethod1(){
        int value4;
        System.out.println(value2);//0
        System.out.println(value3);//0
    }
    public static void staticMethod(){
        int value5 = 0;//必须初始化
        System.out.println(value5);
    }
    public void normalMethod(){
        //static int value5;//static only for attributes and normal method. not for variable and static method
    }
    /*
    * 总结：
    * what ever use static
    * variable in method must be initialized
    * attribute has default value
    *
    * */
}
