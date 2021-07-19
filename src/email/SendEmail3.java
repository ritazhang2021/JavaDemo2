package email;

import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * @Author: Rita
 * @Date:5/25/2021 11:12 PM
 */
public class SendEmail3 {
    public static void main(String[] args) throws MessagingException {
        //第一步：构造 SMTP 邮件服务器的基本环境
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.gmail.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);
        //构造邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.addRecipients(Message.RecipientType.TO, "infinity@gmail.com");//设置收信人
        //mimeMessage.addRecipients(Message.RecipientType.CC, "infinityeeeee@gmail.com");//抄送
        mimeMessage.setFrom(new InternetAddress("y@gmail.com"));//邮件发送人
        mimeMessage.setSubject("测试邮件主题");//邮件主题
        mimeMessage.setContent("Hello,这是一封测试邮件", "text/html;charset=utf-8");//正文
        //第三步：发送邮件
        Transport transport = session.getTransport();
        transport.connect("smtp.gmail.com", "y@gmail.com", "");
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());//发送邮件，第二个参数为收件人
        transport.close();


    }

    @Test
    public void test2(){
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        final String username = "yueshao04@gmail.com";//
        final String password = "IYnl2009";
        try{
            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }});

            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress("yueshao04@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("rita2021.zhang@gmail.com",false));
            msg.setSubject("Hello");
            msg.setText("How are you");
            msg.setSentDate(new Date());
            Transport.send(msg);
            System.out.println("Message sent.");
        }catch (MessagingException e){
            System.out.println("Erreur d'envoi, cause: " + e);
        }
    }


}
