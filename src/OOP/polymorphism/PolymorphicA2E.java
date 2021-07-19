package OOP.polymorphism;

/**
 * @Author: Rita
 */
public class PolymorphicA2E {
    public static class A {
        public void m() {
            System.out.println("This is A method");
        }
        public void f() {
            System.out.println("This is A method");
        }

    }
    public static class B extends A{
        public void n() {
            System.out.println("This is B method");
        }

    }
    public static class C extends A{
        @Override
        public void m() {
            System.out.println("This is C method");
        }

    }
    public static class D extends C{
        @Override
        public void m() {
            System.out.println("This is D method");
        }
    }
    public static class E extends C {
        public void n() {
            System.out.println("This is B method");
        }

    }
    public static void main(String [] args) {
        A a = new E();
        a.m();
        E e = new E();
        e.m();
        e.n();
        D d = new D();
        d.f();
    }
}
