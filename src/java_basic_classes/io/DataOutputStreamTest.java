package java_basic_classes.io;

import org.junit.Test;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author: Rita
 * DataOutputStream可以将Java基本数据类型值写入输出流。 *
 * DataOutputStream类包含一个写入数据类型的写入方法。它支持使用writeUTF（String text）方法将字符串写入输出流。 *
 * */
public class DataOutputStreamTest {
    //以下代码将一个int值，一个double值，一个布尔值和一个字符串写入名为primitives.dat的文件。
    @Test
    public void test1(){
        String destFile = "primitives.dat";

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(
                destFile))) {
            dos.writeInt(765);
            dos.writeDouble(6789.50);
            dos.writeBoolean(true);
            dos.writeUTF("Java Input/Output  is cool!");

            dos.flush();

            System.out.println("Data has  been  written to "
                    + (new File(destFile)).getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
