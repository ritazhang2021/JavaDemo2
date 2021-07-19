package jvm;

/**
 * @Author: Rita
 */
public class JVMLoading {
    /**
     类的加载指的是将类的.class文件中的二进制数据读入到内存中，将其放在运行时数据区的方法区内，
     然后在堆区创建一个 java.lang.Class对象，用来封装类在方法区内的数据结构。
     类加载器并不需要等到某个类被“首次主动使用”时再加载它，JVM规范允许类加载器在预料某个类将要被使用时就预先加载它，
     如果在预先加载的过程中遇到了.class文件缺失或存在错误，类加载器必须在程序首次主动使用该类时才报告错误（LinkageError错误）
     如果这个类一直没有被程序主动使用，那么类加载器就不会报告错误
     * */
    /**
     其中类加载的过程包括了加载、验证、准备、解析、初始化五个阶段。在这五个阶段中，加载、验证、准备和初始化这四个阶段发生的顺序是确定的，
     而解析阶段则不一定，它在某些情况下可以在初始化阶段之后开始，这是为了支持Java语言的运行时绑定（也成为动态绑定或晚期绑定）。
     另外注意这里的几个阶段是按顺序开始，而不是按顺序进行或完成，因为这些阶段通常都是互相交叉地混合进行的，
     通常在一个阶段执行的过程中调用或激活另一个阶段。
     * */
}
