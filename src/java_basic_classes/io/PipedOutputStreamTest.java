package java_basic_classes.io;

import org.junit.Test;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @Author: Rita
 * 管道连接输入流和输出流。 *
 * 管道 I/O基于生产者 - 消费者模式，其中生产者产生数据并且消费者消费数据。 *
 * 在管道 I/O中，我们创建两个流代表管道的两端。 PipedOutputStream对象表示一端，PipedInputStream对象表示另一端。
 * 我们使用两个对象上的connect()方法连接两端。 *
 * 我们还可以通过在创建另一个对象时将一个对象传递给构造函数来连接它们。
 */
public class PipedOutputStreamTest {
    @Test
    public void test1() throws IOException {
        //以下代码显示了创建和连接管道两端的两种方法：

        //第一种方法创建管道输入和输出流并连接它们。它使用connect方法连接两个流。
        PipedInputStream pis  = new PipedInputStream();
        PipedOutputStream pos  = new PipedOutputStream();
        pis.connect(pos); /* Connect  the   two  ends  */


        //第二种方法创建管道输入和输出流并连接它们。它通过将输入管道流传递到输出流构造器来连接两个流。

        PipedInputStream pis2  = new PipedInputStream();
        PipedOutputStream pos2  = new PipedOutputStream(pis2);


    }

    //以下代码演示了如何使用管道 I/O。
    @Test
    public void test2() throws IOException {
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = new PipedOutputStream();
        pos.connect(pis);

        Runnable producer = () -> produceData(pos);
        Runnable consumer = () -> consumeData(pis);
        new Thread(producer).start();
        new Thread(consumer).start();
    }

    public static void produceData(PipedOutputStream pos) {
        try {
            for (int i = 1; i <= 50; i++) {
                pos.write((byte) i);
                pos.flush();
                System.out.println("Writing: " + i);
                Thread.sleep(500);
            }
            pos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void consumeData(PipedInputStream pis) {
        try {
            int num = -1;
            while ((num = pis.read()) != -1) {
                System.out.println("Reading: " + num);
            }
            pis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
