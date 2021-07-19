package java_basic_classes.date;

import org.junit.Test;

import java.time.*;

/**
 * @Author: Rita
 * OffsetTime表示具有相对于UTC的固定区偏移的时间。
 *
 * OffsetTime组合LocalTime和ZoneOffset。
 *
 * 系统默认时区用于在使用now()的偏移时间时获取区域偏移值。
 */
public class OffsetTimeTest {
    //以下代码显示如何创建偏移时间。
    @Test
    public void test1(){
        // current offset time
        OffsetTime ot1 = OffsetTime.now();
        System.out.println("Current  offset  time: " + ot1);

        // a zone offset +01:30
        ZoneOffset offset = ZoneOffset.ofHoursMinutes(1, 30);

        OffsetTime offsetTime = OffsetTime.of(16, 40, 28, 0, offset);
        System.out.println(offsetTime);
    }
    //偏移日期时间
    //OffsetDateTime表示datetime，固定区偏移UTC。
    //OffsetDateTime组合LocalDateTime和ZoneOffset。
    //我们可以从偏移日期和时间提取本地日期和时间。
    //系统默认时区用于在偏移日期和时间使用now()时获取区域偏移值。
    //以下代码显示如何创建偏移日期时间。
    @Test
    public void test2(){
// Get the current offset datetime OffsetDateTime
        OffsetDateTime odt1 = OffsetDateTime.now();
        // Create an offset datetime
        OffsetDateTime odt2 = OffsetDateTime.of(2012, 5, 11, 18, 10, 30, 0, ZoneOffset.UTC);

        // Get the local date and time from the offset datetime
        LocalDate localDate = odt1.toLocalDate();
        LocalTime localTime = odt1.toLocalTime();
        System.out.println(localDate);
        System.out.println(localTime);
    }
    //以下代码显示如何从即时创建偏移日期时间。
    @Test
    public void test3(){
        Instant i1 = Instant.now();

        ZoneId usChicago = ZoneId.of("America/Chicago");
        OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(i1, usChicago);
        System.out.println(offsetDateTime);
    }
}
