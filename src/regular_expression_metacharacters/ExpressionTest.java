package regular_expression_metacharacters;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Rita
 * @Date:5/26/2021 11:21 PM
 */
public class ExpressionTest {
    // \\
    @Test
    public void test1(){
        Pattern p = Pattern.compile("Java \\d");
        String candidate = "Java 4";
        Matcher m = p.matcher(candidate);

        if (m != null) {
            System.out.println(m.find());
        }
    }

    //以下代码 \w+ 匹配任何单词。 //双斜杠用于转义 \ 。
    @Test
    public void test2() {
        String regex = "\\w+";
        Pattern pattern = Pattern.compile(regex);

        String candidate = "asdf Java2s.com";
        Matcher matcher = pattern.matcher(candidate);
        if (matcher.find()) {
            System.out.println("GROUP 0:" + matcher.group(0));
        }
    }
    //以下代码编译设置CASE_INSENSTIVE和DOTALL标志的正则表达式。
    @Test
    public void test3(){
        String regex   = "[a-z]@.";
        Pattern p  = Pattern.compile(regex,  Pattern.CASE_INSENSITIVE|Pattern.DOTALL);
    }

    @Test
    public void test4(){
        Pattern p = Pattern.compile("java", Pattern.CASE_INSENSITIVE);

        String candidateString = "Java. java JAVA jAVA";

        Matcher matcher = p.matcher(candidateString);

        // display the latter match
        System.out.println(candidateString);
        matcher.find(11);
        System.out.println(matcher.group());

        // display the earlier match
        System.out.println(candidateString);
        matcher.find(0);
        System.out.println(matcher.group());
    }
    //Matcher
    @Test
    public void test5(){
        String regex = "[abc]@.";
        String source = "abc@example.com";
        findPattern(regex, source);
    }
    public static void findPattern(String regex, String source) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(source);

        System.out.println("Regex:" + regex);
        System.out.println("Text:" + source);
        while (m.find()) {
            System.out.println("Matched  Text:" + m.group() + ", Start:" + m.start()
                    + ", " + "End:" + m.end());
        }
    }
    //为了使用正则表达式表达一个数字或更多的模式，我们可以使用量词。
    @Test
    public void test6(){
        // A group of 3 digits followed by 7 digits.
        String regex = "\\b(\\d{3})\\d{7}\\b";

        // Compile the regular expression
        Pattern p = Pattern.compile(regex);

        String source = "12345678, 12345, and 9876543210";

        // Get the Matcher object
        Matcher m = p.matcher(source);

        // Start matching and display the found area codes
        while (m.find()) {
            String phone = m.group();
            String areaCode = m.group(1);
            System.out.println("Phone: " + phone + ", Area  Code:  " + areaCode);
        }
    }
    //* 匹配零个或多个 d 。
    @Test
    public void test7(){
        String regex = "ad*";
        String input = "add";

        boolean isMatch = Pattern.matches(regex, input);
        System.out.println(isMatch);
    }
    @Test
    //Java 正则表达式边界
    public void test8(){
        // \\b to get \b inside the string literal.
        String regex = "\\bJava\\b";
        String replacementStr = "XML";
        String inputStr = "Java and Javascript";
        String newStr = inputStr.replaceAll(regex, replacementStr);

        System.out.println("Regular  Expression: " + regex);
        System.out.println("Input String: " + inputStr);
        System.out.println("Replacement String:  " + replacementStr);
        System.out.println("New String:  " + newStr);

    }
    @Test
    //Java正则表达式组
    public void test9(){
        String regex = "(\\d{3})(\\d{3})(\\d{4})";

        Pattern p = Pattern.compile(regex);
        String source = "1234567890, 12345,  and  9876543210";

        Matcher m = p.matcher(source);

        while (m.find()) {
            System.out.println("Phone: " + m.group() + ", Formatted Phone:  ("
                    + m.group(1) + ") " + m.group(2) + "-" + m.group(3));
        }
    }
    @Test
    public void test10(){
        String regex = "(\\d{3})(\\d{3})(\\d{4})";
        String replacementText = "($1) $2-$3";
        String source = "1234567890, 12345, and 9876543210";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(source);

        String formattedSource = m.replaceAll(replacementText);

        System.out.println("Text: " + source);
        System.out.println("Formatted Text: " + formattedSource);
    }
    //命名组
    @Test
    public void test11(){

        //正则表达式匹配10位数的电话号码
        String regex = "(?<areaCode>\\d{3})(?<prefix>\\d{3})(?<postPhoneNumber>\\d{4})";
        //以下代码显示如何使用命名组。
        //String  replacementText = "(${areaCode}) ${prefix}-${postPhoneNumber}";
        String  replacementText = "(${areaCode}) ${prefix}-$3";

    }
    @Test
    //以下代码显示如何匹配10位电话号码，并为每个成功匹配打印每个组的开始
    public void test12(){
        String regex = "(?<areaCode>\\d{3})(?<prefix>\\d{3})(?<postPhoneNumber>\\d{4})";
        String source = "1234567890, 12345, and 9876543210";
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(source);
        while (m.find()) {
            String matchedText = m.group();
            int start1 = m.start("areaCode");
            int start2 = m.start("prefix");
            int start3 = m.start("postPhoneNumber");
            System.out.println("Matched Text:" + matchedText);
            System.out.println("Area code start:" + start1);
            System.out.println("Prefix start:" + start2);
            System.out.println("Line Number start:" + start3);
        }

    }
    @Test
    public void test13(){

        //以下代码显示如何在正则表达式中使用组名称以及如何在替换文本中使用名称。
        String regex = "(?<areaCode>\\d{3})(?<prefix>\\d{3})(?<postPhoneNumber>\\d{4})";

        String replacementText = "(${areaCode}) ${prefix}-$3";
        String source = "1234567890 and 9876543210";
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(source);

        String formattedSource = m.replaceAll(replacementText);

        System.out.println("Text: " + source);
        System.out.println("Formatted Text: " + formattedSource);

    }
    //组边界
    @Test
    public void test14(){
        String regex = "(?<areaCode>\\d{3})(?<prefix>\\d{3})(?<postPhoneNumber>\\d{4})";
        String source = "1234567890, 12345, and 9876543210";
        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(source);
        while (m.find()) {
            String matchedText = m.group();
            int start1 = m.start("areaCode");
            int start2 = m.start("prefix");
            int start3 = m.start("postPhoneNumber");
            System.out.println("Matched Text:" + matchedText);
            System.out.println("Area code start:" + start1);
            System.out.println("Prefix start:" + start2);
            System.out.println("Line Number start:" + start3);
        }

    }
    //Java 正则表达式查找/替换
    @Test
    public void test15(){
        String regex = "\\d+";
        StringBuffer sb = new StringBuffer();
        String replacementText = "";
        String matchedText = "";

        String text = "We have 7 tutorials for Java, 2 tutorials for Javascript and 1 tutorial for Oracle.";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);

        while (m.find()) {
            matchedText = m.group();
            int num = Integer.parseInt(matchedText);
            if (num == 1) {
                replacementText = "only one";
            } else if (num < 5) {
                replacementText = "a few";
            } else {
                replacementText = "many";
            }
            m.appendReplacement(sb, replacementText);
        }

        m.appendTail(sb);
        System.out.println("Old  Text: " + text);
        System.out.println("New Text: " + sb.toString());
    }


}
