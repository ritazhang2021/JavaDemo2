package java_basic_classes.date;

import com.sun.tools.javac.Main;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.*;

/**
 * @Author: Rita
 * 所有datetime类都支持查询，查询是对信息的请求。 *
 * 我们可以从datetime对象获取日期时间组件，例如，我们可以从LocalDate获取年份。 *
 * 查询请求不可用作组件的信息。 例如，我们可以查询LocalDate以查看它是否为星期一。 查询的结果可以是任何类型。 *
 * TemporalQuery<R> 接口表示一个查询。 *
 * 所有datetime类都有一个query()方法，TemporalQuery作为参数。 *
 * TemporalQueries 类包含几个预定义的查询。 *
 * 如果datetime对象没有查询请求的信息，该查询返回null。
 */
public class TemporalQueryTest {
    //以下代码显示如何使用预定义的查询。
    @Test
    public void test1(){
        TemporalQuery<TemporalUnit> precisionQuery = TemporalQueries.precision();
        TemporalQuery<LocalDate> localDateQuery = TemporalQueries.localDate();

        // Query a LocalDate
        LocalDate ld = LocalDate.now();
        TemporalUnit precision = ld.query(precisionQuery);
        LocalDate queryDate = ld.query(localDateQuery);
        System.out.println("Precision of  LocalDate: " + precision);
        System.out.println("LocalDate of  LocalDate: " + queryDate);

        // Query a LocalTime
        LocalTime lt = LocalTime.now();
        precision = lt.query(precisionQuery);
        queryDate = lt.query(localDateQuery);
        System.out.println("Precision of  LocalTime: " + precision);
        System.out.println("LocalDate of  LocalTime: " + queryDate);

        // Query a ZonedDateTime
        ZonedDateTime zdt = ZonedDateTime.now();
        precision = zdt.query(precisionQuery);
        queryDate = zdt.query(localDateQuery);
        System.out.println("Precision of  ZonedDateTime:  " + precision);
        System.out.println("LocalDate of  ZonedDateTime:  " + queryDate);
    }
    //我们可以通过两种方式创建自定义查询。
    //实现TemporalQuery接口
    //使用方法引用作为查询。该方法应该接受一个TemporalAccessor并返回一个对象。
    @Test
    public void test2(){
        LocalDate ld1  = LocalDate.of(2013, 12,1);
        Boolean  is = ld1.query(new Monday1Query());
        System.out.println(is);
    }
    class Monday1Query implements TemporalQuery<Boolean> {
        @Override
        public Boolean queryFrom(TemporalAccessor temporal) {
            if (temporal.isSupported(ChronoField.DAY_OF_MONTH)
                    && temporal.isSupported(ChronoField.DAY_OF_WEEK)) {
                int dayOfMonth = temporal.get(ChronoField.DAY_OF_MONTH);
                int weekDay = temporal.get(ChronoField.DAY_OF_WEEK);
                DayOfWeek dayOfWeek = DayOfWeek.of(weekDay);
                if (dayOfMonth == 1 && dayOfWeek == DayOfWeek.MONDAY) {
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        }
    }
    //以下代码使用方法引用重写上面的代码。
    public void test3(){
        LocalDate ld1  = LocalDate.of(2013, 12,   1);
        Boolean  is = ld1.query(TemporalQueryTest::queryFrom);
        System.out.println(is);
    }
    public static Boolean queryFrom(TemporalAccessor temporal) {
        if (temporal.isSupported(ChronoField.DAY_OF_MONTH)
                && temporal.isSupported(ChronoField.DAY_OF_WEEK)) {
            int dayOfMonth = temporal.get(ChronoField.DAY_OF_MONTH);
            int weekDay = temporal.get(ChronoField.DAY_OF_WEEK);
            DayOfWeek dayOfWeek = DayOfWeek.of(weekDay);
            if (dayOfMonth == 1 && dayOfWeek == DayOfWeek.MONDAY) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
