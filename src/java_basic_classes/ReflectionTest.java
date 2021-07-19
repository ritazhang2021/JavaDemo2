package java_basic_classes;

import com.sun.tools.javac.Main;
import org.junit.Test;
import project.entity.Person;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: Rita
 */
public class ReflectionTest {
    //反射之前，对于Person的操作
    @Test
    public void test1() {

        //1.创建Person类的对象
        Person p1 = new Person("Tom", 12);

        //2.通过对象，调用其内部的属性、方法
        p1.setAge(10);
        System.out.println(p1.toString());

        p1.show();

        //在Person类外部，不可以通过Person类的对象调用其内部私有结构。
        //比如：name、showNation()以及私有的构造器
    }

    //反射之后，对于Person的操作
    @Test
    public void test2() throws Exception{
        Class clazz = Person.class;
        //1.通过反射，创建Person类的对象
        Constructor cons = clazz.getConstructor(String.class,int.class);
        Object obj = cons.newInstance("Tom", 12);
        Person p = (Person) obj;
        System.out.println(p.toString());
        //2.通过反射，调用对象指定的属性、方法
        //调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(p,10);
        System.out.println(p.toString());

        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        System.out.println("*******************************");

        //通过反射，可以调用Person类的私有结构的。比如：私有的构造器、方法、属性
        //调用私有的构造器
        Constructor cons1 = clazz.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person p1 = (Person) cons1.newInstance("Jerry");
        System.out.println(p1);

        //调用私有的属性
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1,"HanMeimei");
        System.out.println(p1);

        //调用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(p1,"中国");//相当于String nation = p1.showNation("中国")
        System.out.println(nation);


    }
    //疑问1：通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用那个？
    //建议：直接new的方式。
    //什么时候会使用：反射的方式。 反射的特征：动态性
    //疑问2：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
    //不矛盾。

    /*
    关于java.lang.Class类的理解
    1.类的加载过程：
    程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)。
    接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件
    加载到内存中。此过程就称为类的加载。加载到内存中的类，我们就称为运行时类，此
    运行时类，就作为Class的一个实例。

    2.换句话说，Class的实例就对应着一个运行时类。
    3.加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式
    来获取此运行时类。
     */
    //获取Class的实例的方式（前三种方式需要掌握）
    @Test
    public void test3() throws ClassNotFoundException {
        //方式一：调用运行时类的属性：.class
        Class clazz1 = Person.class;
        System.out.println(clazz1);
        //方式二：通过运行时类的对象,调用getClass()
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);

        //方式三：调用Class的静态方法：forName(String classPath)
        Class clazz3 = Class.forName("com.atguigu.java.Person");
