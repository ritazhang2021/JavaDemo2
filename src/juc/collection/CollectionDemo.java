package juc.collection;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: Rita
 * @Date:8/15/2021 10:39 AM
 */
public class CollectionDemo {
    /*单元测试时如果开多个线程，主线程运行结束就结束了，并不会等待子线程结束。如果用main方法就没问题，
    * 解决办法，使用CountDownLatch, main函数， join
     *
    * */
    @Test
    public void listTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        //创建ArrayList集合
        List<String> list = new ArrayList<>();
        for (int i = 0; i <30; i++) {
            new Thread(()->{
                //向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }

        latch.await();

    }

    @Test
    public void hashMapTest(){
        Map<String,String> map = new ConcurrentHashMap<>();

        for (int i = 0; i <30; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                //多线程，一边存一边取，会发生并发修改异常
                //向集合添加内容
                map.put(key, UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

    @Test
    public void vectorTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        //创建ArrayList集合
        List<String> list = new Vector<>();
        for (int i = 0; i <30; i++) {
            new Thread(()->{
                //向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }

        latch.await();

    }
    @Test
    public void collectionTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        //创建ArrayList集合
        //Collections解决
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i <30; i++) {
            new Thread(()->{
                //向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }

        latch.await();

    }
    @Test
    public void copyOnWriteArrayListTest() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        //创建ArrayList集合
        // CopyOnWriteArrayList解决
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i <30; i++) {
            new Thread(()->{
                //向集合添加内容
                list.add(UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(list);
            },String.valueOf(i)).start();
        }

        latch.await();

    }
    public static void main(String[] args) {

        //演示Hashset
//        Set<String> set = new HashSet<>();

//        Set<String> set = new CopyOnWriteArraySet<>();
//        for (int i = 0; i <30; i++) {
//            new Thread(()->{
//                //向集合添加内容
//                set.add(UUID.randomUUID().toString().substring(0,8));
//                //从集合获取内容
//                System.out.println(set);
//            },String.valueOf(i)).start();
//        }

        //演示HashMap
//        Map<String,String> map = new HashMap<>();

        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i <30; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                //向集合添加内容
                map.put(key, UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
