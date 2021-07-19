package formatter;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Formatter;

/**
 * @Author: Rita
 * 网页上有祥细说明
 */
public class FormatTest {
    @Test
    public void test1(){
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);

        // Formatting strings
        fm.format("%1$s, %2$s,  and  %3$s %n", "A", "B", "C");
        fm.format("%3$s, %2$s,  and  %1$s %n", "D", "E", "F");

        // Formatting numbers
        fm.format("%1$4d, %2$4d, %3$4d %n", 1, 10, 100);
        fm.format("%1$4d, %2$4d, %3$4d %n", 10, 100, 1000);
        fm.format("%1$-4d, %2$-4d,  %3$-4d %n", 1, 10, 100);
        fm.format("%1$-4d, %2$-4d,  %3$-4d %n", 10, 100, 1000);

        // Formatting date and time
        Date dt = new Date();
        fm.format("Today  is %tD  %n", dt);
        fm.format("Today  is %tF %n", dt);
        fm.format("Today  is %tc %n", dt);

        // Display the entire formatted string
        System.out.println(sb.toString());

    }
    //要将所有格式化的文本写入文件，请使用以下代码。
    //我们必须处理FileNotFoundException，如果指定的文件不存在，它可能会从Formatter类的构造函数抛出。
    //我们必须调用它的close()方法来关闭输出文件。
    @Test
    public void test2(){
        File file = new File("xyz.txt");
        Formatter fm = null;
        try {
            // Create a Formatter that will write the output the file
            fm = new Formatter(file);

            // Formatting strings
            fm.format("%1$s, %2$s,  and  %3$s %n", "A", "B", "C");
            fm.format("%3$s, %2$s,  and  %1$s %n", "A", "B", "C");

            // Format more text here...
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fm != null) {
                fm.close();
            }
        }
    }
    //format()方法的第一个版本使用默认语言环境进行格式化。第二个版本允许您指定语言环境。
    //PrintStream类的format()/printf()方法和String类的format()方法支持format()方法的这两个版本。
    /**
     使用Formatter格式化数据需要两种类型的输入:

     格式字符串
     值列表
     格式字符串定义如何格式化值列表。

     格式字符串可以包含静态文本和嵌入格式说明符。静态文本在格式字符串中作为结果输出。

     格式说明符用于两个目的。

     它用作格式字符串中格式化数据的占位符
     它指定如何格式化数据
     * */
    //以下代码显示如何使用上述格式字符串打印格式化文本。
    //在代码中，我们创建了一个LocalDate来存储Mike的生日。
    //本地日期值和“Mike"成为格式字符串的输入值。
    @Test
    public void test3(){
        LocalDate dob = LocalDate.of(1971, Month.MAY, 16);
        System.out.printf(
                "%1$tB %1$td,  %1$tY is %2$s's birth day. Let's go and celebrate.",
                dob, "Mike");
    }
    //格式说明符的一般语法如下:
    //%<argument-index$><flags><width><.precision><conversion>
}
