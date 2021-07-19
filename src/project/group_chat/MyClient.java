package project.group_chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Author: Rita
 * * 创建客户端：发送数据+接收数据
 *  * 发送数据：输出流
 *  * 接收数据：输入流
 *  * 输出流和输入流应该彼此独立，使用线程处理。
 *  * 加入名称。1
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        //通过名称判断不同的客户 端，存放在Send类中。
        System.out.println("请输入名称：");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        if(name.equals("")) {
            return;
        }

        Socket client = new Socket("localhost", 8888);
        //发送  控制台输入并发送到服务端
        new Thread(new Send(client, name)).start();//一条路径
        //接收  接收服务端数据并打印
        new Thread(new Receive(client)).start();//一条路径
    }
}
