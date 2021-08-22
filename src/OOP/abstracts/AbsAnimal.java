package OOP.abstracts;

/**
 * @Author: Rita
 */
 abstract class AbsAnimal {
    public void normalMethod(){
        System.out.println("normalMethod");
    }
    private void privateMethod(){
        System.out.println("privateMethod");
    }

    public void animalSoundNormal() {
        System.out.println("The animalSoundNormal makes a sound");
    }
    //abstract method can not have body, the body must be provided by the subclass:
    abstract void animalSoundAbstract();
}
//Inheritance, Polymorphism

class Tiger extends AbsAnimal {
    @Override
    void animalSoundAbstract() {
        System.out.println("The tiger makes a sound");
    }
}

class Dog extends AbsAnimal {
    @Override
    void animalSoundAbstract() {
        System.out.println("The dog makes a sound");
    }
}
