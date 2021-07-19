package project.entity;

import project.project3_bank.Account_Synchronized;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Rita
 */
public class Person implements Serializable, Comparable {
    /**
     将serialVersionUID改为2， 新加email, 再将之前序列化的Person返序列化回来会报错
     * */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Integer age;
    private transient String address;
    private String email;
    private Date birthDate;
    private Account_Synchronized account;

    public Person() {
    }
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Person(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
    public Person(String name, Integer age, String address,String email) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.email = email;
    }
    public Person(String name, Integer age,Account_Synchronized account) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.account = account;
    }
    public Person(String name, int age, Date d) {
        this.name = name;
        this.age = age;
        this.birthDate = d;
    }

    public Person(String name, int age) {
        this(name, age, null);
    }

    public Person(String name, Date d) {
        this(name, 30, d);
    }

    public Person(String name) {
        this(name, 30);
    }

    public Person(String john, String male, double v) {
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void show(){
        System.out.println("you look so good[");
    }

    private String showNation(String nation){
        System.out.println("我的国籍是：" + nation);
        return nation;
    }


    @Override
    public int compareTo(Object o) {
        if(o instanceof Person) {
            Person p = (Person) o;
            return this.getAge().compareTo(p.getAge());
        }
        throw new RuntimeException("the type doesn't match");
    }
}
