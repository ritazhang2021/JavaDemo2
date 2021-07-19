package java_basic_classes.util;

import org.junit.Test;

import java.io.*;
import java.util.Enumeration;
import java.util.stream.Stream;
import java.util.zip.*;

/**
 * @Author: Rita
 */
public class ZipTest {
    //以下代码说明如何使用Adler32和CRC32类来计算校验和。
    @Test
    public void test1() throws UnsupportedEncodingException {
        String str = "HELLO";
        byte[] data = str.getBytes("UTF-8");
        System.out.println("Adler32 and  CRC32  checksums  for " + str);

        // Compute Adler32 checksum
        Adler32 ad = new Adler32();
        ad.update(data);
        long adler32Checksum = ad.getValue();
        System.out.println("Adler32: " + adler32Checksum);

        // Compute CRC32 checksum
        CRC32 crc = new CRC32();
        crc.update(data);
        long crc32Checksum = crc.getValue();
        System.out.println("CRC32: " + crc32Checksum);
    }

    //压缩字节数组
    // 以下代码显示如何使用Deflater和Inflater类压缩和解压缩字节数组。
    @Test
    public void test2() throws IOException, DataFormatException {
        /**
         我们可以使用java.util.zip包中的Deflater和Inflater类来分别压缩和解压缩字节数组中的数据。
         我们可以使用Deflater类中的一个常量来指定压缩级别。
         这些常数是BEST_COMPRESSION，BEST_ SPEED，DEFAULT_COMPRESSION和NO_COMPRESSION。
         最佳速度意味着较低的压缩比，最好的压缩意味着较慢的压缩速度。
         * */
        String input = "Hello world!";
        byte[] uncompressedData = input.getBytes("UTF-8");

        byte[] compressedData = compress(uncompressedData,
                Deflater.BEST_COMPRESSION, false);

        byte[] decompressedData = decompress(compressedData, false);

        String output = new String(decompressedData, "UTF-8");

        System.out.println("Uncompressed data length: " + uncompressedData.length);
        System.out.println("Compressed data length:  " + compressedData.length);
        System.out.println("Decompressed data length:  " + decompressedData.length);
    }
    public static byte[] compress(byte[] input, int compressionLevel, boolean GZIPFormat) throws IOException {

        //默认情况下，压缩数据使用Deflater对象将以ZLIB格式。
        //要以GZIP或PKZIP格式压缩数据，请通过在构造函数中使用布尔标志为true来指定。

        Deflater compressor = new Deflater(compressionLevel, GZIPFormat);
        compressor.setInput(input);
        compressor.finish();
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] readBuffer = new byte[1024];
        int readCount = 0;

        while (!compressor.finished()) {
            readCount = compressor.deflate(readBuffer);
            if (readCount > 0) {
                bao.write(readBuffer, 0, readCount);
            }
        }

