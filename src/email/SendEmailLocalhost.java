package email;


import org.junit.Test;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SendEmailLocalhost {

    public static void main(String [] args) throws IOException {
        sendMail("aaa@book.com","xxwdwd");
    }
    @Test
    public void test2() throws Exception {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "发送服务器地址");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、连上邮件服务器
        ts.connect("发送服务器地址", "本地邮箱", "邮箱密码");
        //4、创建邮件
        Message message = createAttachMail(session);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    public static MimeMessage createAttachMail(Session session) throws Exception{
        MimeMessage message = new MimeMessage(session);

        //设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("发件人邮箱 "));
        //收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("收件人邮箱"));
        //邮件标题
        message.setSubject("标题");

        //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("邮件文字内容", "text/html;charset=UTF-8");

        //创建邮件附件
        MimeBodyPart attach = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("D:/附件地址/附件名称.xlsx"));
        attach.setDataHandler(dh);
        attach.setFileName(dh.getName());  //

        //创建容器描述数据关系
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(text);
        mp.addBodyPart(attach);
        mp.setSubType("mixed");

        message.setContent(mp);
        message.saveChanges();
        //将创建的Email写入到E盘存储
        message.writeTo(new FileOutputStream("d:\\attachMail.eml"));
        //返回生成的邮件
        return message;
    }


    public void test1(){

        // 收件人电子邮箱
        String to = "abcd@gmail.com";

        // 发件人电子邮箱
        String from = "web@gmail.com";

        // 指定发送邮件的主机为 localhost
        //String host = "localhost";
        String host = "127.0.0.1";


        // Setup mail server

      /*  Socket socket = new Socket();
        socket.bind(new InetSocketAddress(host,  8000));
        socket.connect(new InetSocketAddress(host,  8000));*/

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);


        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties);

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("This is the Subject Line!");

            // 设置消息体
            message.setText("This is actual message");

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }


    }

    //如果你想发送一封e-mail给多个收件人，那么使用下面的方法来指定多个收件人ID：
    void addRecipients(Message.RecipientType type, Address[] addresses) throws MessagingException{
        //下面是对于参数的描述：
        //
        //type:要被设置为TO, CC 或者BCC. 这里CC 代表抄送、BCC 代表秘密抄送y. 举例：Message.RecipientType.TO
        //
        //addresses: 这是email ID的数组。在指定电子邮件ID时，你将需要使用InternetAddress()方法。

    }

    /**
     Have you checked the log files?
     Do you get any exceptions or errors when running the program?
     Do you have a SMTP server running in localhost?
     Is the SMTP server accepting connections from localhost?
     Can you send emails via that server using normal email client and receive them somehow?
     Try to make your program a standalone commandline program and try to execute i
     * */

    @Test
    public static void sendMail(String recipient,String code) {
        /*
         * 1.获取Session对象
         * 2.创建一个代表邮件的对象Message
         * 3.发送邮件Transport
         */

        //1.获取连接对象
        Properties props = new Properties();
        props.setProperty("mail.host", "localhost");
        Session session = Session.getInstance(props, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("BookCityService@book.com","123");
            }
        });

        //2.创建邮件对象
        Message message = new MimeMessage(session);

        try {
            //设置发件人
            message.setFrom(new InternetAddress("BookCityService@book.com"));
            //设置收件人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            //设置邮件标题
            message.setSubject("来自网上书城的激活邮件");
            //设置邮件内容,命令行ipconfig查看本地ip地址
            String url = "http://192.168.121.100:8080/bookCity/user_active.action?code="+code;
            message.setContent("<h1>您好，请点击下面的链接完成激活！</h1><h3><a href='" + url + "'>" + url + "</a></h3>","text/html;charset=UTF-8");
            //3.发送邮件
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }




}
