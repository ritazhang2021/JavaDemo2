package java_basic_classes.io;
import org.junit.Test;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;
import java.io.IOException;
import java.util.concurrent.Future;

import static java.nio.file.FileVisitOption.FOLLOW_LINKS;
import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;
import static java.nio.file.StandardOpenOption.*;
import static java.nio.file.StandardWatchEventKinds.*;
import static java.nio.file.attribute.AclEntryPermission.READ_DATA;
import static java.nio.file.attribute.AclEntryPermission.WRITE_DATA;
import static java.nio.file.attribute.PosixFilePermission.*;

/**
 * @Author: Rita
 * 在NIO中，我们处理I/O操作的通道和缓冲区。
 *
 * 像流一样的通道表示数据源/接收器和用于数据传输的Java程序之间的连接。
 *
 * 通道提供双向数据传输设施。我们可以使用通道来读取数据以及写入数据。根据我们的需要，我们可以获得只读通道，只写通道或读写通道。
 *
 * 在基于流的I/O中，数据传输的基本单位是一个字节。在基于通道的NIO中，数据传输的基本单位是缓冲器。
 *
 * 缓冲区具有确定其可以包含的数据的上限的固定容量。
 *
 * 在基于通道的I/O中，我们将数据写入缓冲区，并将该缓冲区传递到写入数据的通道。
 *
 * 为了从数据源读取数据，我们向一个通道传递一个缓冲区。通道将数据从数据源读入缓冲区。
 *
 * 缓冲区
 * 缓冲区是固定长度的数据容器。有一个单独的缓冲区类型来保存每种类型的基本值的数据，除了布尔类型值。
 *
 * 缓冲区是我们程序中的一个对象。我们有一个单独的类来表示每种类型的缓冲区。
 *
 * 所有缓冲区类都继承自一个抽象的Buffer类。包含原始值的缓冲区类如下：
 *
 * ByteBuffer
 * ShortBuffer
 * CharBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 不同的缓冲区保存不同数据类型的数据。例如，ByteBuffer保存字节值; ShortBuffer保存短值;一个CharBuffer保存字符，等等。
 *
 *
 * 缓冲区属性
 * 以下是缓冲区的四个重要属性。
 *
 * Capacity
 * Position
 * Limit
 * Mark
 * 缓冲区的容量是它可以容纳的元素的最大数量。并且当创建缓冲器时它是固定的。
 *
 * 我们可以通过调用hasArray()方法检查缓冲区是否由数组支持，如果缓冲区由数组支持则返回true。
 *
 * 我们可以通过使用缓冲对象的array()方法来访问支持数组。
 *
 * 一旦我们访问了后台数组，对该数组所做的任何更改都将反映在缓冲区中。
 *
 * 缓冲区具有返回其容量的capacity()方法。
 */
public class NIOTest {
    //以下代码创建一个新缓冲区并显示其四个属性。
    @Test
    public void test1() throws IOException {
        //以下代码创建一个容量为8的字节缓冲区
        ByteBuffer bb = ByteBuffer.allocate(8);
        //获得容量
        System.out.println("Capacity: " + bb.capacity());
        System.out.println("Limit: " + bb.limit());
        /*
        缓冲区索引位置
        缓冲区的每个元素都有一个索引。第一个元素的索引为0，最后一个元素的索引为capacity-1。
        创建缓冲区时，其位置设置为0，其限制等于其容量。
        我们可以使用它的重载position()方法获取/设置缓冲区的位置。
        position()方法返回缓冲区位置的当前值。
        position(int newPosition)方法将缓冲区的位置设置为指定的newPosition值，并返回缓冲区的引用。
        我们可以使用它的重载limit()方法获取/设置缓冲区的限制。
        limit()方法返回缓冲区限制的当前值。limit（int newLimit）方法将缓冲区的限制设置为指定的newLimit值，并返回缓冲区的引用
        我们可以使用mark()方法为缓冲区的位置添加书签。当我们调用mark()方法时，缓冲区将其位置的当前值存储为其标记值。
        我们可以通过使用reset()方法将缓冲区的位置设置为之前加书签的值。
        缓冲区的标记在创建时未定义。只有当定义了它的标记时，我们才必须在缓冲区上调用reset()方法。
        否则，reset()方法会抛出InvalidMarkException异常。
         * */
        System.out.println("Position: " + bb.position());

       // 以下代码创建一个容量为1024的字符缓冲区
        CharBuffer cb  = CharBuffer.allocate(1024);

        //另一种创建缓冲区的方法是使用缓冲区的static wrap()方法包装数组。
        byte[]  byteArray = new byte[512];
        ByteBuffer bb2  = ByteBuffer.wrap(byteArray);

        // The mark is not set for a new buffer. Calling the
        // reset() method throws a runtime exception if the mark is not set.
        try {
            bb.reset();
            System.out.println("Mark: " + bb.position());
        } catch (InvalidMarkException e) {
            System.out.println("Mark is not  set");
        }

    }
    //以下代码显示如何写入缓冲区和从缓冲区读取。
    @Test
    public void test2() throws IOException {

         /*
        有两种方法从缓冲区读取数据:
        绝对位置
        相对位置
        使用四个版本重载的get()方法用于从缓冲区读取数据。
        get(int index)返回给定索引处的数据。
        get()从缓冲区中的当前位置返回数据，并将位置增加1。
        get(byte [] destination，int offset，int length)从缓冲区中批量读取数据。
        它从缓冲区的当前位置读取长度字节数，并将它们放在从指定偏移量开始的指定目标数组中。
        get(byte [] destination)通过从缓冲区的当前位置读取数据并且每次读取数据元素时将当前位置递增1来填充指定的目标数组。
        * */
        ByteBuffer bb = ByteBuffer.allocate(8);
        printBufferInfo(bb);
        for (int i = 50; i < 58; i++) {
            bb.put((byte) i);
        }
        printBufferInfo(bb);
    }
    /*
        缓冲区写入
        使用重载五个版本的put()方法将数据写入缓冲区。
        put(int index，byte b)将指定的b数据写入指定的索引。 调用此方法不会更改缓冲区的当前位置。
        put(byte b)将指定的字节写入缓冲区的当前位置，并将位置递增1。
        put(byte [] source，int offset，int length)将起始于偏移量的源数组的字节长度写入从当前位置开始的缓冲区。
        如果缓冲区中没有足够的空间来写入所有字节，它会抛出BufferOverflowException。 缓冲区的位置按长度增加。
        put(byte [] source)与调用put(byte [] source，0，source.length)相同。
        ByteBuffer put(ByteBuffer src)从指定的字节缓冲区src读取剩余的字节，并将它们写入缓冲区。
        如果目标缓冲区中的剩余空间小于源缓冲区中的剩余字节，则抛出运行时BufferOverflowException。
        * */
    public static void printBufferInfo(ByteBuffer bb) {
        int limit = bb.limit();
        System.out.println("Position =  " + bb.position() + ", Limit   = " + limit);
        for (int i = 0; i < limit; i++) {
            System.out.print(bb.get(i) + "  ");
        }
        System.out.println();
    }
    //以下代码显示如何使用相对读取和写入之间的缓冲区的flip()和hasRemaining()方法。
    @Test
    public void test3() throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(8);
        printBufferInfo(bb);

