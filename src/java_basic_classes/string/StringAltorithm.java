package java_basic_classes.string;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: Rita
 */
public class StringAltorithm {

    /*
    反转字符串
    方式一：转换为char[]
     */
    public String reverse1(String str, int startIndex, int endIndex) {

        if (str != null) {
            char[] arr = str.toCharArray();
            for (int x = startIndex, y = endIndex; x < y; x++, y--) {
                char temp = arr[x];
                arr[x] = arr[y];
                arr[y] = temp;
            }
            return new String(arr);
        }
        return null;
    }

    //方式三：使用StringBuffer/StringBuilder替换String
    public String reverse3(String str, int startIndex, int endIndex) {
        if (str != null) {
            StringBuilder builder = new StringBuilder(str.length());

            //第1部分
            builder.append(str.substring(0, startIndex));
            //第2部分
            for (int i = endIndex; i >= startIndex; i--) {

                builder.append(str.charAt(i));
            }
            //第3部分
            builder.append(str.substring(endIndex + 1));

            return builder.toString();
        }
        return null;

    }

    @Test
    public void testReverse() {
        String str = "abcdefg";
        String reverse = reverse1(str, 0, 6);
        System.out.println(reverse);
    }

     /*
    获取一个字符串在另一个字符串中出现的次数。
      比如：获取“ab”在 “abkkcadkabkebfkaabkskab” 中出现的次数
     */

    public int getCountSubString(String mainStr, String subStr) {
        int mainLength = mainStr.length();
        int subLength = subStr.length();
        int count = 0;
        int index = 0;
        if (mainLength >= subLength) {
            //方式一：
//            while((index = mainStr.indexOf(subStr)) != -1){
//                count++;
//                mainStr = mainStr.substring(index + subStr.length());
//            }
            //方式二：对方式一的改进
            while ((index = mainStr.indexOf(subStr, index)) != -1) {
                count++;
                index += subLength;
            }

            return count;
        } else {
            return 0;
        }
    }

    @Test
    public void testGetCount() {
        String mainStr = "abkkcadkabkebfkaabkskab";
        String subStr = "ab";
        int count = getCountSubString(mainStr, subStr);
        System.out.println(count);
    }

    /*
  获取两个字符串中最大相同子串
 提示：将短的那个串进行长度依次递减的子串与较长的串比较。
   */
    //前提：两个字符串中只有一个最大相同子串
    public String getMaxSameString(String str1, String str2) {
        if (str1 != null && str2 != null) {
            String maxStr = (str1.length() >= str2.length()) ? str1 : str2;
            String minStr = (str1.length() < str2.length()) ? str1 : str2;
            int length = minStr.length();

            for (int i = 0; i < length; i++) {
                for (int x = 0, y = length - i; y <= length; x++, y++) {
                    String subStr = minStr.substring(x, y);
                    if (maxStr.contains(subStr)) {
                        return subStr;
                    }
                }
            }

        }
        return null;
    }

    // 如果存在多个长度相同的最大相同子串
    // 此时先返回String[]，后面可以用集合中的ArrayList替换，较方便
    public String[] getMaxSameString1(String str1, String str2) {
        if (str1 != null && str2 != null) {
            StringBuffer sBuffer = new StringBuffer();
            String maxString = (str1.length() > str2.length()) ? str1 : str2;
            String minString = (str1.length() > str2.length()) ? str2 : str1;

            int len = minString.length();
            for (int i = 0; i < len; i++) {
                for (int x = 0, y = len - i; y <= len; x++, y++) {
                    String subString = minString.substring(x, y);
                    if (maxString.contains(subString)) {
                        sBuffer.append(subString + ",");
                    }
                }
//                System.out.println(sBuffer);
                if (sBuffer.length() != 0) {
                    break;
                }
            }
            String[] split = sBuffer.toString().replaceAll(",$", "").split("\\,");
            return split;
        }
        return null;
    }

    @Test
    public void testGetMaxSameString() {
        String str1 = "abcdefghsfsafsfsdfdsfafsafsfigk";
        String str2 = "fsafsfsdfdsfafsafs";
        String[] maxSameStrings = getMaxSameString1(str1, str2);
        System.out.println(Arrays.toString(maxSameStrings));

    }

    @Test
    public void sortSentence() {
       int a = 42;
        int b = 42;
        System.out.println(a == b);

    }
  /*  @Test
    public void test(){
    }
    final class Parent{
        protected int age;
        @Override
        //object中是public这里只能比它大，不能比它小
        void finalize() throws Throwable{
            System.out.println();
        }

    }
    class Child extends Parent{
        @Override
        public void finalize() throws Throwable{
            System.out.println();
        }
        int compare(Parent parent) {
            try{
                if(age > parent.age){
                    throw new IllegalStateException();
                }
                return age = parent.age;
            }finally {
                System.out.println();
            }
        }
        final int gender(){
            return 0;
        }
    }
    class Son extends Child{
        @Override
        final int gender(){
            return -1;
        }
    }
    class Daughter extends Child{
        @Override
        int gender(){
            return 1;
        }
    }
*/
}