package email;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @Author: Rita

 */
public class SendMail {

    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    public static String myEmailAccount = "xxxxxxx@126.com";
    public static String myEmailPassword = "xxxxxxxx";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易126邮箱的 SMTP 服务器地址为: smtp.126.com
    public static String myEmailSMTPHost = "smtp.126.com";

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = "xxxxxx@qq.com";

    public static void main(String[] args) throws Exception {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     取消下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        /*
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        */

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);

        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        //
        //    PS_01: 如果连接服务器失败, 都会在控制台输出相应失败原因的log。
        //    仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接,
        //    根据给出的错误类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        transport.connect(myEmailAccount, myEmailPassword);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session     和服务器交互的会话
     * @param sendMail    发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "昵称", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("主题", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent("邮件正文", "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

    /**
     * 下面我们对我们的代码解析一下：
     * 1、对应用程序配置邮件会话
     * javax.mail.Session保存邮件系统的配置属性和提供用户验证的信息，发送email首先要获取session对象。
     * (1)Session.getInstance(java.util.Properties)获取非共享的session对象
     * (2)Session.getDefaultInstance(java.utilProperties)获取共享的session对象
     * 两者都必须建立Properties prop=new Properties()对象;
     * 注意：一般对单用户桌面应用程序使用共享Session对象。
     * 用SMTP协议发送Email时通常要设置mail.smtp.host(mail.protocol.host协议特定邮件服务器名)属性。
     *
     * prop.put("mail.smtp.host","smtp.mailServer.com");
     * Session mailSession=Session.getInstance(prop);
     * 注意：在真正使用创建的过程中，往往会让我们验证密码，这是我们要写一个密码验证类。javax.mail.Authenticator是一个抽象类，我们要写MyAuthenticator的密码验证类，该类继承Authenticator实现：
     *
     * protected PasswordAuthentication getPasswordAuthentication(){
     * return new PasswordAuthentication(String userName, String password);
     * }
     * 这时我们创建Session对象：
     * Session mailSession=Session.getInstance(prop,new MyAuthenticator(userName,Password));
     * 并且要设置使用验证：prop.put("mail.smtp.auth","true");
     * 使用 STARTTLS安全连接:prop.put("mail.smtp.starttls.enable","true");
     * 2、配置邮件会话之后，要编写消息
     * 要编写消息就要生成javax.mail.Message子类的实例或对Internet邮件使用javax.mail.interet.MimeMessage类。
     * (1)建立MimeMessage对象
     * MimeMessage扩展抽象的Message类，构造MimeMessage对象：
     * MimeMessage message=new MimeMessage(mailSession);
     * (2)消息发送者、日期、主题
     *
     * message.setFrom(Address theSender);
     * message.setSentDate(java.util.Date theDate);
     * message.setSubject(String theSubject);
     * (3)设置消息的接受者与发送者（寻址接收）
     *
     * setRecipient(Message.RecipientType type , Address theAddress);
     * setRecipients(Message.RecipientType type , Address[] theAddress);
     * addRecipient(Message.RecipientType type , Address theAddress);
     * addRecipients(Message.RecipientType type,Address[] theAddress);
     * 方法都可以指定接受者类型，但是一般用后两个，这样可以避免意外的替换或者覆盖接受者名单。定义接受者类型：
     * Message.RecipientType.TO：消息接受者
     * Message.RecipientType.CC：消息抄送者
     * Message.RecipientType.BCC：匿名抄送接收者(其他接受者看不到这个接受者的姓名和地址)
     * (4)设置消息内容
     * JavaMail基于JavaBean Activation FrameWork(JAF)，JAF可以构造文本消息也可以支持附件。
     * 设置消息内容时，要提供消息的内容类型-----即方法签名:
     * MimeMessage.setContent(Object theContent,String type);
     * 也可以不用显式的制定消息的内容类型：MimeMessage.setText(String theText);

     *
     *
     * */

}
