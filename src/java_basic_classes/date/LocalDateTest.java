package java_basic_classes.date;

import org.junit.Test;

import java.time.*;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * @Author: Rita
 * LocalDate 类表示没有时间或时区的日期。 *
 * 当时间和时区相关时使用LocalDate。 *
 * LocalDate 类包含两个常量，MAX和MIN。 *
 * MAX和MIN分别是最大和最小支持的LocalDate。 *
 * LocalDate.MAX为+ 999999999-12-31，LocalDate.MIN为-999999999-01-01。
 */
public class LocalDateTest {
    //of()方法
    //Java 日期时间 API 的 XXX()方法用于创建对象。
    //以下代码显示了如何创建 LocalDate 类的对象:
    @Test
    public void test1(){
        LocalDate localDate1  = LocalDate.of(2014, 5, 21);
        System.out.println(localDate1);

        LocalDate localDate2  = LocalDate.of(2014, Month.MARCH, 4);
        System.out.println(localDate2);

        LocalDate localDate3  = LocalDate.ofEpochDay(2014);
        System.out.println(localDate3);

        LocalDate localDate4  = LocalDate.ofYearDay(2014, 39);
        System.out.println(localDate4);
    }
    /*from()方法
    from()是一个静态工厂方法，用于从指定的参数派生 datetime 对象。
    与 of()不同， from()需要对指定参数进行数据转换。
    */
    //以下代码显示如何从 LocalDateTime 派生 LocalDate:
    @Test
    public void test2(){
        LocalDateTime localDateTime = LocalDateTime.of(2015, 6,  21,  13, 40);
        System.out.println(localDateTime);

        LocalDate localDate  = LocalDate.from(localDateTime);
        System.out.println(localDate);

        //2015-06-21T13:40
        //2015-06-21
    }
    //with()方法
    //要更改 datetime 对象中的字段，我们可以使用带有前缀的方法。
    //withXXX()方法返回一个对象的副本，指定的字段已更改，因为 Date Time API 中的大多数对象都是不可变的。
    //以下代码显示如何从另一个 LocalDate 获取 LocalDate，并更改年份：
    @Test
    public void test3(){
        LocalDate localDate1  = LocalDate.of(2014, Month.MAY,  2);
        System.out.println(localDate1);

        LocalDate localDate2  = localDate1.withYear(2015);
        System.out.println(localDate2);

        LocalDate localDate3  = localDate1.withYear(2014).withMonth(7);
        System.out.println(localDate3);
        //2014-05-02
        //2015-05-02
        //2014-07-02
    }

