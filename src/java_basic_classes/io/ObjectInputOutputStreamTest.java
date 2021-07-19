package java_basic_classes.io;

import org.junit.Test;
import project.entity.Person;
import project.project3_bank.Account_Synchronized;

import java.io.*;

/**
 * @Author: Rita
 * 对象流的使用
 * 1.ObjectInputStream 和 ObjectOutputStream
 * 2.作用：用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
 *
 * 3.要想一个java对象是可序列化的，需要满足相应的要求。见Person.java
 *
 * 4.序列化机制：
 * 对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种
 * 二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。
 * 当其它程序获取了这种二进制流，就可以恢复成原来的Java对象。

 */
public class ObjectInputOutputStreamTest {


    /*
    序列化过程：将内存中的java对象保存到磁盘中或通过网络传输出去
    使用ObjectOutputStream实现
     */
    @Test
    public void testObjectOutputStream(){
        ObjectOutputStream oos = null;

        try {
            //1.
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            //2.
            oos.writeObject(new String("我爱北京天安门"));
            oos.flush();//刷新操作

            oos.writeObject(new Person("王铭",23));
            oos.flush();

            oos.writeObject(new Person("张学良",23,new Account_Synchronized(5000)));
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null){
                //3.
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    /*
    反序列化：将磁盘文件中的对象还原为内存中的一个java对象
    使用ObjectInputStream来实现
     */
    @Test
    public void testObjectInputStream(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            Object obj = ois.readObject();
            String str = (String) obj;

            Person p = (Person) ois.readObject();
            Person p1 = (Person) ois.readObject();

            System.out.println(str);
            System.out.println(p);
            System.out.println(p1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    //以下代码显示如何序列化实现可序列化接口的Person类。
    @Test
    public void test1(){
        Person p1 = new Person("John", "Male", 1.7);
        Person p2 = new Person("Wally", "Male", 1.7);
        Person p3 = new Person("Katrina", "Female", 1.4);

        //以下代码创建ObjectOutputStream类的对象，并将对象保存到person.ser文件。
        File fileObject = new File("person.ser");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                fileObject))) {
            //使用ObjectOutputStream类的writeObject()方法通过将对象引用作为参数传递来序列化对象
            oos.writeObject(p1);
            oos.writeObject(p2);
            oos.writeObject(p3);

            // Display the serialized objects on the standard output
            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //以下代码显示如何从文件读取对象。
    @Test
    public void test2(){
        File fileObject = new File("person.ser");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
                fileObject))) {

            Person p1 = (Person) ois.readObject();
            Person p2 = (Person) ois.readObject();
            Person p3 = (Person) ois.readObject();

            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
