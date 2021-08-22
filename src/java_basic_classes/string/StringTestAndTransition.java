package java_basic_classes.string;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @Author: Rita
/*
String:字符串，使用一对""引起来表示。
1.String声明为final的，不可被继承
2.String实现了Serializable接口：表示字符串是支持序列化的。
实现了Comparable接口：表示String可以比较大小
3.String内部定义了final char[] value用于存储字符串数据
4.String:代表不可变的字符序列。简称：不可变性。
体现：1.当对字符串重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值。
2. 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
3. 当调用String的replace()方法修改指定字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
5.通过字面量的方式（区别于new）给一个字符串赋值，此时的字符串值声明在字符串常量池中。
6.字符串常量池中是不会存储相同内容的字符串的。
 */

public class StringTestAndTransition {
    public static void main(String[] args) {
        //test args
        System.out.println("String[] args = "+ Arrays.toString(args));
        String [] emptyString1 = new String[0];
        System.out.println("emptyString1 = "+Arrays.toString(emptyString1));
        String [] emptyString2 = {};
        System.out.println("emptyString2 = "+Arrays.toString(emptyString2));
        // all false
        System.out.println("args equals emptyString1 ? "+ args.equals(emptyString1));
        System.out.println("args == emptyString1 ? "+ (args == emptyString1));
        System.out.println("args equals emptyString2 ? "+ args.equals(emptyString2));
        System.out.println("args == emptyString2 ? "+ (args == emptyString2));
        System.out.println(emptyString1.equals(emptyString2));
        System.out.println(emptyString1 == emptyString2);
        System.out.println(emptyString1.length);
        boolean equals1 = emptyString1.equals(null);
        System.out.println("emptyString1 == null ? "+equals1);
        boolean equals2 = emptyString2.equals(null);
        System.out.println("emptyString2 == null ? "+equals2);

        String s1 = "abc";
        String s2 = "ab" + "c";
        System.out.println(s1 == s2);
    }
    @Test
    public  void initializeString(){
        String a1 = new String();
        String a2 = new String("adc");
        String a3 = null;
        char[] c1= {'a', 'b','c'};
        String a4 = new String(c1);
        byte[] b1= {97, 98,99};
        String a5 = new String(b1);
        String a6 = "abc";

        String s2="Hello";//It doesn't create a new instance, point to the constant pool(heap)
        //创建了两个对象，一个是堆空间中new结构，另一个是char[]对应的常量池中的数据："abc"
        String s=new String("abc");
    }

    /**
    替换：
    String replace(char oldChar, char newChar)：返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。
    String replace(CharSequence target, CharSequence replacement)：使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。
    String replaceAll(String regex, String replacement)：使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串。
    String replaceFirst(String regex, String replacement)：使用给定的 replacement 替换此字符串匹配给定的正则表达式的第一个子字符串。
    匹配:
    boolean matches(String regex)：告知此字符串是否匹配给定的正则表达式。
    切片：
    String[] split(String regex)：根据给定正则表达式的匹配拆分此字符串。
    String[] split(String regex, int limit)：根据匹配给定的正则表达式来拆分此字符串，最多不超过limit个，如果超过了，剩下的全部都放到最后一个元素中。
     */
    @Test
    public void buildInMethodTest(){
        String str1 = "北京迪士尼";
        String str2 = str1.replace('北', '东');

        System.out.println(str1);
        System.out.println(str2);

        String str3 = str1.replace("北京", "上海");
        System.out.println(str1);
        System.out.println(str3);

        System.out.println("*************************");
        //把字符串中的数字替换成,，如果结果中开头和结尾有，的话去掉
        String str = "12hello34world5java67891";
        String string = str.replaceAll("\\d+", ",").replaceAll("^,|,$", "");
        System.out.println(string);

        System.out.println("*************************");
        //判断str字符串中是否全部有数字组成，即有1-n个数字组成
        str = "123456";
        boolean matches = str.matches("\\d+");
        System.out.println(matches);

        //判断这是否是一个北京的固定电话
        String tel = "010-8888888";
        boolean result = tel.matches("010-\\d{7,8}");
        System.out.println(result);


        System.out.println("*************************");
        str = "hello|world|java";
        String[] strs = str.split("\\|");
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
        System.out.println();
        str2 = "hello.world.java";
        String[] strs2 = str2.split("\\.");
        for (int i = 0; i < strs2.length; i++) {
            System.out.println(strs2[i]);
        }


    }

