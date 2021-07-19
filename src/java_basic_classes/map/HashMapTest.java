package java_basic_classes.map;

import java.util.*;

/**
 * @Author: Rita
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name1", "josan1");
        hashMap.put("name2", "josan2");
        hashMap.put("name3", "josan3");

        Set<Map.Entry<String, String>> set = hashMap.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println("key:" + key + ",value:" + value);
            /**
             * key:name3,value:josan3
             * key:name2,value:josan2
             * key:name1,value:josan1             *
             * */
        }

    }

}
