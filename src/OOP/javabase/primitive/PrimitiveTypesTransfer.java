package OOP.javabase.primitive;

/**
 * @Author: Rita
 *
byte 1字节=8 bit位 -128 ~ 127
short 2字节 -2 15 ~215 -1
char 型数据用来表示通常意义上“字符”(2字节)
int 4字节 -2 31 ~ 231 -1 (约21亿)
long 8字节 -2 63 ~ 263 -1
float 4字节 -3.403E38 ~ 3.403E38 （不是字节小存内存就小，字节提升）
double 8字节 -1.798E308 ~ 1.798E308

byte, short, char->int->long->float->double

有多种类型的数据混合运算时，系统首先自动将所有数据转换成容量最大的 那种数据类型，然后再进行计算。

byte,short,char之间不会相互转换，他们三者在计算时首先转换为int类型。 
 */
public class PrimitiveTypesTransfer {


    public static void transferToString(){
        //String str1 = 4; //wrong
        String str2 = 3.5f + "";
        System.out.println(str2); //”3.5”
        System.out .println(9+4+"Hello!"); //13Hello!
        System.out.println("Hello!"+3+4); //输出：Hello!34
        System.out.println('a'+1+"Hello!"); //输出：98Hello!
        System.out.println("Hello"+'a'+1); //输出：Helloa1
    }
    public static void charAndString(){
        //1.The most efficient
        String s1 = String.valueOf('c');
        //2.Converts an char array to strings
        String s2 = String.valueOf(new char[] {'H','e','l','l','o'});
        //3.It's actually called String.valueOf(char)
        //only convert characters, but cannot convert char array
        String s3 = Character.toString('c');
        //4.直接用 空 String 拼接 'c'
        //The least efficient, because the String class is final
        //"" + 'c' creates a new String class. If you do a lot, you will create many classes and get overflow
        String s4 = "" + 'c' ;
        
    }
    public static void FoatAndString(){
        String   s   =   "123.456 ";  //要确保字符串为一个数值，否则会出异常
        float   f   =   Float.parseFloat(s);
        float   f2   =   Float.valueOf(s);
        String s1 = Float.toString(f2);
        double d = f;

    }
    public static void DoubleAndString(){
        String   s   =   "123.456 ";  //要确保字符串为一个数值，否则会出异常
        double   d   =   Double.parseDouble(s);
        float f = (float)(d);
        //Float f1 = (Float)(d);//只能是基本数据类型，不能是object
        int b = (int)d;
    }

    public static void toBoolean(){
        //int i = true;
        String   s   =   "123.456 ";
        boolean aBoolean = Boolean.getBoolean(s);
        Boolean aBoolean1 = Boolean.valueOf(s);
        boolean b = Boolean.parseBoolean(s);
        String s1 = Boolean.toString(true);


    }
    public static void toShort(){
        short s = 5;
        //s = s-2; //判断：no, int 转short，需要强转
        s +=  s -2; //判断：yes, s在加操作后变为整型
        byte b = 3;
        //b = b + 4; //判断：no
        b = (byte)(b+4); //判断：yes
        char c = 'a';
        int i = 5;
        float d = .314F;
        double result = c+i+d; //判断：yes
        byte bb = 5;
        short ss = 3;
        //short t = ss + bb; //判断：no
    }
    public static void decimalToOther(){
        //十进制整数转换成二进制整数，返回结果是一个字符串
        int i = 1;
        Integer.toBinaryString(i);

        Integer.toHexString(i);
        Integer.toOctalString(i);
        //五进制字符串14414转换成十进制整数
        System.out.println(Integer.valueOf("14414", 5));

        //Integer和Long提供的valueOf(String source, int radix)方法，可以将任意进制的字符串转换成十进制数据。

    }


}
