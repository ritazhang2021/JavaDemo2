package formatter;

import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @Author: Rita
 */
public class PrintfTest {
    @Test
    public void test1(){
        // Formatting strings
        System.out.printf("%1$s, %2$s,  and  %3$s %n", "ABC", "DEF", "XYZ");
        System.out.printf("%3$s, %2$s,  and  %1$s %n", "ABC", "DEF", "XYZ");

        // Formatting numbers
        System.out.printf("%1$4d, %2$4d, %3$4d %n", 1, 10, 100);
        System.out.printf("%1$4d, %2$4d, %3$4d %n", 10, 100, 1000);
        System.out.printf("%1$-4d, %2$-4d,  %3$-4d %n", 1, 10, 100);
        System.out.printf("%1$-4d, %2$-4d,  %3$-4d %n", 10, 100, 1000);

        // Formatting date and time
        Date dt = new Date();
        System.out.printf("Today is  %tD  %n", dt);
        System.out.printf("Today is  %tF  %n", dt);
        System.out.printf("Today is  %tc  %n", dt);

        ZonedDateTime currentTime = ZonedDateTime.now();
        System.out.printf("%tA %<tB  %<te,  %<tY  %n", currentTime);
        System.out.printf("%TA %<TB  %<te,  %<tY  %n", currentTime);
        System.out.printf("%tD %n", currentTime);
        System.out.printf("%tF %n", currentTime);
        System.out.printf("%tc %n", currentTime);
        System.out.printf("%Tc %n", currentTime);

        //ABC, DEF,  and  XYZ
        //XYZ, DEF,  and  ABC
        //   1,   10,  100
        //  10,  100, 1000
        //1   , 10  ,  100
        //10  , 100 ,  1000
        //Today is  06/03/21
        //Today is  2021-06-03
        //Today is  Thu Jun 03 15:14:06 CDT 2021
    }

}
