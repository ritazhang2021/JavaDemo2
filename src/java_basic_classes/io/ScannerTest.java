package java_basic_classes.io;

import org.junit.Test;

import java.util.Scanner;

/**
 * @Author: Rita
 * 要从标准输入读取数字，我们必须将其读取为字符串并将其解析为数字。 *
 * java.util包中的Scanner类根据模式读取并解析基本类型和字符串中的文本。 *
 * 文本源可以是InputStream，文件，String对象或可读对象。 *
 * 我们可以使用Scanner对象从标准输入System.in中读取原始类型值。 *
 * 以下代码说明了如何使用Scanner类构建一个简单的计算器来执行加，减，乘和除。
 */
public class ScannerTest {
    @Test
    public void test1(){
        System.out.println("type something like: 1+3");
        Scanner scanner = new Scanner(System.in);
        double n1 = Double.NaN;
        double n2 = Double.NaN;
        String operation = null;

        try {
            n1 = scanner.nextDouble();
            operation = scanner.next();
            n2 = scanner.nextDouble();
            double result = calculate(n1, n2, operation);
            System.out.printf("%s %s  %s  = %.2f%n", n1, operation, n2, result);
        }

        catch (Exception e) {
            System.out.println("An invalid expression.");
        }
    }

    public static double calculate(double op1, double op2, String operation) {
        switch (operation) {
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                return op1 / op2;
        }

        return Double.NaN;
    }
}