    /**
     boolean endsWith(String suffix)：测试此字符串是否以指定的后缀结束
     boolean startsWith(String prefix)：测试此字符串是否以指定的前缀开始
     boolean startsWith(String prefix, int toffset)：测试此字符串从指定索引开始的子字符串是否以指定前缀开始

     boolean contains(CharSequence s)：当且仅当此字符串包含指定的 char 值序列时，返回 true
     int indexOf(String str)：返回指定子字符串在此字符串中第一次出现处的索引
     int indexOf(String str, int fromIndex)：返回指定子字符串在此字符串中第一次出现处的索引，从指定的索引开始
     int lastIndexOf(String str)：返回指定子字符串在此字符串中最右边出现处的索引
     int lastIndexOf(String str, int fromIndex)：返回指定子字符串在此字符串中最后一次出现处的索引，从指定的索引开始反向搜索

     注：indexOf和lastIndexOf方法如果未找到都是返回-1

     */
    @Test
    public void buildInMethodTest2(){
        String str1 = "hellowworld";
        boolean b1 = str1.endsWith("rld");
        System.out.println(b1);

        boolean b2 = str1.startsWith("He");
        System.out.println(b2);

        boolean b3 = str1.startsWith("el",1);
        System.out.println(b3);

        String str2 = "wor";
        System.out.println(str1.contains(str2));

        System.out.println(str1.indexOf("lol"));
        System.out.println(str1.indexOf("lo",5));

        String str3 = "hellorworld";
        //什么情况下，indexOf(str)和lastIndexOf(str)返回值相同？
        //情况一：存在唯一的一个str。情况二：不存在str
        System.out.println(str3.lastIndexOf("or"));
        System.out.println(str3.lastIndexOf("or",6));


    }


    /**
    int length()：返回字符串的长度： return value.length
    char charAt(int index)： 返回某索引处的字符return value[index]
    boolean isEmpty()：判断是否是空字符串：return value.length == 0
    String toLowerCase()：使用默认语言环境，将 String 中的所有字符转换为小写
    String toUpperCase()：使用默认语言环境，将 String 中的所有字符转换为大写
    String trim()：返回字符串的副本，忽略前导空白和尾部空白
    boolean equals(Object obj)：比较字符串的内容是否相同
    boolean equalsIgnoreCase(String anotherString)：与equals方法类似，忽略大小写
    String concat(String str)：将指定字符串连接到此字符串的结尾。 等价于用“+”
    int compareTo(String anotherString)：比较两个字符串的大小
    String substring(int beginIndex)：返回一个新的字符串，它是此字符串的从beginIndex开始截取到最后的一个子字符串。
    String substring(int beginIndex, int endIndex) ：返回一个新字符串，它是此字符串从beginIndex开始截取到endIndex(不包含)的一个子字符串。

     */
    @Test
    public void buildInMethodTest3() {
        String s1 = "HelloWorld";
        String s2 = "helloworld";
        System.out.println(s1.equals(s2));
        System.out.println(s1.equalsIgnoreCase(s2));

        String s3 = "abc";
        String s4 = s3.concat("def");
        System.out.println(s4);

        String s5 = "abc";
        String s6 = new String("abe");
        System.out.println(s5.compareTo(s6));//涉及到字符串排序

        String s7 = "我爱北京天安门";
        String s8 = s7.substring(2);
        System.out.println(s7);
        System.out.println(s8);

        String s9 = s7.substring(2, 5);
        System.out.println(s9);
    }