    //getXXX()返回对象的指定元素。
    //以下代码显示如何从 LocalDate 对象获取年，月和日:
    @Test
    public void test4(){
        LocalDate localDate = LocalDate.of(2014, 6, 21);
        int year = localDate.getYear();
        System.out.println(year);
        Month month = localDate.getMonth();
        System.out.println(month);

        int day = localDate.getDayOfMonth();
        System.out.println(day);
    }
    //toXXX()方法
    //toXXX()将对象转换为相关类型。
    //以下代码显示了使用 toXXX() 方法的一些示例。
    @Test
    public void test5(){
        LocalDate localDate = LocalDate.of(2014, 6, 21);
        long days = localDate.toEpochDay();
        System.out.println(days);
    }
    //atXXX()方法
    //atXXX()从带有附加信息的现有 datetime 对象创建一个新的 datetime 对象。
    //以下代码在方法中使用以向日期对象添加附加信息。
    @Test
    public void test6(){
        LocalDate localDate  = LocalDate.of(2014, 6, 21);
        System.out.println(localDate);

        LocalDateTime  localTime1 = localDate.atStartOfDay();
        System.out.println(localTime1);

        LocalDateTime  localTime2 = localDate.atTime(16, 21);
        System.out.println(localTime2);
    }
    //以下代码显示了如何使用支持构建器模式来构建本地日期的atXXX()方法：
    @Test
    public void test7(){
        LocalDate localDate  = Year.of(2014).atMonth(6).atDay(21);
        System.out.println(localDate);
    }
    //plusXXX()方法
    //plusXXX()通过添加指定的值来返回对象的副本。
    //以下代码显示如何使用plus方法向本地添加更多时间日期对象。
    @Test
    public void test8(){
        LocalDate localDate  = LocalDate.of(2014, 6, 21);
        LocalDate localDate1  = localDate.plusDays(5);
        System.out.println(localDate1);
        LocalDate localDate2  = localDate.plusMonths(3);
        System.out.println(localDate2);
        LocalDate localDate3  = localDate.plusWeeks(3);
        System.out.println(localDate3);
    }
    //minusXXX()方法
    //minusXXX()通过减去指定的值来返回对象的副本。
    //以下代码显示如何从本地日期对象中减去时间。
    @Test
    public void test9(){
        LocalDate localDate  = LocalDate.of(2014, 6, 21);
        LocalDate localDate1  = localDate.minusMonths(5);
        System.out.println(localDate1);
        LocalDate localDate2  = localDate.minusWeeks(3);
        System.out.println(localDate2);
    }
    //now()方法
    //now() 方法返回各种类的当前时间，例如 LocalDate，LocalTime，LocalDateTime，ZonedDateTime。
    //以下代码显示如何使用 now()方法返回当前日期和时间。
    @Test
    public void test10(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        LocalTime localTime  = LocalTime.now();
        System.out.println(localTime);

        LocalDateTime  dateTime  = LocalDateTime.now();
        System.out.println(dateTime);

        ZonedDateTime dateTimeWithZone  = ZonedDateTime.now();
        System.out.println(dateTimeWithZone);
    }
    //以下代码显示如何合并Year和MonthDay以获取LocalDate。
    //它在未来五年创造圣诞节。
    //以下代码将为12月25日创建一个MonthDay，并将其合并到一个年份以获取LocalDate。
    @Test
    public void test11(){
        MonthDay dec25 = MonthDay.of(Month.DECEMBER, 25);
        Year year = Year.now();

        for (int i = 1; i <= 5; i++) {
            LocalDate ld = year.plusYears(i).atMonthDay(dec25);
            int yr = ld.getYear();
            String weekDay = ld.getDayOfWeek().getDisplayName(TextStyle.FULL,
                    Locale.getDefault());
            System.out.format("Christmas in  %d  is on  %s.%n", yr, weekDay);
        }
    }
    //以下代码片段创建 LocalTime 对象:
    @Test
    public void test12(){
        // current
        LocalTime  localTime1 = LocalTime.now();
        System.out.println(localTime1);
        // 09:30
        LocalTime  localTime2 = LocalTime.of(9, 30);
        System.out.println(localTime2);
        // 09:30:50
        LocalTime  localTime3 = LocalTime.of(9, 30, 50);
        System.out.println(localTime3);
        // 09:30:50.000005678
        LocalTime  localTime4 = LocalTime.of(9, 30, 50, 5678);
        System.out.println(localTime4);
    }
    //本地日期时间
    //LocalDateTime 类表示没有时区的日期和时间。
    //LocalDateTime 是 LocalDate 和 LocalTime 的组合。
    //以下代码显示如何创建 LocalDateTime 对象:
    @Test
    public void test13(){
        // current
        LocalDateTime  localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1);

        // 2014-06-21T16:12:34
        LocalDateTime  localDateTime2 = LocalDateTime.of(2014, Month.JUNE, 21, 16, 12, 34);
        System.out.println(localDateTime2);
        // from  a  local date and  a  local  time
        LocalDate localDate1  = LocalDate.of(2014, 5, 10);
        LocalTime  localTime= LocalTime.of(16, 18,   41);
        LocalDateTime  localDateTime3 = LocalDateTime.of(localDate1, localTime);
        System.out.println(localDateTime3);
    }
}
