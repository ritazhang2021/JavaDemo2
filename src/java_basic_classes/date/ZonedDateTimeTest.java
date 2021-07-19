package java_basic_classes.date;

import org.junit.Test;

import java.time.*;

/**
 * @Author: Rita
 * ZonedDateTime 类表示带有时区规则的日期时间。
 *
 * ZonedDateTime 结合了 LocalDateTime 和 ZoneId 。
 */
public class ZonedDateTimeTest {
    //下面说明如何从 LocalDateTime 创建 ZonedDateTime 。
    @Test
    public void test1(){
        ZoneId usCentral = ZoneId.of("America/Chicago");
        LocalDateTime localDateTime = LocalDateTime.of(2014, Month.MAY, 21, 9, 30);
        System.out.println(localDateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, usCentral);
        System.out.println(zonedDateTime);
        /*注意
        由于夏令时更改，可能会发生时区中的本地时间轴上的间隙或重叠。
        当时钟向前或向后移动一个小时时，会有时间上的间隙或重叠
        如果时间落在间隙的中间，则时间向前移动与间隙相同的量。
        如果时间落在重叠的中间，则时间有效。
        默认情况下，使用较早版本。 withEarlierOffsetAtOverlap()和
        fromLaterOffsetAtOverlap()从ZonedDateTime让您选择所需的区域偏移量，
        如果时间落在重叠。*/
    }
    //以下代码显示ZonedDateTime，其中时间落在间隙和重叠中。
    @Test
    public void test2(){
        ZoneId usChicago = ZoneId.of("America/Chicago");

        // 2014-03-09T02:30 did not exist in America/Chicago time zone
        LocalDateTime ldt = LocalDateTime.of(2014, Month.MARCH, 9, 2, 30);
        ZonedDateTime zdt = ZonedDateTime.of(ldt, usChicago);
        System.out.println(zdt);

        // 2013-10-03T01:30 existed twice in America/Chicago time zone
        LocalDateTime ldt2 = LocalDateTime.of(2013, Month.NOVEMBER, 3, 1, 30);
        ZonedDateTime zdt2 = ZonedDateTime.of(ldt2, usChicago);

        System.out.println(zdt2.withEarlierOffsetAtOverlap());
        System.out.println(zdt2.withLaterOffsetAtOverlap());
    }
    //ZonedDateTime.of Local(LocalDateTime localDateTime，ZoneId zone，Zone Offset preferred Offset)从区域偏移创建ZonedDateTime。
    //如果指定的引用区域偏移无效，则使用重叠的较早区域偏移。
    //-07:00无效，则使用较早的偏移量-05:00。
    @Test
    public void test3(){
        ZoneId  usChicago   = ZoneId.of("America/Chicago");
        ZoneOffset offset5  = ZoneOffset.of("-05:00");
        ZoneOffset offset6  = ZoneOffset.of("-06:00");
        ZoneOffset offset7  = ZoneOffset.of("-07:00");

        LocalDateTime  ldt = LocalDateTime.of(2012, Month.NOVEMBER,  4, 1, 30);
        ZonedDateTime zdt5   = ZonedDateTime.ofLocal(ldt,  usChicago, offset5);
        ZonedDateTime zdt6   = ZonedDateTime.ofLocal(ldt,  usChicago, offset6);
        ZonedDateTime zdt7   = ZonedDateTime.ofLocal(ldt,  usChicago, offset7);

        System.out.println("With offset  "  + offset5 + ": "  + zdt5);
        System.out.println("With offset  "  + offset6 + ": "  + zdt6);
        System.out.println("With offset  "  + offset7 + ": "  + zdt7);
    }
    //以下代码显示了如何将ZonedDateTime内容到本地和偏移量日期，时间和日期时间。
    @Test
    public void test4(){
        ZonedDateTime zdt1   = ZonedDateTime.now();
        System.out.println("Current zoned  datetime:" + zdt1);

        LocalDateTime  ldt = LocalDateTime.of(2012, Month.MARCH,  11,   7, 30);

        ZoneId  usCentralZone = ZoneId.of("America/Chicago");
        ZonedDateTime zdt2   = ZonedDateTime.of(ldt, usCentralZone);
        System.out.println(zdt2);
    }
    //分区日期时间和持续时间
    //当您添加一天的持续时间时，它将始终添加24小时，无论该天有多少小时(23，24或25小时)。
    //当从正常时间改变为日光节约时间或返回时，发生23和25小时。 当进入日光节约时间时，我们失去一个小时。 当离开日光节约时间，我们得到一个小时额外。
    //在2012-03-11T02：00，美国中部时区通过将时钟向前移动一小时进入日光节约，使2012-03-11的时间为23小时。
    @Test
    public void test5(){
        ZoneId usCentral = ZoneId.of("America/Chicago");
        LocalDateTime ldt = LocalDateTime.of(2012, Month.MARCH, 10, 7, 30);
        ZonedDateTime zdt1 = ZonedDateTime.of(ldt, usCentral);

        Duration d1 = Duration.ofHours(24);

        ZonedDateTime zdt2 = zdt1.plus(d1);
        System.out.println(zdt2);
    }
}
