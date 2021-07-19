package java_basic_classes.io;

import org.junit.Test;

import java.io.*;

/**
 * @Author: Rita
 * 我们可以使用System.out和System.err对象引用，只要我们可以使用OutputStream对象。 *
 * 我们可以使用System.in对象，只要我们可以使用InputStream对象。 *
 * System类提供了三个静态设置器方法setOut()，setIn()和setErr()，以用您自己的设备替换这三个标准设备。 *
 */
public class SystemTest {
    //要将所有标准输出重定向到一个文件，我们需要通过传递一个代表我们文件的PrintStream对象来调用setOut()方法
    @Test
    public void test1() throws FileNotFoundException {
        File outFile = new File("stdout.txt");
        PrintStream ps = new PrintStream(new FileOutputStream(outFile));

        System.out.println(outFile.getAbsolutePath());

        System.setOut(ps);

        System.out.println("Hello world!");
        System.out.println("Java I/O  is cool!");
    }

    //我们可以使用System.in对象从标准输入设备（通常是键盘）读取数据。
    //当用户输入数据并按Enter键时，输入的数据变得可用，read()方法每次返回一个字节的数据。
    //以下代码说明如何读取使用键盘输入的数据。\n是Windows上的新行字符。
    @Test
    public void test2() throws IOException {
        System.out.print("Please type   a  message  and  press enter: ");

        String c = "\n";
        while ((c = String.valueOf(System.in.read())) != "\n") {
            System.out.print( c);
        }
    }
    //以下代码说明如何将System.in对象与BufferedReader一起使用。程序不断提示用户输入一些文本，直到用户输入Q或q退出程序。
    @Test
    public void test3() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = "q";
        while (true) {
            System.out.print("Please type a message (Q/q to quit) and press enter:");
            text = br.readLine();
            if (text.equalsIgnoreCase("q")) {
                System.out.println("We have  decided to exit  the   program");
                break;
            } else {
                System.out.println("We typed: " + text);
            }
        }


    }

    @Test
    public void test4() throws IOException {
        //如果我们想要标准输入来自一个文件，我们必须创建一个输入流对象来表示该文件，并使用System.setIn（）方法设置该对象，如下所示。
        FileInputStream fis  = new FileInputStream("stdin.txt");
        System.setIn(fis);
        // Now  System.in.read() will read   from  stdin.txt file
    }

    //标准错误设备用于显示任何错误消息。Java提供了一个名为System.err的PrintStream对象。我们使用它如下:
    @Test
    public void test5() throws IOException {
        //标准错误设备用于显示任何错误消息。Java提供了一个名为System.err的PrintStream对象。我们使用它如下:

        System.err.println("This is  an  error message.");
    }
}
