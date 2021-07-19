package java_basic_classes.date;

import org.junit.Test;

import java.time.*;
import java.time.zone.ZoneOffsetTransition;
import java.time.zone.ZoneRules;
import java.util.List;
import java.util.Set;

/**
 * @Author: Rita
 * ZoneOffset 表示与UTC时区偏移的固定区域。
 *
 * ZoneOffset不跟踪由夏令时导致的区域偏移的更改。
 *
 * ZoneOffset类声明三个常量:
 *
 * UTC
 * MAX
 * MIN
 * UTC是UTC的时区偏移常量。
 *
 * MAX和MIN是最大和最小支持的区域偏移。
 *
 * Z用作UtC时区的区域偏移指示符。
 */
public class YearMonthDay {
    //我们可以用小时，分钟和秒的组合创建 ZoneOffset 。
    @Test
    public void test1(){
        ZoneOffset zoneOffset1  = ZoneOffset.ofHours(-1);
        System.out.println(zoneOffset1);
        ZoneOffset zoneOffset2  = ZoneOffset.ofHoursMinutes(6, 30);
        System.out.println(zoneOffset2);
        ZoneOffset zoneOffset3  = ZoneOffset.ofHoursMinutesSeconds(9, 30,  45);
        System.out.println(zoneOffset3);
    }

