package java_basic_classes.io;

import org.junit.Test;

import java.io.*;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: Rita
 * Java 实例 - 文件写入
 * Java 实例 - 读取文件内容
 * Java 实例 - 删除文件
 * Java 实例 - 将文件内容复制到另一个文件
 * Java 实例 - 向文件中追加数据
 * Java 实例 - 创建临时文件
 * Java 实例 - 修改文件最后的修改日期
 * Java 实例 - 获取文件大小
 * Java 实例 - 文件重命名
 * Java 实例 - 设置文件只读
 * Java 实例 - 检测文件是否存在
 * Java 实例 - 在指定目录中创建文件
 * Java 实例 - 获取文件修改时间
 * Java 实例 - 创建文件
 * Java 实例 - 文件路径比较
 */
public class FileTest {
    @Test
    public void test1(){
        //1.造文件
    try {

        //构造器1
        File file1 = new File("src\\11.txt");//相对于当前module
        /**
         * 该createNewFile()方法创建一个新的空文件，如果有指定名称的文件不存在。         *
         * 如果文件已成功创建，则返回true;否则，返回false。如果发生I/O错误，该方法将抛出IOException。         *
         * 我们还可以在默认的临时文件目录或目录中创建一个临时文件。要在默认临时目录中创建临时文件，
         * 请使用File类的createTempFile()静态方法，该方法接受前缀和后缀以生成临时文件名。
         * File  tempFile = File.createTempFile("abc", ".txt");*/


        file1.createNewFile();
        File file2 =  new File("C:\\Users\\J&C\\Desktop\\22.txt");
        file2.createNewFile();

        File  tempFile = File.createTempFile("abc", ".txt");

        System.out.println(file1);
        System.out.println(file2);
        System.out.println(tempFile);

        //构造器2：
        /*我们可以使用mkdir()或mkdirs()方法创建一个新目录。仅当路径名中指定的父目录已存在时，mkdir()方法才创建目录。*/
        File file3 = new File("C:\\Users\\J&C\\Desktop\\FolderForTesting","Java");
        file3.createNewFile();
        System.out.println(file3);

        //构造器3：
        File file4 = new File(file3,"33.txt");
        System.out.println(file4);

        //1.造文件
        File destFile = new File("C:\\Users\\J&C\\Desktop\\55.txt");
        if (destFile.createNewFile()) {
            FileWriter writer = new FileWriter("6.txt");
            writer.write("hello world!");
            writer.close();
            System.out.println("Successfully wrote to the file.");
            System.out.println("File created: " + destFile.getName());
        } else {
            System.out.println("File already exists.");
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
}

    /*
canRead()	Boolean	Tests whether the file is readable or not
canWrite()	Boolean	Tests whether the file is writable or not
createNewFile()	Boolean	Creates an empty file
delete()	Boolean	Deletes a file
exists()	Boolean	Tests whether the file exists
getName()	String	Returns the name of the file
getAbsolutePath()	String	Returns the absolute pathname of the file
length()	Long	Returns the size of the file in bytes
list()	String[]	Returns an array of the files in the directory
mkdir()	Boolean	Creates a directory
     */
    @Test
    public void test2(){
        File file1 = new File("hello.txt");
        File file2 = new File("d:\\io\\hi.txt");

        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(new Date(file1.lastModified()));

        System.out.println();

        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());
    }
    @Test
    public void findAllFileUnderThePath(){
        File file = new File("C:\\Users\\J&C\\Desktop");
        //return file name
        String[] list = file.list();
        for(String s : list){
            System.out.println(s);
        }

        System.out.println();
        //return file path
        File[] files = file.listFiles();
        for(File f : files){
            System.out.println(f);
        }

    }
    /**
    public boolean renameTo(File dest):把文件重命名为指定的文件路径
     比如：file1.renameTo(file2)为例：要想保证返回true,需要file1在硬盘中是存在的，且file2不能在硬盘中存在。
     */
    @Test
    public void renameFile(){
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\io\\hi.txt");

        boolean renameTo = file2.renameTo(file1);
        System.out.println(renameTo);

        // Rename old-dummy.txt to new_dummy.txt
        File oldFile = new File("old_dummy.txt");
        File newFile = new File("new_dummy.txt");

        boolean fileRenamed = oldFile.renameTo(newFile);
        //我们需要检查返回值，以确保重命名成功。
        if (fileRenamed) {
            System.out.println(oldFile + "  renamed  to " + newFile);
        } else {
            System.out.println("Renaming " + oldFile + "  to " + newFile
                    + "  failed.");
        }

    }
    /*
public boolean isDirectory()：判断是否是文件目录
public boolean isFile() ：判断是否是文件
public boolean exists() ：判断是否存在
public boolean canRead() ：判断是否可读
public boolean canWrite() ：判断是否可写
public boolean isHidden() ：判断是否隐藏

     */
    @Test
    public void fileMethodTest(){
        File file1 = new File("hello.txt");
        file1 = new File("hello1.txt");

        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());

        System.out.println();

        File file2 = new File("d:\\io");
        file2 = new File("d:\\io1");
        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.exists());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());

    }
    /*
    创建硬盘中对应的文件或文件目录
public boolean createNewFile() ：创建文件。若文件存在，则不创建，返回false
public boolean mkdir() ：创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
public boolean mkdirs() ：创建文件目录。如果此文件目录存在，就不创建了。如果上层文件目录不存在，一并创建

    删除磁盘中的文件或文件目录
public boolean delete()：删除文件或者文件夹
    删除注意事项：Java中的删除不走回收站。

     */
    @Test
    public void creatFile() throws IOException {
        /*文件可以创建常规文件，目录，符号链接和临时文件/目录。
        大多数方法接受FileAttribute类型的varargs参数，这允许我们指定文件属性。
        createFile()方法创建一个新的常规文件。创建的新文件为空。
        如果文件已存在，或父目录不存在，文件创建将失败。
        以下代码显示如何创建新文件。*/

        File file1 = new File("hi.txt");
        if(!file1.exists()){
            //文件的创建
            file1.createNewFile();
            System.out.println("创建成功");
        }else{//文件存在
            file1.delete();
            System.out.println("删除成功");
        }


    }
    @Test
    public void creatPath(){
        //文件目录的创建
        File file1 = new File("d:\\io\\io1\\io3");

        boolean mkdir = file1.mkdir();
        if(mkdir){
            System.out.println("创建成功1");
        }

        File file2 = new File("d:\\io\\io1\\io4");

        boolean mkdir1 = file2.mkdirs();
        if(mkdir1){
            System.out.println("创建成功2");
        }
        //要想删除成功，io4文件目录下不能有子目录或文件
        File file3 = new File("D:\\io\\io1\\io4");
        file3 = new File("D:\\io\\io1");
        System.out.println(file3.delete());
    }

    //File  CRUD
    @Test
    public void testPath() throws IOException{
        Path path1 = Paths.get("d:\\nio", "hello.txt");
        File file = new File("hello.txt");
        file.createNewFile();
        FileWriter writer = new FileWriter("hello.txt");
        writer.write("Files in Java is fun!\"");

        Path path2 = Paths.get("hello.txt");

//		Path copy(Path src, Path dest, CopyOption … how) : 文件的复制
        //要想复制成功，要求path1对应的物理上的文件存在。path1对应的文件没有要求。
//		Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING);

//		Path createDirectory(Path path, FileAttribute<?> … attr) : 创建一个目录
        //要想执行成功，要求path对应的物理上的文件目录不存在。一旦存在，抛出异常。
        Path path3 = Paths.get("d:\\nio\\nio1");
//		Files.createDirectory(path3);

//		Path createFile(Path path, FileAttribute<?> … arr) : 创建一个文件
        //要想执行成功，要求path对应的物理上的文件不存在。一旦存在，抛出异常。
        Path path4 = Paths.get("d:\\nio\\hi.txt");
//		Files.createFile(path4);

//		void delete(Path path) : 删除一个文件/目录，如果不存在，执行报错
//		Files.delete(path4);

//		void deleteIfExists(Path path) : Path对应的文件/目录如果存在，执行删除.如果不存在，正常执行结束
        Files.deleteIfExists(path3);

//		Path move(Path src, Path dest, CopyOption…how) : 将 src 移动到 dest 位置
        //要想执行成功，src对应的物理上的文件需要存在，dest对应的文件没有要求。
//		Files.move(path1, path2, StandardCopyOption.ATOMIC_MOVE);

//		long size(Path path) : 返回 path 指定文件的大小
        long size = Files.size(path2);
        System.out.println(size);

    }

    @Test
    public void testLinkOption() throws IOException{
        Path path1 = Paths.get("d:\\nio", "hello.txt");
        Path path2 = Paths.get("hello.txt");
//		boolean exists(Path path, LinkOption … opts) : 判断文件是否存在
        System.out.println(Files.exists(path2, LinkOption.NOFOLLOW_LINKS));

//		boolean isDirectory(Path path, LinkOption … opts) : 判断是否是目录
        //不要求此path对应的物理文件存在。
        System.out.println(Files.isDirectory(path1, LinkOption.NOFOLLOW_LINKS));

//		boolean isRegularFile(Path path, LinkOption … opts) : 判断是否是文件

//		boolean isHidden(Path path) : 判断是否是隐藏文件
        //要求此path对应的物理上的文件需要存在。才可判断是否隐藏。否则，抛异常。
//		System.out.println(Files.isHidden(path1));

//		boolean isReadable(Path path) : 判断文件是否可读
        System.out.println(Files.isReadable(path1));
//		boolean isWritable(Path path) : 判断文件是否可写
        System.out.println(Files.isWritable(path1));
//		boolean notExists(Path path, LinkOption … opts) : 判断文件是否不存在
        System.out.println(Files.notExists(path1, LinkOption.NOFOLLOW_LINKS));
    }

    /**
     * StandardOpenOption.READ:表示对应的Channel是可读的。
     * StandardOpenOption.WRITE：表示对应的Channel是可写的。
     * StandardOpenOption.CREATE：如果要写出的文件不存在，则创建。如果存在，忽略
     * StandardOpenOption.CREATE_NEW：如果要写出的文件不存在，则创建。如果存在，抛异常
     */
    @Test
    public void test33() throws IOException{
        Path path1 = Paths.get("d:\\nio", "hello.txt");

//		InputStream newInputStream(Path path, OpenOption…how):获取 InputStream 对象
        InputStream inputStream = Files.newInputStream(path1, StandardOpenOption.READ);

//		OutputStream newOutputStream(Path path, OpenOption…how) : 获取 OutputStream 对象
        OutputStream outputStream = Files.newOutputStream(path1, StandardOpenOption.WRITE,StandardOpenOption.CREATE);


//		SeekableByteChannel newByteChannel(Path path, OpenOption…how) : 获取与指定文件的连接，how 指定打开方式。
        SeekableByteChannel channel = Files.newByteChannel(path1, StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);

//		DirectoryStream<Path>  newDirectoryStream(Path path) : 打开 path 指定的目录
        Path path2 = Paths.get("e:\\teach");
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path2);
        Iterator<Path> iterator = directoryStream.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void deleteFile() throws IOException{
        //立即删除dummy.txt文件
        File dummyFile1 = new File("dummy.txt");
        dummyFile1.delete();
        //在JVM终止时删除dummy.txt文件
        File dummyFile2 = new File("dummy.txt");
        dummyFile2.deleteOnExit();
    }
    //文件属性
    @Test
    public void fileSetMethod() throws IOException{
        /**
         File类包含让我们获取/设置文件和目录的属性的方法。
         我们可以设置分别使用setReadOnly()，setReadable()，setWritable()和setExecutable()方法将文件设置为只读，可读，可写和可执行。
         我们可以使用lastModified()和setLastModified()方法来获取和设置文件的最后修改日期和时间。
         我们可以使用isHidden()方法检查文件是否被隐藏。
         * */
        /*如果File对象表示不存在的文件，则length()方法返回零。length()方法的返回类型是long，而不是int。*/
        File myFile  = new File("myfile.txt");
        long  fileLength = myFile.length();
       /* myFile.setReadOnly();
        myFile.setReadable(true);
        myFile.setWritable(true);
        myFile.setExecutable(true);
        myFile.lastModified();
        myFile.setLastModified(1L);
        myFile.isHidden();
        */

    }

    //列出文件和目录
    //我们可以使用File类的listRoots()静态方法获取文件系统中可用根目录的列表。 它返回一个File对象数组。
    @Test
    public void fileContents() throws IOException{
        //以下代码显示如何列出所有可用的根目录。
        File[] roots = File.listRoots();
        System.out.println("List  of  root directories:");
        for (File f : roots) {
            System.out.println(f.getPath());
        }
        //我们可以使用File类的list()或listFiles()方法列出目录中的所有文件和目录。
        //list()方法返回一个String数组，而listFiles()方法返回一个File数组。
        //我们还可以使用这些方法的文件过滤器从返回的结果中排除一些文件和目录。
        //以下代码显示如何列出目录中的所有文件和目录。
        // Change the dirPath value to list files from your directory
        String dirPath = "C:\\";

        File dir = new File(dirPath);
        File[] list = dir.listFiles();

        for (File f : list) {
            if (f.isFile()) {
                System.out.println(f.getPath() + "  (File)");
            } else if (f.isDirectory()) {
                System.out.println(f.getPath() + "  (Directory)");
            }
        }

    }
    //文件过滤器
    @Test
    public void fileFilter() throws IOException{
        /**
         要从列表中排除扩展名为.SYS的所有文件，我们可以使用由功能接口FileFilter的实例表示的文件过滤器来实现。
         它包含一个accept()方法，它将File作为参数列出，如果应该列出文件，则返回true。返回false不会列出文件。
         以下代码创建一个文件过滤器，将过滤扩展名为.SYS的文件。
         * */
        FileFilter filter = file ->  {
            if (file.isFile()) {
                String fileName   = file.getName().toLowerCase();
                if (fileName.endsWith(".sys"))  {
                    return false;
                }
            }
            return true;
        };
        //以下代码创建两个文件过滤器 - 一个仅过滤文件，另一个仅过滤目录:
        // Filters only  files
        FileFilter fileOnlyFilter = File::isFile;

        // Filters only  directories
        FileFilter  dirOnlyFilter = File::isDirectory;

        //以下代码显示如何使用FileFilter过滤文件。

        String dirPath = "C:\\";
        File dir = new File(dirPath);

        // Create a file filter to exclude any .SYS file
        FileFilter filter2 = file -> {
            if (file.isFile()) {
                String fileName = file.getName().toLowerCase();
                if (fileName.endsWith(".sys")) {
                    return false;
                }
            }
            return true;
        };

        File[] list = dir.listFiles(filter2);

        for (File f : list) {
            if (f.isFile()) {
                System.out.println(f.getPath() + "  (File)");
            } else if (f.isDirectory()) {
                System.out.println(f.getPath() + "  (Directory)");
            }
        }
    }
    //创建目录
    /**
     createDirectory()和createDirectories()方法创建一个新目录。
     如果父目录不存在，createDirectory()方法将失败。
     createDirectories()方法创建不存在的父目录。
     createTempDirectory()和createTempFile()方法分别创建一个临时目录和一个临时文件。
     * */
    //以下代码显示了如何创建临时文件和目录。
    @Test
    public void creatContent() throws IOException{
        String dirPrefix = "KDir";
        Path tDir = Files.createTempDirectory(dirPrefix);
        System.out.println("Temp directory: " + tDir);
        String fPrefix = "Header_";
        String fSuffix = ".txt";
        Path tFile1 = Files.createTempFile(fPrefix, fSuffix);
        System.out.println("Temp file1: " + tFile1);

        Path p1 = Paths.get("C:\\temp");
        Path tFile2 = Files.createTempFile(p1, fPrefix, fSuffix);
        System.out.println("Temp file2: " + tFile2);
        //不会自动删除临时文件/目录。我们可能希望使用java.io.File类的deleteOnExit()方法在JVM退出时删除该文件。
        Path  tempFile = Files.createTempFile("myTempFile", ".txt");
        tempFile.toFile().deleteOnExit();

    }
    //文件属性
    /**
     java.nio.attribute包包含属性相关的类。它在以下六种类型的视图中捆绑文件属性。
     BasicFileAttributeView管理基本文件属性，如创建时间，上次访问时间，
     上次修改时间，大小，文件类型(常规文件，目录，符号链接或其他)和文件键(文件的唯一编号)。所有平台都支持此视图。
     DosFileAttributeView扩展BasicFileAttributeView访问特定于DOS的文件属性。
     它提供了支持以检查文件是否是隐藏文件，系统文件，归档文件和只读文件。它仅在支持DOS的系统(如Microsoft Windows)上可用。
     POSIX代表UNIX的便携式操作系统接口。PosixFileAttributeView扩展了BasicFileAttributeView并添加了
     对支持POSIX标准(如UNIX)的系统上可用的属性的支持。 它允许我们管理所有者，组和[相关访问]权限。
     FileOwnerAttributeView管理文件的所有者。
     ACL代表访问控制列表。AclFileAttributeView管理文件的ACL。
     UserDefinedFileAttributeView管理一组文件的用户定义属性。属性的名称是一个字符串。属性的值可以是任何数据类型。
     * */
    //文件属性视图支持
    //支持FileStore类中的File Attribute View()方法文件存储器是否支持特定文件属性视图。
   // 如果支持指定的文件属性视图，则返回true; 否则，返回false。
    //以下代码显示如何检查文件属性支持。

    @Test
    public void fileAttribute() throws IOException{
        Path path = Paths.get("");
        FileStore fs = Files.getFileStore(path);

        // Check if POSIX file attribute is supported by the file store
        boolean supported = fs
                .supportsFileAttributeView(PosixFileAttributeView.class);
        if (supported) {
            System.out.println("POSIX file attribute view  is supported.");
        } else {
            System.out.println("POSIX file attribute view  is not  supported.");
        }
    }
    //以下代码显示如何检查文件存储的支持的文件属性视图。
    @Test
    public void test() throws IOException{
        Path path = Paths.get("C:");

        try {
            FileStore fs = Files.getFileStore(path);
            printDetails(fs, AclFileAttributeView.class);
            printDetails(fs, BasicFileAttributeView.class);
            printDetails(fs, DosFileAttributeView.class);
            printDetails(fs, FileOwnerAttributeView.class);
            printDetails(fs, PosixFileAttributeView.class);
            printDetails(fs, UserDefinedFileAttributeView.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void printDetails(FileStore fs,
                                    Class<? extends FileAttributeView> attribClass) {
        boolean supported = fs.supportsFileAttributeView(attribClass);
        System.out.format("%s is  supported: %s%n", attribClass.getSimpleName(),
                supported);
    }
    //读取和更新文件属性
    /**
     * 要读取或更新一个文件属性，请使用Files类。     *
     * Files类具有以下两种静态方法，以使用属性名称作为字符串来读取和更新文件属性:
     * 要读取或更新文件的多个属性，请使用特定的文件属性视图。     *
     * 对于大多数文件属性视图，我们必须使用名为 TypeAttributes和 TypeAttributeView的两个接口。     *
     * 对于基本文件属性，我们有BasicFileAttributes和BasicFileAtrributeView接口。     *
     * 该Type Attributes读取的属性。该Type AttributeView读取/更新的属性。
     * */
    @Test
    //以下代码打印文件C:\Java_Dev\test1.txt的大小和最后修改时间。
    public void test3() throws IOException{
        Path path = Paths.get("C:\\Java_Dev\\test1.txt");

        // Prepare the attribute list
        String attribList = "basic:size,lastModifiedTime";

        // Read the attributes
        Map<String, Object> attribs = Files.readAttributes(path, attribList);

        System.out.format("Size:%s, Last   Modified   Time:%s %n",
                attribs.get("size"), attribs.get("lastModifiedTime"));

    }
    //以下代码读取文件C:\Java_Dev\test1.txt的基本文件属性，并在标准输出上打印其中的一些。
    @Test
    public void test4() throws IOException{
        Path path = Paths.get("C:\\Java_Dev\\test1.txt");

        try {
            BasicFileAttributes bfa = Files.readAttributes(path,
                    BasicFileAttributes.class);
            System.out.format("Size:%s bytes %n", bfa.size());
            System.out.format("Creation Time:%s %n", bfa.creationTime());
            System.out.format("Last Access  Time:%s %n", bfa.lastAccessTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //以下代码使用基本视图对象读取C:\Java_Dev\test1.txt文件的所有基本属性:
    @Test
    public void test5() throws IOException{
        Path  path   = Paths.get("C:\\Java_Dev\\test1.txt");
        BasicFileAttributeView bfv = Files.getFileAttributeView(path,  BasicFileAttributeView.class);
        BasicFileAttributes bfa  = bfv.readAttributes();
    }
    //以下代码显示如何使用基本文件属性视图来读取和更新基本文件属性。
    @Test
    public void test6() throws IOException{
        Path path = Paths.get("C:\\Java_Dev\\test1.txt");

        try {
            BasicFileAttributeView bfv = Files.getFileAttributeView(path,
                    BasicFileAttributeView.class);
            BasicFileAttributes bfa = bfv.readAttributes();

            System.out.format("Size:%s bytes %n", bfa.size());
            System.out.format("Creation  Time:%s %n", bfa.creationTime());
            System.out.format("Last Access  Time:%s %n", bfa.lastAccessTime());

            FileTime newLastModifiedTime = null;
            FileTime newLastAccessTime = null;
            FileTime newCreateTime = FileTime.from(Instant.now());

            bfv.setTimes(newLastModifiedTime, newLastAccessTime, newCreateTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
