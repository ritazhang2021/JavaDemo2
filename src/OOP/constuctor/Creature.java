package OOP.constuctor;

/**
 @Author: Rita
 */
public class Creature {
    public Creature() {
        System.out.println("Creature Constructor with no arguments");
    }

    class Animal extends Creature {
        public Animal(String name) {
            System.out.println("Animal Constructor with one parameter, the name of the animal is " + name);
        }

        public Animal(String name, int age) {
            this(name);
            System.out.println("Animal takes a two-argument constructor whose age is " + age);
        }
    }
    class Mammal extends Animal {
        public Mammal() {
            super("Pig", 3);
            System.out.println("Pig Constructor with one parameter");
        }

    }

    public static void main(String[] args) {

        Creature c = new Creature();
        System.out.println("************************************************");
        c.new Animal("dog");
        System.out.println("************************************************");
        c.new Animal("dog").new Mammal();

    }
}

