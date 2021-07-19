package java_basic_classes.date;

import java.time.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @Author: Rita
 * Java 8之前，Java日期和时间由 java.util.Date ， java.util.Calendar 和 java.util.TimeZone 类以及它们的子类（如java.util.GregorianCalendar）定义。
 *
 * 旧的日期时间API在java.util包中定义，而新的Java 8日期时间API在java.time包中定义。
 *
 * java.util.Date
 * java.util.Calendar
 * java.util.GregorianCalendar
 * java.util.TimeZone
 * java.sql.Date
 * java.sql.Time
 * java.sql.Timestamp
 * java.nio.file.attribute.FileTime
 * JDK 8日期时间API定义了几种方法在 java.util 和 java.time 对象之间进行转换。
 *
 * Calendar.toInstant() 将Calendar对象转换为Instant。
 * java.util.Date toInstant() 将Date对象转换为Instant。
 * java.util.Date from() 方法从Instant中创建一个Date对象。
 * GregorianCalendar.toZonedDateTime() 将GregorianCalendar实例转换为ZonedDateTime。
 * GregorianCalendar.from(ZonedDateTime) 使用ZonedDateTime实例中的默认语言环境创建一个GregorianCalendar对象。
 * TimeZone.toZoneId() 将TimeZone对象转换为ZoneId。
 * java.sql.Date toLocalDate() 转换为LocalDate。
 * java.sql.Date valueOf() 从LocalDate创建java.sql.Date
 * java.sql.Time toLocalTime() 转换为LocalTime。
 * java.sql.Time valueOf() 从LocalTime创建java.sql.Time。
 * java.sql.Timestamp toInstant() 转换为Instant。
 * java.sql.Timestamp valueOf() 从LocalDateTime进行转换。
 * java.nio.file.attribute.FileTime toInstant() 转换为Instant。
 * java.nio.file.attribute.FileTime from() 转换为Instant。
 */
public class Conversion {
    //以下代码显示如何将日期转换为即时。
    public void test1(){
        Date dt =  new Date();
        System.out.println("Date: "  + dt);

        Instant in = dt.toInstant();
        System.out.println("Instant: "  + in);

        Date  dt2  = Date.from(in);
        System.out.println("Date: "  + dt2);
    }
    //我们可以将GregorianCalendar转换为ZonedDateTime，它可以转换为新的Date-Time API中的任何其他类。
    //我们可以将Instant转换为ZonedDateTime，然后将ZonedDateTime转换为GregorianCalendar与from()方法从GregorianCalendar。
    //以下代码显示了如何将GregorianCalendar转换为ZonedDateTime，反之亦然。
    public void test2(){
        GregorianCalendar gc = new GregorianCalendar(2014, 1, 11, 15, 45, 50);
        LocalDate ld = gc.toZonedDateTime().toLocalDate();
        System.out.println("Local  Date: " + ld);

        LocalTime lt = gc.toZonedDateTime().toLocalTime();
        System.out.println("Local Time:  " + lt);

        LocalDateTime ldt = gc.toZonedDateTime().toLocalDateTime();
        System.out.println("Local DateTime:  " + ldt);

        OffsetDateTime od = gc.toZonedDateTime().toOffsetDateTime();
        System.out.println("Offset  Date: " + od);

        OffsetTime ot = gc.toZonedDateTime().toOffsetDateTime().toOffsetTime();
        System.out.println("Offset Time:  " + ot);

        ZonedDateTime zdt = gc.toZonedDateTime();
        System.out.println("Zoned DateTime:  " + zdt);

        ZoneId zoneId = zdt.getZone();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);
        System.out.println("Zone ID:  " + zoneId);
        System.out.println("Time Zone ID:  " + timeZone.getID());

        GregorianCalendar gc2 = GregorianCalendar.from(zdt);
        System.out.println("Gregorian  Calendar: " + gc2.getTime());
    }
}
