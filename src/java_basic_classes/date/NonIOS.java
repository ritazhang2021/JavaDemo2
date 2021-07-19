package java_basic_classes.date;

import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DecimalStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

/**
 * @Author: Rita
 * LocalDate使用ISO日历系统，这是公历。
 *
 * Java Date-Time API还支持其他日历，例如泰国佛教日历，Hijrah日历，Minguo日历和日历。
 *
 * 非ISO日历相关类在java.time.chrono包中定义。
 *
 * 对于每个可用的非ISO日历系统，有一个自定义的年表和自定义的Date类。
 *
 * 自定义的Chronology类表示日历系统，而自定义的Date类表示自定义日历系统中的日期。
 *
 * 每个自定义Chronology类都包含一个INSTANCE常量，表示该类的单例实例。
 */
public class NonIOS {
    //以下代码显示获取泰国佛教日历中的当前日期：
    @Test
    public void test1(){
        ThaiBuddhistChronology thaiBuddhistChrono = ThaiBuddhistChronology.INSTANCE;
        ThaiBuddhistDate now = thaiBuddhistChrono.dateNow();
        ThaiBuddhistDate now2 = ThaiBuddhistDate.now();
        System.out.println("Current Date  in Thai  Buddhist: " + now);
        System.out.println("Current Date  in Thai  Buddhist: " + now2);
    }
    //我们可以使用 from()方法将日历系统中的日期转换为另一个日历系统。
    //以下代码显示如何将ISO日期转换为泰语佛教日期，反之亦然。
    @Test
    public void test2(){
        ThaiBuddhistDate thaiBuddhistNow = ThaiBuddhistDate.now();
        LocalDate isoNow = LocalDate.now();
        System.out.println("Thai  Buddhist Current Date: " + thaiBuddhistNow);
        System.out.println("ISO  Current Date: " + isoNow);

        // Convert Thai Buddhist date to ISO date and vice versa
        ThaiBuddhistDate thaiBuddhistNow2 = ThaiBuddhistDate.from(isoNow);
        LocalDate isoNow2 = LocalDate.from(thaiBuddhistNow);
        System.out.println("Thai  Buddhist Current Date  from  ISO:  "
                + thaiBuddhistNow2);
        System.out.println("ISO  Current Date  from  Thai  Buddhist: " + isoNow2);
    }
    //以下代码显示如何将日期转换为不同的日期系统。
    @Test
    public void test3(){
        LocalDate date = LocalDate.of(1996, Month.OCTOBER, 29);
        System.out.printf("%s%n", toString(date, JapaneseChronology.INSTANCE));
        System.out.printf("%s%n", toString(date, MinguoChronology.INSTANCE));
        System.out.printf("%s%n", toString(date, ThaiBuddhistChronology.INSTANCE));
        System.out.printf("%s%n", toString(date, HijrahChronology.INSTANCE));

        System.out.printf("%s%n",
                fromString("10/29/0008 H", JapaneseChronology.INSTANCE));
        System.out.printf("%s%n",
                fromString("10/29/0085 1", MinguoChronology.INSTANCE));
        System.out.printf("%s%n",
                fromString("10/29/2539 B.E.", ThaiBuddhistChronology.INSTANCE));
        System.out.printf("%s%n",
                fromString("6/16/1417 1", HijrahChronology.INSTANCE));
    }
    /**
     * Parses a String to a ChronoLocalDate using a DateTimeFormatter with a short
     * pattern based on the current Locale and the provided Chronology, then
     * converts this to a LocalDate (ISO) value.
     *
     * @param text
     *          - the input date text in the SHORT format expected for the
     *          Chronology and the current Locale.
     *
     * @param chrono
     *          - an optional Chronology. If null, then IsoChronology is used.
     */
    public static LocalDate fromString(String text, Chronology chrono) {
        if (text != null && !text.isEmpty()) {
            Locale locale = Locale.getDefault(Locale.Category.FORMAT);
            if (chrono == null) {
                chrono = IsoChronology.INSTANCE;
            }
            String pattern = "M/d/yyyy GGGGG";
            DateTimeFormatter df = new DateTimeFormatterBuilder().parseLenient()
                    .appendPattern(pattern).toFormatter().withChronology(chrono)
                    .withDecimalStyle(DecimalStyle.of(locale));
            TemporalAccessor temporal = df.parse(text);
            ChronoLocalDate cDate = chrono.date(temporal);
            return LocalDate.from(cDate);
        }
        return null;
    }

    /**
     * Converts a LocalDate (ISO) value to a ChronoLocalDate date using the
     * provided Chronology, and then formats the ChronoLocalDate to a String using
     * a DateTimeFormatter with a SHORT pattern based on the Chronology and the
     * current Locale.
     *
     * @param localDate
     *          - the ISO date to convert and format.
     * @param chrono
     *          - an optional Chronology. If null, then IsoChronology is used.
     */
    public static String toString(LocalDate localDate, Chronology chrono) {
        if (localDate != null) {
            Locale locale = Locale.getDefault(Locale.Category.FORMAT);
            ChronoLocalDate cDate;
            if (chrono == null) {
                chrono = IsoChronology.INSTANCE;
            }
            try {
                cDate = chrono.date(localDate);
            } catch (DateTimeException ex) {
                System.err.println(ex);
                chrono = IsoChronology.INSTANCE;
                cDate = localDate;
            }
            String pattern = "M/d/yyyy GGGGG";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            return dateFormatter.format(cDate);
        } else {
            return "";
        }
    }
}