    //以下代码显示如何从偏移创建区域偏移。
    @Test
    public void test2(){
        ZoneOffset zoneOffset1  = ZoneOffset.of("+05:00");
        ZoneOffset zoneOffset2  = ZoneOffset.of("Z"); // Same as ZoneOffset.UTC
        System.out.println(zoneOffset1);
        System.out.println(zoneOffset2);
    }
    //以下代码从 ZoneOffset 输出常量值。
    @Test
    public void test3(){
        System.out.println("ZoneOffset.UTC: "    + ZoneOffset.UTC);
        System.out.println("ZoneOffset.MIN: "    + ZoneOffset.MIN);
        System.out.println("ZoneOffset.MAX: "    + ZoneOffset.MAX);
        /*注意
        Java Date-Time API支持以小时，分钟和秒为单位的区域偏移量。
        来自ZoneOffset的compareTo()允许我们比较两个区域偏移。
        +1:30的区域偏移在+1:00的区域偏移之前。
        Java Date-Time API支持-18:00到+18:00之间的区域偏移。*/

    }
    /**
     * 时区ID
     * 每个时区都有一个ID，可以用三种格式定义：
     *
     * 在区域偏移中，可以是“Z”，“+ hh:mm:ss”或“-hh:mm:ss”，例如“+01:00”。
     * 前缀为“UTC”，“GMT”或“UT”，后跟区域偏移量，例如“UTC + 01:00”。
     * 在区域名称中，例如，“美洲/芝加哥”。
     * */
    //以下代码显示如何使用of()工厂方法创建ZoneId。
    @Test
    public void test4(){
        ZoneId usChicago   = ZoneId.of("America/Chicago");
        System.out.println(usChicago);
        ZoneId  fixedZoneId = ZoneId.of("+01:00");
        System.out.println(fixedZoneId);
    }
    //ZoneId 中的 getAvailableZoneIds()返回所有已知时区ID。
    @Test
    public void test5(){
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for  (String  zoneId: zoneIds) {
            System.out.println(zoneId);
        }
    }
    //ZoneRules
    //ZoneRules 跟踪区域偏移如何变化。
    //下面的代码显示了如何使用 ZoneRules 以获取有关ZoneId的时间偏移和时间更改的信息。
    @Test
    public void test6(){
        LocalDateTime now = LocalDateTime.now();
        ZoneId  usChicago   = ZoneId.of("America/Chicago");
        System.out.println("Zone ID:  "  + usChicago.getId());

        ZoneRules rules = usChicago.getRules();
        System.out.println("isFixedOffset(): "  + rules.isFixedOffset());
        ZoneOffset offset = rules.getOffset(now);
        System.out.println("Zone offset: "  + offset);

        List<ZoneOffsetTransition> transitions = rules.getTransitions();
        System.out.println(transitions);
    }
    //以下代码显示如何创建Year对象并对其执行基本操作。
    @Test
    public void test7(){
        Year y1 = Year.of(2014);
        System.out.println(y1);
        Year y2 = y1.minusYears(1);
        System.out.println(y2);
        Year y3 = y1.plusYears(1);
        System.out.println(y3);
        Year y4 = Year.now();
        System.out.println(y4);
        if (y1.isLeap()) {
            System.out.println(y1 + "  is a  leap year.");
        } else {
            System.out.println(y1 + "  is not  a  leap year.");
        }
    }
    //年月
    //年月表示年和月的有效组合，例如2012-05，2013-09等。
    //以下代码显示了如何创建Year Month对象并对其执行一些基本操作。
    @Test
    public void test8(){
        YearMonth ym1 = YearMonth.of(2014, Month.JUNE);

        int monthLen = ym1.lengthOfMonth();
        System.out.println(monthLen);

        int yearLen  = ym1.lengthOfYear();
        System.out.println(yearLen);
    }
    /**
     * 月
     * Month 枚举有12个常量来表示12个月。     *
     * 常量名称为 一月，二月，三月，四月，五月，六月，七月，八月，九月，十月，十一月和十二月。     *
     * Month 枚举从1到12按顺序编号，其中一月为1，十二月为12。     *
     * Month枚举从int值创建一个Month实例。     *
     * 我们可以使用from()从date对象创建Month。     *
     * 要获取Month的int值，请使用Month枚举 getValue()方法。
    */
    @Test
    public void test9(){
        LocalDate localDate  = LocalDate.of(2014, Month.AUGUST, 3);
        System.out.println(localDate);
        Month month1  = Month.from(localDate);
        System.out.println(month1);

        Month month2  = Month.of(2);
        System.out.println(month2);

        Month month3  = month2.plus(2);
        System.out.println(month3);

        Month month4  = localDate.getMonth();
        System.out.println(month4);

        int monthIntValue  = month2.getValue();
        System.out.println(monthIntValue);
    }
    //月日
    //MonthDay表示一个月和一个月中某一天的有效组合，例如12-15。
    //以下代码显示了如何创建MonthDay对象并对其执行基本操作。
    @Test
    public void test10(){
        MonthDay md1 = MonthDay.of(Month.DECEMBER, 25);
        MonthDay md2 = MonthDay.of(Month.FEBRUARY, 29);

        if (md2.isValidYear(2014)) {
            System.out.println(md2);
        }
        System.out.println(md1.getDayOfMonth());
    }
    //DayOfWeek
    //DayOfWeek 枚举定义七个常量来表示一周中的七天。
    //DayOfWeek 枚举的常量是 MONDAY，TUESDAY，WEDNESDAY，THURSDAY，FRIDAY，SATURDAY和SUNDAY 。
    //后端的int值为1到7。1表示星期一，2表示星期二...
    //以下代码显示了如何使用DayOfWeek枚举。
    @Test
    public void test11(){
        LocalDate localDate  = LocalDate.of(2014, 6, 21);
        System.out.println(localDate);
        DayOfWeek  dayOfWeek1 = DayOfWeek.from(localDate);
        System.out.println(dayOfWeek1);
        int intValue = dayOfWeek1.getValue();
        System.out.println(intValue);
        DayOfWeek  dayOfWeek2 = localDate.getDayOfWeek();
        System.out.println(dayOfWeek2);
        DayOfWeek  dayOfWeekFromInteger = DayOfWeek.of(7);
        System.out.println(dayOfWeekFromInteger);
        DayOfWeek  dayOfWeekAdded = dayOfWeekFromInteger.plus(1);
        System.out.println(dayOfWeekAdded);
        DayOfWeek  dayOfWeekSubtracted = dayOfWeekFromInteger.minus(2);
        System.out.println(dayOfWeekSubtracted);
    }
}
