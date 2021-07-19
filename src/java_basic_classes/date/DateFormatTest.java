package java_basic_classes.date;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @Author: Rita
 */
public class DateFormatTest {
    //Java库提供了两个类来格式化日期:
    //java.text.DateFormat
    //java.text.SimpleDateFormat
    //DEFAULT	Mar 27, 2014
    //SHORT	3/27/14
    //MEDIUM	Mar 26, 2014
    //LONG	March 26, 2014
    //FULL	Sunday, November 2, 2014
    //以下代码显示如何以简体中文格式显示语言环境的默认日期，法国和德国。
    @Test
    public void test1() {
        Date today = new Date();

        // Print date in the default locale format
        Locale defaultLocale = Locale.getDefault();
        printLocaleDetails(defaultLocale);
        printDate(defaultLocale, today);

        // Print date in French format
        printLocaleDetails(Locale.FRANCE);
        printDate(Locale.FRANCE, today);

        // Print date in German format. We could also use Locale.GERMANY
        // instead of new Locale ("de", "DE").
        Locale germanLocale = new Locale("de", "DE");
        printLocaleDetails(germanLocale);
        printDate(germanLocale, today);
    }
    public static void printLocaleDetails(Locale locale) {
        String languageCode = locale.getLanguage();
        String languageName = locale.getDisplayLanguage();
        String countryCode = locale.getCountry();
        String countryName = locale.getDisplayCountry();
        // Print the locale info
        System.out.println("Language: " + languageName + "(" + languageCode + "); "
                + "Country: " + countryName + "(" + countryCode + ")");
    }

    public static void printDate(Locale locale, Date date) {
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        String formattedDate = formatter.format(date);
        System.out.println("SHORT: " + formattedDate);

        formatter = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        formattedDate = formatter.format(date);
        System.out.println("MEDIUM: " + formattedDate+"\n");

    }
    //SimpleDateFormat类
    //要创建自定义日期格式，我们可以使用 SimpleDateFormat 类。
    //SimpleDateFormat类是对语言环境敏感的。
    //它的默认构造函数创建一个格式化程序，默认日期格式为默认语言环境。
    //SimpleDateFormat 类中的 format()方法执行日期格式。
    //要更改后续格式化的日期格式，可以通过将新日期格式作为参数传递来使用applyPattern()方法。
    @Test
    public void test2() {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        String formattedDate = simpleFormatter.format(today);
        System.out.println("Today is (dd/MM/yyyy):  " + formattedDate);

        simpleFormatter.applyPattern("MMMM dd, yyyy");
        formattedDate = simpleFormatter.format(today);
        System.out.println("Today is  (MMMM dd, yyyy): " + formattedDate);
    }
    //SimpleDateFormat日期和时间格式由日期和时间模式字符串指定。
    //在格式字符串中，从“A"到“Z"和从“a"到“z"被视为表示日期或时间字符串的组成部分的格式字母。
    //模式详见网页中
    @Test
    public void test3() {
        GregorianCalendar gc  = new GregorianCalendar(2010, Calendar.SEPTEMBER,9);
        Date  birthDate = gc.getTime();
        String pattern = "'I was born on the day' dd 'of the month 'MMMM 'in' yyyy";

        SimpleDateFormat simpleFormatter  = new SimpleDateFormat(pattern);
        System.out.println(simpleFormatter.format(birthDate));
    }
    //以下代码显示如何将字符串解析为日期值。
    @Test
    public void test4() {
        String text = "09/19/2014";
        // Create a pattern for the date text "09/19/2014"
        String pattern = "MM/dd/yyyy";
        SimpleDateFormat simpleFormatter = new SimpleDateFormat(pattern);
        // a ParsePosition object with value zero
        ParsePosition startPos = new ParsePosition(0);
        // Parse the text
        Date parsedDate = simpleFormatter.parse(text, startPos);

        System.out.println(parsedDate);
    }
    //以下代码解析单个字符串中的两个日期值。
    @Test
    public void test5() {
        String text = "ab01/01/1999cd12/31/2000ef";
        String pattern = "MM/dd/yyyy";

        SimpleDateFormat simpleFormatter  = new SimpleDateFormat(pattern);

        // Set  the   start index   at 2
        ParsePosition startPos  = new ParsePosition(2);

        // Parse the   text to get   the   first date (January 1,  1999)
        Date  firstDate = simpleFormatter.parse(text, startPos);
        System.out.println(firstDate);

        //Now, startPos has  its index set after the last  character of the first date parsed.

        int  currentIndex = startPos.getIndex();
        System.out.println(currentIndex);
        // To set its index   to the   next   date increment its index   by  2.

        int  nextIndex = currentIndex + 2;
        startPos.setIndex  (nextIndex);

        // Parse the   text to get   the   second  date (December  31,   2000)
        Date  secondDate = simpleFormatter.parse(text, startPos);
        System.out.println(secondDate);

    }
    //以下代码显示如何解析时间戳以获取时间零件
    @Test
    public void test6() {
        String input = "2014-05-04 09:10:40.321";

        // Prepare the pattern
        String pattern = "yyyy-MM-dd HH:mm:ss.SSS";

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        // Parse the text into a Date object
        Date dt = sdf.parse(input, new ParsePosition(0));
        System.out.println(dt);

        // Get the Calendar instance
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        // Print time parts
        System.out.println("Hour:" + cal.get(Calendar.HOUR));
        System.out.println("Minute:" + cal.get(Calendar.MINUTE));
        System.out.println("Second:" + cal.get(Calendar.SECOND));
        System.out.println("Millisecond:" + cal.get(Calendar.MILLISECOND));
    }

}