//        clazz3 = Class.forName("java.lang.String");
        System.out.println(clazz3);

        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);

        //方式四：使用类的加载器：ClassLoader  (了解)
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.atguigu.java.Person");
        System.out.println(clazz4);

        System.out.println(clazz1 == clazz4);

    }


    //万事万物皆对象？对象.xxx,File,URL,反射,前端、数据库操作


    //Class实例可以是哪些结构的说明：
    @Test
    public void test4(){
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        // 只要数组的元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);

    }

    /*

        不需要掌握
     */
    @Test
    public void testField() throws Exception {
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();


        //获取指定的属性：要求运行时类中属性声明为public
        //通常不采用此方法
        Field id = clazz.getField("id");

        /*
        设置当前属性的值

        set():参数1：指明设置哪个对象的属性   参数2：将此属性值设置为多少
         */

        id.set(p,1001);

        /*
        获取当前属性的值
        get():参数1：获取哪个对象的当前属性值
         */
        int pId = (int) id.get(p);
        System.out.println(pId);


    }
    /*
    如何操作运行时类中的指定的属性 -- 需要掌握
     */
    @Test
    public void testField1() throws Exception {
        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        //1. getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");

        //2.保证当前属性是可访问的
        name.setAccessible(true);
        //3.获取、设置指定对象的此属性值
        name.set(p,"Tom");

        System.out.println(name.get(p));
    }

    /*
    如何操作运行时类中的指定的方法 -- 需要掌握
     */
    @Test
    public void testMethod() throws Exception {

        Class clazz = Person.class;

        //创建运行时类的对象
        Person p = (Person) clazz.newInstance();

        /*
        1.获取指定的某个方法
        getDeclaredMethod():参数1 ：指明获取的方法的名称  参数2：指明获取的方法的形参列表
         */
        Method show = clazz.getDeclaredMethod("show", String.class);
        //2.保证当前方法是可访问的
        show.setAccessible(true);

        /*
        3. 调用方法的invoke():参数1：方法的调用者  参数2：给方法形参赋值的实参
        invoke()的返回值即为对应类中调用的方法的返回值。
         */
        Object returnValue = show.invoke(p,"CHN"); //String nation = p.show("CHN");
        System.out.println(returnValue);

        System.out.println("*************如何调用静态方法*****************");

        // private static void showDesc()

        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        //如果调用的运行时类中的方法没有返回值，则此invoke()返回null
//        Object returnVal = showDesc.invoke(null);
        Object returnVal = showDesc.invoke(Person.class);
        System.out.println(returnVal);//null

    }

    /*
    如何调用运行时类中的指定的构造器
     */
    @Test
    public void testConstructor() throws Exception {
        Class clazz = Person.class;

        //private Person(String name)
        /*
        1.获取指定的构造器
        getDeclaredConstructor():参数：指明构造器的参数列表
         */

        Constructor constructor = clazz.getDeclaredConstructor(String.class);

        //2.保证此构造器是可访问的
        constructor.setAccessible(true);

        //3.调用此构造器创建运行时类的对象
        Person per = (Person) constructor.newInstance("Tom");
        System.out.println(per);

    }
    //我们可以使用Java反射来获取关于类的信息，例如作为其包名称，其访问修饰符等。
    //要获得简单的类名，请使用 Class 中的 getSimpleName()方法。
    //String simpleName = c.getSimpleName();
    //类的修饰符是关键字之前的关键字类在类声明中，如 abstract ， public 。
    //Class 中的 getModifiers()方法返回类的所有修饰符。
    //getModifiers()方法返回一个整数。我们必须调用 java.lang.reflect.Modifier.toString(int modifiers)以获得修饰符的文本形式。
    //要获取超类的名称，请使用 Class 中的 getSuperclass()方法。
    //如果对Object类调用getSuperclass()方法，它将返回null，因为它没有超类。
    //要获取类实现的所有接口的名称，请使用 getInterfaces()
    @Test
    public void test5() throws Exception {
        // Print the class declaration for the Class class
        String classDesciption = getClassDescription(MyClass.class);
        System.out.println(classDesciption);
    }
    public static String getClassDescription(Class c) {
        StringBuilder classDesc = new StringBuilder();
        int modifierBits = 0;
        String keyword = "";
        if (c.isInterface()) {
            modifierBits = c.getModifiers() & Modifier.interfaceModifiers();
            if (c.isAnnotation()) {
                keyword = "@interface";
            } else {
                keyword = "interface";
            }
        } else if (c.isEnum()) {
            modifierBits = c.getModifiers() & Modifier.classModifiers();
            keyword = "enum";
        }
        modifierBits = c.getModifiers() & Modifier.classModifiers();
        keyword = "class";

        String modifiers = Modifier.toString(modifierBits);
        classDesc.append(modifiers);
        classDesc.append(" " + keyword);
        String simpleName = c.getSimpleName();
        classDesc.append(" " + simpleName);

        String genericParms = getGenericTypeParams(c);
        classDesc.append(genericParms);

        Class superClass = c.getSuperclass();
        if (superClass != null) {
            String superClassSimpleName = superClass.getSimpleName();
            classDesc.append("  extends " + superClassSimpleName);
        }
        String interfaces = getClassInterfaces(c);
        if (interfaces != null) {
            classDesc.append("  implements " + interfaces);
        }
        return classDesc.toString();
    }

    public static String getClassInterfaces(Class c) {
        Class[] interfaces = c.getInterfaces();
        String interfacesList = null;
        if (interfaces.length > 0) {
            String[] interfaceNames = new String[interfaces.length];
            for (int i = 0; i < interfaces.length; i++) {
                interfaceNames[i] = interfaces[i].getSimpleName();
            }
            interfacesList = String.join(", ", interfaceNames);
        }
        return interfacesList;
    }

    public static String getGenericTypeParams(Class c) {
        StringBuilder sb = new StringBuilder();
        TypeVariable<?>[] typeParms = c.getTypeParameters();

        if (typeParms.length > 0) {
            String[] paramNames = new String[typeParms.length];
            for (int i = 0; i < typeParms.length; i++) {
                paramNames[i] = typeParms[i].getTypeName();
            }
            sb.append("<");
            String parmsList = String.join(",", paramNames);
            sb.append(parmsList);
            sb.append(">");
        }
        return sb.toString();
    }
    class MyClass<T> implements Cloneable, Serializable {
        private int id = -1;
        private String name = "Unknown";
        public MyClass(int id, String name) {
            this.id = id;
            this.name = name;
        }
        @Override
        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        @Override
        public String toString() {
            return "MyClass: id=" + this.id + ", name=" + this.name;
        }
    }
    //我们可以使用java.lang.reflect.Field类来获取关于类中的字段的信息。
    @Test
    public void test6() throws Exception {
        Class<MyClass2> c = MyClass2.class;

        // Print declared fields
        ArrayList<String> fieldsDesciption = getDeclaredFieldsList(c);

        System.out.println("Declared Fields for " + c.getName());
        for (String desc : fieldsDesciption) {
            System.out.println(desc);
        }
        fieldsDesciption = getFieldsList(c);

        System.out.println("\nAccessible Fields for " + c.getName());
        for (String desc : fieldsDesciption) {
            System.out.println(desc);
        }
    }
        public static ArrayList<String> getFieldsList (Class c){
            Field[] fields = c.getFields();
            ArrayList<String> fieldsList = getFieldsDesciption(fields);
            return fieldsList;
        }

        public static ArrayList<String> getDeclaredFieldsList (Class c){
            Field[] fields = c.getDeclaredFields();
            ArrayList<String> fieldsList = getFieldsDesciption(fields);
            return fieldsList;
        }

        public static ArrayList<String> getFieldsDesciption (Field[]fields){
            ArrayList<String> fieldList = new ArrayList<>();

            for (Field f : fields) {
                int mod = f.getModifiers() & Modifier.fieldModifiers();
                String modifiers = Modifier.toString(mod);

                Class<?> type = f.getType();
                String typeName = type.getSimpleName();

                String fieldName = f.getName();

                fieldList.add(modifiers + "  " + typeName + "  " + fieldName);
            }

            return fieldList;
        }

    class MySuperClass {
        public int super_id = -1;
        public String super_name = "Unknown";

    }

    class MyClass2 extends MySuperClass{
        public int id = -1;
        public String name = "Unknown";

    }
    //Java方法反射
    //类型参数
    @Test
    public void test7() throws Exception {
        Class<MyClass> cls = MyClass.class;
        for (Method m : cls.getMethods()) {
            System.out.println(m.getName());
            System.out.println(getModifiers(m));
            System.out.println(getParameters(m));
            System.out.println(getExceptionList(m));
        }
    }
    public static ArrayList<String> getParameters(Executable exec) {
        Parameter[] parms = exec.getParameters();
        ArrayList<String> parmList = new ArrayList<>();
        for (int i = 0; i < parms.length; i++) {

            int mod = parms[i].getModifiers() & Modifier.parameterModifiers();
            String modifiers = Modifier.toString(mod);
            String parmType = parms[i].getType().getSimpleName();
            String parmName = parms[i].getName();
            String temp = modifiers + "  " + parmType + "  " + parmName;
            if(temp.trim().length() == 0){
                continue;
            }
            parmList.add(temp.trim());
        }
        return parmList;
    }

    public static ArrayList<String> getExceptionList(Executable exec) {
        ArrayList<String> exceptionList = new ArrayList<>();
        for (Class<?> c : exec.getExceptionTypes()) {
            exceptionList.add(c.getSimpleName());
        }
        return exceptionList;
    }
    public static String getModifiers(Executable exec) {
        int mod = exec.getModifiers();
        if (exec instanceof Method) {
            mod = mod & Modifier.methodModifiers();
        } else if (exec instanceof Constructor) {
            mod = mod & Modifier.constructorModifiers();
        }
        return Modifier.toString(mod);
    }

    //反射方法
    //Method类中的getReturnType()方法返回包含有关返回类型信息的Class对象。
    @Test
    public void test8() throws Exception {
        Class<MyClass> c = MyClass.class;

        ArrayList<String> methodsDesciption = getDeclaredMethodsList(c);
        System.out.println("Declared Methods  for " + c.getName());
        for (String desc : methodsDesciption) {
            System.out.println(desc);
        }
        methodsDesciption = getMethodsList(c);
        System.out.println("\nMethods for  " + c.getName());
        for (String desc : methodsDesciption) {
            System.out.println(desc);
        }

    }

    public static ArrayList<String> getMethodsList(Class c) {
        Method[] methods = c.getMethods();
        ArrayList<String> methodsList = getMethodsDesciption(methods);
        return methodsList;
    }

    public static ArrayList<String> getDeclaredMethodsList(Class c) {
        Method[] methods = c.getDeclaredMethods();
        ArrayList<String> methodsList = getMethodsDesciption(methods);
        return methodsList;
    }

    public static ArrayList<String> getMethodsDesciption(Method[] methods) {
        ArrayList<String> methodList = new ArrayList<>();

        for (Method m : methods) {
            String modifiers = getModifiers(m);

            Class returnType = m.getReturnType();
            String returnTypeName = returnType.getSimpleName();

            String methodName = m.getName();

            String params = getParameters(m).toString();

            String throwsClause = getExceptionList(m).toString();

            methodList.add(modifiers + "  " + returnTypeName + "  " + methodName
                    + "(" + params + ") " + throwsClause);
        }

        return methodList;
    }
    //Java反射 - Java构造函数反射
    @Test
    public void test9() throws Exception {
        Class<MyClass> c = MyClass.class;

        System.out.println("Constructors for " + c.getName());
        Constructor[] constructors = c.getConstructors();
        ArrayList<String> constructDescList = getConstructorsDesciption(constructors);
        for (String desc : constructDescList) {
            System.out.println(desc);
        }
    }
    public static ArrayList<String> getConstructorsDesciption(
            Constructor[] constructors) {
        ArrayList<String> constructorList = new ArrayList<>();
        for (Constructor constructor : constructors) {
            String modifiers = getModifiers(constructor);

            String constructorName = constructor.getName();

            constructorList.add(modifiers + "  " + constructorName + "("
                    + getParameters(constructor) + ") " + getExceptionList(constructor));
        }
        return constructorList;
    }
    //Java反射对象创建
    //我们可以使用反射动态创建类的对象。通过调用其中一个构造函数。
    //然后我们可以访问对象的字段的值，设置它们的值，并调用它们的方法。
    //有两种方法来创建对象:
    //使用no-args构造函数
    //使用带参数的构造函数
    @Test
    public void test10() throws Exception {
        //无参数构造函数
        Class<MyClass> personClass = MyClass.class;
        try {
            MyClass p = personClass.newInstance();
            System.out.println(p);
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        //带参数的构造函数
        //您可以通过调用特定的构造函数使用反射创建对象。它涉及两个步骤。
        //调用newInstance来调用它
        //你可以得到这个构造函数的引用，如图所示:
        //Constructor<MyClass> cons  = myClass.getConstructor(int.class, String.class);
        //然后调用带有参数的 newInstance()方法来创建一个对象。
        Class<MyClass> myClass = MyClass.class;
        try {
            Constructor<MyClass> cons = myClass.getConstructor(int.class,
                    String.class);
            MyClass chris = cons.newInstance(1, "abc");
            System.out.println(chris);
        } catch (NoSuchMethodException | SecurityException | InstantiationException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
    }
    //调用方法
    //我们可以通过方法引用使用反射调用方法。
    //要调用方法，请调用方法引用的 invoke()方法。
    //它的第一个参数是它来自和的对象第二个参数是同一顺序中所有参数的varargs作为方法的声明。
    //在静态方法的情况下，我们只需要为第一个参数指定null。
    @Test
    public void test11() throws Exception {
        Class<MyClass> myClass = MyClass.class;
        try {
            MyClass p = myClass.newInstance();
            Method setName = myClass.getMethod("setName", String.class);
            setName.invoke(p, "abc");
        } catch (InstantiationException | IllegalAccessException
                | NoSuchMethodException | SecurityException | IllegalArgumentException
                | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
    }
    //Java反射字段访问
    //我们可以使用反射在两个步骤中获取或设置字段。
    //获取字段的引用。
    //要读取字段的值，请在字段上调用getXxx()方法，其中Xxx是字段的数据类型。
    //要设置字段的值，请调用相应的setXxx()方法。
    //以相同的方式访问静态和实例字段。
    @Test
    public void test12() throws Exception {
        Class<MyClass> ppClass = MyClass.class;
        try {
            MyClass p = ppClass.newInstance();
            Field name = ppClass.getField("name");
            String nameValue = (String) name.get(p);
            System.out.println("Current name is " + nameValue);
            name.set(p, "abc");
            nameValue = (String) name.get(p);
            System.out.println("New  name is " + nameValue);
        } catch (InstantiationException | IllegalAccessException
                | NoSuchFieldException | SecurityException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    //绕过辅助功能检查
    //使用反射访问类的非可访问字段，方法和构造函数从 AccessibleObject 类调用 setAccessible(boolean flag)方法。
    //我们需要使用true参数调用此方法，以使该字段，方法和构造函数可访问。
    @Test
    public void test13() throws Exception {
        Class<MyClass> my = MyClass.class;
        try {
            MyClass p = my.newInstance();
            Field nameField = my.getDeclaredField("name");
            nameField.setAccessible(true);
            String nameValue = (String) nameField.get(p);
            System.out.println("Current name is " + nameValue);
            nameField.set(p, "abc");
            nameValue = (String) nameField.get(p);
            System.out.println("New name is " + nameValue);
        } catch (InstantiationException | IllegalAccessException
                | NoSuchFieldException | SecurityException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    //Java反射 - Java数组反射
    //以下代码显示了如何动态创建数组并操作其元素。
    @Test
    public void test14() throws Exception {
        try {
            Object my = Array.newInstance(int.class, 2);

            int n1 = Array.getInt(my, 0);
            int n2 = Array.getInt(my, 1);
            System.out.println("n1 = " + n1 + ", n2=" + n2);

            Array.set(my, 0, 11);
            Array.set(my, 1, 12);

            n1 = Array.getInt(my, 0);
            n2 = Array.getInt(my, 1);
            System.out.println("n1 = " + n1 + ", n2=" + n2);
        } catch (NegativeArraySizeException | IllegalArgumentException
                | ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
    //获取数组的维度
    //Java支持array数组。
    //类中的 getComponentType()方法返回数组的元素类型的Class对象。
    //以下代码说明了如何获取数组的维度。
    @Test
    public void test15() throws Exception {
        int[][][] intArray = new int[1][2][3];

        System.out.println("int[][][] dimension is " + getArrayDimension(intArray));
    }

    public static int getArrayDimension(Object array) {
        int dimension = 0;
        Class c = array.getClass();
        if (!c.isArray()) {
            throw new IllegalArgumentException("Object is not  an  array");
        }
        while (c.isArray()) {
            dimension++;
            c = c.getComponentType();
        }
        return dimension;
    }
    //展开数组
    //Java数组是一个固定长度的数据结构。
    //要放大数组，我们可以创建一个更大尺寸的数组，并将旧数组元素复制到新数组元素。
    //以下代码显示如何使用反射展开数组。
    @Test
    public void test16() throws Exception {
        int[] ids = new int[2];
        System.out.println(ids.length);
        System.out.println(Arrays.toString(ids));

        ids = (int[]) expandBy(ids, 2);

        ids[2] = 3;
        System.out.println(ids.length);
        System.out.println(Arrays.toString(ids));
    }

    public static Object expandBy(Object oldArray, int increment) {
        Object newArray = null;
        int oldLength = Array.getLength(oldArray);
        int newLength = oldLength + increment;
        Class<?> c = oldArray.getClass();
        newArray = Array.newInstance(c.getComponentType(), newLength);
        System.arraycopy(oldArray, 0, newArray, 0, oldLength);
        return newArray;
    }



}
