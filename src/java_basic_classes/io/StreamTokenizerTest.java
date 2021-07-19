package java_basic_classes.io;

import org.junit.Test;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.StringTokenizer;

import static java.io.StreamTokenizer.*;

/**
 * @Author: Rita
 * Java有一些实用程序类，让我们将一个字符串分解成称为令牌的部分。
 *
 * 我们通过定义分隔符字符来定义被认为是令牌的字符序列。
 *
 * StringTokenizer类位于java.util包中。 StreamTokenizer类位于java.io包中。
 *
 * StringTokenizer将字符串拆分成令牌，而StreamTokenizer让我们在基于字符的流中访问令牌。
 */
public class StreamTokenizerTest {
    @Test
    public void test1(){
        /**
        StringTokenizer对象根据您对定界符的定义将字符串拆分为标记。它一次返回一个令牌
        我们还可以随时更改分隔符。我们可以通过指定字符串并接受默认分隔符来创建一个StringTokenizer，
        它是空格，制表符，换行符，回车符和换行符（“\t \n \r \f”）如下：
        * */
        StringTokenizer st  = new StringTokenizer("here is my string");
        //我们可以在创建StringTokenizer时指定自己的分隔符，如下所示：下面的代码使用空格，逗号和分号作为分隔符。
        String delimiters = " ,;";
        StringTokenizer st2  = new StringTokenizer("my text...",  delimiters);
    }
    @Test
    public void test2(){
        /**
         我们可以使用hasMoreTokens()方法来检查是否有更多的令牌和nextToken()方法从字符串中获取下一个令牌。
         我们还可以使用String类的split()方法将字符串拆分为基于分隔符的令牌。
         split()方法接受正则表达式作为分隔符。
         * */
        //以下代码显示如何使用StringTokenizer和String类的split()方法。
        String str = "This is a  test, this is another test.";
        String delimiters = "  ,"; // a space and a comma
        StringTokenizer st = new StringTokenizer(str, delimiters);

        System.out.println("Tokens  using a  StringTokenizer:");
        String token = null;
        while (st.hasMoreTokens()) {
            token = st.nextToken();
            System.out.println(token);
        }
    }
    //要根据标记的类型区分标记，请使用StreamTokenizer类。
    @Test
    public void test3(){
        /**
         该程序使用StringReader对象作为数据源。我们可以使用FileReader对象或任何其他Reader对象作为数据源。
         重复调用StreamTokenizer的nextToken()方法。它填充StreamTokenizer对象的三个字段：
         ttype，sval和nval。ttype字段指示已读取的令牌类型。
         * */
        String str = "This is a  test, 200.89  which  is  simple 50";
        StringReader sr = new StringReader(str);
        StreamTokenizer st = new StreamTokenizer(sr);
        try {
            while (st.nextToken() != TT_EOF) {
                switch (st.ttype) {
                    case TT_WORD: /* a word has been read */
                        System.out.println("String value: " + st.sval);
                        break;
                    case TT_NUMBER: /* a number has been read */
                        System.out.println("Number value:  " + st.nval);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