        compressor.end();
        return bao.toByteArray();
    }

    public static byte[] decompress(byte[] input, boolean GZIPFormat)
            throws IOException, DataFormatException {
        Inflater decompressor = new Inflater(GZIPFormat);
        decompressor.setInput(input);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] readBuffer = new byte[1024];
        int readCount = 0;

        while (!decompressor.finished()) {
            readCount = decompressor.inflate(readBuffer);
            if (readCount > 0) {
                bao.write(readBuffer, 0, readCount);
            }
        }
        decompressor.end();
        return bao.toByteArray();
    }

    @Test
    public void test3() throws UnsupportedEncodingException {
        /**
         Java对ZIP文件格式有直接支持。通常，我们将使用java.util.zip包中的以下四个类来处理ZIP文件格式：

         ZipEntry
         ZipInputStream
         ZipOutputStream
         ZipFile
         ZipEntry对象表示ZIP文件格式的归档文件中的条目。

         zip条目可以是压缩的或未压缩的。

         ZipEntry类具有设置和获取有关ZIP文件中的条目的信息的方法。

         ZipInputStream可以从每个条目的ZIP文件读取数据。

         ZipOutputStream可以将数据写入每个条目的ZIP文件。

         ZipFile是一个从ZIP文件读取条目的实用程序类。
         * */

        //以下代码显示如何创建ZIP文件

        String zipFileName = "ziptest.zip";
        String[] entries = new String[2];
        entries[0] = "test1.txt";
        entries[1] = "notes" + File.separator + "test2.txt";
        zip(zipFileName, entries);
    }
    public static void zip(String zipFileName, String[] zipEntries) {

        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(zipFileName)))) {

            // Set the compression level to best compression
            zos.setLevel(Deflater.BEST_COMPRESSION);

            for (int i = 0; i < zipEntries.length; i++) {
                File entryFile = new File(zipEntries[i]);
                if (!entryFile.exists()) {
                    System.out.println("The entry file  " + entryFile.getAbsolutePath()
                            + "  does  not  exist");
                    System.out.println("Aborted   processing.");
                    return;
                }
                ZipEntry ze = new ZipEntry(zipEntries[i]);
                zos.putNextEntry(ze);
                addEntryContent(zos, zipEntries[i]);
                zos.closeEntry();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addEntryContent(ZipOutputStream zos, String entryFileName)
            throws IOException, FileNotFoundException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                entryFileName));

        byte[] buffer = new byte[1024];
        int count = -1;
        while ((count = bis.read(buffer)) != -1) {
            zos.write(buffer, 0, count);
        }
        bis.close();
    }

    //以下代码显示如何读取ZIP文件的内容。
    @Test
    public void test4() throws UnsupportedEncodingException {
        String zipFileName = "ziptest.zip";
        String unzipdirectory = "extracted";
        unzip(zipFileName, unzipdirectory);
    }
    public static void unzip(String zipFileName, String unzipdir) {
        try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(
                new FileInputStream(zipFileName)))) {

            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {
                // Extract teh entry"s contents
                extractEntryContent(zis, entry, unzipdir);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void extractEntryContent(ZipInputStream zis, ZipEntry entry,
                                           String unzipdir) throws IOException, FileNotFoundException {

        String entryFileName = entry.getName();
        String entryPath = unzipdir + File.separator + entryFileName;

        createFile(entryPath);

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(
                entryPath));

        byte[] buffer = new byte[1024];
        int count = -1;
        while ((count = zis.read(buffer)) != -1) {
            bos.write(buffer, 0, count);
        }

        bos.close();
    }

    public static void createFile(String filePath) throws IOException {
        File file = new File(filePath);
        File parent = file.getParentFile();

        if (!parent.exists()) {
            parent.mkdirs();
        }
        file.createNewFile();
    }

    //下面的代码显示了如何使用ZipFile类。
    //当你只想在ZIP文件中列出条目时，ZipFile类派上用场
    @Test
    public void test5() throws IOException {
        ZipFile zf = new ZipFile("ziptest.zip");

        // Get the enumeration for all zip entries and loop through them
        Enumeration<? extends ZipEntry> e = zf.entries();
        ZipEntry entry = null;

        while (e.hasMoreElements()) {
            entry = e.nextElement();

            // Get the input stream for the current zip entry
            InputStream is = zf.getInputStream(entry);

            /* Read data for the entry using the is object */

            // Print the name of the entry
            System.out.println(entry.getName());
        }
    }
    //以下代码使用Stream类和lambda表达式重写上述代码。
    @Test
    public void test6() throws IOException {
        ZipFile zf = new ZipFile("ziptest.zip");

        Stream<? extends ZipEntry> entryStream = zf.stream();
        entryStream.forEach(entry -> {
            try {
                // Get the input stream for the current zip entry
                InputStream is = zf.getInputStream(entry);
                System.out.println(entry.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        //GZIPInputStream和GZIPOutputStream类用于与GZIP文件格式配合使用。
    }



}
