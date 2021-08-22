package juc.sync;

import java.util.concurrent.TimeUnit;

class Phone {

    public  synchronized void sendSMS() throws Exception {
        //停留4秒
        TimeUnit.SECONDS.sleep(4);
        System.out.println("------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }
}

/**
 * @Description: 8锁
 *
1 标准访问，先打印短信还是邮件(同一个对象，同时调用这两个方法，thread1 后有Thread.sleep(100),当前的main线程会被暂停;
所以thread2先不会执行，所以它不会跟thread1抢cpu资源，所以先执行thread1)
thread1------sendSMS
thread2------sendEmail

2 停4秒在短信方法内，先打印短信还是邮件（因为是同一个对象，只有一把锁，，thread2都要等）
------sendSMS无论thread1花多长时间
------sendEmail

3 新增普通的hello方法，是先打短信还是hello（现在跟锁没关系，但因为thread1里面等待了4秒，所以先输出getHello ）
------getHello
------sendSMS

4 现在有两部手机，先打印短信还是邮件(这样是可以并发的，但因为thread1里面等待了4秒，所以thread2先输出，没有等待的话，)
------sendEmail
------sendSMS

5 两个静态同步方法，1部手机，先打印短信还是邮件（锁是当前类，1部手机同时调用的话，只有一个线程可以拿到，thread1 后有Thread.sleep(100),
当前的main线程会被暂停;所以thread2先不会执行，所以它不会跟thread1抢cpu资源，所以先执行thread1)
------sendSMS
------sendEmail

6 两个静态同步方法，2部手机，先打印短信还是邮件（锁是一样的，不管用几个对象）
------sendSMS
------sendEmail

7 1个静态同步方法,1个普通同步方法，1部手机，先打印短信还是邮件
------sendEmail
------sendSMS

8 1个静态同步方法,1个普通同步方法，2部手机，先打印短信还是邮件
------sendEmail
------sendSMS

 */

public class Synchronized_Lock_8 {
    public static void main(String[] args) throws Exception {

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "1").start();

        System.out.println(Thread.currentThread().getName());
        Thread.sleep(100);

        new Thread(() -> {
            try {
               // phone.sendEmail();
               // phone.getHello();
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "2").start();
    }
}
