package temp;

import org.junit.Test;

import java.util.*;

public class Test1 {

    @Test
    public void test1(){
        String s = "tomorrow";
        char[] c = s.toCharArray();
        Arrays.sort(c);
        System.out.println(c);

        sortByValue();
    }

    public static void sortByValue() {
        Map<String,String> map = new TreeMap<String,String>();
        map.put("a", "dddd");
        map.put("d", "aaaa");
        map.put("b", "cccc");
        map.put("c", "bbbb");

        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());

        Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
            //升序排序
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for (Map.Entry<String, String> e: list) {
            System.out.println(e.getKey()+":"+e.getValue());
        }
    }

    @Test
    public void test2(){
        String s = "tomorrow";
        Map<Character, Integer> map = new LinkedHashMap<>();
        for(int i=0; i<s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort(Map.Entry.comparingByValue());
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> result = new LinkedHashMap<>();
        for(Map.Entry<Character, Integer> entry: list) {
            result.put(entry.getKey(), entry.getValue());
        }
        for(Map.Entry<Character, Integer> entry: result.entrySet()) {
            for(int i=entry.getValue()-1; i>=0; i--) {
                sb.append(entry.getKey());
            }
        }

        //return sb.reverse().toString();
    }
}
