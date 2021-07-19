package exception;

import org.junit.Test;

/**
 * @Author: Rita
 */
public class TestException {
    @Test
    public void test1(){
        try {
            throw new Exception("My Exception");
        } catch (Exception e) {
            System.err.println("Caught Exception");
            System.err.println("getMessage():" + e.getMessage());
            System.err.println("getLocalizedMessage():"
                    + e.getLocalizedMessage());
            System.err.println("toString():" + e);
            System.err.println("printStackTrace():");
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {

        try { // try语句包含可能发生异常的语句
            quotient(); // 调用方法quotient()
        } catch (MyException e) { // 处理自定义异常
            System.out.println(e.getMessage()); // 输出异常信息
        } catch (ArithmeticException e) { // 处理ArithmeticException异常
            System.out.println("除数不能为0"); // 输出提示信息
        } catch (Exception e) { // 处理其他异常
            System.out.println("程序发生了其他的异常"); // 输出提示信息
        }
    }

    @Test
    public void quotient() throws MyException { // 定义方法抛出异常
        int x, y;
        x = 3; y= 0;
        if (y < 0) { // 判断参数是否小于0
            throw new MyException("除数不能是负数"); // 异常信息
        }
        System.out.println(x/y);
    }


}
class MyException extends Exception { // 创建自定义异常类
    String message; // 定义String类型变量
    public MyException(String ErrorMessagr) { // 父类方法
        //super(ErrorMessagr);
        message = ErrorMessagr;
    }

    @Override
    public String getMessage() { // 覆盖getMessage()方法
        return message;
    }
}
