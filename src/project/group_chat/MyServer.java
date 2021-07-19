package project.group_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Rita
 * 在多人聊天室的基础上增加了私聊功能。 实现方法：在Send类中添加name区别客户端，
 * 用于客户端；在MyChannel中添加name区别客户端连接，用于服务端。
 */
public class MyServer {
    //存放服务端与客户端的连接
    private List<MyChannel> all = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        new MyServer().start();
    }
    public void start() throws IOException {
        ServerSocket server = new ServerSocket(8888);
        while(true) {
            Socket socket = server.accept();
            MyChannel myChannel = new MyChannel(socket);
            all.add(myChannel);
            new Thread(myChannel).start();
        }
    }
    /**
     * 一个客户端一条道路，
     * 一个客户端一个线程。
     * 1.输入流
     * 2.输出流
     * 3.接收数据
     * 4.发送数据
     * 成员内部类，静态方法不能访问。
     */
    class MyChannel implements Runnable{
        //输入流
        private DataInputStream dis;
        //输出流
        private DataOutputStream dos;
        //控制线程的标识符
        private boolean isRunning = true;
        //名称  给每个连接命名，区别客户端  不考虑重名
        private String name;
        //初始化建立管道
        public MyChannel(Socket socket) {
            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());

                name = dis.readUTF();

                send("欢迎您进入聊天室！");//发送给自己客户端
                sendOthers(name + "进入了聊天室！", true); //发给其他的客户端
            } catch (IOException e) {
                e.printStackTrace();
                CloseUtil.closeAll(dis, dos);
                all.remove(this);
                isRunning = false;
            }
        }
        //读取数据
        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                CloseUtil.closeAll(dis, dos);
                all.remove(this);
                isRunning = false;
            }
            return msg;
        }
        //发送给this数据
        private void send(String msg) {
            if(msg == null || msg.equals("")) {
                return;
            }
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
                CloseUtil.closeAll(dis, dos);
                all.remove(this);
                isRunning = false;
            }
        }
        //发送给其他客户端
        private void sendOthers(String msg, boolean isSysMsg) {
            //是否为私聊
            if(msg.startsWith("@") && msg.indexOf(":") != -1) {//向一个特定的人发消息，格式为@xxx:message
                //获取name
                String name = msg.substring(1, msg.indexOf(":"));
                //获取内容
                String content = msg.substring(msg.indexOf(":") + 1);
                for(MyChannel other:all) {
                    if(other.name.equals(name)) {
                        other.send(this.name + "对您悄悄地说：" + content);
                    }
                }
            } else {//向全体发消息
                //遍历容器
                for(MyChannel other : all) {
                    if(other == this) {
                        continue;
                    }
                    if(isSysMsg) {//是系统消息
                        //发送给其他客户端
                        other.send("系统信息：" + msg);
                    } else {//不是系统消息
                        //发送给其他客户端
                        other.send(name + "对所有人说：" + msg);
                    }

                }
            }
        }
        @Override
        public void run() {
            while(isRunning) {
                sendOthers(receive(), false);
            }
        }
    }
}
