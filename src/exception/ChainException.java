package exception;

import org.junit.Test;

/**
 * @Author: Rita
 */
public class ChainException {

    //以下实例演示了使用多个 catch 来处理链试异常：
    @Test
    public void test1(){
        int n=20,result=0;
        try{
            result=n/0;
            System.out.println("结果为"+result);
        }
        catch(ArithmeticException ex){
            System.out.println("发现算术异常: "+ex);
            try {
                throw new NumberFormatException();
            }
            catch(NumberFormatException ex1) {
                System.out.println("手动抛出链试异常 : "+ex1);
            }
        }
    }
}
