package formatter;

import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Formatter;

/**
 * @Author: Rita
 */
public class FormatDecimal {
    @Test
    public void DecimalFormat(){
        //1.DecimalFormat的格式化结果是String类型的，想要结果为double需要再次强转。
        DecimalFormat df = new DecimalFormat("#.0000000");
        Float r = 3.56f;
        System.out.println(Double.parseDouble(df.format(Math.PI*r*r)));

    }
    @Test
    public void MathRound(){
        //2.round() 方法可把一个数字舍入为最接近的整数
        //原理是用round方法来将多余的位数舍弃掉，在还原到原来的位数
        Float r = 3.56f;
        System.out.println((double)Math.round(Math.PI*r*r*10000000)/10000000);
    }
    @Test
    public void StringFormat(){
        Float r = 3.56f;
        System.out.println(String.format("%.7f", Math.PI*r*r));

    }
    @Test
    public void NumberFormat(){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(7);
        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);
        Float r = 3.56f;
        System.out.println(nf.format(Math.PI*r*r));

    }
    @Test
    public void SystemPrintf(){
        double x = 8.055;
        System.out.printf("%.2f\n",x);//8.06

    }
    @Test
    public void SystemFormat(){
        double x = 8.055;
        System.out.format("%.2f\n",x);//8.06

    }
    public void Formatter(){
        Formatter a = new Formatter(System.out);
        double x = 8.055;
        a.format("%.2f\n", x);//8.06
        a.close();//关闭a

    }

    public void format(){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);//不足两位自动补0，超过两位的部分不舍去
        double x = 8.055;
        double y = 8.5;
        System.out.println(nf.format(x));//8.055
        System.out.println(nf.format(y));//8.50

    }
    //DecimalFormat类
    //要执行更高级的格式化，我们可以使用DecimalFormat类。
    //DecimalFormat类允许我们提供我们自己的格式模式。 的下表显示模式及其用法。
    //一旦我们创建了DecimalFormat类的对象，就可以改变格式模式使用其 applyPattern()方法。
    @Test
    public void test1(){
        formatNumber("##.##", 12.345);
        formatNumber("##.##", 12.345);
        formatNumber("0000.0000", 12.345);
        formatNumber("#.##", -12.345);

        // Positive and negative number format
        formatNumber("#.##;(#.##)", -12.735);
    }

    public static void formatNumber(String pattern, double value) {
        DecimalFormat formatter = new DecimalFormat();
        // Apply the pattern formatter.applyPattern ( pattern );

        String formattedNumber = formatter.format(value);

        System.out.println("Number:" + value + ", Pattern:" + pattern
                + ", Formatted Number:" + formattedNumber);
    }
    //解析
    //我们还可以使用 parse()方法将字符串解析为数字。 parse()方法返回 java.lang.Number 类的对象。
    //我们可以使用 java.lang.Number 类中的xxxValue()方法来获取原始值，其中xxx可以是byte，double，float，int，long和short。
    @Test
    public void test2(){
        DecimalFormat formatter = new DecimalFormat();
        // Parse a string to decimal number
        String str = "qq1,234.567";
        String pattern = "#,###.###";
        formatter.applyPattern(pattern);

        // Create a ParsePosition object to specify the first digit of
        // number in the string. It is 1 in "qq1,234.567"
        // with the index 2.
        ParsePosition pp = new ParsePosition(2);

        Number numberObject = formatter.parse(str, pp);

        double value = numberObject.doubleValue();
        System.out.println("Parsed Value  is " + value);
    }


}
