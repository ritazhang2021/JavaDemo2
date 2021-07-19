package serialization;

import org.junit.Test;

import project.entity.Person;

import java.io.*;

/**
 * @Author: Rita
 * @Date:4/17/2021 3:13 PM
 */
public class Serialization implements  Serializable{

    @Test
    public void testversion1L() throws Exception {
        File file = new File("person.out");
        // 序列化
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(file));
        Person person = new Person("Haozi", 22, "上海");
        oout.writeObject(person);
        oout.close();
        // 反序列化
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        Object newPerson = oin.readObject();
        oin.close();
        System.out.println(newPerson);
    }
    @Test
    public void testversion1LWithExtraEmail() throws Exception {
        //可以用transient String email表示这个属性不需要序列化;
        File file = new File("person.out");
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream(file));
        Object newPerson = oin.readObject();
        oin.close();
        System.out.println(newPerson);
    }

    /**
     * ObjectOutputStream 类用来序列化一个对象，如下的SerializeDemo例子实例化了一个Employee5对象，并将该对象序列化到一个文件中。     *
     * 该程序执行后，就创建了一个名为employee.ser文件。该程序没有任何输出，但是你可以通过代码研读来理解程序的作用。     *
     * 注意： 当序列化一个对象到文件时， 按照Java的标准约定是给文件一个.ser扩展名。
     * */
    @Test
    public void test1(){
        Employee5 e = new Employee5();
        e.name = "Reyan Ali";
        e.address = "Phokka Kuan, Ambehta Peer";
        e.SSN = 11122333;
        e.number = 101;
        try
        {
            //如果文件不存在则会新建一个
            //这种方式会先将源文件先清空，然后重新写入
            //fos =new FileOutputStream("C:\\Users\\Administrator\\IdeaProjects\\xuexi\\src\\xiaobaobao\\test.txt");
            //采用这种方式会在文件末尾写入，不会清空原文件内容
            //fos = new FileOutputStream("C:\\Users\\Administrator\\IdeaProjects\\xuexi\\src\\xiaobaobao\\test.txt",true);

            FileOutputStream fileOut = new FileOutputStream("/employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /temp/employee.ser");
        }catch(IOException i)
        {
            i.printStackTrace();
        }

    }

    /**
     * 下面的DeserializeDemo程序实例了反序列化，/tmp/employee.ser存储了Employee5对象。
     *
     * */
    @Test
    public void test2(){
        Employee5 e = null;
        try
        {
            FileInputStream fileIn = new FileInputStream("/employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Employee5) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Employee5 class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("Deserialized Employee5...");
        System.out.println("Name: " + e.name);
        System.out.println("Address: " + e.address);
        System.out.println("SSN: " + e.SSN);
        System.out.println("Number: " + e.number);
    }
        /**
         * 这里要注意以下要点：         *
         * readObject() 方法中的try/catch代码块尝试捕获 ClassNotFoundException异常。对于JVM可以反序列化对象，
         * 它必须是能够找到字节码的类。如果JVM在反序列化对象的过程中找不到该类，则抛出一个 ClassNotFoundException异常。
         *
         * 注意，readObject()方法的返回值被转化成Employee5引用。         *
         * 当对象被序列化时，属性SSN的值为111222333，但是因为该属性是短暂的，该值没有被发送到输出流。
         * 所以反序列化后Employee5对象的SSN属性为0。
         * */

    


}

class Employee5 implements java.io.Serializable{
    /**
     请注意，一个类的对象要想序列化成功，必须满足两个条件：
     该类必须实现 java.io.Serializable 对象。
     该类的所有属性必须是可序列化的。如果有一个属性不是可序列化的，则该属性必须注明是短暂的。
     如果你想知道一个Java标准类是否是可序列化的，请查看该类的文档。检验一个类的实例是否能序列化十分简单，
     只需要查看该类有没有实现java.io.Serializable接口。
     * */

    public String name;
    public String address;
    public transient int SSN;
    public int number;
    public void mailCheck()
    {
        System.out.println("Mailing a check to " + name
                + " " + address);
    }

}
