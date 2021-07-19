package network;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: Rita
要创建选择器对象，请调用其open()静态方法。
Selector selector = Selector.open();
ServerSocketChannel用于监听来自客户端的新连接请求。

调用其open()静态方法来创建一个ServerSocketChannel。
ServerSocketChannel ssChannel = ServerSocketChannel.open();
默认情况下，服务器套接字通道或套接字通道是阻塞通道。要使其成为非阻塞通道，请调用以下方法。
ssChannel.configureBlocking(false);
选择器
服务器套接字必须向选择器注册才能执行某些操作。
有四种操作，我们可以用选择器注册一个通道。
使用SelectionKey.OP_CONNECT连接操作，可以在客户端为SocketChannel注册。选择器将通知有关连接操作进度。
使用SelectionKey.OP_ACCEPT接受操作，可以在服务器上为ServerSocketChannel注册。当客户端请求新连接到达时，选择器将通知。
使用SelectionKey.OP_READ读取操作，可以在客户端和服务器上为SocketChannel注册。选择器将在通道准备好读取某些数据时通知。
使用SelectionKey.OP_WRITE进行写操作，可以在客户端和服务器上为SocketChannel注册。选择器将在通道准备好写入某些数据时通知。
 */
public class NonBlockingServer {
    public static void main(String[] args) throws Exception {
        InetAddress hostIPAddress = InetAddress.getByName("localhost");
        int port = 19000;
        Selector selector = Selector.open();
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        ssChannel.configureBlocking(false);
        ssChannel.socket().bind(new InetSocketAddress(hostIPAddress, port));
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            if (selector.select() <= 0) {
                continue;
            }
            processReadySet(selector.selectedKeys());
        }
        /**
         玩几天我刚编了一个局域网内多线程文件传输的程序，是winform的。也遇到过你的问题。
         如果你的服务器端（文件接收方）Receive数据时，关闭了客户端（文件发送端）就会强制关闭连接，就会出现上面的错误提示。
         建议你在接收完数据后退出死循环不要让Receive函数阻塞住，关闭客户端（文件发送端）前建议发送一个消息告诉服务器端（文件接收方）
         “我要断开连接了”，然后两方都关闭连接close。
         服务端在接收客户端数据的时候，得到了ConnectionAborted的异常
         而服务端在遇到这个异常的时候，会主动关闭和客户端的连接
         之后，服务端再使用这个socket向外发送数据的时候，就会出现“您的主机中的软件中止了一个已建立的连接”
         this socket connection was aborted
         也可能是序列化错误导致的问题，类的某个属性上需要DataMember这个Attribute
         * */
    }
    public static void processReadySet(Set readySet) throws Exception {
        Iterator iterator = readySet.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = (SelectionKey) iterator.next();
            iterator.remove();
            if (key.isAcceptable()) {
                ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                SocketChannel sChannel = (SocketChannel) ssChannel.accept();
                sChannel.configureBlocking(false);
                sChannel.register(key.selector(), SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                String msg = processRead(key);
                if (msg.length() > 0) {
                    SocketChannel sChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                    sChannel.write(buffer);
                }
            }
        }
    }
    public static String processRead(SelectionKey key) throws Exception {
        SocketChannel sChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int bytesCount = sChannel.read(buffer);
        if (bytesCount > 0) {
            buffer.flip();
            return new String(buffer.array());
        }
        return "NoMessage";
    }
}
