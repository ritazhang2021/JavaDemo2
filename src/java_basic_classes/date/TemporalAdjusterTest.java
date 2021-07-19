package java_basic_classes.date;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @Author: Rita
我们可能要将日期和时间调整为该月的第一个星期一或下一个星期二。

我们可以使用 TemporalAdjuster 界面来调整日期和时间。接口有一个方法， adjustInto()，它接受一个时间并返回一个时间。

TemporalAdjusters 类包含返回不同类型的预定义日期调整器的静态方法。
 */
public class TemporalAdjusterTest {
    //以下代码显示了如何计算2014年1月1日之后的第一个星期一：
    @Test
    public void test1(){
        LocalDate ld1  = LocalDate.of(2014, Month.JANUARY,  1);
        LocalDate ld2  = ld1.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println(ld1);
        System.out.println(ld2);
    }
    //TemporalAdjusters
    //TemporalAdjusters定义了一些可用于调整日期的有用方法。
    //
    //next(DayOfWeek dayOfWeek)
    //nextOrSame(DayOfWeek dayOfWeek)
    //previous(DayOfWeek dayOfWeek)
    //previousOrSame(DayOfWeek dayOfWeek)
    //firstInMonth(DayOfWeek dayOfWeek)
    //lastInMonth(DayOfWeek dayOfWeek)
    //dayOfWeekInMonth(int ordinal, DayOfWeek dayOfWeek)
    //firstDayOfMonth()
    //lastDayOfMonth()
    //firstDayOfYear()
    //lastDayOfYear()
    //firstDayOfNextMonth()
    //firstDayOfNextYear()
    //ofDateAdjuster(UnaryOperator<LocalDate> dateBasedAdjuster)
    //以下代码显示了如何使用 dayOfWeekInMonth 。
    @Test
    public void test2(){
        LocalDate ld1  = LocalDate.of(2014, Month.MAY,  21);
        System.out.println(ld1);
        LocalDate ld2  = ld1.with(TemporalAdjusters.dayOfWeekInMonth(5, DayOfWeek.SUNDAY));
        System.out.println(ld2);
    }
    //您可以使用ofDateAdjuster()方法为LocalDate创建自己的日期调整器。
    //以下代码创建日期调整程序。
    @Test
    public void test3(){
        // Create an adjuster that retruns a date after 3 months and 2 days
        TemporalAdjuster adjuster = TemporalAdjusters
                .ofDateAdjuster((LocalDate date) -> date.plusMonths(3).plusDays(2));

        LocalDate today = LocalDate.now();
        LocalDate dayAfter3Mon2Day = today.with(adjuster);
        System.out.println("Today: " + today);
        System.out.println("After 3  months  and  2  days: " + dayAfter3Mon2Day);
    }
}
