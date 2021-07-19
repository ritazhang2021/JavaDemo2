package java_basic_classes.io;

import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @Author: Rita
 */
public class BufferedOutputStreamTest {
    /**
     在抽象超类OutputStream中定义了三个重要的方法:write()，flush()和close()。
     write()方法将字节写入输出流。
     它有三个版本，允许我们一次写一个字节或多个字节。
     flush()方法用于将任何缓冲的字节刷新到数据宿。
     close()方法关闭输出流。
     要使用BufferedOutputStream装饰器以更好的速度写入文件，请使用以下语句：
     * */
    @Test
    public void test() throws FileNotFoundException {

        BufferedOutputStream bos  = new BufferedOutputStream(new FileOutputStream("your output file  path"));
        //要将数据写入ByteArrayOutputStream，请使用

        ByteArrayOutputStream baos  = new ByteArrayOutputStream();
        //baos.write(buffer); // buffer is a  byte   array
    }
}
