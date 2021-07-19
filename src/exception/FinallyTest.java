package exception;

/**
@Author: Rita
异常处理：
1. 在写程序时，对可能会出现异常的部分通常要用try{…}catch{…}去捕捉它并对它进行处理；
2. 用try{…}catch{…}捕捉了异常之后一定要对在catch{…}中对其进行处理，那怕是最简单的一句输出语句，
或栈输入e.printStackTrace();
3. 如果是捕捉IO输入输出流中的异常，一定要在try{…}catch{…}后加finally{…}把输入输出流关闭；java10后自动关闭
4. 如果在函数体内用throw抛出了某种异常，最好要在函数名中加throws抛异常声明，然后交给调用它的上层函数进行处理。
 */
public class FinallyTest {
    public static void test(boolean throwIt) throws Exception {//接收抛出的异常，交给调用它的处理

        try {
            // return;
            // System.exit(0);
            if (throwIt) {
                throw new Exception("first");//抛出异常
            }
            System.out.println("haha");//不执行
        } catch (Exception x) {
            System.out.println(x.getMessage());
            throw new Exception("second");//捕获异常，再抛
        } finally {
            System.out.println("finally!");//无论怎样都输出
        }
        System.out.println("last statement");//程序正常执行才输出
    }

    public static void main(String[] args) {
        try {
            test(true);
        } catch (Exception x) {
            System.out.println(x.getMessage());
        }
    }
}
