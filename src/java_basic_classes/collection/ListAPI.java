package java_basic_classes.collection;

import org.junit.Test;
import project.entity.Person;

import java.util.*;

/**
 * @Author: Rita
 */
public class ListAPI {
    @Test
    public  void testListIterator(){
        /**
         1.Iterator可用来遍历Set和List集合,但是ListIterator只能用来遍历List。
         2.Iterator对集合只能是前向遍历,ListIterator既可以前向也可以后向。
         3.ListIterator实现了Iterator接口,并包含其他的功能,比如:增加元素,替换元素,获取前一个和后一个元素的索引,等等。
         * */
        List<String> list = new ArrayList<>();
        ListIterator<String> listIterator = list.listIterator();
        String firstElement = listIterator.next();
        listIterator.remove();
        listIterator.add("e");
        listIterator.previous();
        System.out.println("firstElement:" + firstElement);
        listIterator = list.listIterator();
        while(listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        System.out.println("--------------");
        listIterator = list.listIterator();
        System.out.println("dddd"+listIterator.next());
        listIterator.set("eeee");
        System.out.println("---------------");
        listIterator = list.listIterator();
        while(listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

    }
    /*
   区分List中remove(int index)和remove(Object obj)
    */
    @Test
    public void testListRemove() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);//
    }

    private void updateList(List list) {
//        list.remove(2);
        list.remove(new Integer(2));
    }

