package java_basic_classes.io;

import org.junit.Test;

import java.io.Console;

/**
 * @Author: Rita
 * 控制台类的目的是使Java程序和控制台之间的交互更容易。 *
 * Console类是java.io包中的一个实用程序类，用于访问系统控制台。 *
 * 控制台不能保证在所有机器上的Java程序中可访问。例如，如果您的Java程序作为服务运行，则不会有控制台与JVM相关联。
 */
public class ConsoleTest {
    //我们通过使用System类的静态console()方法获得控制台类的实例，如下所示：
    @Test
    public void test1(){
        //我们通过使用System类的静态console()方法获得控制台类的实例，如下所示：
        Console console = System.console();
        if (console !=  null)  {
            console.printf("Console is available.");
        }
    }
    //程序提示用户输入用户名和密码。如果用户输入password letmein，程序将打印一条消息。
    @Test
    public void test2(){
        Console console = System.console();
        if (console != null) {
            console.printf("Console is  available.%n");
        } else {
            System.out.println("Console is  not  available.%n");
            //return; // A console is not available
        }
        String userName = console.readLine("User Name: ");
        char[] passChars = console.readPassword("Password: ");
        String passString = new String(passChars);
        if (passString.equals("letmein")) {
            console.printf("Hello %s", userName);
        } else {
            console.printf("Invalid  password");
        }
    }

}
