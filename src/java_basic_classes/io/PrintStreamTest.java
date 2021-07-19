package java_basic_classes.io;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * @Author: Rita
PrintStream类是输出流的具体装饰器。
PrintStream可以以合适的格式打印任何数据类型值，基本或对象。
PrintStream可以将数据写入输出流不抛出IOException。
如果一个方法抛出一个IOException，PrintStream设置一个内部标志，而不是抛出异常给调用者。可以使用其checkError()方法检查该标志，
如果在方法执行期间发生IOException，则返回true。
PrintStream具有自动刷新功能。我们可以在其构造函数中指定它应该自动刷新写入它的内容。
如果我们将auto-flush标志设置为true，当写入一个字节数组时，PrintStream将刷新它的内容，
它的一个重载的println()方法用于写入数据，一个换行符被写入，或一个字节（‘\n’）。
 */
public class PrintStreamTest {
    @Test
    public void test1(){
        String destFile = "luci3.txt";

        try (PrintStream ps = new PrintStream(destFile)) {
            ps.println("test");
            ps.println("test1");
            ps.println("test2");
            ps.print("test3");

            // flush the print stream
            ps.flush();

            System.out.println("Text has  been  written to "
                    + (new File(destFile).getAbsolutePath()));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

}
