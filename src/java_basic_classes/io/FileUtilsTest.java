package java_basic_classes.io;

import java.io.File;

/**
 * @Author: Rita
 */
public class FileUtilsTest {
    public static void main(String[] args) {
        File srcFile = new File("day10\\爱情与友情.jpg");
        File destFile = new File("day10\\爱情与友情2.jpg");

       /* try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
