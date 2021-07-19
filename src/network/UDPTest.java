package network;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @Author: Rita
TCP套接字是面向连接的，基于流。基于UDP的套接字是无连接的，基于数据报。
使用UDP发送的数据块称为数据报或UDP数据包。每个UDP分组具有数据，目的地IP地址和目的地端口号。
无连接套接字在通信之前不建立连接。
UDP是不可靠的协议，因为它不保证到达分组的传递和顺序。
在无连接协议UDP中，不会有服务器套接字。
在UDP连接中，客户端和服务器发送或接收一组数据，而无需事先知道它们之间的通信。
发送到同一目的地的每个数据块独立于先前发送的数据。
当编码UDP连接时，使用以下两个类。
DatagramPacket类表示UDP数据报。
DatagramSocket类表示用于发送或接收数据报包的UDP套接字。
 */
public class UDPTest {
    //发送端
    @Test
    public void sender() throws IOException {
        /**
         DatagramPacket包含三个东西：
         目的IP地址
         目的端口号
         数据
         DatagramPacket类的构造函数创建一个数据包来接收数据：
         DatagramPacket(byte[] buf,  int  length)
         DatagramPacket(byte[] buf,  int offset, int length)
         发送数据：
         DatagramPacket(byte[] buf,  int  length,  InetAddress address, int port)
         DatagramPacket(byte[] buf,  int offset, int length,  InetAddress address, int port)
         DatagramPacket(byte[] buf,  int length, SocketAddress address)
         DatagramPacket(byte[] buf,  int offset, int length, SocketAddress address)
         * */
        //创建数据包
        DatagramSocket socket = new DatagramSocket();

        String str = "我是UDP方式发送的导弹";

        //DatagramPacket类的构造函数创建一个数据包来发送数据如下：
        byte[] data = str.getBytes();

        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,9090);

        socket.send(packet);
        socket.close();




    }
    //接收端(相当 于服务器端)
    @Test
    public void receiver() throws IOException {

        DatagramSocket socket = new DatagramSocket(9090);

        //以下代码创建一个包的缓冲区大小为100，并从偏移量0开始接收数据，它将只接收32字节的数据。
        byte[] buffer = new byte[100];
        //数据包中的数据总是指定偏移量和长度。我们需要使用offset和length来读取数据包中的数据。
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);

        socket.receive(packet);

        System.out.println(new String(packet.getData(),0,packet.getLength()));

        socket.close();
    }



    @Test
    public void server() throws IOException {
        int mcPort = 12345;
        String mcIPStr = "230.1.1.1";
        MulticastSocket mcSocket = null;
        InetAddress mcIPAddress = null;
        mcIPAddress = InetAddress.getByName(mcIPStr);
        mcSocket = new MulticastSocket(mcPort);
        System.out.println("Multicast Receiver running at:"
                + mcSocket.getLocalSocketAddress());
        mcSocket.joinGroup(mcIPAddress);

        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

        System.out.println("Waiting for a  multicast message...");
        mcSocket.receive(packet);
        String msg = new String(packet.getData(), packet.getOffset(),
                packet.getLength());
        System.out.println("[Multicast  Receiver] Received:" + msg);

        mcSocket.leaveGroup(mcIPAddress);
        mcSocket.close();
    }

    @Test
    public void client() throws IOException {
        int mcPort = 12345;
        String mcIPStr = "230.1.1.1";
        DatagramSocket udpSocket = new DatagramSocket();

        InetAddress mcIPAddress = InetAddress.getByName(mcIPStr);
        byte[] msg = "Hello".getBytes();
        DatagramPacket packet = new DatagramPacket(msg, msg.length);
        packet.setAddress(mcIPAddress);
        packet.setPort(mcPort);
        udpSocket.send(packet);

        System.out.println("Sent a  multicast message.");
        System.out.println("Exiting application");
        udpSocket.close();
    }

}
