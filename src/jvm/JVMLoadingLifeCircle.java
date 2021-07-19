package jvm;

import com.sun.jdi.Bootstrap;
import org.junit.Test;

import java.io.*;
import java.security.cert.Extension;

/**
 * @Author: Rita
 */
public class JVMLoadingLifeCircle {

    //Loading->Verification->Preparation->Resolution->Initialization->Unloading->Using
    /**
     其中类加载的过程包括了加载、验证、准备、解析、初始化五个阶段。在这五个阶段中，加载、验证、准备和初始化这四个阶段发生的顺序是确定的，
     而解析阶段则不一定，它在某些情况下可以在初始化阶段之后开始，这是为了支持Java语言的运行时绑定（也成为动态绑定或晚期绑定）
     * */
    //loading
    /**
     通过一个类的全限定名来获取其定义的二进制字节流。
     将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构。
     在Java堆中生成一个代表这个类的 java.lang.Class对象，作为对方法区中这些数据的访问入口。
     */
    //Verification
    /**
     验证是连接阶段的第一步，这一阶段的目的是为了确保Class文件的字节流中包含的信息符合当前虚拟机的要求，并且不会危害虚拟机自身的安全。
     验证阶段大致会完成4个阶段的检验动作：
     文件格式验证：验证字节流是否符合Class文件格式的规范；例如：是否以 0xCAFEBABE开头、主次版本号是否在当前虚拟机的处理范围之内、
     常量池中的常量是否有不被支持的类型。
     元数据验证：对字节码描述的信息进行语义分析（注意：对比javac编译阶段的语义分析），以保证其描述的信息符合Java语言规范的要求；
     例如：这个类是否有父类，除了 java.lang.Object之外。
     字节码验证：通过数据流和控制流分析，确定程序语义是合法的、符合逻辑的。
     符号引用验证：确保解析动作能正确执行。
     验证阶段是非常重要的，但不是必须的，它对程序运行期没有影响，如果所引用的类经过反复验证，
     那么可以考虑采用 -Xverifynone参数来关闭大部分的类验证措施，以缩短虚拟机类加载的时间。
     */
    //Preparation
    /**
     * 1、这时候进行内存分配的仅包括类变量（static），而不包括实例变量，实例变量会在对象实例化时随着对象一块分配在Java堆中。
     * 2、这里所设置的初始值通常情况下是数据类型默认的零值（如0、0L、null、false等），而不是被在Java代码中被显式地赋予的值。
     */
    //假设一个类变量的定义为：
    public static int value = 3;
    //那么变量value在准备阶段过后的初始值为0，而不是3，因为这时候尚未开始执行任何Java方法，
    // 而把value赋值为3的 public static指令是在程序编译后，存放于类构造器 <clinit>（）方法之中的，
    // 所以把value赋值为3的动作将在初始化阶段才会执行。
    //编译时Javac将会为value生成ConstantValue属性，在准备阶段虚拟机就会根据 ConstantValue的设置将value赋值为3。
    // 我们可以理解为static final常量在编译期就将其结果放入了调用它的类的常量池中

    //对基本数据类型来说，对于类变量（static）和全局变量，如果不显式地对其赋值而直接使用，则系统会为其赋予默认的零值，
    // 而对于局部变量来说，在使用前必须显式地为其赋值，否则编译时不通过。局部变量不可以是static
    public static int value2;
    public int value3;

    @Test
    public void testMethod1() {
        int value4;
        System.out.println(value2);//0
        System.out.println(value3);//0
    }

    public static void staticMethod() {
        int value5 = 0;
        System.out.println(value5);
        final int value6 = 0;
        System.out.println(value6);
    }

    public void normalMethod() {
        //static int value5;//static only for attributes and method. not for variable

        final int value8;
    }

    //对于同时被static和final修饰的常量，必须在声明的时候就为其显式地赋值，否则编译时不通过；
    static final int value7 = 0;
    // final属性在使用前必须为其显式地赋值，系统不会为其赋予默认零值。
    final int value9 = 0;
//    对于引用数据类型reference来说，如数组引用、对象引用等，如果没有对其进行显式地赋值而直接使用，系统都会为其赋予默认的零值，即null。
//
//    如果在数组初始化时没有对数组中的各元素赋值，那么其中的元素将根据对应的数据类型而被赋予默认的零值。
//
/**
 * 总结，被static修饰，attribute和veriable和不加以前是一样的，attribute有默认值，veriable必须初始化
 * 被final修饰的，无论在哪都要在使用前被赋值
 *
 * */

    //Resolution
    /**
     解析：把类中的符号引用转换为直接引用
     解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程，解析动作主要针对类或接口、字段、类方法、接口方法、方法类型、
     方法句柄和调用点限定符7类符号引用进行。
     * */

    //Initialization

    /**
     初始化，为类的静态变量赋予正确的初始值，JVM负责对类进行初始化，主要对类变量进行初始化。在Java中对类变量进行初始值设定有两种方式：
     ①声明类变量是指定初始值
     ②使用静态代码块为类变量指定初始值
     */
    static {
        //int value;
        int value = 0;
        System.out.println("初始化value值： " + value);//静态初始化块相当于方法里，必须初始化变量

    }

