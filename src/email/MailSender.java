package email;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * @Author: Rita
 * @Date:5/26/2021 7:37 PM
 */
public class MailSender {

    /**
     * 服务邮箱
     */
    private static MailServer mailServer = null;

    //
    private static String userName;


    private static String password;


    private static String stmp;

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        if(MailSender.userName==null) {
            MailSender.userName = userName;
        }
    }
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        if(MailSender.password==null) {
            MailSender.password = password;
        }
    }
    /**
     * @param stmp the stmp to set
     */
    public void setStmp(String stmp) {
        if(MailSender.stmp==null) {
            MailSender.stmp = stmp;
        }
    }
    /**
     * 使用默认的用户名和密码发送邮件
     * @param recipient
     * @param subject
     * @param content
     * @throws MessagingException
     * @throws AddressException
     */
    public static void sendHtml(String recipient, String subject, Object content) throws AddressException, MessagingException{
        if (mailServer == null) {
            mailServer = new MailServer(stmp,userName,password);
        }
        mailServer.send(recipient, subject, content);
    }
    /**
     * 使用指定的用户名和密码发送邮件
     * @param server
     * @param password
     * @param recipient
     * @param subject
     * @param content
     * @throws MessagingException
     * @throws AddressException
     */
    public static void sendHtml(String server,String password,String stmpIp, String recipient, String subject, Object content) throws AddressException, MessagingException {
        new MailServer(stmpIp,server,password).send(recipient, subject, content);
    }
    public static void main(String[] args) {
        try {
            String s = "ceshi2:<br/>您好,我在给你们演示发邮件看见了没.";
            sendHtml("humf@vip.qq.com","password","IP", "humf@vip.qq.com", "我是是是", s);
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
