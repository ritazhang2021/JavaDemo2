package network;

import org.junit.Test;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.util.Enumeration;

/**
 * @Author: Rita
 *
 * java.nio.channels.DatagramChannel类表示数据报通道。默认情况下，它是阻塞。要使其无阻塞，请使用configureBlocking(false)方法。
 *
 * 要创建 DatagramChannel ，请调用其 open()静态方法之一。
 *
 * 要将其用于IP多播，请将多播组的地址类型指定为其 open()方法的参数。
 *
 * open()方法创建一个没有连接的DatagramChannel对象。
 */
public class DatagramSocketChannelServer {
    //列出机器上的可用网络接口
    @Test
    public void test1() throws Exception {
        Enumeration<NetworkInterface> e = NetworkInterface
                .getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface nif = e.nextElement();
            System.out.println("Name: " + nif.getName()
                    + ",  Supports Multicast: " + nif.supportsMulticast()
                    + ", isUp(): " + nif.isUp());
        }
    }

    public static final String MULTICAST_IP = "239.1.1.1";
    public static final int MULTICAST_PORT = 8989;
    public static final String MULTICAST_INTERFACE_NAME = "eth1";
    //以下代码基于DatagramChannel的组播客户端程序
    @Test
    public void client2() throws Exception {
        MembershipKey key = null;
        DatagramChannel client = DatagramChannel.open(StandardProtocolFamily.INET);

        NetworkInterface interf = NetworkInterface.getByName(MULTICAST_INTERFACE_NAME);
        client.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        client.bind(new InetSocketAddress(MULTICAST_PORT));
        client.setOption(StandardSocketOptions.IP_MULTICAST_IF, interf);

        InetAddress group = InetAddress.getByName(MULTICAST_IP);
        key = client.join(group, interf);

        System.out.println("Joined the   multicast  group:" + key);
        System.out.println("Waiting for a  message  from  the"
                + "  multicast group....");

        ByteBuffer buffer = ByteBuffer.allocate(1048);
        client.receive(buffer);
        buffer.flip();
        int limits = buffer.limit();
        byte bytes[] = new byte[limits];
        buffer.get(bytes, 0, limits);
        String msg = new String(bytes);

        System.out.format("Multicast Message:%s%n", msg);
        key.drop();

    }
    //以下代码显示如何创建向多播组发送消息的基于DatagramChannel的组播程序。
    @Test
    public void client3() throws IOException {
        DatagramChannel server = DatagramChannel.open();
        server.bind(null);
        NetworkInterface interf = NetworkInterface
                .getByName(MULTICAST_INTERFACE_NAME);
        server.setOption(StandardSocketOptions.IP_MULTICAST_IF, interf);

        String msg = "Hello!";
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        InetSocketAddress group = new InetSocketAddress(MULTICAST_IP,
                MULTICAST_PORT);

        server.send(buffer, group);
        System.out.println("Sent the   multicast  message: " + msg);
    }


    //以下代码显示如何基于数据报通道创建Echo服务器
    @Test
    public void server() throws IOException {
        DatagramChannel server = null;
        server = DatagramChannel.open();
        InetSocketAddress sAddr = new InetSocketAddress("localhost", 8989);
        server.bind(sAddr);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            System.out.println("Waiting for a  message  from"
                    + "  a  remote  host at " + sAddr);
            SocketAddress remoteAddr = server.receive(buffer);
            buffer.flip();
            int limits = buffer.limit();
            byte bytes[] = new byte[limits];
            buffer.get(bytes, 0, limits);
            String msg = new String(bytes);

            System.out.println("Client at " + remoteAddr + "  says: " + msg);
            buffer.rewind();
            server.send(buffer, remoteAddr);
            buffer.clear();
        }
        //server.close();
    }

    //以下代码基于数据报通道创建客户端程序。
    @Test
    public void client() throws IOException {
        DatagramChannel client = null;
        client = DatagramChannel.open();

        client.bind(null);

        String msg = "Hello";
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        InetSocketAddress serverAddress = new InetSocketAddress("localhost",
                8989);

        client.send(buffer, serverAddress);
        buffer.clear();
        client.receive(buffer);
        buffer.flip();
        int limits = buffer.limit();
        byte bytes[] = new byte[limits];
        buffer.get(bytes, 0, limits);
        String response = new String(bytes);
        System.out.println("Server  responded: " + response);
        client.close();
    }
}
