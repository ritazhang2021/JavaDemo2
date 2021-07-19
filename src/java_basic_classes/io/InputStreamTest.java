package java_basic_classes.io;

import org.junit.Test;

import java.io.*;

/**
 * @Author: Rita
 * 处理流之二：转换流的使用
 * 1.转换流：属于字符流
 *   InputStreamReader：将一个字节的输入流转换为字符的输入流
 *   OutputStreamWriter：将一个字符的输出流转换为字节的输出流
 * 2.作用：提供字节流与字符流之间的转换
 * 3. 解码：字节、字节数组  --->字符数组、字符串
 *    编码：字符数组、字符串 ---> 字节、字节数组
 * 4.字符集
 *ASCII：美国标准信息交换码。
用一个字节的7位可以表示。
ISO8859-1：拉丁码表。欧洲码表
用一个字节的8位表示。
GB2312：中国的中文编码表。最多两个字节编码所有字符
GBK：中国的中文编码表升级，融合了更多的中文文字符号。最多两个字节编码
Unicode：国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符码。所有的文字都用两个字节来表示。
UTF-8：变长的编码方式，可用1-4个字节来表示一个字符。
 */
public class InputStreamTest {
    /**
     * 在Java I/O中，流意味着数据流。流中的数据可以是字节，字符，对象等。     *
     * 要从文件读取，我们需要创建一个FileInputStream类的对象，
     * 它将表示输入流。*/
    @Test
    public void test1() throws IOException {

        //如果文件不存在，FileInputStream类的构造函数将抛出FileNotFoundException异常。
        // 要处理这个异常，我们需要将你的代码放在try-catch块中，如下所示：
        try  {
            FileInputStream fin  = new FileInputStream("dbcp.txt");
            try  {
                fin.close();
            }catch (IOException e)  {
                e.printStackTrace();
            }
        }catch  (FileNotFoundException e){
            // The error  handling code  goes  here
        }

        /**
         通常，我们在try块中构造一个输入流，并在finally块中关闭它，以确保它在我们完成后总是关闭。
         所有输入/输出流都可自动关闭。我们可以使用try-with-resources来创建它们的实例，所以无论是否抛出异常，
         它们都会自动关闭，避免需要显式地调用它们的close()方法。
         以下代码显示使用try-with-resources创建文件输入流：
         * */
        String srcFile = "test.txt";
        try  (FileInputStream fin  = new FileInputStream(srcFile))  {
            // Use fin to read   data from  the   file here
        }
        catch  (FileNotFoundException e)  {
            // Handle  the   exception here
        }

    }
    //读取数据
    /**
     FileInputStream类有一个重载的read()方法从文件中读取数据。我们可以一次读取一个字节或多个字节。
     read()方法的返回类型是int，虽然它返回一个字节值。如果到达文件的结尾，则返回-1。
     我们需要将返回的int值转换为一个字节，以便从文件中读取字节。通常，我们在循环中一次读取一个字节。
     最后，我们需要使用close()方法关闭输入流。
     以下代码显示了如何从文件输入流一次读取一个字节。
     * */
    @Test
    public void test2() throws IOException {
        String dataSourceFile = "asdf.txt";
        try (FileInputStream fin = new FileInputStream(dataSourceFile)) {

            byte byteData;
            while ((byteData = (byte) fin.read()) != -1) {
                System.out.print((char) byteData);
            }
        } catch (FileNotFoundException e) {
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
    此时处理异常的话，仍然应该使用try-catch-finally
    InputStreamReader的使用，实现字节的输入流到字符的输入流的转换
     */
    @Test
    public void test3() throws IOException {

        FileInputStream fis = new FileInputStream("dbcp.txt");
//        InputStreamReader isr = new InputStreamReader(fis);//使用系统默认的字符集
        //参数2指明了字符集，具体使用哪个字符集，取决于文件dbcp.txt保存时使用的字符集
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");//使用系统默认的字符集

        char[] cbuf = new char[20];
        int len;
        while((len = isr.read(cbuf)) != -1){
            String str = new String(cbuf,0,len);
            System.out.print(str);
        }

        isr.close();

    }

    /*
    此时处理异常的话，仍然应该使用try-catch-finally
    综合使用InputStreamReader和OutputStreamWriter
     */
    @Test
    public void test4() throws Exception {
        //1.造文件、造流
        File file1 = new File("dbcp.txt");
        File file2 = new File("dbcp_gbk.txt");

        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        InputStreamReader isr = new InputStreamReader(fis,"utf-8");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"gbk");

        //2.读写过程
        char[] cbuf = new char[20];
        int len;
        while((len = isr.read(cbuf)) != -1){
            osw.write(cbuf,0,len);
        }

        //3.关闭资源
        isr.close();
        osw.close();

    }
}
