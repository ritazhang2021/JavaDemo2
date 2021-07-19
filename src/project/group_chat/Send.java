package project.group_chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Author: Rita
 * @Date:6/1/2021 3:20 PM
 */
public class Send implements Runnable{
    //控制台输入流
    private BufferedReader console;
    //管道输出流
    private DataOutputStream dos;
    //控制线程的标识符
    private boolean isRunning = true;
    //名称  不考虑重名
    private String name;
    //初始化建立管道
    public Send(Socket socket, String name) {
        try {
            console = new BufferedReader(new InputStreamReader(System.in));
            dos = new DataOutputStream(socket.getOutputStream());
            this.name = name;
            send(name);
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll(dos, console);
        }
    }

    /**
     * 1.从控制台接收数据
     * 2.向服务端发送数据
     */
    //1.从控制台接收数据
    private String getMsgFromConsole() {
        String msg = "";
        try {
            msg = console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll(dos, console);
        }
        return msg;
    }
    //2.向服务端发送数据
    private void send(String msg) {
        try {
            if(msg != null && !msg.equals("")) {
                dos.writeUTF(msg);
                dos.flush();//强制刷新
            }
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
            CloseUtil.closeAll(dos, console);
        }
    }
    @Override
    public void run() {
        //线程体
        while(isRunning) {
            send(getMsgFromConsole());
        }
    }
}
