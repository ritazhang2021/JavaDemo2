package java_basic_classes;

import org.junit.Test;

import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: Rita
Class类的常用方法：
getName()
一个Class对象描述了一个特定类的属性，Class类中最常用的方法getName以 String 的形式返回此 Class 对象所表示的实体（类、接口、数组类、基本类型或 void）名称。
newInstance()
Class还有一个有用的方法可以为类创建一个实例，这个方法叫做newInstance()。例如：x.getClass.newInstance()，创建了一个同x一样类型的新实例。newInstance()方法调用默认构造器（无参数构造器）初始化新建对象。
getClassLoader()
返回该类的类加载器。
getComponentType()
返回表示数组组件类型的 Class。
getSuperclass()
返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的超类的 Class。
isArray()
判定此 Class 对象是否表示一个数组类。
 */
public class JavaClassTest {
    @Test
    public void test1(){
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = JavaClassTest.class.getClassLoader();
        System.out.println(classLoader);
        //调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);
        //调用扩展类加载器的getParent()：无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的。
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);

        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println(classLoader3);

    }
    /*
    Properties：用来读取配置文件。

     */
    @Test
    public void test2() throws Exception {

        Properties pros =  new Properties();
        //此时的文件默认在当前的module下。
        //读取配置文件的方式一：
//        FileInputStream fis = new FileInputStream("jdbc.properties");
//        FileInputStream fis = new FileInputStream("src\\jdbc1.properties");
//        pros.load(fis);

        //读取配置文件的方式二：使用ClassLoader
        //配置文件默认识别为：当前module的src下
        ClassLoader classLoader = JavaClassTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");
        pros.load(is);


        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user = " + user + ",password = " + password);

    }
    //获取Class对象的三种方式：
    @Test
    public void test3() throws Exception {
        Class<?> _class;
        // ***1*对象.getClass()
        String str = "";
        _class = str.getClass();
        System.out.println(_class + "-----对象名.getClass()");
        // ***2*类.class
        _class = String.class;
        System.out.println(_class + "-----类名.class");
        // ***3*Class.forName("")
        try {
            _class = Class.forName("java.lang.String");
            System.out.println(_class + "-----Class.forName(...)");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
