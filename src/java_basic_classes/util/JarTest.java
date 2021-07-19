package java_basic_classes.util;

import org.junit.Test;

import java.io.*;
import java.util.Map;
import java.util.jar.*;
import java.util.zip.Deflater;

/**
 * @Author: Rita
 * Jar的命令祥见网页
 */
public class JarTest {
    //以下代码创建包含清单文件的JAR文件。
    @Test
    public void test1(){
        Manifest manifest = getManifest();
        String jarFileName = "jartest.jar";
        String[] entries = new String[2];
        entries[0] = "images/logo.bmp";
        entries[1] = "com/w3cschool/Test.class";

        createJAR(jarFileName, entries, manifest);
    }

    public static void createJAR(String jarFileName, String[] jarEntries, Manifest manifest) {

        try (JarOutputStream jos = new JarOutputStream(new BufferedOutputStream(
                new FileOutputStream(jarFileName)), manifest)) {
            jos.setLevel(Deflater.BEST_COMPRESSION);
            for (int i = 0; i < jarEntries.length; i++) {
                File entryFile = new File(jarEntries[i]);
                if (!entryFile.exists()) {
                    return;
                }
                JarEntry je = new JarEntry(jarEntries[i]);
                jos.putNextEntry(je);
                addEntryContent(jos, jarEntries[i]);
                jos.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addEntryContent(JarOutputStream jos, String entryFileName)
            throws IOException, FileNotFoundException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                entryFileName));
        byte[] buffer = new byte[1024];
        int count = -1;
        while ((count = bis.read(buffer)) != -1) {
            jos.write(buffer, 0, count);
        }

        bis.close();
    }

    public static Manifest getManifest() {
        //JAR API包括使用清单文件的类。 Manifest类的一个对象表示一个清单文件。在代码中创建一个Manifest对象
        Manifest manifest = new Manifest();

        /**
         我们可以从清单文件中读取条目并向其写入条目。
         要将一个条目添加到主要部分，使用Manifest类中的getMainAttributes()方法获取Attributes类的实例，
         并使用其put()方法继续向其添加名称/值对。
         以下代码将一些属性添加到清单对象的主要部分。已知的属性名称在Attributes.Name类中定义为常量。
         例如，常量Attributes.Name.MANIFEST_VERSION表示清单版本属性名称
         * */
        Attributes mainAttribs = manifest.getMainAttributes();
        mainAttribs.put(Attributes.Name.MANIFEST_VERSION, "1.0");
        mainAttribs.put(Attributes.Name.MAIN_CLASS, "utl.JarTest");
        mainAttribs.put(Attributes.Name.SEALED, "true");

        //将单个条目添加到清单文件比添加主条目稍微复杂一些。
        //以下代码显示如何向Manifest对象添加单个条目：

        Map<String, Attributes> attribsMap = manifest.getEntries();

        Attributes a1 = getAttribute("Sealed", "false");
        attribsMap.put("utl/JarTest/", a1);

        Attributes a2 = getAttribute("Content-Type", "image/bmp");
        attribsMap.put("images/logo.bmp", a2);

        return manifest;
    }

    public static Attributes getAttribute(String name, String value) {
        Attributes a = new Attributes();
        Attributes.Name attribName = new Attributes.Name(name);
        a.put(attribName, value);
        return a;
    }
    //要从JAR文件的清单文件读取条目，请使用JarInputStream的getManifest()类获取Manifest类的对象，如下所示：
    @Test
    public void test2() throws IOException {
        JarInputStream jis = new JarInputStream(new FileInputStream("jartest.jar"));
        Manifest manifest = jis.getManifest();

        if (manifest !=  null)  {
            Attributes  mainAttributes = manifest.getMainAttributes();
            String  mainClass = mainAttributes.getValue("Main-Class");
            Map<String, Attributes> entries  = manifest.getEntries();
        }
    }
}

