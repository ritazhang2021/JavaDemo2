package java_basic_classes.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: Rita
 * 1. jdk 7.0 时，引入了 Path、Paths、Files三个类。
 * 2.此三个类声明在：java.nio.file包下。
 * 3.Path可以看做是java.io.File类的升级版本。也可以表示文件或文件目录，与平台无关
 * <p>
 * 4.如何实例化Path:使用Paths.
 * static Path get(String first, String … more) : 用于将多个字符串串连成路径
 * static Path get(URI uri): 返回指定uri对应的Path路径
 *
 * Java 实例 - 递归创建目录
 * Java 实例 - 删除目录
 * Java 实例 - 判断目录是否为空
 * Java 实例 - 判断文件是否隐藏
 * Java 实例 - 获取目录大小
 * Java 实例 - 在指定目录中查找文件
 * Java 实例 - 获取文件的上级目录
 * Java 实例 - 获取目录最后修改时间
 * Java 实例 - 打印目录结构
 * Java 实例 - 遍历指定目录下的所有目录
 * Java 实例 - 遍历指定目录下的所有文件
 * Java 实例 - 在指定目录中查找文件
 * Java 实例 - 遍历系统根目录
 * Java 实例 - 查看当前工作目录
 * Java 实例 - 遍历目录
 */
public class PathTest {
    //如何使用Paths实例化Path
    @Test
    public void test1() {
        Path path1 = Paths.get("d:\\nio\\hello.txt");//new File(String filepath)

        Path path2 = Paths.get("d:\\", "nio\\hello.txt");//new File(String parent,String filename);

        System.out.println(path1);
        System.out.println(path2);

        Path path3 = Paths.get("d:\\", "nio");
        System.out.println(path3);
    }

