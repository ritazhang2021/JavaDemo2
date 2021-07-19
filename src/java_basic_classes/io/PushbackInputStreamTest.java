package java_basic_classes.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * @Author: Rita
 */
public class PushbackInputStreamTest {

    //PushbackInputStream向输入流添加功能，允许我们使用其unread()方法推回读取的字节。
    //有三个版本的unread()方法。一个让我们推回一个字节，另外两个让我们推回多个字节。
    @Test
    public void test1() throws IOException {
        String srcFile = "test.txt";

        try (PushbackInputStream pis = new PushbackInputStream(new FileInputStream(
                srcFile))) {
            byte byteData;
            while ((byteData = (byte) pis.read()) != -1) {
                System.out.print((char) byteData);
                pis.unread(byteData);
                // Reread the byte we unread
                byteData = (byte) pis.read();
                System.out.print((char) byteData);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
