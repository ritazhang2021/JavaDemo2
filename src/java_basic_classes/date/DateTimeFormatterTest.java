package java_basic_classes.date;

import org.junit.Test;

import java.time.*;
import java.time.format.*;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

/**
 * @Author: Rita
 */
public class DateTimeFormatterTest {
    //以下代码显示如何使用ISO_DATE格式化程序格式化LocalDate，OffsetDateTime和ZonedDateTime。
    @Test
    public void test1(){
        String ldStr = DateTimeFormatter.ISO_DATE.format(LocalDate.now());
        System.out.println(ldStr);
        String odtStr = DateTimeFormatter.ISO_DATE.format(OffsetDateTime.now());
        System.out.println(odtStr);
        String zdtStr = DateTimeFormatter.ISO_DATE.format(ZonedDateTime.now());
        System.out.println(zdtStr);
    }
    //我们还可以使用格式()从日期时间类格式化日期时间对象。
    @Test
    public void test2(){
        LocalDate ld = LocalDate.now();
        String ldStr = ld.format(DateTimeFormatter.ISO_DATE);
        System.out.println("Local  Date: " + ldStr);

        OffsetDateTime odt = OffsetDateTime.now();
        String odtStr = odt.format(DateTimeFormatter.ISO_DATE);
        System.out.println("Offset  Datetime: " + odtStr);

        ZonedDateTime zdt = ZonedDateTime.now();
        String zdtStr = zdt.format(DateTimeFormatter.ISO_DATE);
        System.out.println("Zoned  Datetime: " + zdtStr);
    }
    //custom
    @Test
    public void test3(){
        LocalDate ld = LocalDate.of(2014, Month.JUNE, 30);
        format(ld, "M/d/yyyy");
        format(ld, "MM/dd/yyyy");
        format(ld, "MMM   dd,   yyyy");
        format(ld, "MMMM   dd,   yyyy");
        format(ld, "EEEE, MMMM   dd,   yyyy");

    }
    public static void format(Temporal co, String pattern) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        String str = fmt.format(co);
        System.out.println(pattern + ": " + str);
    }
    //使用区域设置的自定义日期格式
    //我们可以使用DateTimeFormatter类的Ptern()方法创建一个具有指定格式模式和语言环境的DateTimeFormatter对象。
    //以下代码显示如何创建两个格式化程序，以便在默认语言环境和德语语言环境中以“Month day，Year”格式设置日期格式。
    @Test
    public void test4(){
        DateTimeFormatter fmt1  = DateTimeFormatter.ofPattern("MMMM dd,   yyyy");

        DateTimeFormatter fmt2  = DateTimeFormatter.ofPattern("MMMM dd,   yyyy", Locale.GERMAN);
        /*DateTimeFormatter 类withLocale()方法从同一模式返回指定区域设置的DateTimeFormatter对象。
        DateTimeFormatter fmt2 = fmt1.withLocale(Locale.GERMAN);
        DateTimeFormatter 类中的 getLocale()方法返回当前格式化程序的语言环境。*/
    }

    @Test
    public void test5(){
        LocalTime lt = LocalTime.of(16, 30, 5, 78899);
        format(lt, "HH:mm:ss");
        format(lt, "KK:mm:ss a");
        format(lt, "[MM-dd-yyyy][' at' HH:mm:ss]");

        ZoneId usCentral = ZoneId.of("America/Chicago");
        ZonedDateTime zdt = ZonedDateTime.of(LocalDate.now(), lt, usCentral);
        format(zdt, "MM/dd/yyyy HH:mm:ssXXX");
        format(zdt, "MM/dd/yyyy VV");
        format(zdt, "[MM-dd-yyyy][' at' HH:mm:ss]");
    }
    //自定义日期格式与可选
    //定义自定义日期时间格式时我们可以使用符号 [和] 以标记可选部分。
    //只有当信息可用于所有元素时，才会输出包含在可选节中的模式。
    //以下代码显示如何使用可选格式。
    @Test
    public void test6(){
        String pattern = "MM/dd/yyyy[ 'at' HH:mm:ss]";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);

        LocalDate ld = LocalDate.of(2014, Month.JUNE, 30);
        LocalTime lt = LocalTime.of(17, 30, 12);
        LocalDateTime ldt = LocalDateTime.of(ld, lt);

        String str1 = fmt.format(ld);
        System.out.println(str1);

        String str2 = fmt.format(ldt);

        System.out.println(str2);
    }
    //我们可以从 DateTimeFormatterBuilder 创建自定义日期时间格式。
    //以下代码构建一个 DateTimeFormatter 对象以格式化日期格式，例如“YEAR中的新年度在WEEK_DAY”：
    @Test
    public void test7(){
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendLiteral("New Year in ")
                .appendValue(ChronoField.YEAR)
                .appendLiteral(" is  on  ")
                .appendText(ChronoField.DAY_OF_WEEK, TextStyle.FULL_STANDALONE)
                .toFormatter();
        LocalDate ld  = LocalDate.of(2014, Month.JANUARY, 1);
        String str = ld.format(formatter);
        System.out.println(str);

    }
    //我们可以使用DateTimeFormatterBuilder中的模式创建相同的自定义格式化程序。
    @Test
    public void test8(){
        LocalDate ld  = LocalDate.of(2014,Month.JANUARY,1);
        String pattern = "'New Year in'  yyyy  'is on' EEEE";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String str = ld.format(formatter);
        System.out.println(str);
    }
    //我们可以从不同的语言环境创建DateTimeFormatter。
    //FormatStyle枚举有四个常量：SHORT，MEDIUM，LONG和FULL。
    //这些常量以不同的长度格式化日期和时间。
    @Test
    public void test9(){
        LocalDate ld = LocalDate.of(2014, Month.JUNE, 21);
        LocalTime lt = LocalTime.of(17, 30, 20);
        LocalDateTime ldt = LocalDateTime.of(ld, lt);

        DateTimeFormatter fmt = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.SHORT);
        System.out.println("Formatter  Default Locale: " + fmt.getLocale());
        System.out.println("Short  Date: " + fmt.format(ld));

        fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        System.out.println("Medium Date: " + fmt.format(ld));

        fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        System.out.println("Long  Date: " + fmt.format(ld));

        fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println("Full  Date: " + fmt.format(ld));

        fmt = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println("Short Time:  " + fmt.format(lt));

        fmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        System.out.println("Short  Datetime: " + fmt.format(ldt));

        fmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println("Medium Datetime: " + fmt.format(ldt));

        // Use German locale to format the datetime in medius style
        fmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(
                Locale.GERMAN);
        System.out.println(fmt.format(ldt));

        // Use Indian(English) locale to format datetime in short style
        fmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(
                new Locale("en", "IN"));
        System.out.println(fmt.format(ldt));

        // Use Indian(English) locale to format datetime in medium style
        fmt = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(
                new Locale("en", "IN"));
        System.out.println(fmt.format(ldt));
    }
    //以下代码显示如何使用DateTimeFormatter对象解析MM/dd /yyyy格式的字符串，以构造LocalDate：
    @Test
    public void test10(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        TemporalAccessor ta = formatter.parse("06/10/2014");
        LocalDate ld = LocalDate.from(ta);
        System.out.println(ld);
    }
    //parse()方法可以通过TemporalQuery将字符串直接解析为特定的datetime对象。
    @Test
    public void test11(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate ld = formatter.parse("06/10/2014", LocalDate::from);
        System.out.println(ld);
    }
    //以下代码显示如何使用可选模式从字符串获取最佳匹配日期时间对象。
    @Test
    public void test12(){
        DateTimeFormatter parser = DateTimeFormatter
                .ofPattern("yyyy-MM-dd['T'HH:mm:ss[Z]]");
        parseStr(parser, "2014-06-31");
        parseStr(parser, "2014-06-31T15:31:12");
        parseStr(parser, "2014-06-31T15:31:12-0500");
        parseStr(parser, "2014-06-31Hello");
    }
    public static void parseStr(DateTimeFormatter formatter, String text) {
        try {
            TemporalAccessor ta = formatter.parseBest(text, OffsetDateTime::from,
                    LocalDateTime::from, LocalDate::from);
            if (ta instanceof OffsetDateTime) {
                OffsetDateTime odt = OffsetDateTime.from(ta);
                System.out.println("OffsetDateTime: " + odt);
            } else if (ta instanceof LocalDateTime) {
                LocalDateTime ldt = LocalDateTime.from(ta);
                System.out.println("LocalDateTime: " + ldt);
            } else if (ta instanceof LocalDate) {
                LocalDate ld = LocalDate.from(ta);
                System.out.println("LocalDate: " + ld);
            } else {
                System.out.println("Parsing returned: " + ta);
            }
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }


}
