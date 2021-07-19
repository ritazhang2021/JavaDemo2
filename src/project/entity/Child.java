package project.entity;

/**
 * @Author: Rita
 */
public class Child extends Father {
    public void generalMethod(){
        System.out.println("private method in Child");
    }
    public static void staticMethod(){
        System.out.println("static method in Child");
    }
}
