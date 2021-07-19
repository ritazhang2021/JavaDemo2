package OOP.javabase.primitive;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;

/**
 * @Author: Rita
 */
public class FormatDecimal {
    @Test
    public void DecimalFormat(){
        //1.DecimalFormat的格式化结果是String类型的，想要结果为double需要再次强转。
        DecimalFormat df = new DecimalFormat("#.0000000");
        Float r = 3.56f;
        System.out.println(Double.parseDouble(df.format(Math.PI*r*r)));

    }
    @Test
    public void MathRound(){
        //2.round() 方法可把一个数字舍入为最接近的整数
        //原理是用round方法来将多余的位数舍弃掉，在还原到原来的位数
        Float r = 3.56f;
        System.out.println((double)Math.round(Math.PI*r*r*10000000)/10000000);

    }
    @Test
    public void bigDecimal(){

        BigDecimal b = new BigDecimal("2.225667").setScale(2, BigDecimal.ROUND_DOWN);
        System.out.println(b);//2.22 直接去掉多余的位数

        BigDecimal c = new BigDecimal("2.224667").setScale(2, BigDecimal.ROUND_UP);
        System.out.println(c);//2.23 跟上面相反，进位处理

        BigDecimal f = new BigDecimal("2.224667").setScale(2, BigDecimal.ROUND_CEILING);
        System.out.println(f);//2.23 如果是正数，相当于BigDecimal.ROUND_UP

        BigDecimal g = new BigDecimal("-2.225667").setScale(2, BigDecimal.ROUND_CEILING);
        System.out.println(g);//-2.22 如果是负数，相当于BigDecimal.ROUND_DOWN

        BigDecimal h = new BigDecimal("2.225667").setScale(2, BigDecimal.ROUND_FLOOR);
        System.out.println(h);//2.22 如果是正数，相当于BigDecimal.ROUND_DOWN

        BigDecimal i = new BigDecimal("-2.224667").setScale(2, BigDecimal.ROUND_FLOOR);
        System.out.println(i);//-2.23 如果是负数，相当于BigDecimal.ROUND_HALF_UP

        BigDecimal d = new BigDecimal("2.225").setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("ROUND_HALF_UP"+d); //2.23  四舍五入（若舍弃部分>=.5，就进位）

        BigDecimal e = new BigDecimal("2.225").setScale(2, BigDecimal.ROUND_HALF_DOWN);
        System.out.println("ROUND_HALF_DOWN"+e);//2.22  四舍五入（若舍弃部分>.5,就进位）

        BigDecimal j = new BigDecimal("2.225").setScale(2, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(j);//2.22 如果舍弃部分左边的数字为偶数，则作   ROUND_HALF_DOWN

        BigDecimal k = new BigDecimal("2.215").setScale(2, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(k);//2.22 如果舍弃部分左边的数字为奇数，则作   ROUND_HALF_UP


        System.out.println("************************************");

        System.out.println("4.05: "+new BigDecimal("4.05").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.05: 4.0  down
        System.out.println("4.15: "+new BigDecimal("4.15").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.15: 4.2  up
        System.out.println("4.25: "+new BigDecimal("4.25").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.25: 4.2  down
        System.out.println("4.35: "+new BigDecimal("4.35").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.35: 4.4  up
        System.out.println("4.45: "+new BigDecimal("4.45").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.45: 4.4  down
        System.out.println("4.55: "+new BigDecimal("4.55").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.55: 4.6  up
        System.out.println("4.65: "+new BigDecimal("4.65").setScale(1, BigDecimal.ROUND_HALF_EVEN));//4.65: 4.6  down

        System.out.println("3.05: "+new BigDecimal("3.05").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.05: 3.0  down
        System.out.println("3.15: "+new BigDecimal("3.15").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.15: 3.2  up
        System.out.println("3.25: "+new BigDecimal("3.25").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.25: 3.2  down
        System.out.println("3.35: "+new BigDecimal("3.35").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.35: 3.4  up
        System.out.println("3.45: "+new BigDecimal("3.45").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.45: 3.4  down
        System.out.println("3.55: "+new BigDecimal("3.55").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.55: 3.6  up
        System.out.println("3.65: "+new BigDecimal("3.65").setScale(1, BigDecimal.ROUND_HALF_EVEN));//3.65: 3.6  down

        BigDecimal l = new BigDecimal("2.215").setScale(3, BigDecimal.ROUND_UNNECESSARY);
        System.out.println(l);
        //断言请求的操作具有精确的结果，因此不需要舍入。
        //如果对获得精确结果的操作指定此舍入模式，则抛出ArithmeticException。
    }

    @Test
    public void StringFormat(){
        Float r = 3.56f;
        System.out.println(String.format("%.7f", Math.PI*r*r));

    }
    @Test
    public void NumberFormat(){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(7);
        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);
        Float r = 3.56f;
        System.out.println(nf.format(Math.PI*r*r));

    }
    @Test
    public void SystemPrintf(){
        double x = 8.055;
        System.out.printf("%.2f\n",x);//8.06

    }
    @Test
    public void SystemFormat(){
        double x = 8.055;
        System.out.format("%.2f\n",x);//8.06

    }
    public void Formatter(){
        Formatter a = new Formatter(System.out);
        double x = 8.055;
        a.format("%.2f\n", x);//8.06
        a.close();//关闭a

    }

    public void format(){
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMinimumFractionDigits(2);//不足两位自动补0，超过两位的部分不舍去
        double x = 8.055;
        double y = 8.5;
        System.out.println(nf.format(x));//8.055
        System.out.println(nf.format(y));//8.50

    }



}
