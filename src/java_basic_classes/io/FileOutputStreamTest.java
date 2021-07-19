package java_basic_classes.io;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author: Rita
要写入文件，我们需要创建一个FileOutputStream类的对象，它将表示输出流。
 */
public class FileOutputStreamTest {

    public void test1(){
        //要写入文件，我们需要创建一个FileOutputStream类的对象，它将表示输出流。

        String destFile = "luci2.txt";

        // Get the line separator for the current platform
        /**要插入一个新行，使用line.separator系统变量如下。*/
        String lineSeparator = System.getProperty("line.separator");

        String line1 = "test";
        String line2 = "test1";

        String line3 = "test2";
        String line4 = "test3";

        //创建一个FileOutputStream类的对象，它将表示输出流。
        /**
         如果文件包含数据，数据将被擦除。为了保留现有数据并将新数据附加到文件，
         我们需要使用FileOutputStream类的另一个构造函数，它接受一个布尔标志，用于将新数据附加到文件。
         要将数据附加到文件，请在第二个参数中传递true，使用以下代码。
         FileOutputStream fos   = new FileOutputStream(destFile, true);
         * */
        try (FileOutputStream fos = new FileOutputStream(destFile)) {
            /**
             FileOutputStream类有一个重载的write()方法将数据写入文件。我们可以使用不同版本的方法一次写入一个字节或多个字节。
             通常，我们使用FileOutputStream写入二进制数据。
             要向输出流中写入字符串，请将字符串转换为字节。
             String类有一个getBytes()方法，该方法返回表示字符串的字节数组。
             * */
            fos.write(line1.getBytes());
            fos.write(lineSeparator.getBytes());


            fos.write(line2.getBytes());
            fos.write(lineSeparator.getBytes());

            fos.write(line3.getBytes());
            fos.write(lineSeparator.getBytes());

            fos.write(line4.getBytes());

            // Flush the written bytes to the file
            //我们需要使用flush()方法刷新输出流
            fos.flush();

            System.out.println("Text has  been  written to "
                    + (new File(destFile)).getAbsolutePath());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