    @Test
    public void buildInMethodTest4() {
        String s1 = "HelloWorld";
        System.out.println(s1.length());
        System.out.println(s1.charAt(0));
        System.out.println(s1.charAt(9));
        //System.out.println(s1.charAt(10));
        //s1 = "";
        System.out.println(s1.isEmpty());

        String s2 = s1.toLowerCase();
        System.out.println(s1);//s1不可变的，仍然为原来的字符串
        System.out.println(s2);//改成小写以后的字符串

        String s3 = "   he  llo   world   ";
        String s4 = s3.trim();//移除前后的空格
        System.out.println("-----" + s3 + "-----");
        System.out.println("-----" + s4 + "-----");
    }


    /*
    结论：
    1.常量与常量的拼接结果在常量池。且常量池中不会存在相同内容的常量。
    2.只要其中有一个是变量，结果就在堆中。
    3.如果拼接的结果调用intern()方法，返回值就在常量池中
     */
    @Test
    public void constantsVariables1(){
        String s1 = "javaEEhadoop";//常量池
        String s2 = "javaEE";//常量池
        String s3 = s2 + "hadoop";//heap
        System.out.println(s1 == s3);//false

        final String s4 = "javaEE";//s4:常量
        String s5 = s4 + "hadoop";
        System.out.println(s1 == s5);//true

    }

    @Test
    public void constantsVariables2(){
        String s1 = "Hello";
        String s2 = "World";

        String s3 = "HelloWorld";
        String s4 = "Hello" + "World";
        String s5 = s1 + "World";
        String s6 = "Hello" + s2;
        String s7 = s1 + s2;//只要有变量操作，每次操作都是一个新的地址
        String s8 = s1 + s2;

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false
        System.out.println(s7 == s8);//false

        String s9 = s6.intern();//返回值得到的s8使用的常量值中已经存在的“HelloWorld”
        System.out.println(s3 == s9);//true,如果常量池中没有就是false

    }

    @Test
    public void constantsVariables3(){
        //通过字面量定义的方式：此时的s1和s2的数据Hello声明在方法区中的字符串常量池中。
        String s1 = "Hello";
        String s2 = "Hello";
        //通过new + 构造器的方式:此时的s3和s4保存的地址值，是数据在堆空间中开辟空间以后对应的地址值。
        String s3 = new String("Hello");
        String s4 = new String("Hello");

        System.out.println(s1 == s2);//true
        System.out.println(s1 == s3);//false
        System.out.println(s1 == s4);//false
        System.out.println(s3 == s4);//false
        System.out.println("***********************");

    }

    @Test
    public void stringToBytes() throws UnsupportedEncodingException {
        String str1 = "abc123二进制";
        byte[] bytes = str1.getBytes();//使用默认的字符集，进行编码。
        System.out.println(Arrays.toString(bytes));

        byte[] gbks = str1.getBytes("gbk");//使用gbk字符集进行编码。
        System.out.println(Arrays.toString(gbks));

        System.out.println("******************");

        String str2 = new String(bytes);//使用默认的字符集，进行解码。
        System.out.println(str2);

        String str3 = new String(gbks);
        System.out.println(str3);//出现乱码。原因：编码集和解码集不一致！

        String str4 = new String(gbks, "gbk");
        System.out.println(str4);//没有出现乱码。原因：编码集和解码集一致！
    }

    @Test
    public void stringToChar(){
        String str1 = "abc123";

        char[] charArray = str1.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            System.out.println(charArray[i]);
        }

        char[] arr = new char[]{'h','e','l','l','o'};
        String str2 = new String(arr);
        System.out.println(str2);
    }


    @Test
    public void stringToPrimitive(){
        //parse, valueof
        String str1 = "123";
//        int num = (int)str1;//错误的
        int num = Integer.parseInt(str1);
        String str2 = String.valueOf(num);//"123"
        String str3 = num + "";

        System.out.println(str1 == str3);

        Float f = Float.parseFloat(str1);
        Float aFloat = Float.valueOf(str1);

        double d = Double.parseDouble(str1);

    }


}


