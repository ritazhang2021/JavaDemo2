package java_basic_classes.io;

import org.junit.Test;

import java.io.*;

/**
 * @Author: Rita
 * Java阅读器和写入器是基于字符的流。 *
 * 当我们要从数据源读取基于字符的数据时，使用读取器。当我们想要写基于字符的数据时使用写入器。 *
 * 如果我们有一个流提供字节，我们想通过将这些字节解码为字符读取字符，我们应该使用InputStreamReader类。
 */
public class InputStreamReaderTest {
    @Test
    public void test1() throws UnsupportedEncodingException {
        InputStream iso = null;
        OutputStream oso = null;
        //例如，如果我们有一个名为iso的InputStream对象，并且我们想要获取一个Reader对象实例，我们可以这样做:
        Reader reader = new InputStreamReader(iso);

        //如果我们知道在基于字节的流中使用的编码，我们可以在创建Reader对象时指定它，如下所示:
        Reader  reader2 = new InputStreamReader(iso,  "US-ASCII");

        //类似地，我们可以创建一个Writer对象，从基于字节的输出流中吐出字符，如下所示，假设oso是一个OutputStream对象:

        //以下代码使用平台默认编码从OutputStream创建Writer对象。

        Writer writer  = new OutputStreamWriter(oso);

        //使用“US-ASCII"编码从OutputStream创建Writer对象

        Writer writer2  = new OutputStreamWriter(oso,  "US-ASCII");
    }

    //write
    @Test
    public void test2() throws Exception {
        String destFile = "test.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(destFile))) {
            bw.append("test");
            bw.newLine();
            bw.append("test1");
            bw.newLine();
            bw.append("test2");
            bw.newLine();
            bw.append("test3");

            bw.flush();
        }  catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    //以下代码从test.txt文件中读取文本。
    @Test
    public void test3() throws Exception {
        String srcFile = "test.txt";
        BufferedReader br = new BufferedReader(new FileReader(srcFile));
        String text = null;

        while ((text = br.readLine()) != null) {
            System.out.println(text);
        }
        br.close();
    }
}