        // Use flip() to reset the position to zero because
        // the printBufferInfo() method uses relative get() method
        bb.flip();

        int i = 50;
        while (bb.hasRemaining()) {
            bb.put((byte) i++);
        }

        // Call flip() again to reset the position to zero,
        // because the above put() call incremented the position
        bb.flip();
        printBufferInfo(bb);
    }
    //只读缓冲区
    //缓冲区可以是只读的或读写的。 我们只能读取只读缓冲区的内容。
    //我们可以通过调用特定缓冲区类的asReadOnlyBuffer()方法来获取只读缓冲区。
    @Test
    public void test4() throws IOException {
        // Create a  buffer that is read-write by  default
        ByteBuffer bb  = ByteBuffer.allocate(1024);
        boolean readOnly   = bb.isReadOnly(); // false
        //要检查缓冲区是否为只读，请按如下所示调用isReadOnly()方法:

        ByteBuffer bbReadOnly = bb.asReadOnlyBuffer();
        boolean readOnly2 = bbReadOnly.isReadOnly();
        //asReadOnlyBuffer()方法返回的只读缓冲区是同一个缓冲区的不同视图。 对原始缓冲区的内容的任何修改都反映在只读缓冲区中。

        //缓冲区视图
        //我们可以获得缓冲区的不同视图。 缓冲区的视图与原始缓冲区共享数据，并保持其自身的位置，标记和限制。
        //我们也可以复制一个缓冲区，在这种情况下，它们共享内容，但独立地保持标记，位置和限制。 使用缓冲区的duplicate()方法获取缓冲区的副本如下:

        // Create a  buffer
        ByteBuffer bb2  = ByteBuffer.allocate(1024);

        // Create a  duplicate view  of  the   buffer
        ByteBuffer bbDuplicate = bb2.duplicate();

        //我们还可以通过使用缓冲区的slice()方法创建其切片视图，从而创建缓冲区的切片视图，如下所示:

        // Create a  buffer
        ByteBuffer bb3  = ByteBuffer.allocate(8);
        bb3.position(3);
        bb3.limit(6);
        // bbSlice will have  position set to 0  and  its  limit set to 3.
        ByteBuffer bbSlice  = bb.slice();

        //我们还可以获得用于不同原始数据类型的字节缓冲区的视图。
        //例如，我们可以获得字节缓冲区的字符视图，浮点视图等。
        // ByteBuffer类包含诸如asCharBuffer()，asLongBuffer()，asFloatBuffer()等方法来获得原始数据类型的视图。

        // Create a  byte   buffer
        ByteBuffer bb4  = ByteBuffer.allocate(8);

        // Create a  char   view  of  the   byte   buffer
        CharBuffer cb  = bb4.asCharBuffer();

        // Create a  float view  of  the   byte   buffer
        FloatBuffer fb  = bb4.asFloatBuffer();
    }
    /**
     我们可以使用编码方案将Unicode字符转换为字节序列，反之亦然。
     java.nio.charset包提供了将CharBuffer编码/解码为ByteBuffer的类，反之亦然。
     Charset类的对象表示编码方案。 CharsetEncoder类执行编码。 CharsetDecoder类执行解码。
     我们可以通过传递字符集的名称作为它的参数，使用它的forName()方法获得Charset类的对象。
     对于简单的编码和解码任务，我们可以使用Charset类的encode()和decode()方法。
     以下代码显示如何对存储在字符缓冲区中的字符串Hello中的字符序列进行编码，并使用UTF-8编码方案对其进行解码。
     * */

    @Test
    public void test5() throws IOException {
        Charset cs  = Charset.forName("UTF-8");
        CharBuffer cb  = CharBuffer.wrap("Hello");
        ByteBuffer encodedData   = cs.encode(cb);
        CharBuffer decodedData   = cs.decode(encodedData);
    }

    /**
     CharsetEncoder和CharsetDecoder类接受要编码或解码的输入块。
     Charset类的encode()和decode()方法将编码和解码的缓冲区返回给我们。
     以下代码显示如何从Charset对象获取编码器和解码器对象。
     * */
    @Test
    public void test6() throws IOException {
        Charset cs  = Charset.forName("UTF-8");
        CharsetEncoder encoder = cs.newEncoder();
        CharsetDecoder decoder = cs.newDecoder();
    }
    //    以下代码演示如何列出JVM支持的所有字符集。
    @Test
    public void test7() throws IOException {
        Map<String, Charset> map = Charset.availableCharsets();
        Set<String> keys = map.keySet();
        System.out.println("Available  Character Set  Count:   " + keys.size());

        for (String charsetName : keys) {
            System.out.println(charsetName);
        }
    }
    //字节顺序仅在存储在字节缓冲器中的多字节值中有用。要知道我们机器的字节顺序，请使用ByteOrder类的nativeOrder()方法。
    @Test
    public void test8() throws IOException {
        ByteOrder b = ByteOrder.nativeOrder();

        if (b.equals(ByteOrder.BIG_ENDIAN)) {
            System.out.println("Big endian");
        } else {

            System.out.println("Little  endian");
        }
    }
    //以下代码演示如何获取和设置字节缓冲区的字节顺序。
    //我们使用ByteBuffer的order()方法来获取或设置字节顺序。
    @Test
    public void test9() throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(2);
        System.out.println("Default  Byte  Order: " + bb.order());
        bb.putShort((short) 300);
        bb.flip();
        showByteOrder(bb);

        bb.clear();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.putShort((short) 300);
        bb.flip();
        showByteOrder(bb);
    }

    public static void showByteOrder(ByteBuffer bb) {
        System.out.println("Byte  Order: " + bb.order());
        while (bb.hasRemaining()) {
            System.out.print(bb.get() + "    ");
        }
        System.out.println();
    }

    //Channel
    /**通道是数据源和Java程序之间的开放连接，用于执行I/O操作。
    Channel接口在java.nio.channels包中。
    通道接口只声明了两个方法:close()和isOpen()。*/
    @Test
    public void test10() throws IOException {
        InputStream myInputStream = null;
        //例如，如果我们有一个名为myInputStream的输入流对象，我们可以获得一个ReadableByteChannel如下：
        ReadableByteChannel rbc  = Channels.newChannel(myInputStream);

       // 如果我们有一个名为rbc的ReadableByteChannel，我们可以获得如下的基本InputStream对象：
        InputStream myInputStream2  = Channels.newInputStream(rbc);


       /* FileInputStream和FileOutputStream类有一个称为getChannel()的新方法来返回一个FileChannel对象。
        FileChannel用于读取和写入数据到文件。
        从FileInputStream获取的FileChannel对象以只读模式打开。*/

        FileInputStream fis  = new FileInputStream("test1.txt");
        FileChannel fcReadOnly  = fis.getChannel(); // A  read-only  channel

        //从FileOutputStream对象获取的FileChannel对象以只写模式打开。

        FileOutputStream fos   = new FileOutputStream("test1.txt");
        FileChannel  fcWriteOnly = fos.getChannel(); // A  write-only  channel

       // 以下代码为不同种类的文件流获取FileChannel对象：
        // read-only mode
        RandomAccessFile  raf1 = new RandomAccessFile("test1.txt", "r");
        FileChannel  rafReadOnly = raf1.getChannel(); // A  read-only  channel

        // read-write mode
        RandomAccessFile  raf2 = new RandomAccessFile("test1.txt", "rw");
        FileChannel rafReadWrite = raf2.getChannel(); // A  read-write channel
    }

    //以下代码从名为test1.txt的文件中读取文本。
    /**
     FileChannel对象维护位置变量作为缓冲区。
     FileChannel的read()和write()方法有两种类型：相对位置读/写和绝对位置读/写。
     当我们打开一个FileChannel时，它的位置设置为0，这是文件的开始。
     当我们使用相对read()方法从FileChannel读取时，它的位置增加读取的字节数。
     从FileChannel读取的绝对位置不会影响其位置。
     我们可以使用position()方法获取FileChannel对象的当前位置值。
     我们可以使用位置（int newPosition）方法将其位置设置为新位置。
     通道也是可自动关闭的。如果我们使用try-with-resources语句来获取一个通道，那么通道将被自动关闭，
     这样就避免了我们明确地调用通道的close()方法。
     * */
    @Test
    public void test11() throws IOException {
        File inputFile = new File("test1.txt");
        if (!inputFile.exists()) {
            System.out.println("The input file " + inputFile.getAbsolutePath()
                    + "  does  not  exist.");
            System.out.println("Aborted the   file reading process.");
            return;
        }
        try (FileChannel fileChannel = new FileInputStream(inputFile).getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(buffer) > 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.print((char) b);
                }
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //以下代码显示如何使用缓冲区和通道写入文件
    @Test
    public void test12() throws IOException {
        File outputFile = new File("test.txt");

        try (FileChannel fileChannel = new FileOutputStream(outputFile)
                .getChannel()) {
            String text = getText();
            byte[] byteData = text.toString().getBytes("UTF-8");
            ByteBuffer buffer = ByteBuffer.wrap(byteData);
            fileChannel.write(buffer);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public static String getText() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append("test");
        sb.append(lineSeparator);
        sb.append("test");
        sb.append(lineSeparator);

        sb.append("test");
        sb.append(lineSeparator);
        sb.append("test");

        return sb.toString();
    }
    //复制文件的内容
    @Test
    public void test13() throws IOException {
        /*我们可以使用缓冲区和通道来复制文件。
        获取源文件和目标文件的FileChannel对象，并对源FileChannel对象调用transferTo()方法或调用目标FileChannel对象上的transferFrom()方法。
        以下代码显示如何复制文件。*/
        FileChannel sourceChannel = new FileInputStream("sourceFile").getChannel();
        FileChannel sinkChannel = new FileOutputStream("newFile").getChannel();

        // Copy source file contents to the sink file
        sourceChannel.transferTo(0, sourceChannel.size(), sinkChannel);

    }
    //Java内存通道
    @Test
    public void test14() throws IOException {
        /**
         对文件执行I/O的另一种方法是将文件的一个区域映射到物理内存，并将其作为内存数组。
         我们可以使用MappedByteBuffer来执行内存映射文件I/O。
         要使用内存映射文件I/O，请为文件获取FileChannel对象，并使用FileChannel的map()方法获取MappedByteBuffer。
         直接读取或写入映射的字节缓冲区，而不是使用FileChannel对象的read()或write()方法。
         当从映射的字节缓冲区读取时，我们从已经映射的文件的区域读取。
         当写入映射的字节缓冲区时，我们写入文件的映射区域。
         要将数据立即写入映射字节缓冲区到存储设备，我们需要使用映射字节缓冲区的force()方法。
         我们可以以只读，读写或私有模式映射文件的区域。
         在只读模式下，我们只能从映射的字节缓冲区读取。
         在读写模式下，我们可以从映射字节缓冲区读取以及写入。
         专用模式也称为写时复制模式。当多个程序映射文件的相同区域时，所有程序共享文件的相同区域。
         当程序修改映射区域时，仅为该程序创建该区域的单独副本，该副本是其私有副本。对私人副本所做的任何修改对其他程序不可见。
         * */
        //下面的代码以只读模式映射整个文件test.txt。它读取文件并在标准输出上显示内容。
        FileInputStream fis = new FileInputStream("test.txt");
        FileChannel fc = fis.getChannel();

        long startRegion = 0;
        long endRegion = fc.size();
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, startRegion,
                endRegion);
        while (mbb.hasRemaining()) {
            System.out.print((char) mbb.get());
        }
        fis.close();
    }
    //Java文件锁
    /**
     NIO支持文件锁定以同步对文件的访问。我们可以锁定文件或整个文件的区域。
     文件锁定机制由操作系统处理。
     有两种文件锁定:排他和共享。
     只有一个程序可以保存文件区域上的排他锁。
     多个程序可以在文件的同一区域上保存共享锁。
     我们不能在文件的同一区域混合排他锁和共享锁。
     java.nio.channels.FileLock类表示文件锁。
     我们通过使用FileChannel对象的lock()或tryLock()方法获取对文件的锁。
     lock()方法阻止所请求区域上的锁是否不可用。
     tryLock()方法不阻塞; 如果获取锁，它立即返回FileLock类的对象; 否则返回null。
     lock()和tryLock()方法有两个版本:一个没有参数，另一个有参数。
     不带参数的版本锁定整个文件。
     带有参数的版本接受要锁定的区域的起始位置，要锁定的字节数以及用于指示锁是否共享的布尔标志。
     如果锁是共享的，FileLock对象的isShared()方法返回true; 否则，返回false。

     * */
    //  以下代码显示了获取文件锁的不同方法。
    @Test
    public void test15() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("test.txt", "rw");
        FileChannel fileChannel = raf.getChannel();

        FileLock lock = fileChannel.lock();

    }

    //获得前10个字节的独占锁
    @Test
    public void test16() throws IOException {

        RandomAccessFile raf = new RandomAccessFile("test.txt", "rw");
        FileChannel fileChannel = raf.getChannel();
        // Get an exclusive lock on first 10 bytes
        FileLock lock = fileChannel.lock(0, 10, false);
    }

    //尝试获取整个文件的独占锁
    @Test
    public void test17() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("test.txt", "rw");
        FileChannel fileChannel = raf.getChannel();
        FileLock lock = fileChannel.tryLock();
        if (lock == null) {
            // Could not get the lock
        } else {
            // Got the lock
        }
    }
    //尝试在共享模式下从第11个字节开始锁定100个字节
    @Test
    public void test18() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("test.txt", "rw");
        FileChannel fileChannel = raf.getChannel();
        FileLock lock = fileChannel.tryLock(11, 100, true);
        if (lock == null) {
            // Could not get the lock
        } else {
            // Got the lock
        }
    }
    //以下代码显示如何使用try-catch-finally块来获取和释放文件锁定，如下所示：
    @Test
    public void test19() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("test.txt", "rw");
        FileChannel fileChannel = raf.getChannel();
        FileLock lock = null;
        try {
            lock = fileChannel.lock(0, 10, true);

        } catch (IOException e) {
            // Handle the exception
        } finally {
            if (lock != null) {
                try {
                    lock.release();
                } catch (IOException e) {
                    // Handle the exception
                }
            }
        }
    }
    //FileSystem
    /**
     FileSystem类的对象表示Java程序中的文件系统。
     FileSystem对象用于执行两个任务：
     Java程序和文件系统之间的接口。
     一个工厂用于创建许多类型的文件系统相关对象和服务。
     FileSystem对象与平台相关。
     * */
    //以下代码显示如何使用FileSystem对象。
    @Test
    public void test20() throws IOException {
        //要获取默认的FileSystem对象，我们需要使用FileSystems类的getDefault()静态方法，如下所示：
        //FileSystem由一个或多个FileStore组成。FileSystem的getFileStores()方法返回FileStore对象的Iterator。
        //FileSystem的getRootDirectories()方法返回Path对象的迭代器，它表示到所有顶级目录的路径。
        //FileSystem的isReadOnly()方法告诉我们是否获得对文件存储的只读访问权限。
        FileSystem fs = FileSystems.getDefault();

        System.out.println("Read-only file system: " + fs.isReadOnly());
        System.out.println("File name separator: " + fs.getSeparator());

        for (FileStore store : fs.getFileStores()) {
            printDetails(store);
        }
        for (Path root : fs.getRootDirectories()) {
            System.out.println(root);
        }
    }
    public static void printDetails(FileStore store) {
        try {
            String desc = store.toString();
            String type = store.type();
            long totalSpace = store.getTotalSpace();
            long unallocatedSpace = store.getUnallocatedSpace();
            long availableSpace = store.getUsableSpace();
            System.out.println(desc + ", Total: " + totalSpace + ",  Unallocated: "
                    + unallocatedSpace + ",  Available: " + availableSpace);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     Java符号链接
     符号链接包含对另一个文件或目录的引用。
     符号链接引用的文件称为符号链接的目标文件。
     符号链接上的操作对应用程序是透明的。我们可以使用java.nio.file.Files类处理符号链接。
     isSymbolicLink(Path p)方法检查指定路径指定的文件是否是符号链接。
     文件的createSymbolicLink()方法（可能不是在所有平台上都支持）创建符号链接。
     * */
    @Test
    public void test21() throws IOException {

        Path existingFilePath = Paths.get("C:\\Java_Dev\\test1.txt");
        Path symLinkPath = Paths.get("C:\\test1_link.txt");
        Files.createSymbolicLink(symLinkPath, existingFilePath);
        /*默认情况下，Java NIO API遵循符号链接。我们可以指定是否要遵循符号链接。
        使用枚举常量LinkOption来指示不遵循符号链接的选项。 NOFOLLOW_LINKS。
        LinkOption枚举在java.nio.file包中声明。支持此选项的方法让我们传递LinkOption类型的参数。
        我们可以使用Files类的createLink（Path newLink，Path existingPath）方法来创建硬链接。
        */

    }
    //Java文件内容
    /**
     Files.probeContentType(Path path)方法探测文件的内容类型。
     该方法以多用途Internet邮件扩展（MIME）内容类型的值的字符串形式返回内容类型。
     如果一个文件的内容类型不能确定，则返回null。
     * */
    @Test
    public void test22() throws IOException {
        Path p = Paths.get("C:\\Java_Dev\\test1.txt");

        try {
            String contentType = Files.probeContentType(p);
            System.out.format("Content type   of  %s  is %s%n", p, contentType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //读取文件的内容
    //以下代码在默认目录中为test2.txt文件获取一个SeekableByteChannel对象。
    //它打开文件以进行读取和写入访问。它使用CREATE选项，因此如果文件不存在，则创建该文件。
    @Test
    public void test23() throws IOException {
        Path src = Paths.get("test2.txt");
        SeekableByteChannel sbc = Files.newByteChannel(src, READ, WRITE, CREATE);
    }
    //以下代码演示了如何读取和显示我们默认目录中test1.txt文件的内容。 如果文件不存在，程序将显示一条错误消息。
    @Test
    public void test24() throws IOException {
        Charset cs = Charset.forName("US-ASCII");
        Path source = Paths.get("test1.txt");

        List<String> lines = Files.readAllLines(source, cs);
        for (String line : lines) {
            System.out.println(line);
        }
    }
    //写入文件
    //我们可以使用Files类的以下write()方法将内容写入文件。
    //write()方法打开文件，将传递的内容写入文件，并关闭它。
    //如果没有打开选项，它将使用CREATE，TRUNCATE_EXISTING和WRITE选项打开文件。
    //如果我们正在向文件写入文本，它会写一个平台相关的行分隔符。
    //如果在写入文本行时未指定charset，则假定使用UTF-8字符集。
    //以下代码演示如何使用write()方法将文本行写入文件。
    @Test
    public void test25() throws IOException {
        List<String> texts = new ArrayList<>();
        texts.add("test");
        texts.add("test");
        Path dest = Paths.get("twinkle.txt");
        Charset cs = Charset.forName("US-ASCII");
        try {
            Path p = Files.write(dest, texts, cs, WRITE, CREATE);
            System.out.println("Text was written to " + p.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //随机访问文件
    //SeekableByteChannel对象提供对文件的随机访问。
    //我们可以使用Files类的newByteChannel()方法为Path获取一个SeekableByteChannel对象，如下所示：

    @Test
    public void test26() throws IOException {
        Path  src = Paths.get("test.txt");
        SeekableByteChannel seekableChannel  = Files.newByteChannel(src, READ,  WRITE,  CREATE,  TRUNCATE_EXISTING);
    }
    //我们可以使用size()方法以字节为单位获取SeekableByteChannel实体的大小。
    //由于数据被截断或写入通道，因此更新了大小。
    @Test
    public void test27() throws IOException {
        Path src = Paths.get("test.txt");
        String encoding = System.getProperty("file.encoding");
        Charset cs = Charset.forName(encoding);
        try (SeekableByteChannel seekableChannel = Files.newByteChannel(src, READ,
                WRITE, CREATE, TRUNCATE_EXISTING)) {
            printDetails(seekableChannel, "Before writing data");
            writeData(seekableChannel, cs);
            printDetails(seekableChannel, "After writing data");

            seekableChannel.position(0);
            printDetails(seekableChannel, "After resetting position to 0");
            readData(seekableChannel, cs);
            printDetails(seekableChannel, "After reading data");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void writeData(SeekableByteChannel seekableChannel, Charset cs)
            throws IOException {
        String separator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append("test");
        sb.append(separator);
        sb.append("test2");
        sb.append(separator);

        CharBuffer charBuffer = CharBuffer.wrap(sb);
        ByteBuffer byteBuffer = cs.encode(charBuffer);
        seekableChannel.write(byteBuffer);
    }

    public static void readData(SeekableByteChannel seekableChannel, Charset cs)
            throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        String encoding = System.getProperty("file.encoding");
        while (seekableChannel.read(byteBuffer) > 0) {
            byteBuffer.rewind();
            CharBuffer charBuffer = cs.decode(byteBuffer);
            System.out.print(charBuffer);
            byteBuffer.flip();
        }
    }

    public static void printDetails(SeekableByteChannel seekableChannel,
                                    String msg) {
        try {
            System.out.println(msg + ": Size   = " + seekableChannel.size()
                    + ", Position = " + seekableChannel.position());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   // 以下代码显示如何打印目录的子目录和文件的名称。
   @Test
   public void test28() throws IOException {
       Path startDir = Paths.get("");
       FileVisitor<Path> visitor = getFileVisitor();
       try {
           Files.walkFileTree(startDir, visitor);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
    public static FileVisitor<Path> getFileVisitor() {
        class DirVisitor<Path> extends SimpleFileVisitor<Path> {
            @Override
            public FileVisitResult preVisitDirectory(Path dir,
                                                     BasicFileAttributes attrs) {

                System.out.format("%s [Directory]%n", dir);
                return CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                System.out.format("%s [File,  Size: %s  bytes]%n", file, attrs.size());
                return CONTINUE;
            }
        }
        FileVisitor<Path> visitor = new DirVisitor<>();
        return visitor;
    }
    //以下代码显示如何使用FileVisitor API删除目录树
    @Test
    public void test29() throws IOException {
        Path dirToDelete = Paths.get("DIR");
        FileVisitor<Path> visitor = getFileVisitor2();

        try {
            Files.walkFileTree(dirToDelete, visitor);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static FileVisitor<Path> getFileVisitor2() {

        class DeleteDirVisitor extends SimpleFileVisitor<Path> {
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException e)
                    throws IOException {
                FileVisitResult result = CONTINUE;
                if (e != null) {
                    System.out.format("Error deleting  %s.  %s%n", dir, e.getMessage());
                    result = TERMINATE;
                } else {
                    Files.delete(dir);
                    System.out.format("Deleted directory  %s%n", dir);
                }
                return result;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {
                Files.delete(file);
                System.out.format("Deleted file %s%n", file);
                return CONTINUE;
            }
        }
        FileVisitor<Path> visitor = new DeleteDirVisitor();
        return visitor;
    }
    //以下代码显示如何使用walkFileTree()方法跟随符号链接。
    @Test
    public void test30() throws IOException {
        Path startDir = Paths.get("");
        FileVisitor<Path> visitor = getFileVisitor2();;

        Set<FileVisitOption> options = EnumSet.of(FOLLOW_LINKS);

        int depth = Integer.MAX_VALUE;

        Files.walkFileTree(startDir, options, depth, visitor);
    }
    //模式匹配
    //我们可以使用glob和正则表达式模式对字符串形式的Path对象执行模式匹配。
    //功能接口PathMatcher用于执行匹配。它包含一个方法matches(Path path)方法，如果指定的路径匹配模式，则该方法返回true。
    //模式字符串由两部分组成，语法和模式由冒号分隔:
    @Test
    public void test31() throws IOException {
        String globPattern = "glob:**txt";
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(globPattern);
        Path path = Paths.get("C:\\Java_Dev\\test1.txt");
        boolean matched = matcher.matches(path);
        System.out.format("%s matches  %s:  %b%n", globPattern, path, matched);
    }
    //Java文件所有者权限
    /**
     有三种方法可以管理文件所有者:

     使用Files.getOwner()和Files.setOwner()方法。
     使用“owner”作为属性名称的Files.getAttribute()和Files.setAttribute()方法。
     使用FileOwnerAttributeView。
     我们需要使用UserPrincipal和GroupPrincipal接口来管理文件的所有者。
     文件的所有者可以是用户或组。
     UserPrincipal表示用户。GroupPrincipal表示组。
     当我们读取文件的所有者时，我们得到一个UserPrincipal的实例。在UserPrincipal对象上调用getName()方法以获取用户的名称。
     要设置文件的所有者，请从用户名获取UserPrincipal的对象。
     要从文件系统获取UserPrincipal，请使用UserPrincipalLookupService类的实例，我们可以使用FileSystem类的getUserPrincipalLookupService()方法获取该实例。
     * */
    //以下代码为用户ID为myName的用户获取一个UserPrincipal对象:
    @Test
    public void test33() throws IOException {
        FileSystem fs  = FileSystems.getDefault();
        UserPrincipalLookupService upls  = fs.getUserPrincipalLookupService();

        UserPrincipal user = upls.lookupPrincipalByName("myName");
        System.out.format("User principal name is %s%n", user.getName());
    }

    //以下代码显示如何使用FileOwnerAttributeView更改文件的所有者。
    @Test
    public void test34() throws IOException {
        Path path = Paths.get("C:\\Java_Dev\\test1.txt");

        FileOwnerAttributeView foav = Files.getFileAttributeView(path,
                FileOwnerAttributeView.class);

        UserPrincipal owner = foav.getOwner();
        System.out.format("Original owner  of  %s  is %s%n", path,
                owner.getName());

        FileSystem fs = FileSystems.getDefault();
        UserPrincipalLookupService upls = fs.getUserPrincipalLookupService();

        UserPrincipal newOwner = upls.lookupPrincipalByName("brice");
        foav.setOwner(newOwner);

        UserPrincipal changedOwner = foav.getOwner();
        System.out.format("New owner  of  %s  is %s%n", path,
                changedOwner.getName());
    }
    //以下代码使用Files.setOwner()方法更新在Windows上使用路径C:\Java_Dev\test1.txt标识的文件的所有者:
    @Test
    public void test35() throws IOException {
        Path  path   = Paths.get("C:\\Java_Dev\\test1.txt");
        FileOwnerAttributeView foav = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
        UserPrincipal owner  = foav.getOwner();
        Files.setOwner(path,  owner);
    }
    //ACL文件权限
    //以下代码获取名为C:\Java_Dev\test1.txt的文件的ACL条目列表:
    @Test
    public void test36() throws IOException {
        Path  path   = Paths.get("C:\\Java_Dev\\test1.txt");
        AclFileAttributeView view  = Files.getFileAttributeView(path,  AclFileAttributeView.class);
        List<AclEntry> aclEntries = view.getAcl();
    }
    //以下代码显示如何读取文件C:\Java_Dev\test1.txt的ACL条目。
    @Test
    public void test37() throws IOException {
        Path path = Paths.get("C:\\Java_Dev\\test1.txt");
        AclFileAttributeView aclView = Files.getFileAttributeView(path,
                AclFileAttributeView.class);
        if (aclView == null) {
            System.out.format("ACL view  is not  supported.%n");
            return;
        }
        List<AclEntry> aclEntries = aclView.getAcl();
        for (AclEntry entry : aclEntries) {
            System.out.format("Principal: %s%n", entry.principal());
            System.out.format("Type: %s%n", entry.type());
            System.out.format("Permissions are:%n");

            Set<AclEntryPermission> permissions = entry.permissions();
            for (AclEntryPermission p : permissions) {
                System.out.format("%s %n", p);
            }
        }
    }
    //以下代码显示如何为名为brice的用户添加新的ACL条目。它在C:\Java_Dev\test1.txt文件中为用户添加DATA_READ和DATA_ WRITE权限。
    @Test
    public void test38() throws IOException {
        Path path = Paths.get("C:\\Java_Dev\\test1.txt");

        AclFileAttributeView aclView = Files.getFileAttributeView(path,
                AclFileAttributeView.class);
        if (aclView == null) {
            System.out.format("ACL view  is not  supported.%n");
            return;
        }
        UserPrincipal bRiceUser = FileSystems.getDefault()
                .getUserPrincipalLookupService().lookupPrincipalByName("brice");

        Set<AclEntryPermission> permissions = EnumSet.of(READ_DATA, WRITE_DATA);

        AclEntry.Builder builder = AclEntry.newBuilder();
        builder.setPrincipal(bRiceUser);
        builder.setType(AclEntryType.ALLOW);
        builder.setPermissions(permissions);
        AclEntry newEntry = builder.build();

        List<AclEntry> aclEntries = aclView.getAcl();

        aclEntries.add(newEntry);

        aclView.setAcl(aclEntries);
    }
    //POSIX文件权限
    /**
     UNIX支持POSIX标准文件属性。POSIX文件权限由九个组件组成:

     three for the owner
     three for the group
     three for others
     这三种类型的权限是读，写和执行。
     * */
    //以下代码以默认目录中名为test的文件的rwxrwxrwx格式读取和打印POSIX文件权限:
    @Test
    public void test39() throws IOException {
        Path path = Paths.get("test");
        PosixFileAttributeView posixView = Files.getFileAttributeView(path,
                PosixFileAttributeView.class);
        PosixFileAttributes attribs = posixView.readAttributes();
        Set<PosixFilePermission> permissions = attribs.permissions();
        // Convert the file permissions into the rwxrwxrwx string form
        String rwxFormPermissions = PosixFilePermissions.toString(permissions);
        // Print the permissions
        System.out.println(rwxFormPermissions);
    }
    //要更新POSIX文件权限，请调用PosixFileAttributeView的setPermissions()方法，将PosixFilePermission枚举常量的Set作为参数传递。
    //以下代码显示如何设置POSIX文件权限:
    @Test
    public void test40() throws IOException {
        String rwxFormPermissions = "rw-r-----";
        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(rwxFormPermissions);
        Path path = Paths.get("test");
        PosixFileAttributeView posixView = Files.getFileAttributeView(path,
                PosixFileAttributeView.class);

        posixView.setPermissions(permissions);
    }
    //以下代码演示如何在类似UNIX的平台上读取和更新名为test的文件的POSIX文件权限。
    @Test
    public void test41() throws Exception {
        Path path = Paths.get("test");
        PosixFileAttributeView posixView = Files.getFileAttributeView(path,
                PosixFileAttributeView.class);
        if (posixView == null) {
            System.out.format("POSIX attribute view  is not  supported%n.");
            return;
        }
        readPermissions(posixView);
        updatePermissions(posixView);

    }
    public static void readPermissions(PosixFileAttributeView posixView)  throws Exception {
        PosixFileAttributes attribs;
        attribs = posixView.readAttributes();
        Set<PosixFilePermission> permissions = attribs.permissions();
        // Convert the set of posix file permissions into rwxrwxrwx form
        String rwxFormPermissions = PosixFilePermissions.toString(permissions);
        System.out.println(rwxFormPermissions);
    }
    public static void updatePermissions(PosixFileAttributeView posixView)  throws Exception {
        Set<PosixFilePermission> permissions = EnumSet.of(OWNER_READ, OWNER_WRITE,
                GROUP_READ);
        posixView.setPermissions(permissions);
        System.out.println("Permissions set successfully.");
    }
    //Java目录事件
    /**
     当文件系统中的对象被修改时，我们可以监听watch服务以获取警报。

     java.nio.file包中的以下类和接口提供watch服务。

     Watchable接口
     WatchService接口
     WatchKey接口
     WatchEvent接口
     WatchEvent.Kind接口
     StandardWatchEventKinds类
     * */
    //创建观察服务以观察目录以进行更改。
    @Test
    public void test42() throws Exception {
        WatchService ws = FileSystems.getDefault().newWatchService();
    }
    //要使用Watch服务注册目录，使用register()方法，该方法将返回一个WatchKey对象作为注册令牌。
    @Test
    public void test43() throws Exception {
        WatchService ws = FileSystems.getDefault().newWatchService();
        // Get  a  Path  object for C:\myName  directory  to watch
        Path  dirToWatch  = Paths.get("C:\\myName");
        WatchKey token   = dirToWatch.register(ws, ENTRY_CREATE,  ENTRY_MODIFY,  ENTRY_DELETE);
        /**
         要取消注册，请使用WatchKey的cancel()方法。

         当注册目录时，其WatchKey处于就绪状态。

         我们可以通过手表服务注册多个目录。

         要从监视服务队列中检索WatchKey，使用WatchService对象的take()或poll()方法来检索和删除发出信号并排队的WatchKey。

         take()方法等待，直到WatchKey可用。poll()方法允许我们为等待指定超时。
         * */
    }
    //以下代码使用无限循环来检索发出信号的WatchKey。
    @Test
    public void test44() throws Exception {
        WatchService ws = FileSystems.getDefault().newWatchService();

        while(true)  {
            WatchKey key  = ws.take();
        }
    }
    //处理事件
    //WatchKey的pollEvents()方法检索并删除所有挂起的事件。它返回一个WatchEvent的列表。 List的每个元素代表WatchKey上的一个事件。
    //以下代码显示了处理事件的典型逻辑:
    @Test
    public void test45() throws Exception {
        WatchService ws = FileSystems.getDefault().newWatchService();
        while(true)  {
            WatchKey key  = ws.take();
            // Process all  events of  the   WatchKey
            for(WatchEvent<?> event  : key.pollEvents())  {
                // Process each  event here
            }
        }
    }
    //处理事件后重置WatchKey
    //我们需要重置WatchKey对象，通过调用其reset()方法来再次接收事件通知。
    //reset()方法将WatchKey置于就绪状态。如果WatchKey仍然有效，reset()方法返回true。 否则，它返回false。
    //如果WatchKey被取消或其监视服务关闭，它可能会失效
    @Test
    public void test46() throws Exception {
        WatchService ws = FileSystems.getDefault().newWatchService();
        WatchKey key  = ws.take();
        Path  dirToWatch  = Paths.get("C:\\myName");
        // Reset   the   WatchKey
        boolean isKeyValid = key.reset();
        if (!isKeyValid)  {
            System.out.println("No  longer  watching "  + dirToWatch);
        }
        /*WatchService是可自动关闭的。我们可以在try-with-resources中创建一个WatchService的对象块，当程序退出块时它将自动关闭。*/
    }
    //以下代码显示了如何实现监视服务以监视目录中的更改。
    @Test
    public void test47() throws Exception {
        try (WatchService ws = FileSystems.getDefault().newWatchService()) {
            Path dirToWatch = Paths.get("C:\\myName");
            dirToWatch.register(ws, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
            while (true) {
                WatchKey key = ws.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> eventKind = event.kind();
                    if (eventKind == OVERFLOW) {
                        System.out.println("Event  overflow occurred");
                        continue;
                    }
                    WatchEvent<Path> currEvent = (WatchEvent<Path>) event;
                    Path dirEntry = currEvent.context();
                    System.out.println(eventKind + "  occurred on  " + dirEntry);
                }
                boolean isKeyValid = key.reset();
                if (!isKeyValid) {
                    System.out.println("No  longer  watching " + dirToWatch);
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

   // Java异步I/O
    /**
     在同步文件I/O中，对I/O操作的请求将等待，直到I/O操作完成。
     在异步文件I/O中，I/O操作的请求由系统异步执行。
     当系统完成文件I/O时，它通知应用程序其请求的完成。
     java.nio.channels.AsynchronousFileChannel类表示异步文件通道。
     AsynchronousFileChannel类的静态open()方法获取AsynchronousFileChannel类的实例。
     * */
    //以下代码显示了如何获取WRITE的异步文件通道。
   @Test
   public void test48() throws Exception {
       Path  path   = Paths.get("C:\\Java_Dev\\rainbow.txt");
       AsynchronousFileChannel afc   = AsynchronousFileChannel.open(path, WRITE,  CREATE);

   }
   /**
    AsynchronousFileChannel提供了两种方法来处理异步文件I/O操作的结果。

    Using a java.util.concurrent.Future object.
    Using a java.nio.channels.CompletionHandler object.
    支持异步文件I/O操作的AsynchronousFileChannel类的每个方法有两个版本。

    一个版本返回一个Future对象，我们可以使用它来处理所请求的异步操作的结果。

    Future对象的get()方法返回写入文件通道的字节数。
    * */
   //以下代码使用返回Future对象的write()方法的版本:
   @Test
   public void test49() throws Exception {
       Path  path   = Paths.get("C:\\Java_Dev\\rainbow.txt");
       ByteBuffer dataBuffer  = ByteBuffer.allocate(8);
       long  startPosition = 0;
       AsynchronousFileChannel afc   = AsynchronousFileChannel.open(path, WRITE,  CREATE);
       Future<Integer> result = afc.write(dataBuffer, startPosition);

       //一旦我们得到一个Future对象，我们可以使用轮询方法或阻塞等待方法来处理异步文件I/O的结果。
       //下面的代码显示了轮询方法，它将继续调用Future对象的isDone()方法，以检查I/O操作是否完成:
       while (!result.isDone()) {
       }
       int writtenNumberOfBytes = result.get();
   }

   /**
    AsynchronousFileChannel类的另一个版本的方法获得一个CompletionHandler对象，
    当请求的异步I/O操作完成或失败时，该对象的方法被调用。
    CompletionHandler接口有两个方法:completed()和failed()。
    当所请求的I/O操作成功完成时，将调用completed()方法。
    当请求的I/O操作时失败，则调用failed()方法。
    * */
   //以下代码使用Attachment类的对象作为完成处理程序的附件:
    @Test
   public void test50() throws Exception {
        class  Attachment {
            public Path  path;
            public  ByteBuffer buffer;
            public  AsynchronousFileChannel asyncChannel;
        }
        class MyHandler implements CompletionHandler<Integer,  Attachment>   {
            @Override
            public void  completed(Integer result, Attachment attach)  {
                // Handle  completion of  the   I/O  operation
            }

            @Override
            public void  failed(Throwable e,  Attachment attach)  {
                // Handle  failure of  the   I/O  operation
            }
        }

    }
    //以下代码使用MyHandler实例作为异步写操作的完成处理程序。
    @Test
    public void test51() throws Exception {
       /* MyHandler handler = new MyHandler();
        ByteBuffer dataBuffer  = get   a  data buffer;
        Attachment attach  = new Attachment();
        attach.asyncChannel = afc;
        attach.buffer = dataBuffer;
        attach.path = path;

// Perform  the   asynchronous write operation
        afc.write(dataBuffer, 0, attach, handler);*/
    }
    //以下代码演示了如何使用CompletionHandler对象来处理对文件的异步写入的结果。
    @Test
    public void test52() throws Exception {
        Path path = Paths.get("test.txt");
        AsynchronousFileChannel afc = AsynchronousFileChannel.open(path, WRITE,
                CREATE);
        WriteHandler handler = new WriteHandler();
        ByteBuffer dataBuffer = getDataBuffer();
        Attachment attach = new Attachment();
        attach.asyncChannel = afc;
        attach.buffer = dataBuffer;
        attach.path = path;

        afc.write(dataBuffer, 0, attach, handler);

        System.out.println("Sleeping for 5  seconds...");
        Thread.sleep(5000);
    }

    public static ByteBuffer getDataBuffer() {
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        sb.append("test");
        sb.append(lineSeparator);
        sb.append("test");
        sb.append(lineSeparator);
        String str = sb.toString();
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.wrap(str.getBytes(cs));
        return bb;
    }

    //以下代码演示了如何使用Future对象来处理对文件的异步写入的结果。

    @Test
    public void test53() throws Exception {
        Path path = Paths.get("test.txt");

        try (AsynchronousFileChannel afc = AsynchronousFileChannel.open(path,
                WRITE, CREATE)) {
            ByteBuffer dataBuffer = getDataBuffer();
            Future<Integer> result = afc.write(dataBuffer, 0);
            while (!result.isDone()) {
                System.out.println("Sleeping for 2  seconds...");
                Thread.sleep(2000);
            }
            int writtenBytes = result.get();
            System.out.format("%s bytes written  to  %s%n", writtenBytes,
                    path.toAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //以下代码演示了如何使用CompletionHandler对象来处理从文件进行异步读取的结果。
    @Test
    public void test54() throws Exception {
        Path path = Paths.get("test.txt");
        AsynchronousFileChannel afc = AsynchronousFileChannel.open(path, READ);
        ReadHandler handler = new ReadHandler();
        int fileSize = (int) afc.size();
        ByteBuffer dataBuffer = ByteBuffer.allocate(fileSize);

        Attachment attach = new Attachment();
        attach.asyncChannel = afc;
        attach.buffer = dataBuffer;
        attach.path = path;

        afc.read(dataBuffer, 0, attach, handler);

        System.out.println("Sleeping for 5  seconds...");
        Thread.sleep(5000);
    }
    //以下代码显示了如何使用Future对象来处理从文件进行异步读取的结果。它使用等待方法(Future.get()方法调用)等待异步文件I/O完成。
    @Test
    public void test55() throws Exception {
        Path path = Paths.get("test.txt");

        try (AsynchronousFileChannel afc = AsynchronousFileChannel.open(path, READ)) {
            int fileSize = (int) afc.size();
            ByteBuffer dataBuffer = ByteBuffer.allocate(fileSize);

            Future<Integer> result = afc.read(dataBuffer, 0);
            int readBytes = result.get();

            System.out.format("%s bytes read   from  %s%n", readBytes, path);
            System.out.format("Read data is:%n");

            byte[] byteData = dataBuffer.array();
            Charset cs = Charset.forName("UTF-8");
            String data = new String(byteData, cs);

            System.out.println(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class Attachment {
    public Path path;
    public ByteBuffer buffer;
    public AsynchronousFileChannel asyncChannel;
}

class ReadHandler implements CompletionHandler<Integer, Attachment> {
    @Override
    public void completed(Integer result, Attachment attach) {
        System.out.format("%s bytes read   from  %s%n", result, attach.path);
        System.out.format("Read data is:%n");
        byte[] byteData = attach.buffer.array();
        Charset cs = Charset.forName("UTF-8");
        String data = new String(byteData, cs);
        System.out.println(data);
        try {
            // Close the channel
            attach.asyncChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable e, Attachment attach) {
        System.out.format("Read operation  on  %s  file failed."
                + "The  error is: %s%n", attach.path, e.getMessage());
        try {
            // Close the channel
            attach.asyncChannel.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

class WriteHandler implements CompletionHandler<Integer, Attachment> {
    @Override
    public void completed(Integer result, Attachment attach) {
        System.out.format("%s bytes written  to  %s%n", result,
                attach.path.toAbsolutePath());
        try {
            attach.asyncChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable e, Attachment attach) {
        try {
            attach.asyncChannel.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    //以下代码显示了如何使用Future对象来处理从文件进行异步读取的结果。它使用等待方法(Future.get()方法调用)等待异步文件I/O完成。

}