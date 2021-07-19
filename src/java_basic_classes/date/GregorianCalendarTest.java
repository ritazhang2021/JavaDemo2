package java_basic_classes.date;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @Author: Rita
 * GregorianCalendar类有一个默认的构造函数，它创建一个对象来表示当前的datetime。
 *
 * GregorianCalendar类还定义了我们可以用来创建特定日期的构造函数。
 *
 * 我们还可以在特定时区创建日期。
 */
public class GregorianCalendarTest {
    @Test
    public void test1(){
        // Get the current date in the system default time zone
        GregorianCalendar currentDate = new GregorianCalendar();
        System.out.println(currentDate.getTime());
        // Get GregorianCalendar object representing March 21, 2014 07:30:45 AM
        GregorianCalendar someDate = new GregorianCalendar(2014, Calendar.MARCH,
                21, 7, 30, 45);
        System.out.println(someDate.getTime());
        // Get Indian time zone, which is GMT+05:30
        TimeZone indianTZ = TimeZone.getTimeZone("GMT+05:30");
        GregorianCalendar indianDate = new GregorianCalendar(indianTZ);
        System.out.println(indianDate.getTime());
    }
    //日期的月份部分范围为0到11.一月为0，二月为1，依此类推。
    //get()与请求的字段返回datetime中的字段的值。
    @Test
    public void test2(){
        GregorianCalendar gc = new GregorianCalendar();
        // current year value
        int year = gc.get(Calendar.YEAR);
        System.out.println(year);
        // current month value
        int month = gc.get(Calendar.MONTH);
        System.out.println(month);
        // day of month
        int day = gc.get(Calendar.DAY_OF_MONTH);
        System.out.println(day);
        // hour value
        int hour = gc.get(Calendar.HOUR);
        System.out.println(hour);
        // minute value
        int minute = gc.get(Calendar.MINUTE);
        System.out.println(minute);
        // second values
        int second = gc.get(Calendar.SECOND);
        System.out.println(second);
    }
    //add()向日期添加一个值。 金额可以是负数或正数。 日历知道如何调整。
    @Test
    public void test3(){
        GregorianCalendar gc  = new GregorianCalendar(2014, Calendar.DECEMBER,  1);
        gc.add(Calendar.MONTH,  5);
        System.out.println(gc.getTime());
    }
    //roll()
    //假设我们将GregorianCalendar设置为1999年8月31日。调用roll(Calendar.MONTH，8)将日历设置为1999年4月30日.DAY_OF_MONTH字段在四月份不能为31。 DAY_OF_MONTH被设置为最接近的可能值30.YEAR字段保持1999的值，因为它是比MONTH大的字段。
    //roll(Calendar.MONTH，1)与roll相同(Calendar。MONTH，true)。
    //roll(Calendar.MONTH，-1)与roll(Calendar.MONTH，false)相同。
    @Test
    public void test4(){
        GregorianCalendar gc = new GregorianCalendar();
        System.out.println("Current  Date: " + gc.getTime());
        // Add 1 year
        gc.add(Calendar.YEAR, 1);
        System.out.println(gc.getTime());

        // Add 15 days
        gc.add(Calendar.DATE, 15);
        System.out.println(gc.getTime());
    }
}
