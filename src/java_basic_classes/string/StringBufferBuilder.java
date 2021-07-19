package java_basic_classes.string;

import org.junit.Test;

/**
 * @Author: Rita
 */
public class StringBufferBuilder {
    /**
    效率从高到低：StringBuilder，线程不安全 > StringBuffer， 线程安全> String，不可变
     String、StringBuffer、StringBuilder三者的异同？
     String:不可变的字符序列；底层使用char[]存储
     StringBuffer:可变的字符序列；线程安全的，效率低；底层使用char[]存储，创建了一个长度是16的数组
     StringBuilder:可变的字符序列；jdk5.0新增的，线程不安全的，效率高；底层使用char[]存储
     扩容问题:如果要添加的数据底层数组盛不下了，那就需要扩容底层的数组。
     默认情况下，扩容为原来容量的2倍 + 2，同时将原有数组中的元素复制到新的数组中。
     */
    @Test
    public void testEfficiency(){
        //初始设置
        long startTime = 0L;
        long endTime = 0L;
        String text = "";
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");
        //开始对比
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer的执行时间：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder的执行时间：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            text = text + i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String的执行时间：" + (endTime - startTime));

    }
    //StringBuilder一样的方法
    @Test
    public void testBuildInMethod(){
        StringBuffer s1 = new StringBuffer("abc");
        //增
        s1.append(1);
        s1.append(true);
        System.out.println("append后： "+s1);
        //删
        s1.delete(3,4);
        System.out.println("delete后： "+ s1);
        //改
        s1.replace(2,4,"_replace");
        System.out.println("replace后： "+ s1);
        s1.setCharAt(0,'H');
        System.out.println("setCharAt后："+ s1);
        //查
        System.out.println("查询index 0："+ s1.charAt(0));
        String s2 = s1.substring(1, 3);
        System.out.println("查询index 1, 3："+ s2);
        //插
        s1.insert(2,false);
        System.out.println("插入index 2以后："+ s1);
        System.out.println(s1);
        System.out.println(s1.length());
        System.out.println(s2);
        s1.reverse();
        System.out.println(s1);
    }
}
