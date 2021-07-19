package OOP.override;

import project.entity.Child;
import project.entity.Father;

/**
 * @Author: Rita
 * @Date:4/26/2021 9:03 PM
 */
/**
 Rules for Method Overriding
 1.	The argument list must be exactly the same as the argument list for the overridden method.
 2.	The return type may not be the same as the return type of the overridden method, but it must be a subclass class of the return value of the parent class (Java 5 and earlier may have the same return type, Java 7 and later may have a different return type).
 3.	The access permissions cannot be lower than that of the overridden method in the parent class.For example, if a method of a parent class is declared public, overriding that method in a child class cannot be declared protected.
 4.	A member method of a parent class can only be overridden by its child class.
 5.	Methods declared final cannot be overridden.
 6.	Methods declared static cannot be overridden, but can be declared again.
 7.	The overridden method cannot throw a more extensive exception than the one declared by the overridden method
 8.	Constructors cannot be overridden.
 9.	If you cannot inherit from a class, you cannot override the methods of that class.
 */
public class OverrideMethod {
    public static void main(String[] args){
        Father father = new Child();
        //father.generalMethod(); private,so Child doesn't need to override
        //father.staticMethod(); wrong, staticMethod() belong to Father class
        //Father.staticMethod();// wrong, deafault, not public

        Child child = new Child();
        child.generalMethod();//public
        child.methodNotInChild();
        //child.staticMethod();wrong,staticMethod() belong to Child class
        Child.staticMethod();//public

    }
}
