package formatter;

import org.junit.Test;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @Author: Rita
 */
public class FormatNumber {
    //下面的代码说明了如何以默认格式为当前语言环境，法语语言环境和德语语言环境格式化数字。
    @Test
    public void test1(){
        double value = 123456789.9876543;

        // Default locale
        printFormatted(Locale.getDefault(), value);

        // Indian locale
        Locale indianLocale = new Locale("en", "IN");
        printFormatted(indianLocale, value);
    }

    public static void printFormatted(Locale locale, double value) {
        // Get number and currency formatter
        NumberFormat nf = NumberFormat.getInstance(locale);
        NumberFormat cf = NumberFormat.getCurrencyInstance(locale);

        System.out.println("Format value: " + value + "  for locale: " + locale);
        System.out.println("Number: " + nf.format(value));
        System.out.println("Currency: " + cf.format(value));
    }
}
