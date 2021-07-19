package network;

import org.junit.Test;

import java.io.*;
import java.net.*;

/**
 * @Author: Rita
 */
public class TCPTest {
    @Test
    public void initialize() throws Exception {
       /**ServerSocket 类的一个对象表示Java中的TCP服务器套接字。
        ServerSocket 对象可以接受来自远程客户端的连接请求。
        使用no-args构造函数创建一个未绑定的服务器套接字，并使用其bind()方法将其绑定到本地端口和本地IP地址。*/

        //初始化1
        // Create an unbound server socket
        ServerSocket serverSocket = new ServerSocket();

        // Create a socket address object
        InetSocketAddress endPoint = new InetSocketAddress("localhost", 8000);

        // Set the wait queue size to 100
        int waitQueueSize = 100;

        // Bind the server socket to localhost and at port 12900 with
        // a wait queue size of 100
        serverSocket.bind(endPoint, waitQueueSize);

        //初始化2
        new ServerSocket(8000);
        //初始化3
        new ServerSocket(8000, 50);
        //初始化4
        new ServerSocket(8000, 100, InetAddress.getByName("localhost"));


        //要接受远程连接请求，请调用 accept()方法。 accept()方法调用阻塞执行，直到来自远程客户端的请求到达其等待队列。
        Socket activeSocket = serverSocket.accept();

        //Socket类包含两个方法 getInputStream()和 getOutputStream()用于读取和写入连接的套接字。
        BufferedReader br  = new BufferedReader(new InputStreamReader(activeSocket.getInputStream()));
        BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(activeSocket.getOutputStream()));
        String s = br.readLine();
        bw.write("hello");
        bw.flush();

        //最后，使用套接字的close()方法关闭连接。关闭套接字还会关闭其输入和输出流。
        activeSocket.close();

    }
    @Test
    public void initialize2() throws Exception {
        // Create Socket for 192.168.1.2 at  port 8000
        //Socket   socket = new Socket("192.168.1.2", 8000);

        Socket socket = new Socket();
        socket.bind(new InetSocketAddress("localhost",  8000));
        socket.connect(new InetSocketAddress("localhost",  8000));

        //在连接Socket对象之后，我们可以分别使用getInputStream()和getOutputStream()方法使用其输入和输出流。
    }

    //以下实例演示了如何检测端口是否已经使用

    @Test
    public void checkPort() throws Exception {
        Socket Skt;
        String host = "localhost";
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("查看 "+ i);
                Skt = new Socket(host, i);
                System.out.println("端口 " + i + " 已被使用");
            }
            catch (UnknownHostException e) {
                System.out.println("Exception occured"+ e);
                break;
            }
            catch (IOException e) {
            }
        }
        /**
         * 查看 17
         * 查看 18
         * 查看 19
         * 查看 20
         * 查看 21
         * 端口 21 已被使用
         * 查看 22
         * 查看 23
         * 查看 24
         * */
    }

    //以下实例演示了如何使用 net.Socket 类的 getInetAddress() 方法来连接到指定主机：
    @Test
    public void conactPhone() throws Exception {
        try {
            InetAddress addr;
            Socket sock = new Socket("www.****.cn", 80);
            addr = sock.getInetAddress();
            System.out.println("连接到 " + addr);
            sock.close();
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
    }



}