    /*
void add(int index, Object ele):在index位置插入ele元素
boolean addAll(int index, Collection eles):从index位置开始将eles中的所有元素添加进来
Object get(int index):获取指定index位置的元素
int indexOf(Object obj):返回obj在集合中首次出现的位置
int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置
Object remove(int index):移除指定index位置的元素，并返回此元素
Object set(int index, Object ele):设置指定index位置的元素为ele
List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex位置的子集合

总结：常用方法
增：add(Object obj)
删：remove(int index) / remove(Object obj)
改：set(int index, Object ele)
查：get(int index)
插：add(int index, Object ele)
长度：size()
遍历：① Iterator迭代器方式
    ② 增强for循环
    ③ 普通的循环

    */
    @Test
    public void testTraverse(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");

        //方式一：Iterator迭代器方式
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("***************");

        //方式二：增强for循环
        for(Object obj : list){
            System.out.println(obj);
        }

        System.out.println("***************");

        //方式三：普通for循环
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i));
        }
    }


    @Test
    public void testBuildinMethod(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom",12));
        list.add(456);
        //int indexOf(Object obj):返回obj在集合中首次出现的位置。如果不存在，返回-1.
        int index = list.indexOf(4567);
        System.out.println(index);

        //int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置。如果不存在，返回-1.
        System.out.println(list.lastIndexOf(456));

        //Object remove(int index):移除指定index位置的元素，并返回此元素
        Object obj = list.remove(0);
        System.out.println(obj);
        System.out.println(list);

        //Object set(int index, Object ele):设置指定index位置的元素为ele
        list.set(1,"CC");
        System.out.println(list);

        //List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex位置的左闭右开区间的子集合
        List subList = list.subList(2, 4);
        System.out.println(subList);
        System.out.println(list);


    }


    @Test
    public void testAdd(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom",12));
        list.add(456);

        System.out.println(list);

        //void add(int index, Object ele):在index位置插入ele元素
        list.add(1,"BB");
        System.out.println(list);

        //boolean addAll(int index, Collection eles):从index位置开始将eles中的所有元素添加进来
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
//        list.add(list1);
        System.out.println(list.size());//9

        //Object get(int index):获取指定index位置的元素
        System.out.println(list.get(0));

    }

    //以下实例演示了如何使用 Collections 类的 indexOfSubList() 和 lastIndexOfSubList() 方法来查看子列表是否在列表中，
    // 并查看子列表在列表中所在的位置：
    //String转List
    @Test
    public void test1(){
        List list = Arrays.asList("one Two three Four five six one three Four".split(" "));
        System.out.println("List :"+list);
        List sublist = Arrays.asList("three Four".split(" "));
        System.out.println("子列表 :"+sublist);
        System.out.println("indexOfSubList: "  + Collections.indexOfSubList(list, sublist));
        System.out.println("lastIndexOfSubList: "  + Collections.lastIndexOfSubList(list, sublist));
    }

    //以下实例演示了如何使用 Collections 类的 replaceAll() 来替换List中所有的指定元素：
    public void test2(){
        List list = Arrays.asList("one Two three Four five six one three Four".split(" "));
        System.out.println("List :"+list);
        Collections.replaceAll(list, "one", "hundread");
        System.out.println("replaceAll: " + list);
    }


    //以下实例演示了如何使用 Collections 类的 max() 和 min() 方法来获取List中最大最小值：
    @Test
    public void test6(){
        List list = Arrays.asList("one Two three Four five six one three Four".split(" "));
        System.out.println(list);
        System.out.println("最大值: " + Collections.max(list));
        System.out.println("最小值: " + Collections.min(list));
    }

    //以下实例演示了如何使用 Collections 类的 rotate() 来循环移动元素，方法第二个参数指定了移动的起始位置：
    @Test
    public void test7(){
        List list = Arrays.asList("one Two three Four five six".split(" "));
        System.out.println("List :"+list);
        Collections.rotate(list, 3);
        System.out.println("rotate: " + list);
    }

    //以下实例演示了如何使用 Java Util 类的 list.add() 和 list.toArray() 方法将集合转为数组：
    @Test
    public void test8(){
        List<String> list = new ArrayList<String>();
        list.add("一");
        list.add("二");
        list.add("三");
        list.add("四");
        list.add("five");
        String[] s1 = list.toArray(new String[0]);
        for(int i = 0; i < s1.length; ++i){
            String contents = s1[i];
            System.out.print(contents);
        }
    }

    //以下实例演示了如何使用 Java Util 类的 tMap.keySet(),tMap.values() 和 tMap.firstKey() 方法将集合元素输出：
    @Test
    public void test9(){
        System.out.println("TreeMap 实例！\n");
        TreeMap tMap = new TreeMap();
        tMap.put(1, "Sunday");
        tMap.put(2, "Monday");
        tMap.put(3, "Tuesday");
        tMap.put(4, "Wednesday");
        tMap.put(5, "Thursday");
        tMap.put(6, "Friday");
        tMap.put(7, "Saturday");
        System.out.println("TreeMap 键："
                + tMap.keySet());
        System.out.println("TreeMap 值："
                + tMap.values());
        System.out.println("键为 5 的值为: " + tMap.get(5)+ "\n");
        System.out.println("第一个键: " + tMap.firstKey()
                + " Value: "
                + tMap.get(tMap.firstKey()) + "\n");
        System.out.println("最后一个键: " + tMap.lastKey()
                + " Value: "+ tMap.get(tMap.lastKey()) + "\n");
        System.out.println("移除第一个数据: "
                + tMap.remove(tMap.firstKey()));
        System.out.println("现在 TreeMap 键为: "
                + tMap.keySet());
        System.out.println("现在 TreeMap 包含: "
                + tMap.values() + "\n");
        System.out.println("移除最后一个数据: "
                + tMap.remove(tMap.lastKey()));
        System.out.println("现在 TreeMap 键为: "
                + tMap.keySet());
        System.out.println("现在 TreeMap 包含: "
                + tMap.values());
    }

    //以下实例演示了如何使用 Collection 类的 Collections.unmodifiableList() 方法来设置集合为只读：
    @Test
    public void test10() throws Exception{
        List stuff = Arrays.asList(new String[] { "a", "b" });
        List list = new ArrayList(stuff);
        list = Collections.unmodifiableList(list);
        try {
            list.set(0, "new value");
        }
        catch (UnsupportedOperationException e) {
        }
        Set set = new HashSet(stuff);
        set = Collections.unmodifiableSet(set);
        Map map = new HashMap();
        map = Collections.unmodifiableMap(map);
        System.out.println("集合现在是只读");
    }


}
