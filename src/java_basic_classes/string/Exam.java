package java_basic_classes.string;

import org.junit.Test;

/**
 * @Author: Rita
 */
public class Exam {

    public void change(String str, char ch[]) {
        //传进来的参数str并没在赋值给change里面的局部变量str
        str = "test ok";
        ch[0] = 'b';
    }
    @Test
    public void exam1() {
        String str = new String("hello");
        char[] ch = { 't', 'e', 's', 't' };
        change(str, ch);
        System.out.println(str);//hello
        System.out.println(ch);//best
    }
}
