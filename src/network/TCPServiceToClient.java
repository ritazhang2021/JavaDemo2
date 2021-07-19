package network;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author: Rita
 * 实现TCP的网络编程
 * 例子1：客户端发送信息给服务端，服务端将数据显示在控制台上
 * ServerSocket 类的一个对象表示Java中的TCP服务器套接字。 *
 * ServerSocket 对象可以接受来自远程客户端的连接请求。
 *
 * 1、Server端必须设置超时时间，否则程序结束时Server直接关闭，会导致Client端断开连接而报错。 s.setSoTimeout(10000);
 *
 * 2、String mess = br.readLine(); Socket未关闭时，readLine会一直等待，直到下一个数据传过来；
 * Socket关闭时，readLine会直接返回null，不会抛错
 */
public class TCPServiceToClient {

    //客户端
    @Test
    public void client()  {
        Socket socket = null;
        OutputStream os = null;
        try {
            //1.创建Socket对象，指明服务器端的ip和端口号
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet,8899);
            //2.获取一个输出流，用于输出数据
            os = socket.getOutputStream();
            //3.写出数据的操作
            os.write("你好，我是客户端mm".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //服务端
    @Test
    public void server()  {

        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建服务器端的ServerSocket，指明自己的端口号
            ss = new ServerSocket(8899);
            System.out.println("启动服务器....");

            //2.调用accept()表示接收来自于客户端的socket
            socket = ss.accept();
            System.out.println("客户端:"+ss.getInetAddress().getHostAddress()+"已连接到服务器");

            //3.获取输入流
            is = socket.getInputStream();

            //不建议这样写，可能会有乱码
       /** byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            String str = new String(buffer,0,len);
            System.out.print(str);
        }*/
            //4.读取输入流中的数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while((len = is.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }

            System.out.println(baos.toString());

            System.out.println("收到了来自于：" + socket.getInetAddress().getHostAddress() + "的数据");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(baos != null){
                //5.关闭资源
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    @Test
    public void server2(){
        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");
            Socket s = ss.accept();
            //System.out.println("客户端:"+s.getInetAddress().getLocalHost()+"已连接到服务器");
            System.out.println("客户端:"+s.getInetAddress().getHostAddress()+"已连接到服务器");

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //读取客户端发送来的消息
            String mess = br.readLine();
            System.out.println("客户端："+mess);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bw.write(mess+"\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void client2()  {
        /**
         *创建Socket通信，设置通信服务器的IP和Port
         * 建立IO输出流向服务器发送数据消息
         * 建立IO输入流读取服务器发送来的数据消息
         * */
        try {
            Socket s = new Socket("127.0.0.1",8888);

            //构建IO
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            //向服务器端发送一条消息
            bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
            bw.flush();

            //读取服务器返回的消息
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String mess = br.readLine();
            System.out.println("服务器："+mess);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void server3() throws IOException {
        ServerSocket serverSocket = new ServerSocket(12900, 100, InetAddress.getByName("localhost"));
        System.out.println("Server started  at:  " + serverSocket);

        while (true) {
            System.out.println("Waiting for a  connection...");

            final Socket activeSocket = serverSocket.accept();

            System.out.println("Received a  connection from  " + activeSocket);
            Runnable runnable = () -> handleClientRequest(activeSocket);
            new Thread(runnable).start(); // start a new thread
        }
    }
    public static void handleClientRequest(Socket socket) {
        try{
            BufferedReader socketReader = null;
            BufferedWriter socketWriter = null;
            socketReader = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            socketWriter = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream()));

            String inMsg = null;
            while ((inMsg = socketReader.readLine()) != null) {
                System.out.println("Received from  client: " + inMsg);

                String outMsg = inMsg;
                socketWriter.write(outMsg);
                socketWriter.write("\n");
                socketWriter.flush();
            }
            socket.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void client3() throws IOException {
        Socket socket = new Socket("localhost", 12900);
        System.out.println("Started client  socket at " + socket.getLocalSocketAddress());
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        String promptMsg = "Please enter a  message  (Bye  to quit):";
        String outMsg ;

        System.out.print(promptMsg);
        while ((outMsg = consoleReader.readLine()) != null) {
            if (outMsg.equalsIgnoreCase("bye")) {
                break;
            }
            // Add a new line to the message to the server,
            // because the server reads one line at a time.
            socketWriter.write(outMsg);
            socketWriter.write("\n");
            socketWriter.flush();

            // Read and display the message from the server
            String inMsg = socketReader.readLine();
            System.out.println("Server: " + inMsg);
            System.out.println(); // Print a blank line
            System.out.print(promptMsg);
        }
        socket.close();
    }


}
