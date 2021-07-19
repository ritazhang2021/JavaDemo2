package java_basic_classes.map;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @Author: Rita
 */
public class HashTableTest {
    //使用 Enumeration 遍历 HashTable
    @Test
    public void test4(){
        Hashtable ht = new Hashtable();
        ht.put("1", "One");
        ht.put("2", "Two");
        ht.put("3", "Three");
        Enumeration e = ht.elements();
        while(e.hasMoreElements()){
            System.out.println(e.nextElement());
        }
    }

    //遍历 HashTable 的键值
    @Test
    public void test5(){
        Hashtable ht = new Hashtable();
        ht.put("1", "One");
        ht.put("2", "Two");
        ht.put("3", "Three");
        Enumeration e = ht.keys();
        while (e.hasMoreElements()){
            System.out.println(e.nextElement());
        }
    }
}