    @Test
    public void iniStaticBlock() {
        JVMLoadingLifeCircle jllc = new JVMLoadingLifeCircle();
    }

    /**
     * JVM初始化步骤
     * 1、假如这个类还没有被加载和连接，则程序先加载并连接该类
     * 2、假如该类的直接父类还没有被初始化，则先初始化其直接父类
     * 3、假如类中有初始化语句，则系统依次执行这些初始化语句
     * 类初始化时机：只有当对类的主动使用的时候才会导致类的初始化，类的主动使用包括以下六种：
     * 创建类的实例，也就是new的方式
     * 访问某个类或接口的静态变量，或者对该静态变量赋值
     * 调用类的静态方法
     * 反射（如 Class.forName(“com.shengsiyuan.Test”)）
     * 初始化某个类的子类，则其父类也会被初始化
     * Java虚拟机启动时被标明为启动类的类（ JavaTest），直接使用 java.exe命令来运行某个主类
     */
    @Test
    public void classLoaderTest() throws ClassNotFoundException {
        /**
         类加载有三种方式：
         1、命令行启动应用时候由JVM初始化加载
         2、通过Class.forName()方法动态加载
         3、通过ClassLoader.loadClass()方法动态加载
         * */
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);
        System.out.println(loader.getParent());
        System.out.println(loader.getParent().getParent());

        Bootstrap bootstrap = new Bootstrap();
        Extension extension;

        ClassLoader loader2 = JVMLoadingLifeCircle.class.getClassLoader();
        System.out.println(loader2);
        //使用ClassLoader.loadClass()来加载类，不会执行初始化块
        loader2.loadClass("classLoaderTest");
        //使用Class.forName()来加载类，默认会执行初始化块
        //Class.forName("classLoaderTest");
        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
        //Class.forName("classLoaderTest", false, loader);
        /**
         Class.forName()：将类的.class文件加载到jvm中之外，还会对类进行解释，执行类中的static块；
         ClassLoader.loadClass()：只干一件事情，就是将.class文件加载到jvm中，不会执行static中的内容,
         只有在newInstance才会去执行static块。
         Class.forName(name,initialize,loader)带参函数也可控制是否加载static块。
         并且只有调用了newInstance()方法采用调用构造函数，创建类的对象 。
         * */

        //Parental delegate model
        /**
         双亲委派模型的工作流程是：如果一个类加载器收到了类加载的请求，它首先不会自己去尝试加载这个类，
         而是把请求委托给父加载器去完成，依次向上，因此，所有的类加载请求最终都应该被传递到顶层的启动类加载器中，
         只有当父加载器在它的搜索范围中没有找到所需的类时，即无法完成该加载，子加载器才会尝试自己去加载该类。
         双亲委派机制:
         1、当 AppClassLoader加载一个class时，它首先不会自己去尝试加载这个类，而是把类加载请求委派给父类加载器ExtClassLoader去完成。
         2、当 ExtClassLoader加载一个class时，它首先也不会自己去尝试加载这个类，而是把类加载请求委派给BootStrapClassLoader```去完成。
         3、如果 BootStrapClassLoader加载失败（例如在 $JAVA_HOME/jre/lib里未查找到该class），会使用 ExtClassLoader来尝试加载；
         4、若ExtClassLoader也加载失败，则会使用 AppClassLoader来加载，如果 AppClassLoader也加载失败，
         则会报出异常 ClassNotFoundException。
         双亲委派模型意义：
         系统类防止内存中出现多份同样的字节码
         保证Java程序安全稳定运行
         * */


    }

    //Custom classloaders
    /**
     自定义类加载器的核心在于对字节码文件的获取，如果是加密的字节码则需要在该类中对文件进行解密。由于这里只是演示，
     我并未对class文件进行加密，因此没有解密的过程。这里有几点需要注意：
     1、这里传递的文件名需要是类的全限定性名称，即 com.paddx.test.classloading.Test格式的，
     因为 defineClass 方法是按这种格式进行处理的。
     2、最好不要重写loadClass方法，因为这样容易破坏双亲委托模式。
     3、这类Test 类本身可以被 AppClassLoader类加载，因此我们不能把 com/paddx/test/classloading/Test.class放在类路径下。
     否则，由于双亲委托机制的存在，会直接导致该类由 AppClassLoader加载，而不会通过我们自定义类加载器来加载。
     * */
    public class MyClassLoader extends ClassLoader {
        private String root;

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] classData = loadClassData(name);
            if (classData == null) {
                throw new ClassNotFoundException();
            } else {
                return defineClass(name, classData, 0, classData.length);
            }
        }

        private byte[] loadClassData(String className) {
            String fileName = root + File.separatorChar
                    + className.replace('.', File.separatorChar) + ".class";
            try {
                InputStream ins = new FileInputStream(fileName);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int length = 0;
                while ((length = ins.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                }
                return baos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public String getRoot() {
            return root;
        }
    }
}