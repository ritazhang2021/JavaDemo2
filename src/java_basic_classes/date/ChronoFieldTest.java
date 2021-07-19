package java_basic_classes.date;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * @Author: Rita
 * ChronoField
 * ChronoField枚举定义了一组标准字段，
 * 如 AMPM_OF_DAY，DAY_OF_MONTH，DAY_OF_WEEK，DAY_OF_YEAR，ERA，HOUR_OF_DAY，MINUTE_OF_HOUR，MONTH_OF_YEAR，
 * 年年约SECOND_OF_MINUTE，YEAR_OF_ERA 。
 */
public class ChronoFieldTest {
    //以下代码显示了如何使用 ChronoField 从datetime中提取字段值。
    @Test
    public void test1(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now.get(ChronoField.YEAR));
        System.out.println(now.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(now.get(ChronoField.DAY_OF_MONTH));
        System.out.println(now.get(ChronoField.HOUR_OF_DAY));
        System.out.println(now.get(ChronoField.HOUR_OF_AMPM));
        System.out.println(now.get(ChronoField.AMPM_OF_DAY));
    }
    //以下代码显示了如何检查数据时间对象是否支持ChronoField。
    @Test
    public void test2(){
        LocalDateTime  now = LocalDateTime.now();
        System.out.println(now.isSupported(ChronoField.YEAR));
        System.out.println(now.isSupported(ChronoField.HOUR_OF_DAY));

        System.out.println(ChronoField.YEAR.isSupportedBy(now));
        System.out.println(ChronoField.HOUR_OF_DAY.isSupportedBy(now));
    }
    //ChronoUnit枚举表示时间单位。
    //它包含以下常量：CENTURIES，DAYS，DECADES，ERAS，FOREVER，HALF_DAYS，HOURS，
    // MICROS，MILLENNIA，MILLIS，MINUTES，MONTHS，NANOS，SECONDS，WEEKS和YEARS。
    //以下代码显示了如何使用ChronoUnit枚举常量。
    @Test
    public void test3(){
        LocalDateTime now = LocalDateTime.now();

        // Get the date time 10 days ago
        LocalDateTime localDateTime1 = now.minus(10, ChronoUnit.DAYS);
        System.out.println(localDateTime1);
    }

}
