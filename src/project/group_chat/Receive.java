package project.group_chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @Author: Rita
 */
public class Receive implements Runnable {
    //输入流
    private DataInputStream dis;
    //线程标识符
    private boolean isRunning = true;
    //初始化建立管道
    public Receive(Socket socket) {
        try {
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll(dis);
        }
    }
    /**
     * 接收数据
     */
    private String receive() {
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll(dis);
        }
        return msg;
    }

    @Override
    public void run() {
        //线程体
        while(isRunning) {
            System.out.println(receive());
        }
    }
}
