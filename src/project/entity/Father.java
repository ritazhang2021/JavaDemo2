package project.entity;


/**
 * @Author: Rita
 */
public class Father {
    private void generalMethod(){
        System.out.println("private method in father");
    }
    static void staticMethod(){
        System.out.println("static method in father");
    }
    public void methodNotInChild(){
        System.out.println("the method in father not in Child");
    }

}
