package OOP.javabase.primitive;

import project.entity.Person;

import java.util.Scanner;

/**
 * @Author: Rita
 * @Date:4/21/2021 3:57 PM
 */

/**
 * 1.相同点：
 *  * 		1.1  定义变量的格式：数据类型  变量名 = 变量值
 *  * 		1.2 先声明，后使用
 *  * 		1.3 变量都其对应的作用域
 * 2.不同点：
 * 		2.1 在类中声明的位置的不同
 *  * 		属性：直接定义在类的一对{}内
 *  * 		局部变量：声明在方法内、方法形参、代码块内、构造器形参、构造器内部的变量 *  *
 *  * 		2.2 关于权限修饰符的不同
 *  * 		属性：可以在声明属性时，指明其权限，使用权限修饰符。
 *  * 			常用的权限修饰符：private、public、缺省、protected  --->封装性
 *  * 			目前，大家声明属性时，都使用缺省就可以了。
 *  * 		局部变量：不可以使用权限修饰符。 *  *
 *  * 		2.3 默认初始化值的情况：
 *  * 		属性：类的属性，根据其类型，都默认初始化值。
 *  * 			整型（byte、short、int、long：0）
 *  * 			浮点型（float、double：0.0）
 *  * 			字符型（char：0  （或'\u0000'））
 *  * 			布尔型（boolean：false）
 *              引用类型（null）
 局部变量：没默认初始化值。意味着，我们在调用局部变量之前，一定要显式赋值。
 特别地：形参在调用时，我们赋值即可。
         2.4 在内存中加载的位置：
         * 		属性：加载到堆空间中   （非static）heap
         * 		局部变量：加载到栈空间 stack
 * */
public class InitializedLocalVariables {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();

        // Write your code here.
        double d = scan.nextDouble();
        String s ="";

        if(scan.hasNext()){
            s = scan.nextLine();
        }

        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
    }

    public static void primitivesTest(){
        //must be initialized, both primitives and object,否则不会在jvm
        char a;
        int num;
        byte aByte;
        Person p;
        float f = 0f;
        //java: variable a might not have been initialized
        //System.out.println("char a = "+ a );
        //p.toString();
        //System.out.println(p);

    }
}
