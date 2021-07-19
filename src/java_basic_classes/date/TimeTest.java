package java_basic_classes.date;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

/**
 * @Author: Rita
 * 实例和持续时间允许我们记录时间戳和已用时间。 *
 * 我们可以从瞬间添加和减去持续时间，以获得另一个瞬间。 *
 * 通过添加两个持续时间，我们可以获得另一个持续时间。 *
 * 即时和持续时间类别分别存储秒和纳秒。 *
 * 即时和持续时间更常使用机器级时间。
 */
public class TimeTest {
    //我们可以使用Instant.now()来获取使用系统默认时钟的当前时刻。
    @Test
    public void test1(){
        Instant instantNow = Instant.now();
        System.out.println(instantNow);
    }
    //以下代码创建一个Instant对象以表示从时代开始的9秒。
    @Test
    public void test2(){
        Instant instance9 = Instant.ofEpochSecond(9);
        System.out.println(instance9);

        instance9 = Instant.ofEpochSecond(-9);
        System.out.println(instance9);
    }
    //持续时间
    //Duration 对象表示两个时刻之间的时间跨度。
    //Duration类可以具有正值和负值。
    //我们可以使用它的一个XXXX()静态工厂方法创建Duration类。
    @Test
    public void test3(){
        Duration d1  = Duration.ofDays(2);
        System.out.println(d1);

        Duration d2  = Duration.ofMinutes(2);
        System.out.println(d2);
    }
    //乘法，除法和否定适用于Duration对象。
    @Test
    public void test4(){
        Duration d  = Duration.ofSeconds(200); // 3 minutes and 20 seconds
        Duration d1  = d.multipliedBy(2);   // 6  minutes and  40  seconds
        Duration d2  = d.negated();            // -3  minutes and  -20  seconds
        System.out.println(d);
        System.out.println(d1);
        System.out.println(d2);
    }
    //以下代码显示了如何从Instant中获取秒和纳秒：
    @Test
    public void test5(){
        Instant i1 = Instant.now();

        long seconds = i1.getEpochSecond();
        System.out.println(seconds);
        int nanoSeconds = i1.getNano();
        System.out.println(nanoSeconds);
    }
    //以下代码显示了如何进行即时和持续时间计算。
    @Test
    public void test6(){
        Duration d1 = Duration.ofSeconds(55);
        Duration d2 = Duration.ofSeconds(-17);

        Instant i1 = Instant.now();

        Instant i4 = i1.plus(d1);
        Instant i5 = i1.minus(d2);

        Duration d3 = d1.plus(d2);
        System.out.println(d3);
    }


}
