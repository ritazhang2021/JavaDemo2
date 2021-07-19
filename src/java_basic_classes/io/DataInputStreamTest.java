package java_basic_classes.io;

import org.junit.Test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: Rita
 */
public class DataInputStreamTest {
    /**
     DataInputStream可以从输入流中读取Java基本数据类型值。
     DataInputStream类包含读取数据类型值的读取方法。例如，要读取int值，它包含一个readInt()方法;读取char值，
     它有一个readChar()方法等。它还支持使用readUTF()方法读取字符串。
     * */
    //以下代码显示了如何从文件读取原始值和字符串。
    @Test
    public void test1() throws IOException {
        String srcFile = "primitives.dat";

        try (DataInputStream dis = new DataInputStream(new FileInputStream(srcFile))) {
            // Read the data in the same order they were written
            int intValue = dis.readInt();
            double doubleValue = dis.readDouble();
            boolean booleanValue = dis.readBoolean();
            String msg = dis.readUTF();

            System.out.println(intValue);
            System.out.println(doubleValue);
            System.out.println(booleanValue);
            System.out.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
