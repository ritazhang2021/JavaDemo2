package java8;

import org.junit.Test;

import java.util.Optional;

/**
 @Author: Rita
 Optional类：为了在程序中避免出现空指针异常而创建的。
 常用的方法：ofNullable(T t)
 orElse(T t)
 */
public class OptionalTest {

    /*
    Optional.of(T t) : 创建一个 Optional 实例，t必须非空；
    Optional.empty() : 创建一个空的 Optional 实例
    Optional.ofNullable(T t)：t可以为null

     */
    @Test
    public void test1(){
        Car car = new Car();
//        car = null;
        //of(T t):保证t是非空的
        Optional<Car> optionalCar = Optional.of(car);

    }

    @Test
    public void test2(){
        Car car = new Car();//是个空对象，但不是null
//        car = null;

        //ofNullable(T t)：t可以为null
        Optional<Car> optionalCar = Optional.ofNullable(car);
        System.out.println(optionalCar);
        //orElse(T t1):如果当前的Optional内部封装的t是非空的，则返回内部的t.
        //如果内部的t是空的，则返回orElse()方法中的参数t1.
        Car car1 = optionalCar.orElse(new Car("奥迪"));
        System.out.println(car1);

    }

    @Test
    public void test3(){
        Person person = new Person();
        person = null;
        //String carName = getCarName(person);
        //String carName = getCarName1(person);
        //String carName = getCarName2(person);
        //System.out.println(carName);

        person = new Person(new Car("奔驰"));
        String carName = getCarName2(person);
        System.out.println(carName);

    }
    public String getCarName(Person person){
        return person.getCar().getName();
    }

    //优化以后的getCarName():
    public String getCarName1(Person person){
        if(person != null){
            Car car = person.getCar();
            if(car != null){
                return car.getName();
            }
        }
        return null;
    }

    //使用Optional类的getCarName():
    public String getCarName2(Person person){
        Optional<Person> personOptional = Optional.ofNullable(person);
        //此时的person1一定非空
        Person person1 = personOptional.orElse(new Person(new Car("宝马")));
        Car car = person1.getCar();

        Optional<Car> carOptional = Optional.ofNullable(car);
        //car1一定非空
        Car car1 = carOptional.orElse(new Car("toyota"));
        return car1.getName();
    }

}

class Person {
    private Car car;

    @Override
    public String toString() {
        return "Person{" +
                "car=" + car +
                '}';
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Person() {
    }

    public Person(Car car) {

        this.car = car;
    }
}
class Car {

    private String name;

    @Override
    public String toString() {
        return "Gar {" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car() {
    }

    public Car(String name) {
        this.name = name;
    }
}

