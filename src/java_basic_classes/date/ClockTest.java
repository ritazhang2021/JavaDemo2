package java_basic_classes.date;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @Author: Rita
 * 时钟
 * Clock 类表示真实世界的时钟。
 *
 * 我们可以从Clock类中访问时区中的当前时间，日期和时间。
 */
public class ClockTest {
    //我们可以从Clock类中访问时区中的当前时间，日期和时间。
    @Test
    public void test1() {
        //我们可以为系统默认时区创建一个时钟，如下所示。
        Clock clock = Clock.systemDefaultZone();

        //要为指定的时区创建时钟。
        ZoneId z = ZoneId.of("America/Los_Angeles");
        Clock  clock2 = Clock.system(z);
    }
    //要从时钟获取当前的时间，日期和时间，请使用datetime相关类的now(Clock c)方法。
    @Test
    public void test2() {
        Clock clock = Clock.systemDefaultZone();

        Instant instant1 = clock.instant();
        System.out.println(instant1);

        Instant instant2 = Instant.now(clock);
        System.out.println(instant2);

        LocalDate localDate = LocalDate.now(clock);
        System.out.println(localDate);

        ZonedDateTime zoneDateTime = ZonedDateTime.now(clock);
        System.out.println(zoneDateTime);

    }
    //周期
    //周期是以年，月和日为单位的时间跨度。
    //支持负周期。
    //持续时间也是以秒和纳秒为单位测量的时间跨度。
    //持续时间表示机器的精确纳秒数。一个时期更适合人类。
    //1天，2个月，3天，4个月和5天都是周期的实例。2个月期间可能意味着不同的天数，具体取决于不同的月份。
    //以下代码显示了如何创建Period
    @Test
    public void test3() {
        Period p1 = Period.of(2, 3, 5); // 2 years, 3 months, and 5 days
        Period p2 = Period.ofDays(2);  // 2 days
        Period p3 = Period.ofMonths(-3); // -3 months
        Period p4 = Period.ofWeeks(3); // 3 weeks
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
    }
    //Period支持加法，减法，乘法和求反运算。
    //除法运算执行整数除法，例如，除以3除以7为2。
    //以下代码显示如何使用周期上的操作。
    @Test
    public void test4() {
        Period p1  = Period.ofDays(15);
        System.out.println(p1);
        Period p2  = p1.plusDays(12);
        System.out.println(p2);
        Period p3  = p1.minusDays(12);
        System.out.println(p3);
        Period p4  = p1.negated();
        System.out.println(p4);
        Period p5  = p1.multipliedBy(3);
        System.out.println(p5);
    }
    //周期normalized()方法标准化年和月。该方法确保月份值保持在0到11之间。“2年零16个月"被标准化为“3年零4个月"。
    //Period plus()向另一个周期添加一个周期。
    //Period minus()从另一个周期中减去一个周期。
    @Test
    public void test5() {
        Period p1  = Period.of(2, 3, 5);
        Period p2  = Period.of(1, 15,   28);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p1.minus(p2));
        System.out.println(p1.plus(p2));
        System.out.println(p1.plus(p2).normalized());
    }
    //Period Between
    //Date-Time API提供了计算两个日期和时间之间的已用时间的方法。
    //我们可以在ChronoUnit枚举中的一个常量上使用 between()方法。
    //ChronoUnit枚举between()方法需要两个datetime对象并返回一个long。 如果第二个参数出现在第一个参数之前，它返回一个负数。
    //返回金额是两个日期和时间之间的完整单位数。 例如，在06:00和09:30之间调用HOURS.between()，返回值为3，而不是3.5。
    // 而MINUTES.在06:00至09:30之间返回210。
    @Test
    public void test6() {
        LocalDate ld1  = LocalDate.of(2014, Month.JANUARY,  7);
        LocalDate ld2  = LocalDate.of(2014, Month.MAY,  21);
        long  days  = ChronoUnit.DAYS.between(ld1, ld2);
        System.out.println(days);

        LocalTime  lt1 = LocalTime.of(6, 0);
        LocalTime  lt2 = LocalTime.of(9, 30);
        long  hours   = ChronoUnit.HOURS.between(lt1, lt2);
        System.out.println(hours);
        long  minutes = ChronoUnit.MINUTES.between(lt1,   lt2);
        System.out.println(minutes);
    }
    //Period Util
    //Date-Time API提供了计算两个日期和时间之间的已用时间的方法。
    //我们可以对一个日期时间相关类使用 until(end_date_or_time，time_unit)方法，例如LocalDate，
    // LocalTime，LocalDateTime，ZonedDateTime等。
    @Test
    public void test7() {
        LocalDate ld1 = LocalDate.of(2014, Month.JANUARY, 7);
        LocalDate ld2 = LocalDate.of(2014, Month.MAY, 18);

        LocalTime lt1 = LocalTime.of(7, 0);
        LocalTime lt2 = LocalTime.of(9, 30);

        long days = ld1.until(ld2, ChronoUnit.DAYS);
        System.out.println(days);
        long hours = lt1.until(lt2, ChronoUnit.HOURS);
        System.out.println(hours);
        long minutes = lt1.until(lt2, ChronoUnit.MINUTES);
        System.out.println(minutes);
    }
}
