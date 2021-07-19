package java_basic_classes.collection;

import org.junit.Test;

import java.util.*;

/**
 * @Author: Rita
 */
public class ListTest {




    //删除集合中指定元素
    @Test
    public void test11() {
        System.out.println( "集合实例!\n" );
        int size;
        HashSet collection = new HashSet ();
        String str1 = "Yellow", str2 = "White", str3 =
                "Green", str4 = "Blue";
        Iterator iterator;
        collection.add(str1);
        collection.add(str2);
        collection.add(str3);
        collection.add(str4);
        System.out.print("集合数据: ");
        iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        collection.remove(str2);
        System.out.println("删除之后 [" + str2 + "]\n");
        System.out.print("现在集合的数据是: ");
        iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        size = collection.size();
        System.out.println("集合大小: " + size + "\n");
    }

    //以下实例演示了如何使用 Collection 和 Listiterator 类的 listIterator() 和 collection.reverse() 方法来反转集合中的元素：
    /**
     1.Iterator可用来遍历Set和List集合,但是ListIterator只能用来遍历List。
     2.Iterator对集合只能是前向遍历,ListIterator既可以前向也可以后向。
     3.ListIterator实现了Iterator接口,并包含其他的功能,比如:增加元素,替换元素,获取前一个和后一个元素的索引,等等。
     * */
    @Test
    public void test12() {
        String[] coins = { "A", "B", "C", "D", "E" };
        List l = new ArrayList();
        for (int i = 0; i < coins.length; i++) {
            l.add(coins[i]);
        }
        ListIterator liter = l.listIterator();
        System.out.println("反转前");
        while (liter.hasNext()) {
            System.out.println(liter.next());
        }
        Collections.reverse(l);
        liter = l.listIterator();
        System.out.println("反转后");
        while (liter.hasNext()) {
            System.out.println(liter.next());
        }

    }
    //以下实例演示了使用 Java Util 类的 Arrays.asList(name) 方法将数组转换为集合：
    @Test
    public void test13() {
        int n = 5;         // 5 个元素
        String[] name = new String[n];
        for(int i = 0; i < n; i++){
            name[i] = String.valueOf(i);
        }
        List<String> list = Arrays.asList(name);
        System.out.println();
        for(String li: list){
            String str = li;
            System.out.print(str + " ");
        }
    }

    //以下实例将字符串转换为集合并使用 Collection 类的 Collection.min() 和 Collection.max() 来比较集合中的元素：
    @Test
    public void test14() {
        String[] coins = { "Penny", "nickel", "dime",
                "Quarter", "dollar" };
        Set set = new TreeSet();
        for (int i = 0; i < coins.length; i++) {
            set.add(coins[i]);
        }
        System.out.println(Collections.min(set));
        System.out.println(Collections.min(set, String.CASE_INSENSITIVE_ORDER));
        for(int i=0;i<=10;i++) {
            System.out.print("-");
        }
        System.out.println("");
        System.out.println(Collections.max(set));
        System.out.println(Collections.max(set, String.CASE_INSENSITIVE_ORDER));
    }

    //以下实例演示了如何使用 Collections 类 Collections.shuffle() 方法来打乱集合元素的顺序：
    @Test
    public void test15() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(new Integer(i));
        }
        System.out.println("打乱前:");
        System.out.println(list);

        for (int i = 1; i < 6; i++) {
            System.out.println("第" + i + "次打乱：");
            Collections.shuffle(list);
            System.out.println(list);
        }

    }




}