    //Path中的常用方法
    @Test
    public void test2() {
        Path path1 = Paths.get("d:\\", "nio\\nio1\\nio2\\hello.txt");
        Path path2 = Paths.get("hello.txt");

//		String toString() ： 返回调用 Path 对象的字符串表示形式
        System.out.println(path1);

//		boolean startsWith(String path) : 判断是否以 path 路径开始
        System.out.println(path1.startsWith("d:\\nio"));
//		boolean endsWith(String path) : 判断是否以 path 路径结束
        System.out.println(path1.endsWith("hello.txt"));
//		boolean isAbsolute() : 判断是否是绝对路径
        System.out.println(path1.isAbsolute() + "~");
        System.out.println(path2.isAbsolute() + "~");
//		Path getParent() ：返回Path对象包含整个路径，不包含 Path 对象指定的文件路径
        System.out.println(path1.getParent());
        System.out.println(path2.getParent());
//		Path getRoot() ：返回调用 Path 对象的根路径
        System.out.println(path1.getRoot());
        System.out.println(path2.getRoot());
//		Path getFileName() : 返回与调用 Path 对象关联的文件名
        System.out.println(path1.getFileName() + "~");
        System.out.println(path2.getFileName() + "~");
//		int getNameCount() : 返回Path 根目录后面元素的数量
//		Path getName(int idx) : 返回指定索引位置 idx 的路径名称
        for (int i = 0; i < path1.getNameCount(); i++) {
            System.out.println(path1.getName(i) + "*****");
        }

//		Path toAbsolutePath() : 作为绝对路径返回调用 Path 对象
        System.out.println(path1.toAbsolutePath());
        System.out.println(path2.toAbsolutePath());
//		Path resolve(Path p) :合并两个路径，返回合并后的路径对应的Path对象
        Path path3 = Paths.get("d:\\", "nio");
        Path path4 = Paths.get("nioo\\hi.txt");
        path3 = path3.resolve(path4);
        System.out.println(path3);

//		File toFile(): 将Path转化为File类的对象
        File file = path1.toFile();//Path--->File的转换

        Path newPath = file.toPath();//File--->Path的转换

    }
    //以下代码显示了如何从Path对象获取实际路径:
    @Test
    public void test3() throws IOException {
        Path p2 = Paths.get("test2.txt");
        // Follow link for p2, if it is a symbolic link
        Path p2RealPath = p2.toRealPath();
        System.out.println("p2RealPath:" + p2RealPath);
        Path p3 = Paths.get("test3.txt");
        // Do not follow link for p3, if it is a symbolic link
        Path p3RealPath = p3.toRealPath(LinkOption.NOFOLLOW_LINKS);
        System.out.println("p3RealPath:" + p3RealPath);
    }
    //使用Path的toUri()方法来获取其URI表示。
    //以下代码显示了如何获取路径的URI形式
    @Test
    public void test4() throws IOException {
        Path p2 = Paths.get("test2.txt");
        java.net.URI p2UriPath = p2.toUri();
        System.out.println("Absolute Path: " + p2.toAbsolutePath());
        System.out.println("URI Path: " + p2UriPath);

    }
    //以下代码显示如何比较Windows路径:
    @Test
    public void test5() throws IOException {
        /*equals()方法在不解析实际文件引用的情况下以文本方式比较两个路径，并且不会测试文件系统中是否存在路径。
        Path接口实现java.lang.Comparable接口。我们可以使用它的compareTo()方法来与另一个Path对象进行文本比较。
        compareTo()方法返回一个int值。

        0 - 当两条路径相等时
        小于0 - 路径小于指定的路径
        大于0 - 路径大于指定的路径*/

        Path  p1  = Paths.get("C:\\Java_Dev\\test1.txt");
        Path  p2  = Paths.get("C:\\Java_Dev\\LUCI1.TXT");
        Path  p3  = Paths.get("C:\\Java_Dev\\..\\Java_Dev\\test1.txt");
        boolean b1  = p1.equals(p2);
        System.out.println(b1);
        boolean b2  = p1.equals(p3);
        System.out.println(b2);
    }
    //以下代码显示如何使用compareTo()方法:
    @Test
    public void test6() throws IOException {
        Path  p1  = Paths.get("C:\\Java_Dev\\test1.txt");
        Path  p2  = Paths.get("C:\\Java_Dev\\Test1.txt");
        Path  p3  = Paths.get("C:\\Java_Dev\\..\\Java_Dev\\test1.txt");
        int v1  = p1.compareTo(p2);
        System.out.println(v1);
        int v2  = p1.compareTo(p3);
        System.out.println(v2);
    }
    //以下代码显示了如何在路径中使用endsWith()和startsWith()方法。
    @Test
    public void test7() throws IOException {
        Path p1 = Paths.get("C:\\Java_Dev\\test1.txt");
        Path p2 = Paths.get("test1.txt");
        Path p3 = Paths.get("Java_Dev\\test1.txt");
        Path p4 = Paths.get(".txt");

        // Using endsWith()
        boolean b1 = p1.endsWith(p2);
        System.out.println(b1);
        boolean b2 = p1.endsWith(p3);
        boolean b3 = p1.endsWith(p4);

        // Using startsWith()
        Path p5 = Paths.get("C:\\");
        Path p6 = Paths.get("C:\\Java_Dev");
        Path p7 = Paths.get("C:\\Java_Dev");

        boolean b4 = p1.startsWith(p5);
        boolean b5 = p1.startsWith(p6);
        boolean b6 = p1.startsWith(p7);
    }
    //以下代码演示了isSameFile()方法的工作原理。它检查如果两个路径将找到相同的文件。
    @Test
    public void test8() throws IOException {
        // Assume that C:\Java_Dev\test1.txt file exists
        Path p1 = Paths.get("C:\\Java_Dev\\test1.txt");
        Path p2 = Paths.get("C:\\Java_Dev\\..\\Java_Dev\\test1.txt");

        // Assume that C:\abc.txt file does not exist
        Path p3 = Paths.get("C:\\abc.txt");
        Path p4 = Paths.get("C:\\abc.txt");

        try {
            boolean isSame = Files.isSameFile(p1, p2);
            System.out.println("p1 and  p2  are   the   same:  " + isSame);

            isSame = Files.isSameFile(p3, p4);
            System.out.println("p3 and  p4  are   the   same:  " + isSame);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    //以下代码显示了在Windows上标准化路径的一些示例。
    @Test
    public void test9() throws IOException {
        Path p1 = Paths.get("C:\\Java_Dev\\..\\\\Java_Dev\\test1.txt");
        Path p1n = p1.normalize();
        System.out.println(p1 + "  normalized to " + p1n);

        Path p2 = Paths.get("C:\\Java_Dev\\test1.txt");
        Path p2n = p2.normalize();
        System.out.println(p2 + "  normalized to " + p2n);

        Path p3 = Paths.get("\\..\\.\\test.txt");
        Path p3n = p3.normalize();
        System.out.println(p3 + "  normalized to " + p3n);
    }
    //以下代码具有一些解析路径的示例。
    @Test
    public void test10() throws IOException {
        Path p1 = Paths.get("C:\\Java_Dev");
        Path p2 = Paths.get("test1.txt");
        System.out.println(p1.resolve(p2));

        Path p3 = Paths.get("C:\\test.txt");
        System.out.println(p1.resolve(p3));

        Path p4 = Paths.get("");
        System.out.println(p1.resolve(p4));

        Path p5 = Paths.get("Java_Dev\\Test");
        Path p6 = Paths.get("test4.txt");
        System.out.println(p5.resolve(p6));
    }
    //以下代码有一些获取相对路径的示例。
    @Test
    public void test11() throws IOException {
        Path p1 = Paths.get("Java_Dev");
        Path p2 = Paths.get("Java_Dev", "recent", "Test");
        System.out.println(p1.relativize(p2));
        System.out.println(p2.relativize(p1));

        Path p3 = Paths.get("Abc");
        Path p4 = Paths.get("Def");
        System.out.println(p3.relativize(p4));
        System.out.println(p4.relativize(p3));

    }

}
